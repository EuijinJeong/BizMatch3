package com.ktdsuniversity.edu.bizmatch.common.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.bizmatch.common.category.dao.CategoryDao;
import com.ktdsuniversity.edu.bizmatch.common.category.service.CategoryService;
import com.ktdsuniversity.edu.bizmatch.common.vo.IndstrInfoVO;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<IndstrInfoVO> selectSubIndstr(String indstrId) {
		List<IndstrInfoVO> indstrInfoVOs = this.categoryDao.selectSubIndstr(indstrId);
		if(indstrInfoVOs == null) {
			throw new IllegalArgumentException("해당 산업군에 해당하는 하위 산업군이 존재하지 않습니다.");
		}
		return indstrInfoVOs;
	}
}
