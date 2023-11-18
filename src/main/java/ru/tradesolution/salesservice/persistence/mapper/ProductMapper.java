package ru.tradesolution.salesservice.persistence.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.tradesolution.salesservice.persistence.entity.Product;
import ru.tradesolution.salesservice.rest.dto.ProductDto;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target ="id", ignore = true)
    @Mapping(target ="createdAt", ignore = true)
    @Mapping(target ="updatedAt", ignore = true)
    Product toEntity(ProductDto source);

    ProductDto toDomain(Product entity);

    @AfterMapping
    default void afterMapping(@MappingTarget Product target, ProductDto source) {
        target.setCreatedAt(LocalDateTime.now());
        target.setUpdatedAt(LocalDateTime.now());
    }
}
