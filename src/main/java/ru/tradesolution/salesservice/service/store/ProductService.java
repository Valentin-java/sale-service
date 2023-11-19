package ru.tradesolution.salesservice.service.store;

import ru.tradesolution.salesservice.rest.dto.ProductDto;

public interface ProductService {

    ProductDto addProduct(ProductDto request);

    ProductDto addProductByManual(ProductDto request);

}
