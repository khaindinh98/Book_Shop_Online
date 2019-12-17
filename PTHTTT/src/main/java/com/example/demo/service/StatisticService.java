package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.response.StatisticRest;

public interface StatisticService {
	
	StatisticRest statisticThisDate();
	
	StatisticRest statisticThisMonth();
	
	StatisticRest statisticByMonth(int month, int year);
	
	StatisticRest statisticThisYear();

	StatisticRest statisticByDate(LocalDateTime date);
	
	StatisticRest statisticByMonth(LocalDateTime date);
	
	StatisticRest statisticByYear(LocalDateTime date);
}
