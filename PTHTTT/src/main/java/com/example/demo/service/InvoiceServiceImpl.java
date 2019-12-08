package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Invoice;
import com.example.demo.repository.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	public Invoice getInvoiceById(Integer id) {
		return invoiceRepository.findById(id).orElse(null);
	}

	@Override
	public List<Invoice> getAllInvoices() {
		return invoiceRepository.findAll();
	}

	@Override
	public void saveInvoice(Invoice invoice) {
		invoiceRepository.save(invoice);
		
	}

	@Override
	public void delete(Integer id) {
		invoiceRepository.deleteById(id);
		
	}

	@Override
	public void saveAndFlush(Invoice invoice) {
		invoiceRepository.saveAndFlush(invoice);
	}

	@Override
	public List<Invoice> findInvoicesByUsername(String key) {
		List<Invoice> invoices = invoiceRepository.findByUserUsername(key);
		List<Invoice> containInvoices = invoiceRepository.findByUserUsernameContaining(key);
		if(invoices.size()!=0) {
			for(Invoice invoice : invoices) {
				if(!invoices.contains(invoice)) {
					invoices.add(invoice);
				}
			}
			return invoices;
		}
		return containInvoices;
	}
	
}
