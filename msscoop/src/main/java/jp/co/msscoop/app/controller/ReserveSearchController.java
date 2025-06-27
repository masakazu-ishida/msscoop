package jp.co.msscoop.app.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.msscoop.app.dto.Reserve;
import jp.co.msscoop.app.dto.UserDetailsImpl;
import jp.co.msscoop.app.service.ReservedSearchService;

@Controller
@RequestMapping("/reserve/search")
public class ReserveSearchController {
	
	
	private ReservedSearchService reservedSearchService;
	
	
	public ReserveSearchController(ReservedSearchService reservedSearchService) {
		this.reservedSearchService = reservedSearchService;
		
	}
	
	@GetMapping("")
	public ModelAndView index(@AuthenticationPrincipal UserDetailsImpl info) {
		
		ModelAndView modelView = new ModelAndView();
		
		List<Reserve> list = reservedSearchService.search(info.getUsername());
		
		modelView.addObject("reservedList", list);
		modelView.setViewName("reserve/reservedSearchResult");
		return modelView;
		
		
		
	}

}
