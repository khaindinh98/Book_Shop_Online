package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Invoice;

public interface InvoiceService {
	
	Invoice getInvoiceById(Integer id);
	
	List<Invoice> getAllInvoices();
	
	List<Invoice> findInvoicesByUsername(String key);
	
	void saveInvoice(Invoice invoice);
	
	void saveAndFlush(Invoice invoice);
	
	void delete(Integer id);


}
