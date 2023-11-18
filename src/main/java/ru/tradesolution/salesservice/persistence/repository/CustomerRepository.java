package ru.tradesolution.salesservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tradesolution.salesservice.persistence.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
