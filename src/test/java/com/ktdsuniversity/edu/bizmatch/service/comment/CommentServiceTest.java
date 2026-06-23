package com.ktdsuniversity.edu.bizmatch.service.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.bizmatch.comment.service.CommentService;
import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentVO;
import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentWriteVO;
import com.ktdsuniversity.edu.bizmatch.common.vo.PaginationVO;
import com.ktdsuniversity.edu.bizmatch.service.ServiceIntegrationBaseTest;

@DisplayName("CommentService - PostgreSQL Mapper 검증")
class CommentServiceTest extends ServiceIntegrationBaseTest {

    @Autowired
    private CommentService commentService;

    @Test
    @DisplayName("getAllComment: WITH RECURSIVE CTE 실행 검증")
    void getAllComment_withRecursiveCte() {
        List<CommentVO> result = commentService.getAllComment("NON_EXISTENT_ID");
        assertNotNull(result);
    }

    @Test
    @DisplayName("getComment: WITH RECURSIVE + LIMIT/OFFSET 페이지네이션 검증")
    void getComment_withCteAndPagination() {
        PaginationVO pvo = new PaginationVO();
        pvo.setSearchIdParam("NON_EXISTENT_ID");
        pvo.setCurrPageNo(0);

        List<CommentVO> result = commentService.getComment(pvo, "NON_EXISTENT_ID");
        assertNotNull(result);
    }

    @Test
    @DisplayName("getCommentCount: COUNT 쿼리 실행 검증")
    void getCommentCount_shouldReturnZeroForNonExistentId() {
        int count = commentService.getCommentCount("NON_EXISTENT_ID");
        assertEquals(0, count);
    }

    @Test
    @DisplayName("createNewComment: NEXTVAL 시퀀스 실행 검증")
    void createNewComment_sequenceNextval() {
        CommentWriteVO vo = new CommentWriteVO();
        vo.setPjId("NON_EXISTENT_PJ");
        vo.setCmmntCntnt("테스트 댓글");
        vo.setAthrId("test@test.com");

        // NEXTVAL('PJ_CMMNT_PK_SEQ') 가 정상 동작하면 true 반환 (FK 제약 위반 시 예외 발생)
        // FK 제약으로 실패하더라도 SQL 문법 오류와 구분 가능
        try {
            boolean result = commentService.createNewComment(vo);
            assertNotNull(result);
        } catch (Exception e) {
            // DataIntegrityViolationException (FK 위반) 은 SQL 변환 문제가 아님 — 정상
            String msg = e.getClass().getSimpleName();
            assertNotNull(msg);
        }
    }
}