package ru.tradesolution.salesservice.service.store;

import ru.tradesolution.salesservice.rest.dto.ProductRequestDto;
import ru.tradesolution.salesservice.rest.dto.ProductDto;

public interface ProductService {

    ProductDto addProduct(ProductRequestDto request);

    ProductDto addProductByManual(ProductDto request);

}
