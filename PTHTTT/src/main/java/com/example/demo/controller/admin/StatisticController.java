package com.example.demo.controller.admin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Invoice;
import com.example.demo.response.StatisticRest;
import com.example.demo.service.InvoiceService;
import com.example.demo.service.StatisticService;

@Controller
@RequestMapping(value = "/admin/statistic")
public class StatisticController {

	@Autowired
    private StatisticService statisticService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String statistic(Model model) {
		return "admin/Statistic";
	}
	
	@RequestMapping(value = "/thisdate", method = RequestMethod.GET)
	@ResponseBody
	public StatisticRest statisticByThisDate(Model model) {
		
		return statisticService.statisticThisDate();
	}
	
	@RequestMapping(value = "/thismonth", method = RequestMethod.GET)
	@ResponseBody
	public StatisticRest statisticByThisMonth(Model model) {
		return statisticService.statisticThisMonth();
	}
	
	@RequestMapping(value = "/bymonth", method = RequestMethod.GET)
	@ResponseBody
	public StatisticRest statisticByMonth(@RequestParam(name = "month") Integer month, Model model) {
		return statisticService.statisticByMonth(month, LocalDateTime.now().getYear());
	}
	
	@RequestMapping(value = "/thisyear", method = RequestMethod.GET)
	@ResponseBody
	public StatisticRest statisticByThisYear(Model model) {
		return statisticService.statisticThisYear();
	}
	
}
