package com.care.testDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.care.DTO.BankDTO;

public class BankDAO {
	private JdbcTemplate template;
	private TransactionTemplate transactionTemplate;
	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	//통장 금액 확인
	public ArrayList<BankDTO> resultMoney() {
		String sql_balance = "select * from balance";
		ArrayList<BankDTO> arr = new ArrayList<BankDTO>();
		
		try {
			arr = (ArrayList<BankDTO>)template.query(
					sql_balance,
					new BeanPropertyRowMapper<BankDTO>(BankDTO.class)
			);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return arr;
	}
	
	//입금
	public int deposit(BankDTO dto) {
		
		int depositMoney = dto.getMoney();
		int totMoney = dto.getTotmoney() + dto.getMoney();
		
		String sql_user = "insert into myaccount(num, money) values(?,?)";
		String sql_system = "insert into sysaccount(num, money) values(?,?)";
		String sql_balance = "insert into balance(num, totmoney) values(?,?)";
		
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					template.update(sql_user, ps->{
						ps.setInt(1, dto.getNum()+1);
						ps.setInt(2, depositMoney);
					});		
					template.update(sql_system, ps->{
						ps.setInt(1, dto.getNum()+1);
						ps.setInt(2, depositMoney);
					});	
					template.update(sql_balance, ps->{
						ps.setInt(1, dto.getNum()+1);
						ps.setInt(2, totMoney);
					});	
				}
			});
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return totMoney;		
	}
	
	/*
	public int[] buyTicket(TicketDTO dto) {
		String sql_user = "insert into userticket(id, ticketnum) values(?,?)";
		String sql_system = "insert into systemticket(id,ticketnum) values(?,?)";
		
		int arr[] = new int[2];
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					arr[0] = template.update(sql_user, ps->{
						ps.setString(1, dto.getId());
						ps.setInt(2, dto.getTicketnum());				
					});
					
					arr[1] = template.update(sql_system, ps->{
						ps.setString(1, dto.getId());
						ps.setInt(2, dto.getTicketnum());				
					});
						
				}
			});
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return arr;		
	}
	
	
	public Map<String, ArrayList<TicketDTO>> resultTicket() {
		String sql_user = "select * from userticket";
		String sql_system = "select * from systemticket";
		
		Map<String, ArrayList<TicketDTO>> map = new HashMap<String, ArrayList<TicketDTO>>();
		
		ArrayList<TicketDTO> userticket = null;
		ArrayList<TicketDTO> systemticket = null;
		
		try {
			userticket = (ArrayList<TicketDTO>)template.query(
					sql_user,
					new BeanPropertyRowMapper<TicketDTO>(TicketDTO.class)
			);
			
			systemticket = (ArrayList<TicketDTO>)template.query(
					sql_system,
					new BeanPropertyRowMapper<TicketDTO>(TicketDTO.class)
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		map.put("userticket", userticket);
		map.put("systemticket", systemticket);
		return map;
	}*/
}
