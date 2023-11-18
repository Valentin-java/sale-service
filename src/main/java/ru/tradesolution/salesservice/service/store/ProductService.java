package ru.tradesolution.salesservice.service.store;

import ru.tradesolution.salesservice.rest.dto.ProductManualRequestDto;
import ru.tradesolution.salesservice.rest.dto.ProductRequestDto;
import ru.tradesolution.salesservice.rest.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto addProduct(ProductRequestDto request);

    ProductResponseDto addProductByManual(ProductManualRequestDto request);

}
