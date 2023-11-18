package ru.tradesolution.salesservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tradesolution.salesservice.persistence.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByBarcode(String barcode);
}
