package com.ktdsuniversity.edu.bizmatch.common.category.dao;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.common.vo.IndstrInfoVO;


public interface CategoryDao {
	
	public String NAMESPACE = "com.ktdsuniversity.edu.bizmatch.common.category.dao.CategoryDao";
	
	/**
	 * 대분류 산업군 아래에 있는 중분류 산업군을 조회하는 쿼리문.
	 * @param indstrId
	 * @return
	 */
	public List<IndstrInfoVO> selectSubIndstr(String indstrId);

}
