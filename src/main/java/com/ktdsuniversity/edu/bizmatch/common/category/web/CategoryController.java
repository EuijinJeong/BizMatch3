package com.ktdsuniversity.edu.bizmatch.common.category.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.bizmatch.common.category.service.CategoryService;
import com.ktdsuniversity.edu.bizmatch.common.vo.IndstrInfoVO;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 대분류에 따른 중분류 결과 값을 전달해주는 컨트롤러.
	 * @param ctgryId
	 * @return
	 */
	@GetMapping("/category/filter/{ctgryId}")
	public List<Map<String, Object>> loadCategoryFilter(@PathVariable String ctgryId) {
		List<Map<String, Object>> response = new ArrayList<>();
		List<IndstrInfoVO> resultList = this.categoryService.selectSubIndstr(ctgryId);
		for (IndstrInfoVO indstrInfoVO : resultList) {
			Map<String, Object> tempMap = new HashMap<>();
			tempMap.put("id", indstrInfoVO.getIndstrId());
			tempMap.put("name", indstrInfoVO.getIndstrNm());
			response.add(tempMap);
		}
		
		return response;
	}
}
