package com.ktdsuniversity.edu.bizmatch.service.project;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.bizmatch.project.review.service.ReviewService;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.ReviewVO;
import com.ktdsuniversity.edu.bizmatch.service.ServiceIntegrationBaseTest;

@DisplayName("ReviewService - PostgreSQL Mapper 검증")
class ReviewServiceTest extends ServiceIntegrationBaseTest {

    @Autowired
    private ReviewService reviewService;

    @Test
    @DisplayName("selectAllReviews: 프로젝트 리뷰 전체 조회")
    void selectAllReviews_withNonExistentId() {
        List<ReviewVO> result = reviewService.selectAllReviews("NON_EXISTENT_ID");
        assertNotNull(result);
    }

    @Test
    @DisplayName("readReviewListSortedHighRate: 높은 평점순 정렬 (ORDER BY DESC)")
    void readReviewListSortedHighRate_withNonExistentId() {
        List<ReviewVO> result = reviewService.readReviewListSortedHighRate("NON_EXISTENT_ID");
        assertNotNull(result);
    }

    @Test
    @DisplayName("readReviewListSortLowRate: 낮은 평점순 정렬 (ORDER BY ASC)")
    void readReviewListSortLowRate_withNonExistentId() {
        List<ReviewVO> result = reviewService.readReviewListSortLowRate("NON_EXISTENT_ID");
        assertNotNull(result);
    }
}
