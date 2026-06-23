package com.ktdsuniversity.edu.bizmatch.service.report;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.bizmatch.report.service.ReviewReportService;
import com.ktdsuniversity.edu.bizmatch.report.vo.ReviewReportVO;
import com.ktdsuniversity.edu.bizmatch.service.ServiceIntegrationBaseTest;

@DisplayName("ReviewReportService - PostgreSQL Mapper 검증")
class ReviewReportServiceTest extends ServiceIntegrationBaseTest {

    @Autowired
    private ReviewReportService reviewReportService;

    @Test
    @DisplayName("selectAllReviewReports: 리뷰 신고 목록 조회")
    void selectAllReviewReports_withNonExistentId() {
        List<ReviewReportVO> result = reviewReportService.selectAllReviewReports("NON_EXISTENT_ID");
        assertNotNull(result);
    }
}
