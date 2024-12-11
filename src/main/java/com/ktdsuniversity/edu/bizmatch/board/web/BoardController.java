package com.ktdsuniversity.edu.bizmatch.board.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.bizmatch.board.service.BoardService;

import com.ktdsuniversity.edu.bizmatch.board.vo.BoardCommentVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardCommentWriteVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardModifyCommentVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardModifyVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardWriteVO;
import com.ktdsuniversity.edu.bizmatch.comment.web.CommentController;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.board.BoardException;
import com.ktdsuniversity.edu.bizmatch.common.utils.ParameterCheck;
import com.ktdsuniversity.edu.bizmatch.common.vo.ApiResponse;


@RestController
@RequestMapping("/api")
public class BoardController {
	
	public static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	private BoardService boardService;
	
	/**
	 * 게시글 목록 페이지
	 * @return
	 */
	@GetMapping("/board")
	public ApiResponse viewBoardList(Authentication loginMemberVO) {
		List<BoardVO> boardList = this.boardService.getBoardList();
		
		return new ApiResponse(boardList);
	}
	
	/**
	 * 게시글 조회 페이지
	 * 
	 * @return
	 */
	@GetMapping("/board/view/{id}")
	public ApiResponse viewOneBoard(@PathVariable String id
							, Authentication memberVO) {
		
		BoardVO boardVO = boardService.getOneBoard(id);

		return new ApiResponse(boardVO);
	}
	
	
//	/**
//	 * 게시글 작성페이지
//	 * 
//	 * @return
//	 */
//	@GetMapping("/board/write")
//	public String viewBoardWritePage(@SessionAttribute(value="_LOGIN_USER_", required =false) MemberVO loginMemberVO, Model model) {
//		return "board/boardwrite";
//	}

	/**
	 * 새로운 댓글을 작성하는 컨트롤러.
	 * @param boardWirteVO
	 * @param loginMemberVO
	 * @return
	 */
	@PostMapping("/board/write")
	public ApiResponse doCreateNewBoard(BoardWriteVO boardWirteVO 
										, Authentication loginMemberVO) {
		
		boardWirteVO.setAthrId(loginMemberVO.getName());
		if(ParameterCheck.parameterCodeValid(boardWirteVO.getPstNm(), 0)) {
			throw new BoardException("제목은 필수 입력입니다.", boardWirteVO);
		}
		if(ParameterCheck.parameterCodeValid(boardWirteVO.getPstCntnt(), 0)) {
			throw new BoardException("내용을 입력해주세요", boardWirteVO);
		}
		boolean result = boardService.createNewPost(boardWirteVO);
		
		return new ApiResponse(result);
	}
	
//	/**
//	 * 게시글 수정 페이지
//	 * 
//	 * @return
//	 */
//	@GetMapping("/board/modify/{id}")
//	public String viewBoardModifyPage(@PathVariable String id , Model model, @SessionAttribute(value="_LOGIN_USER_", required =false) MemberVO loginMemberVO){
//		
//		BoardVO boardVO = boardService.getOneBoard(id);
//		model.addAttribute("loginMemberVO",loginMemberVO);
//		model.addAttribute("boardVO", boardVO);
//		model.addAttribute("boardId", id);
//		return "board/boardmodify";
//	}
	
	/**
	 * 특정 게시글을 수정하는 메서드.
	 * @param boardModifyVO
	 * @return
	 */
	@PostMapping("/board/modify")
	public ApiResponse doModify(@RequestBody BoardModifyVO boardModifyVO) {
		boolean result = boardService.doModifyPost(boardModifyVO);
		
		return new ApiResponse(result);
	}
	
	/**
	 * 특정 게시글을 삭제하는 요청을 하는 컨트롤러.
	 * @param id
	 * @param memberVO
	 * @return
	 */
	@PostMapping("/board/delete/{id}")
	public ApiResponse viewBoardModifyPage(@PathVariable String id
										, Authentication memberVO){
		boolean result = boardService.doDeletePost(id);
		
		return new ApiResponse(result);
	}
	
	
	
	
	/**
	 * 게시글의 댓글을 불러오는 컨트롤러.
	 * @param BoardCommentVO
	 * @param memberVO
	 * @return
	 */
	@GetMapping("/board/comment/view/{boardId}")
	public ApiResponse doModifyComment(@PathVariable String boardId 
									, Authentication memberVO) {
		List<BoardCommentVO> result  = boardService.getAllBoardComment(boardId);
		
		return new ApiResponse(result);
	}
	
	/**
	 * 새로운 댓글을 추가하는 메서드.
	 * @param boardCommentWriteVO
	 * @param loginMemberVO
	 * @return
	 */
	@PostMapping("/board/view")
	public ApiResponse doCreateNewComment(@RequestBody BoardCommentWriteVO boardCommentWriteVO 
										, Authentication loginMemberVO) {
		boardCommentWriteVO.setAthrId(loginMemberVO.getName());
		boolean result = boardService.createBoardComment(boardCommentWriteVO);
		
		return new ApiResponse(result);
	}
	
	
	/**
	 * 특정 댓글을 수정하는 요청을 하는 컨트롤러.
	 * @param boardModifyCommentVO
	 * @param memberVO
	 * @return
	 */
	@PostMapping("/board/comment/modify")
	public ApiResponse doModifyComment(@RequestBody BoardModifyCommentVO boardModifyCommentVO
									, Authentication memberVO) {
		boolean result = boardService.modifyBoardComment(boardModifyCommentVO);
		
		return new ApiResponse(result);
	}
	
	/**
	 * 특정 댓글을 삭제하는 컨트롤러.
	 * @param id
	 * @param memberVO
	 * @return
	 */
	@PostMapping("/board/comment/delete/{id}")
	public ApiResponse doDeleteComment(@PathVariable String id
									, Authentication memberVO) {
		boolean result = boardService.fixDeleteState(id);

		return new ApiResponse(result);
	}
}
