package ru.tradesolution.salesservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tradesolution.salesservice.persistence.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
