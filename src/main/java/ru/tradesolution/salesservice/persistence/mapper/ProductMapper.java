package ru.tradesolution.salesservice.persistence.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.tradesolution.salesservice.persistence.entity.Product;
import ru.tradesolution.salesservice.rest.dto.ProductResponseDto;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target ="id", ignore = true)
    @Mapping(target ="createdAt", ignore = true)
    @Mapping(target ="updatedAt", ignore = true)
    Product toEntity(ProductResponseDto source);

    ProductResponseDto toDomain(Product entity);

    @AfterMapping
    default void afterMapping(@MappingTarget Product target, ProductResponseDto source) {
        target.setCreatedAt(LocalDateTime.now());
        target.setUpdatedAt(LocalDateTime.now());
    }
}
