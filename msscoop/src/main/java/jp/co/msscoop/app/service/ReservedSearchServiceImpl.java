package jp.co.msscoop.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.msscoop.app.common.SystemDateUtil;
import jp.co.msscoop.app.dao.ReserveDAO;
import jp.co.msscoop.app.dto.Reserve;


@Service
public class ReservedSearchServiceImpl implements ReservedSearchService {

	private ReserveDAO reserveDAO;
	private SystemDateUtil systemDateUtil;
	
	public ReservedSearchServiceImpl(ReserveDAO reserveDAO, SystemDateUtil systemDateUtil) {
		this.reserveDAO = reserveDAO;
		this.systemDateUtil = systemDateUtil;
	}
	
	@Override
	public List<Reserve> search(String userId) {
		
		LocalDate tomorrowDate  =systemDateUtil.today().plusDays(1);
		
		List<Reserve> list =  reserveDAO.search(userId, tomorrowDate);
		
		/*
		for(Reserve reserve : list) {
			
			reserve.setAmount(reserve.getStayNumberOfPeople() + reserve.getRoom().getPrice());
		}
		*/
		return list;
	}
	
	

}
