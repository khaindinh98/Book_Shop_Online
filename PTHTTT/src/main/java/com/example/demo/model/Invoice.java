package com.example.demo.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.form.DetailInvoiceForm;
import com.example.demo.form.InvoiceForm;
import com.example.demo.service.BookService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "invoice")
@Data
@NoArgsConstructor
public class Invoice implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private User user;
	
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetailInvoice> detailInvoices = new ArrayList<DetailInvoice>();	
	
	@Column(name = "total_price")
	private int totalPrice = 0;
	
	@Column(name = "date_modified", columnDefinition = "TIMESTAMP")
	private LocalDateTime dateModified =  LocalDateTime.now();
	
	//Change priceBuy when new priceBuy is different with old priceBuy 
	public void addBook(Book book, int quantity, int priceBuy) {
		
		DetailInvoice detailInvoice = new DetailInvoice(this, book, quantity, book.getPriceBuy());
		detailInvoices.add(detailInvoice);
		totalPrice += (quantity * book.getPriceBuy());
	}
	
//	public void changeBookQuantity(Book book, int difference) { 
//		for(DetailInvoice detailInvoice : detailInvoices) {
//			if(detailInvoice.getBook().getId() == book.getId()) {
//				detailInvoice.setQuantity(detailInvoice.getQuantity() + difference);
//				this.totalPrice += (difference*detailInvoice.getPriceBuy());
//				return;
//			}
//		}
//	}
//	
//	public void changeBookPriceBuy(Book book, int priceBuy) { 
//		for(DetailInvoice detailInvoice : detailInvoices) {
//			if(detailInvoice.getBook().getId() == book.getId()) {
//				detailInvoice.setPriceBuy(priceBuy);
//				return;
//			}
//		}
//	}
//	
//	public void removeBook(Book book) {
//		for(DetailInvoice detailInvoice : detailInvoices) {
//			if(detailInvoice.getBook().getId() == book.getId()) {	
//				this.totalPrice -= (detailInvoice.getQuantity() * detailInvoice.getPriceBuy());
//				this.detailInvoices.remove(detailInvoice);
//				return;
//			}
//		}
//	}

}
