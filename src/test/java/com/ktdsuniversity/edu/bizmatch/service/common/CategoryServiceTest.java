package com.ktdsuniversity.edu.bizmatch.service.common;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.bizmatch.common.category.service.CategoryService;
import com.ktdsuniversity.edu.bizmatch.common.vo.IndstrInfoVO;
import com.ktdsuniversity.edu.bizmatch.service.ServiceIntegrationBaseTest;

@DisplayName("CategoryService - PostgreSQL Mapper 검증")
class CategoryServiceTest extends ServiceIntegrationBaseTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    @DisplayName("selectSubIndstr: 하위 카테고리 조회 (단순 SELECT)")
    void selectSubIndstr_withNonExistentId() {
        List<IndstrInfoVO> result = categoryService.selectSubIndstr("NON_EXISTENT");
        assertNotNull(result);
    }
}
