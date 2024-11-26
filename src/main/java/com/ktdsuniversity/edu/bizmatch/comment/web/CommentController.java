package com.ktdsuniversity.edu.bizmatch.comment.web;

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

import com.ktdsuniversity.edu.bizmatch.comment.service.CommentService;
import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentModifyVO;
import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentVO;
import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentWriteVO;
import com.ktdsuniversity.edu.bizmatch.common.vo.PaginationVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;





/**
 * 통합 댓글에 관한 컨트롤러
 * /comment/list >> 댓글 리스트 주소
 * /comment/write >> 댓글 작성 주소
 * comment/commentlist >> comment 폴더의 댓글 리스트 commentlist.jsp 주소
 * comment/commentwrite >> comment 폴더의 댓글 작성 commentwrite.jsp
 * @author 한상범
 */
@Controller
public class CommentController {
	
	public static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/comment/list")
	public String viewCommnetListModal(Model model
										//, @RequestParam int id
										,PaginationVO paginationVO ){
		List<CommentVO> comments = commentService.getAllComment("0");
		List<CommentVO> pagenationComment = commentService.getComment(paginationVO, "0");
		paginationVO.setPageCount(comments.size());
		
		logger.debug("커맨트 사이즈"+comments.size());
		logger.debug("페이지네이션 사이즈" + paginationVO.getPageCount());
		//
		
		model.addAttribute("comments",pagenationComment);
		
		model.addAttribute("paginationVO",paginationVO);
		return "project/project_inquiry_page";
	}
	

	@ResponseBody
	@PostMapping("/comment/list")
	public Map<String, Object>  writeNewComment( CommentWriteVO commentWriteVO 
								 , Model model 
								 //, @PathVariable String id
								 , @SessionAttribute(value="_LOGIN_USER_", required =false) MemberVO loginMemberVO) {
		// 1. 현재 보드 id 가져오기
		//commentWriteVO.setPjId(id);
		commentWriteVO.setPjId("0");
		// 2. 작성자 id 가져오기
		commentWriteVO.setAthrId(loginMemberVO.getEmilAddr());
		// 상위 댓글 아이디 가져오기>> 해결해야됨
		// 필수 입력 값 체크
		boolean result = commentService.createNewComment(commentWriteVO);
		
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}
	
	// 페이지네이션 빈
	// 삭제 기능
	@GetMapping("/comment/delete/{id}")
	@ResponseBody
	public Map<String, Object> setDeleteSate(@PathVariable String id) {
		boolean result = commentService.updateDeleteState(id);
	
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		
		return resultMap;
	}
	
	
	// 수정 기능
	@PostMapping("/comment/modify/{id}")
	@ResponseBody
	public Map<String, Object> modifyComment(@PathVariable String id , CommentModifyVO commentModifyVO) {
		
		commentModifyVO.setId(id);
		
		boolean result = commentService.modifyComment(commentModifyVO);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}
	
}
