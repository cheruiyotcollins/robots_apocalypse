package com.indomidas.apocalypse.repository;

import com.indomidas.apocalypse.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
