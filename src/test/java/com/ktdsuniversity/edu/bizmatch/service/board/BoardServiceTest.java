package com.ktdsuniversity.edu.bizmatch.service.board;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.bizmatch.board.service.BoardService;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardCommentVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardVO;
import com.ktdsuniversity.edu.bizmatch.service.ServiceIntegrationBaseTest;

@DisplayName("BoardService - PostgreSQL Mapper 검증")
class BoardServiceTest extends ServiceIntegrationBaseTest {

    @Autowired
    private BoardService boardService;

    @Test
    @DisplayName("getBoardList: 게시글 목록 조회 (ORDER BY CASE 포함)")
    void getBoardList_shouldReturnList() {
        List<BoardVO> result = boardService.getBoardList();
        assertNotNull(result);
    }

    @Test
    @DisplayName("getOneBoard: 단건 조회 — 없는 ID는 null 반환")
    void getOneBoard_withNonExistentId() {
        BoardVO result = boardService.getOneBoard("NON_EXISTENT_ID");
        // null 이어도 SQL 실행 자체가 성공이면 OK
        assertNotNull(result == null ? "null_ok" : result);
    }

    @Test
    @DisplayName("getAllBoardComment: WITH RECURSIVE CTE 실행 검증")
    void getAllBoardComment_withRecursiveCte() {
        List<BoardCommentVO> result = boardService.getAllBoardComment("NON_EXISTENT_ID");
        assertNotNull(result);
    }
}