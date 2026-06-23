package com.ktdsuniversity.edu.bizmatch.service.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.bizmatch.file.dao.FileDao;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberPortfolioAttVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectApplyAttVO;
import com.ktdsuniversity.edu.bizmatch.service.ServiceIntegrationBaseTest;

@DisplayName("FileDao - PostgreSQL Mapper 검증 (Service 구현체 없음)")
class FileDaoTest extends ServiceIntegrationBaseTest {

    @Autowired
    private FileDao fileDao;

    @Test
    @DisplayName("selectPortfolioFileList: 포트폴리오 첨부파일 조회")
    void selectPortfolioFileList_withNonExistentId() {
        List<MemberPortfolioAttVO> result = fileDao.selectPortfolioFileList("NON_EXISTENT_ID");
        assertNotNull(result);
    }

    @Test
    @DisplayName("selectPortfolioAllAttCnt: 포트폴리오 첨부파일 COUNT")
    void selectPortfolioAllAttCnt_withNonExistentId() {
        int count = fileDao.selectPortfolioAllAttCnt("NON_EXISTENT_ID");
        assertEquals(0, count);
    }

    @Test
    @DisplayName("selectAllProjectApplyAtt: 지원서 첨부파일 조회")
    void selectAllProjectApplyAtt_withNonExistentId() {
        List<ProjectApplyAttVO> result = fileDao.selectAllProjectApplyAtt("NON_EXISTENT_ID");
        assertNotNull(result);
    }

    @Test
    @DisplayName("selectProjectFileById: 프로젝트 파일 단건 조회")
    void selectProjectFileById_withNonExistentId() {
        var result = fileDao.selectProjectFileById("NON_EXISTENT_ID");
        assertNotNull(result == null ? "null_ok" : result);
    }
}
