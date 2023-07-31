package com.shopping.cart.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "storage")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class Storage implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long itemId;
	private int total;

	public Storage() {
	}

	public Storage(long itemId, int total) {
		this.itemId = itemId;
		this.total = total;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
