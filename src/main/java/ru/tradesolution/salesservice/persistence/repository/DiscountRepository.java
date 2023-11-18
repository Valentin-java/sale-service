package ru.tradesolution.salesservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tradesolution.salesservice.persistence.entity.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
