package com.example.demo.response;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.DetailInvoice;
import com.example.demo.model.DetailOrder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatisticRest {

	private String timeStart;

	private String timeEnd;

	private int totalOfItemBought = 0;

	private int totalOfItemSold = 0;

	private int totalOfProductBought = 0;

	private int totalOfProductSold = 0;

	private int earnings = 0;

	private List<StatisticProductRest> statisProductRests = new ArrayList<StatisticProductRest>();

	public void addDetailInvoice(DetailInvoice detailInvoice) {

		for (StatisticProductRest statisProductRest : statisProductRests) {
			if (statisProductRest.getNameBook().equals(detailInvoice.getBook().getName())) {
				if(statisProductRest.getNumOfItemBought() == 0)
					this.totalOfProductBought += 1;
				statisProductRest
						.setNumOfItemBought(statisProductRest.getNumOfItemBought() + detailInvoice.getQuantity());
//				statisProductRest.setEarningsByBook(statisProductRest.getEarningsByBook()
//						- (detailInvoice.getQuantity() * detailInvoice.getPriceBuy()));
				this.totalOfItemBought += detailInvoice.getQuantity();
//				this.earnings -= detailInvoice.getQuantity() * detailInvoice.getPriceBuy();
				return;
			}
		}
		statisProductRests.add(new StatisticProductRest(detailInvoice.getBook().getName(), detailInvoice.getQuantity(),
				0, 0, detailInvoice.getBook().getPriceBuy(),
				detailInvoice.getBook().getPriceSell(), detailInvoice.getBook().getQuantity()));
		this.totalOfItemBought += detailInvoice.getQuantity();
		this.totalOfProductBought += 1;
//		this.earnings -= detailInvoice.getQuantity() * detailInvoice.getPriceBuy();
	}

	public void addDetailOrder(DetailOrder detailOrder) {

		for (StatisticProductRest statisProductRest : statisProductRests) {
			if (statisProductRest.getNameBook().equals(detailOrder.getBook().getName())) {
				if(statisProductRest.getNumOfItemSold() == 0)
					this.totalOfProductSold += 1;
				statisProductRest.setNumOfItemSold(statisProductRest.getNumOfItemSold() + detailOrder.getQuantity());
				statisProductRest.setEarningsByBook(statisProductRest.getEarningsByBook()
						+ (detailOrder.getQuantity() * (detailOrder.getUnitPrice()-detailOrder.getBook().getPriceBuy())));
				this.totalOfItemSold += detailOrder.getQuantity();
				this.earnings += detailOrder.getQuantity() * (detailOrder.getUnitPrice()-detailOrder.getBook().getPriceBuy());
				return;
			}
		}
		statisProductRests.add(new StatisticProductRest(detailOrder.getBook().getName(), 0, detailOrder.getQuantity(),
				(detailOrder.getQuantity() * (detailOrder.getUnitPrice()-detailOrder.getBook().getPriceBuy())), detailOrder.getBook().getPriceBuy(),
				detailOrder.getBook().getPriceSell(), detailOrder.getBook().getQuantity()));
		this.totalOfItemSold += detailOrder.getQuantity();
		this.totalOfProductSold += 1;
		this.earnings += detailOrder.getQuantity() * (detailOrder.getUnitPrice()-detailOrder.getBook().getPriceBuy());
	}

}
