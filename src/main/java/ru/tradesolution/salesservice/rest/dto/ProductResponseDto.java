package ru.tradesolution.salesservice.rest.dto;

import lombok.Data;

@Data
public class ProductResponseDto {
    private String barcode;
    private String name;
    private String category;
    private String vendor;
    private String price;
}
