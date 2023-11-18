package ru.tradesolution.salesservice.persistence.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.tradesolution.salesservice.persistence.entity.InventoryItem;
import ru.tradesolution.salesservice.persistence.entity.Product;
import ru.tradesolution.salesservice.rest.dto.ProductManualRequestDto;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface InventoryItemMapper {

    @Mapping(target ="id", ignore = true)
    @Mapping(target ="quantity", ignore = true)
    @Mapping(target ="specialPrice", ignore = true)
    @Mapping(target ="available", constant = "true")
    @Mapping(target ="createdAt", ignore = true)
    @Mapping(target ="updatedAt", ignore = true)
    InventoryItem toEntity(Product source);

    @Mapping(target ="id", ignore = true)
    @Mapping(target ="quantity", ignore = true)
    @Mapping(target ="specialPrice", ignore = true)
    @Mapping(target ="available", constant = "true")
    @Mapping(target ="createdAt", ignore = true)
    @Mapping(target ="updatedAt", ignore = true)
    InventoryItem toEntity(ProductManualRequestDto source);



    @AfterMapping
    default void afterMapping(@MappingTarget InventoryItem target) {
        target.setCreatedAt(LocalDateTime.now());
        target.setUpdatedAt(LocalDateTime.now());
    }
}
