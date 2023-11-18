package ru.tradesolution.salesservice.rest.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.tradesolution.salesservice.rest.dto.ProductResponseDto;

@FeignClient(value = "ean-online", url = "${feign.ean-online.url}", path = " /api/v1/barcode")
public interface EANOnline {

    @GetMapping(value = "/{barcode}")
    ResponseEntity<ProductResponseDto> findProductByBarcode(@PathVariable("barcode") String barcode);
}
