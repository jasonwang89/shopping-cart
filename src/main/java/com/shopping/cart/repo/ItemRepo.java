package com.shopping.cart.repo;

import com.shopping.cart.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Long> {
	List<Item> findByItemId(int itemId);
}
