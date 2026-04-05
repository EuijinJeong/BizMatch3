package com.ktdsuniversity.edu.bizmatch.portfolio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PorfolioViewController {

	
	@GetMapping("/portfolio/view")
	public String viewPortfoiloPage() {
		return "portfolio/portfolio_view";
	}
	@GetMapping("/portfolio/write")
	public String viewPortfoiloWritePage() {
		return "portfolio/portfolio_write";
	}
}
