package com.ktdsuniversity.edu.bizmatch.member.vo;

import java.util.List;
import java.util.Map;

public class PortfolioResponse {
	  private List<Map<String, Object>> portfolioList;
	  private MemberPortfolioPagenationVO pagination;
	public List<Map<String, Object>> getPortfolioList() {
		return portfolioList;
	}
	public void setPortfolioList(List<Map<String, Object>> portfolioList) {
		this.portfolioList = portfolioList;
	}
	public MemberPortfolioPagenationVO getPagination() {
		return pagination;
	}
	public void setPagination(MemberPortfolioPagenationVO pagination) {
		this.pagination = pagination;
	}


}
