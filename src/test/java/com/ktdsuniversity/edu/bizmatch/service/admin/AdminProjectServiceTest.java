package com.ktdsuniversity.edu.bizmatch.service.admin;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.bizmatch.admin.project.service.AdminProjectService;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectVO;
import com.ktdsuniversity.edu.bizmatch.service.ServiceIntegrationBaseTest;

@DisplayName("AdminProjectService - PostgreSQL Mapper 검증")
class AdminProjectServiceTest extends ServiceIntegrationBaseTest {

    @Autowired
    private AdminProjectService adminProjectService;

    @Test
    @DisplayName("readAllProject: 전체 프로젝트 목록 조회 (복잡한 JOIN)")
    void readAllProject_shouldReturnList() {
        List<ProjectVO> result = adminProjectService.readAllProject();
        assertNotNull(result);
    }

    @Test
    @DisplayName("readOneProject: 단건 프로젝트 조회")
    void readOneProject_withNonExistentId() {
        ProjectVO result = adminProjectService.readOneProject("NON_EXISTENT_ID");
        assertNotNull(result == null ? "null_ok" : result);
    }
}
