package com.example.demo.form;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.DetailInvoice;
import com.example.demo.model.Invoice;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class InvoiceForm {

	private int id = -1;

	private String username;
	
	private String fullName;
	
	private String dateModified;

	private int totalPrice;
	
	private List<DetailInvoiceForm> detailInvoiceForms = new ArrayList<DetailInvoiceForm>();
	
	public InvoiceForm(Invoice invoice) {
		super();
		if(invoice != null) {
			id = invoice.getId();
			if(invoice.getUser()!=null) {
				username = invoice.getUser().getUsername();
				fullName = invoice.getUser().getFullName();
			}
			dateModified = invoice.getDateModified().toString();
			totalPrice = invoice.getTotalPrice();
			for(DetailInvoice detailInvoice : invoice.getDetailInvoices()) {
				detailInvoiceForms.add(new DetailInvoiceForm(detailInvoice));
			}
		}
	}
	public InvoiceForm() {
		super();
		dateModified = null;
	}
	
	public static List<InvoiceForm> convertToInvoiceForms(List<Invoice> invoices){
		List<InvoiceForm> invoiceForms = new ArrayList<InvoiceForm>();
		for(Invoice invoice:invoices)
			invoiceForms.add(new InvoiceForm(invoice));
		return invoiceForms;
	}
}
