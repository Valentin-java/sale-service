package ru.tradesolution.salesservice.rest.controller.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tradesolution.salesservice.rest.dto.ProductManualRequestDto;
import ru.tradesolution.salesservice.rest.dto.ProductRequestDto;
import ru.tradesolution.salesservice.rest.dto.ProductResponseDto;
import ru.tradesolution.salesservice.service.store.ProductService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/product-api")
public class ProductController {

    private final ProductService productService;

    @PostMapping(path = "/add-product", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto request) {
        return ResponseEntity.ok(productService.addProduct(request));
    }

    @PostMapping(path = "/add-product-manual", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ProductResponseDto> addProductByManual(@RequestBody ProductManualRequestDto request) {
        return ResponseEntity.ok(productService.addProductByManual(request));
    }


    // если статус 200
    // возвращаем сущность из БД то есть товар который добавлен на склад магазина,
    // что бы отредактировать стоимость, количество и доступность



}
