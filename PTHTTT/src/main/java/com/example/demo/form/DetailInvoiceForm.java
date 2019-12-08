package com.example.demo.form;

import com.example.demo.model.DetailInvoice;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetailInvoiceForm {
	
	private int bookId = -1;
	
	private String bookName;
	
	private String pathImage;
	
	private int priceBuy = 0;
	
	private int quantity = 0;

	public DetailInvoiceForm(DetailInvoice detailInvoice) {
		super();
		if(detailInvoice!=null) {
			bookId = detailInvoice.getBook().getId();
			bookName = detailInvoice.getBook().getName();
			pathImage = detailInvoice.getBook().getPathImages();
			priceBuy = detailInvoice.getPriceBuy();
			quantity = detailInvoice.getQuantity();
		}
	}
	
}
