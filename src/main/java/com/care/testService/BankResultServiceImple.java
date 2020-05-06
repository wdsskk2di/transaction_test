package com.care.testService;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.care.DTO.BankDTO;
import com.care.testDAO.BankDAO;

public class BankResultServiceImple implements BankService {
	private BankDAO dao;
	
	public BankResultServiceImple() {
		String config = "classpath:applicationJDBC.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(config);
		dao = ctx.getBean("dao", BankDAO.class);
	}
	
	@Override
	public void execute(Model model) {
//		Map<String, Object> map = model.asMap();
//		BankDTO dto = (BankDTO)map.get("dto");
		ArrayList<BankDTO> result = dao.resultMoney();
		
		model.addAttribute("result", result.get(0));
	}

}
