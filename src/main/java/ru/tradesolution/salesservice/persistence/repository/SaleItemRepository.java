package ru.tradesolution.salesservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tradesolution.salesservice.persistence.entity.SaleItem;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}
