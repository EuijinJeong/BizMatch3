package com.ktdsuniversity.edu.bizmatch.service.admin;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.bizmatch.admin.report.service.ReportService;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.ReviewVO;
import com.ktdsuniversity.edu.bizmatch.service.ServiceIntegrationBaseTest;

@DisplayName("AdminReportService - PostgreSQL Mapper 검증")
class AdminReportServiceTest extends ServiceIntegrationBaseTest {

    @Autowired
    private ReportService reportService;

    @Test
    @DisplayName("readAllReportReview: 신고된 리뷰 목록 조회")
    void readAllReportReview_shouldReturnList() {
        List<ReviewVO> result = reportService.readAllReportReview();
        assertNotNull(result);
    }
}
