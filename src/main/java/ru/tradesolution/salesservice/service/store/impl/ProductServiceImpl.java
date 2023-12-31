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
import ru.tradesolution.salesservice.rest.dto.ProductDto;
import ru.tradesolution.salesservice.rest.exception.ProductProcessingException;
import ru.tradesolution.salesservice.service.store.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    public static final String INPUT_DATA_ERROR = "Данные некорректные: %s";
    public static final String PRODUCT_ALREADY_EXIST_ERROR = "Товар с таким штрихкодом: %s, уже существует. Отредактируйте существующий.";

    private final ProductRepository productRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final ExternalEANAdapter externalEANAdapter;

    private final InventoryItemMapper inventoryItemMapper = Mappers.getMapper(InventoryItemMapper.class);
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public ProductDto addProduct(ProductDto request) {
        return Optional.ofNullable(request)
                .map(this::validateProductManualRequest)
                .flatMap(this::getProductByBarcode)
                .map(product -> inventoryItemMapper.toEntity(product, request.getStoreId()))
                .map(inventoryItemRepository::save)
                .map(InventoryItem::getProduct)
                .map(productMapper::toDomain)
                .orElseThrow(() ->
                        new ProductProcessingException(String.format(INPUT_DATA_ERROR, getNullSafetyBarcode(request))));
    }


    private Optional<Product> getProductByBarcode(ProductDto request) {
        var productFromDb = productRepository.findByBarcode(request.getBarcode());
        return Optional.ofNullable(productFromDb)
                .or(() -> Optional.ofNullable(externalEANAdapter.getProductByBarcode(getNullSafetyBarcode(request))));
    }

    @Override
    public ProductDto addProductByManual(ProductDto request) {
        return Optional.of(request)
                .map(this::validateProductManualRequest)
                .map(this::hasAlreadyProduct)
                .map(productMapper::toEntity)
                .map(productRepository::save)
                .map(product -> inventoryItemMapper.toEntity(product, request.getStoreId()))
                .map(inventoryItemRepository::save)
                .map(InventoryItem::getProduct)
                .map(productMapper::toDomain)
                .orElseThrow(() ->
                        new ProductProcessingException(String.format(INPUT_DATA_ERROR, getNullSafetyBarcode(request))));
    }

    private ProductDto hasAlreadyProduct(ProductDto request) {
        if (productRepository.findByBarcode(request.getBarcode()) != null) {
            throw new ProductProcessingException(String.format(PRODUCT_ALREADY_EXIST_ERROR, getNullSafetyBarcode(request)));
        }
        return request;
    }

    private ProductDto validateProductManualRequest(ProductDto request) {
        List<String> errors = new ArrayList<>();
        if (StringUtils.isBlank(request.getStoreId())) {
            errors.add("Отсутствует id магазина");
        }
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

    private static String getNullSafetyBarcode(ProductDto request) {
        return request != null && request.getBarcode() != null ? request.getBarcode() : "Request or barcode is missing";
    }
}
