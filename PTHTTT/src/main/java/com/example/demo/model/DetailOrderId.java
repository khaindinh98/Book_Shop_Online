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
public class DetailOrderId implements Serializable{
	
	@Column(name = "order_id")
	private int order_id;
	
	@Column(name = "book_id")
	private int book_id;
	
}
