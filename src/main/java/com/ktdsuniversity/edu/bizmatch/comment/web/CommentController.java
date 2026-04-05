package com.ktdsuniversity.edu.bizmatch.comment.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.bizmatch.comment.service.CommentService;
import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentModifyVO;
import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentVO;
import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentWriteVO;
import com.ktdsuniversity.edu.bizmatch.common.vo.ApiResponse;
import com.ktdsuniversity.edu.bizmatch.common.vo.PaginationVO;

/**
 * 통합 댓글에 관한 컨트롤러
 * /comment/list >> 댓글 리스트 주소
 * /comment/write >> 댓글 작성 주소
 * comment/commentlist >> comment 폴더의 댓글 리스트 commentlist.jsp 주소
 * comment/commentwrite >> comment 폴더의 댓글 작성 commentwrite.jsp
 * @author 한상범
 */
@RestController
public class CommentController {
	
	public static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * 댓글 리스트를 가져오는 컨트롤러.
	 * @param paginationVO
	 * @return
	 */
	@GetMapping("/comment/list")
	public ApiResponse viewCommnetListModal(PaginationVO paginationVO ){
		List<CommentVO> comments = commentService.getAllComment("0");
		
		return new ApiResponse(comments);
	}
	
	@PostMapping("/comment/list")
	public ApiResponse  writeNewComment(CommentWriteVO commentWriteVO 
									, Authentication memberVO) {
		// 1. 현재 보드 id 가져오기
		commentWriteVO.setPjId("0");
		// 2. 작성자 id 가져오기
		commentWriteVO.setAthrId(memberVO.getName());
		// 상위 댓글 아이디 가져오기>> 해결해야됨
		// 필수 입력 값 체크
		boolean result = commentService.createNewComment(commentWriteVO);
		
		return new ApiResponse(result);
	}
	
	/**
	 * 댓글을 삭제하는 컨트롤러.
	 * @param id
	 * @return
	 */
	@PostMapping("/comment/delete/{id}")
	public ApiResponse setDeleteSate(@PathVariable String id) {
		boolean result = commentService.updateDeleteState(id);
	
		return new ApiResponse(result);
	}
	
	/**
	 * 댓글을 수정을 하는 컨트롤러.
	 * @param id
	 * @return
	 */
	@PostMapping("/comment/modify/{id}")
	public ApiResponse modifyComment(@PathVariable String id) {
		CommentModifyVO commentModifyVO = new CommentModifyVO();
		commentModifyVO.setId(id);
		boolean result = commentService.modifyComment(commentModifyVO);
		
		return new ApiResponse(result);
	}
	
}
