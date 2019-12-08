package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Invoice;
import com.example.demo.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	Order findById(int id);
	List<Order> findByUserUsername(String key);
	List<Order> findByUserUsernameContaining(String key);
	List<Order> findByDateOrderBetween(LocalDateTime start, LocalDateTime end);
}
