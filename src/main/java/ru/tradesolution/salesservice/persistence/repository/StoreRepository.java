package ru.tradesolution.salesservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tradesolution.salesservice.persistence.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
