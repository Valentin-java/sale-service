package ru.tradesolution.salesservice.rest.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String storeId;
    private String barcode;
    private String name;
    private String category;
    private String vendor;
    private String price;
}
