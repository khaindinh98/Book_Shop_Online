package com.example.demo.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detail_invoice")
@Data
@NoArgsConstructor
public class DetailInvoice {

	
	public DetailInvoice(Invoice invoice, Book book, int quantity, int priceBuy) {
		this.detailInvoiceId = new DetailInvoiceId(invoice.getId(), book.getId());
		this.invoice = invoice;
		this.book = book;
		this.quantity = quantity;
		this.priceBuy = priceBuy;
	}
	
	@EmbeddedId
	private DetailInvoiceId detailInvoiceId;
	
	@MapsId(value = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invoice_id", referencedColumnName = "id")
	private Invoice invoice;
	
	@MapsId(value = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	private Book book;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "price_buy")
	private int priceBuy;

}
