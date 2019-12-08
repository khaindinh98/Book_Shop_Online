package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailInvoiceId implements Serializable {
	
	@Column(name = "invoice_id")
	private int invoice_id;
	
	@Column(name = "book_id")
	private int book_id;
	
}