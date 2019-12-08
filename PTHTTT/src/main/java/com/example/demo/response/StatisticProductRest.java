package com.example.demo.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticProductRest {

	private String nameBook;
	
	private int numOfItemBought=0;
	
	private int numOfItemSold=0;
	
	private int earningsByBook=0;
	
	private int priceBuy=0;
	
	private int priceSell=0;
	
	private int numOfItemRest=0;
	
}
