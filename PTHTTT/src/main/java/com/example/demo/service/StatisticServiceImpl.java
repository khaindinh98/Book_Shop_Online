package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DetailInvoice;
import com.example.demo.model.DetailOrder;
import com.example.demo.model.Invoice;
import com.example.demo.model.Order;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.response.StatisticRest;
@Service
public class StatisticServiceImpl implements StatisticService{

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	private int statisticEarningsByInvoicesTime(LocalDateTime start, LocalDateTime end) {
		int totalPriceByDate = 0;
		List<Invoice> invoices = invoiceRepository.findByDateModifiedBetween(start, end);		
		for(Invoice invoice : invoices)
			totalPriceByDate+=invoice.getTotalPrice();
		return totalPriceByDate;
	}
	
	private int statisticEarningsByOrdersTime(LocalDateTime start, LocalDateTime end) {
		int totalPriceByDate = 0;
		List<Order> orders = orderRepository.findByDateOrderBetween(start, end);		
		for(Order order : orders)
			totalPriceByDate+=order.getTotalPrice();
		return totalPriceByDate;
	}
	
	
	private int statisticEarningsByTime(LocalDateTime start, LocalDateTime end) {
		return statisticEarningsByOrdersTime(start, end) - statisticEarningsByInvoicesTime(start, end);
	}
	
	
	private int statisticEarningsByDate(LocalDateTime dateTime) {
		int seconds = 24 * 60 * 60;
		return statisticEarningsByTime(dateTime, dateTime.plusSeconds(seconds));
	}

	
	private int statisticEarningsByYear(LocalDateTime dateTime) {
		int seconds = dateTime.getDayOfYear() * 24 * 60 * 60;
		return statisticEarningsByTime(dateTime, dateTime.plusSeconds(seconds));
	}

	
	private StatisticRest statisticByTime(LocalDateTime start, LocalDateTime end) {
		StatisticRest statisticRest = new StatisticRest();
		statisticRest.setTimeStart(start.toString());
		statisticRest.setTimeEnd(end.toString());
		List<Invoice> invoices = invoiceRepository.findByDateModifiedBetween(start, end);
		for(Invoice invoice : invoices) {
			for(DetailInvoice detailInvoice: invoice.getDetailInvoices()) {
				statisticRest.addDetailInvoice(detailInvoice);
			}
		}
		List<Order> orders = orderRepository.findByDateOrderBetween(start, end);
		for(Order order : orders) {
			for(DetailOrder detailOrder: order.getDetailOrders()) {
				statisticRest.addDetailOrder(detailOrder);
			}
		}
		
		return statisticRest;
	}
	
	@Override
	public StatisticRest statisticThisDate() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime start = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(),0,0);
		return statisticByTime(start, start.plusDays(1));
	}

	@Override
	public StatisticRest statisticThisMonth() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime start = LocalDateTime.of(now.getYear(), now.getMonth(), 1,0,0);
		return statisticByTime(start, start.plusMonths(1));
	}
	
	@Override
	public StatisticRest statisticByMonth(int month, int year) {
		LocalDateTime start = LocalDateTime.of(year, month, 1,0,0);
		return statisticByTime(start, start.plusMonths(1));
	}

	@Override
	public StatisticRest statisticThisYear() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime start = LocalDateTime.of(now.getYear(), 1, 1,0,0);
		return statisticByTime(start, start.plusYears(1));
	}
	
}
