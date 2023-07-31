package com.shopping.cart.repo;

import com.shopping.cart.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepo extends JpaRepository<Storage, Long> {
}
