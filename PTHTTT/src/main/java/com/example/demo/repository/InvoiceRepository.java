package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{
	Invoice findById(int id);
	List<Invoice> findByUserUsername(String key);
	List<Invoice> findByUserUsernameContaining(String key);
	List<Invoice> findByDateModifiedBetween(LocalDateTime start, LocalDateTime end);
}
