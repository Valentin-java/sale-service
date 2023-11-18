package ru.tradesolution.salesservice.service.store.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.tradesolution.salesservice.adapter.external.ExternalEANAdapter;
import ru.tradesolution.salesservice.persistence.entity.InventoryItem;
import ru.tradesolution.salesservice.persistence.entity.Product;
import ru.tradesolution.salesservice.persistence.mapper.InventoryItemMapper;
import ru.tradesolution.salesservice.persistence.mapper.ProductMapper;
import ru.tradesolution.salesservice.persistence.repository.InventoryItemRepository;
import ru.tradesolution.salesservice.persistence.repository.ProductRepository;
import ru.tradesolution.salesservice.rest.dto.ProductManualRequestDto;
import ru.tradesolution.salesservice.rest.dto.ProductRequestDto;
import ru.tradesolution.salesservice.rest.dto.ProductResponseDto;
import ru.tradesolution.salesservice.rest.exception.ProductProcessingException;
import ru.tradesolution.salesservice.service.store.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    public static final String INPUT_DATA_ERROR = "Данные либо отсутствует, либо некорректные: %s";
    public static final String PRODUCT_ALREADY_EXIST_ERROR = "Товар с таким штрихкодом: %s, уже существует, либо удалите его, либо отредактируйте";

    private final ProductRepository productRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final ExternalEANAdapter externalEANAdapter;

    private final InventoryItemMapper inventoryItemMapper = Mappers.getMapper(InventoryItemMapper.class);
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public ProductResponseDto addProduct(ProductRequestDto request) {
        return Optional.ofNullable(request.getBarcode())
                .flatMap(this::getProductByBarcode)
                .map(inventoryItemMapper::toEntity)
                .map(inventoryItemRepository::save)
                .map(InventoryItem::getProduct)
                .map(productMapper::toDomain)
                .orElseThrow(() ->
                        new ProductProcessingException(String.format(INPUT_DATA_ERROR, request.getBarcode())));
    }

    private Optional<Product> getProductByBarcode(String barcode) {
        var productFromDb = productRepository.findByBarcode(barcode);
        return Optional.ofNullable(productFromDb)
                .or(() -> Optional.ofNullable(externalEANAdapter.getProductByBarcode(barcode)));
    }

    @Override
    public ProductResponseDto addProductByManual(ProductManualRequestDto request) {
        return Optional.of(request)
                .map(this::validateProductManualRequest)
                .map(this::hasAlreadyProduct)
                .map(inventoryItemMapper::toEntity)
                .map(inventoryItemRepository::save)
                .map(InventoryItem::getProduct)
                .map(productMapper::toDomain)
                .orElseThrow(() ->
                        new ProductProcessingException(String.format(INPUT_DATA_ERROR, request.getBarcode())));
    }

    private ProductManualRequestDto hasAlreadyProduct(ProductManualRequestDto request) {
        if (productRepository.findByBarcode(request.getBarcode()) != null) {
            throw new ProductProcessingException(String.format(PRODUCT_ALREADY_EXIST_ERROR, request.getBarcode()));
        }
        return request;
    }

    private ProductManualRequestDto validateProductManualRequest(ProductManualRequestDto request) {
        List<String> errors = new ArrayList<>();
        if (StringUtils.isBlank(request.getBarcode())) {
            errors.add("Отсутствует штрихкод");
        }
        if (StringUtils.isBlank(request.getName())) {
            errors.add("Отсутствует название товара");
        }
        if (StringUtils.isBlank(request.getCategory())) {
            errors.add("Отсутствует категория товара");
        }
        if (StringUtils.isBlank(request.getPrice())) {
            errors.add("Отсутствует стоимость товара");
        }
        if (!errors.isEmpty()) {
            throw new ProductProcessingException(StringUtils.join(errors, System.lineSeparator()));
        }
        return request;
    }
}
