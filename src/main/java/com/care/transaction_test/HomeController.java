package com.care.transaction_test;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.care.DTO.BankDTO;
import com.care.testService.BankDepositServiceImple;
import com.care.testService.BankResultServiceImple;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private BankDepositServiceImple bds;
	private BankResultServiceImple brs;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("deposit")
	public String deposit() {
		return "deposit";
	}
	
	@RequestMapping("remit")
	public String remit() {
		return "remit";
	}
	
	@RequestMapping("depostcheck")
	public String depostcheck(BankDTO dto, Model model) {
		bds = new BankDepositServiceImple();
		model.addAttribute("dto", dto);
		bds.execute(model);
		
		return "check";
	}
	
	@RequestMapping("check")
	public String check(Model model) {
		brs = new BankResultServiceImple();
		model.addAttribute("model", model);
		brs.execute(model);
		
		return "check";
	}
	
}
