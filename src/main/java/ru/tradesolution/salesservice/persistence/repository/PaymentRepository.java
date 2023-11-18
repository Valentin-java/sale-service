package ru.tradesolution.salesservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tradesolution.salesservice.persistence.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
