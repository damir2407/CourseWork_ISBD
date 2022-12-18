package com.oasis.oasisapp.repository;

import com.oasis.oasisapp.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query(value = "SELECT * FROM inventory WHERE inventory.user_login=:user_login", nativeQuery = true)
    Optional<List<Inventory>> getAllByUserLogin(@Param("user_login") String user_login);
}
