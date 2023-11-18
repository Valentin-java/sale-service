package ru.tradesolution.salesservice.adapter.external;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.tradesolution.salesservice.persistence.entity.Product;
import ru.tradesolution.salesservice.persistence.mapper.ProductMapper;
import ru.tradesolution.salesservice.persistence.repository.ProductRepository;
import ru.tradesolution.salesservice.rest.dto.ProductResponseDto;
import ru.tradesolution.salesservice.rest.exception.ExternalEANServerErrorException;
import ru.tradesolution.salesservice.rest.exception.ProductProcessingException;
import ru.tradesolution.salesservice.rest.feign.EANOnline;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Component
public class ExternalEANAdapter {

    private static final String PRODUCT_NOT_FOUND_ERROR = "Продукт не найден, добавьте продукт в ручном режиме";
    private static final String CONNECTION_EXTERNAL_ERROR = "Нет связи с внешней базой данных для проверки баркода";
    private static final String UNKNOWN_ERROR = "Не известная ошибка в методе getProductByBarcode, товар со штрихкодом: %s";

    private final EANOnline eanOnline;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    public Product getProductByBarcode(String barcode) {
        return Optional.of(barcode)
                .map(eanOnline::findProductByBarcode)
                .map(this::checkExternalConnection)
                .map(this::isFoundData)
                .map(this::getProductFromResponse)
                .map(productRepository::save)
                .orElseThrow(() ->
                        new ProductProcessingException(String.format(UNKNOWN_ERROR, barcode)));
    }

    private ResponseEntity<ProductResponseDto> checkExternalConnection(ResponseEntity<ProductResponseDto> response) {
        if (response.getStatusCode().is5xxServerError()) {
            throw new ExternalEANServerErrorException(CONNECTION_EXTERNAL_ERROR);
        }
        return response;
    }

    private ResponseEntity<ProductResponseDto> isFoundData(ResponseEntity<ProductResponseDto> response) {
        if (response.getStatusCode().is4xxClientError()) {
            throw new ProductProcessingException(PRODUCT_NOT_FOUND_ERROR);
        }
        return response;
    }

    private Product getProductFromResponse(ResponseEntity<ProductResponseDto> response) {
        return Optional.ofNullable(response.getBody())
                .map(productMapper::toEntity)
                .orElseThrow(() -> new ProductProcessingException(PRODUCT_NOT_FOUND_ERROR));
    }
}
