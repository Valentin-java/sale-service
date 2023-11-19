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

    @Mapping(target ="storeId", ignore = true)
    ProductDto toDomain(Product source);

    @AfterMapping
    default void afterMapping(@MappingTarget Product target) {
        target.setCreatedAt(LocalDateTime.now());
        target.setUpdatedAt(LocalDateTime.now());
    }
}
