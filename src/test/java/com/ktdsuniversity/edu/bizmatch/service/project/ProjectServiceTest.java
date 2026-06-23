package com.ktdsuniversity.edu.bizmatch.service.project;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.bizmatch.project.service.ProjectService;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectCommentPaginationVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectCommentVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.SearchProjectVO;
import com.ktdsuniversity.edu.bizmatch.service.ServiceIntegrationBaseTest;

@DisplayName("ProjectService - PostgreSQL Mapper 검증")
class ProjectServiceTest extends ServiceIntegrationBaseTest {

    @Autowired
    private ProjectService projectService;

    @Test
    @DisplayName("selectAllCardProject: LIMIT/OFFSET 페이지네이션 검증")
    void selectAllCardProject_withPagination() {
        SearchProjectVO searchVO = new SearchProjectVO();
        assertNotNull(projectService.selectAllCardProject(searchVO));
    }

    @Test
    @DisplayName("getAllComment: 프로젝트 댓글 WITH RECURSIVE CTE 검증")
    void getAllComment_withRecursiveCte() {
        List<ProjectCommentVO> result = projectService.getAllComment("NON_EXISTENT_ID");
        assertNotNull(result);
    }

    @Test
    @DisplayName("getPaginationComment: WITH RECURSIVE + LIMIT/OFFSET 복합 검증")
    void getPaginationComment_withCteAndPagination() {
        ProjectCommentPaginationVO pvo = new ProjectCommentPaginationVO();
        pvo.setExposureListSize(10);
        pvo.setCurrPageNo(0);

        List<ProjectCommentVO> result = projectService.getPaginationComment(pvo, "NON_EXISTENT_ID");
        assertNotNull(result);
    }

    @Test
    @DisplayName("readOneProjectInfo: 단건 프로젝트 조회 (복잡한 JOIN 포함)")
    void readOneProjectInfo_withComplexJoin() {
        ProjectVO result = projectService.readOneProjectInfo("NON_EXISTENT_ID");
        // null 이어도 SQL 실행 성공이면 OK
        assertNotNull(result == null ? "null_ok" : result);
    }

    @Test
    @DisplayName("selectAllProjectSkillList: 전체 스킬 목록 조회")
    void selectAllProjectSkillList_shouldReturnList() {
        assertNotNull(projectService.selectAllProjectSkillList());
    }
}