package ru.tradesolution.salesservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tradesolution.salesservice.persistence.entity.InventoryItem;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
}
