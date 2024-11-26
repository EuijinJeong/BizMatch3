package com.ktdsuniversity.edu.bizmatch.board.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.bizmatch.board.service.BoardService;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardCommentPaginationVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardCommentVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardCommentWriteVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardModifyCommentVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardModifyVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardPaginationVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardSearchVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardWriteVO;
import com.ktdsuniversity.edu.bizmatch.comment.web.CommentController;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.board.BoardApproachException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.board.BoardException;
import com.ktdsuniversity.edu.bizmatch.common.utils.ParameterCheck;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;


@Controller
public class BoardController {
	
	public static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	@Autowired
	private BoardService boardService;

	
	/**
	 * 게시글 목록 페이지
	 * @return
	 */
	@GetMapping("/board/list")
	public String viewBoardList(Model model, BoardPaginationVO boardPaginationVO, BoardSearchVO boardSearchVO, @SessionAttribute(value="_LOGIN_USER_", required =false) MemberVO loginMemberVO) {

		// 만약 로그인한 사람이 없다면,
//		if(loginMemberVO == null) {
//			return "main/mainpage";
//		}
		if(loginMemberVO==null) {
			throw new BoardApproachException("로그인");
		}
		if(loginMemberVO.getEmilAddr().equals("test@test")) {
			boardPaginationVO.setIsAdmin(1);
		}	
		else {
			boardPaginationVO.setIsAdmin(0);
		}
		
		
		int isAdmin =boardPaginationVO.getIsAdmin();
		boardSearchVO.setIsAdmin(isAdmin);
		boardSearchVO.setId(loginMemberVO.getEmilAddr());
		boardPaginationVO.setSearchIdParam(loginMemberVO.getEmilAddr());
		List<BoardVO> paginationBoardList = boardService.getForPagination(boardPaginationVO,boardSearchVO);
		List<BoardVO> boardList=boardService.getBoardList(boardSearchVO);
		boardPaginationVO.setPageCount(boardList.size());

	
		
		int boardCount = boardService.getBoardCount();
		logger.debug("이메일"+loginMemberVO.getEmilAddr());
		model.addAttribute("boardList", boardList);
		model.addAttribute("boardCount", boardCount);
		model.addAttribute("paginationBoardList",paginationBoardList);
		model.addAttribute("paginationVO",boardPaginationVO);
		model.addAttribute("size", paginationBoardList.size());
		return "board/boardlist";
	}
	
	/**
	 * 게시글 조회 페이지
	 * 
	 * @return
	 */
	@GetMapping("/board/view/{id}")
	public String viewOneBoard(Model model
								, @PathVariable String id
								, BoardCommentPaginationVO boardCommentPaginationVO
								, @SessionAttribute(value="_LOGIN_USER_", required =false) MemberVO loginMemberVO
								) {
		
		boardService.doIncreaseViews(id);
		
		BoardVO boardVO = boardService.getOneBoard(id);
		MemberVO loginInfo =loginMemberVO;
		
		int size = boardService.getAllBoardComment(id).size();
		boardCommentPaginationVO.setPageCount(size);
		boardCommentPaginationVO.setSearchIdParam(id);
		List<BoardCommentVO> comments = boardService.getPaginationComment(boardCommentPaginationVO, id);
		
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("loginInfo", loginInfo);
		model.addAttribute("comments",comments);
		model.addAttribute("paginationVO",boardCommentPaginationVO);
		return "board/boardview";
	}
	
	@ResponseBody
	@PostMapping("/board/view")
	public Map<String, Object> doCreateNewComment( BoardCommentWriteVO boardCommentWriteVO , Model model,@SessionAttribute(value="_LOGIN_USER_", required =false) MemberVO loginMemberVO) {
		
		boardCommentWriteVO.setAthrId(loginMemberVO.getEmilAddr());


		boolean result = boardService.createBoardComment(boardCommentWriteVO);
		
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}
	
	/**
	 * 게시글 작성페이지
	 * 
	 * @return
	 */
	@GetMapping("/board/write")
	public String viewBoardWritePage(@SessionAttribute(value="_LOGIN_USER_", required =false) MemberVO loginMemberVO, Model model) {
		model.addAttribute("loginMemberVO",loginMemberVO);
		return "board/boardwrite";
	}

	@ResponseBody
	@PostMapping("/board/write")
	public Map<String, Object> doCreateNewBoard(BoardWriteVO boardWirteVO 
											, Model model
											, @SessionAttribute(value="_LOGIN_USER_", required =false) MemberVO loginMemberVO) {
		
		boardWirteVO.setAthrId(loginMemberVO.getEmilAddr());
		if(ParameterCheck.parameterCodeValid(boardWirteVO.getPstNm(), 0)) {
			throw new BoardException("제목은 필수 입력입니다.", boardWirteVO);
		}
		if(ParameterCheck.parameterCodeValid(boardWirteVO.getPstCntnt(), 0)) {
			throw new BoardException("내용을 입력해주세요", boardWirteVO);
		}
		boolean result = boardService.createNewPost(boardWirteVO);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}
	
	/**
	 * 게시글 수정 페이지
	 * 
	 * @return
	 */
	@GetMapping("/board/modify/{id}")
	public String viewBoardModifyPage(@PathVariable String id , Model model, @SessionAttribute(value="_LOGIN_USER_", required =false) MemberVO loginMemberVO){
		
		BoardVO boardVO = boardService.getOneBoard(id);
		model.addAttribute("loginMemberVO",loginMemberVO);
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("boardId", id);
		return "board/boardmodify";
	}
	
	@ResponseBody
	@PostMapping("/board/modify")
	public Map<String, Object> doModify(BoardModifyVO boardModifyVO) {
		
		boolean result = boardService.doModifyPost(boardModifyVO);
		
		boardService.doModifyPost(boardModifyVO);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}
	@ResponseBody
	@PostMapping("/board/delete/{id}")
	public Map<String, Object> viewBoardModifyPage(@PathVariable String id){
		boolean result = boardService.doDeletePost(id);
	
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}
	
	@ResponseBody
	@PostMapping("/board/comment/modify")
	public Map<String, Object> doModifyComment(BoardModifyCommentVO boardModifyCommentVO) {
		boolean result = boardService.modifyBoardComment(boardModifyCommentVO);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}
	
	@ResponseBody
	@PostMapping("/board/comment/delete/{id}")
	public Map<String, Object> doDeleteComment(@PathVariable String id) {
		boolean result = boardService.fixDeleteState(id);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}
	
}
