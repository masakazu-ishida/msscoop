package jp.co.msscoop.app.service;

import java.util.List;

import jp.co.msscoop.app.dto.Reserve;

public interface ReservedSearchService {
	
	
	public List<Reserve> search(String userId);

}
