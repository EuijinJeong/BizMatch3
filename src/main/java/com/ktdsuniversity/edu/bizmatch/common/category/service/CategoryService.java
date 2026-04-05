package com.ktdsuniversity.edu.bizmatch.common.category.service;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.common.vo.IndstrInfoVO;


public interface CategoryService {
	
	/**
	 * 해당 산업군에 대한 중분류 정보를 조회하는 메서드.
	 * @param indstrId
	 * @return
	 */
	public List<IndstrInfoVO> selectSubIndstr(String indstrId);
}
