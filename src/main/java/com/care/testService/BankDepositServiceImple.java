package com.care.testService;

import java.util.Map;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.care.DTO.BankDTO;
import com.care.testDAO.BankDAO;

public class BankDepositServiceImple implements BankService{
	private BankDAO dao;
	
	public BankDepositServiceImple() {
		String config = "classpath:applicationJDBC.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(config);
		dao = ctx.getBean("dao", BankDAO.class);
	}
	
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		BankDTO dto = (BankDTO)map.get("dto");
		int[] bankMoney = dao.resultMoney();
		
		model.addAttribute("result", dao.deposit(bankMoney, dto));
	}

}
