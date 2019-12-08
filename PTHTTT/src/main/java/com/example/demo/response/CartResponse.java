package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
	private int idCart;
	private int idBook;
	private String name;
	private String images;
	private int priceSell;
	private int number;
}
