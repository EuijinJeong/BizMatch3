package com.ktdsuniversity.edu.bizmatch.common.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.bizmatch.common.exceptions.board.BoardApproachException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.board.BoardException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.comment.ReviewFailException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.comment.ReviewReportFailException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.common.IndustryException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.file.CreateNewProjectFileException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.file.FileUploadFailedException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.member.LoginFailException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.member.MemberNotFoundException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.member.MemberPortfolioException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.member.ResetPassword;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.member.SessionNotFoundException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.member.SignUpCompanyException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.member.SignUpFailException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.member.SignUpNotApprovedException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.payment.CreateDownPaymentException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.payment.PaymentException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.payment.PaymentServerSaveException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.project.ProjectApplyFailException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.project.ProjectDeleteException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.project.ProjectNotFoundException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.project.ProjectScrapException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.project.ProjectWriteFailException;
import com.ktdsuniversity.edu.bizmatch.common.vo.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    
	/**
	 * 로그인이 안된 상태로 게시판에 접근하면 메세지를 전달해줌.
	 * @param be
	 * @return
	 */
    @ExceptionHandler(BoardException.class)
    @ResponseBody
    public ApiResponse handleBoardException(BoardException be) {
    	return new ApiResponse(HttpStatus.BAD_REQUEST, be);
    }
    
    /**
     * 접근 권한이 없는 게시글에 접근을 할 때 발생해는 예외를 처리해준다.
     * @param bae
     * @param model
     * @return
     */
    @ExceptionHandler(BoardApproachException.class)
    @ResponseBody
    public ApiResponse handleBoardApproachException(BoardApproachException bae) {
    	return new ApiResponse(HttpStatus.BAD_REQUEST, bae.getMessage());
    }
    
    @ExceptionHandler(MemberPortfolioException.class)
    @ResponseBody
    public ApiResponse handleMemberPortfolioException(MemberPortfolioException mpe) {
    	return new ApiResponse(HttpStatus.BAD_REQUEST, mpe);
    }
    
    @ExceptionHandler(ResetPassword.class)
    @ResponseBody
    public ApiResponse handleResetPassword(ResetPassword rp) {
        return new ApiResponse(HttpStatus.BAD_REQUEST, rp);
    }

    /**
     * 회원가입 실패 예외처리.
     * @param sufe
     * @param model
     * @return
     */
    @ExceptionHandler(SignUpFailException.class)
    @ResponseBody
    public ApiResponse handleSignUpFailException(SignUpFailException sufe) {
    	return new ApiResponse(HttpStatus.BAD_REQUEST, sufe.getMessage());
    }
    
    /**
     * 기업형 회원가입 예외처리.
     * @param suce
     * @param model
     * @return
     */
    @ExceptionHandler(SignUpCompanyException.class)
    @ResponseBody
    public ApiResponse handleSignUpCompanyException(SignUpCompanyException suce) {
    	return new ApiResponse(HttpStatus.BAD_REQUEST, suce.getMessage());
    }
    
    /**
     * 로그인 실패시 발생하는 예외를 처리해주는 핸들러.
     * @param lfe
     * @return
     */
    @ExceptionHandler(LoginFailException.class)
    @ResponseBody
    public ApiResponse handleLoginFailException(LoginFailException lfe){
    	return new ApiResponse(HttpStatus.BAD_REQUEST, lfe);
    }
    
    /**
     * 리뷰 등록 실패 예외처리.
     * @param rfe
     * @return
     */
    @ExceptionHandler(ReviewFailException.class)
    @ResponseBody
    public ApiResponse handleReviewFailException(ReviewFailException rfe) {
        return new ApiResponse(HttpStatus.BAD_REQUEST, rfe);
    }
    
    /**
     * 리뷰 신고 실패 예외처리.
     * @param rrfe
     * @return
     */
    @ExceptionHandler(ReviewReportFailException.class)
    @ResponseBody
    public ApiResponse handleReviewReportFailException(ReviewReportFailException rrfe) {
        return new ApiResponse(HttpStatus.BAD_REQUEST, rrfe);
    }
    
    /**
     * 회원가입 승낙 아직 안되어있을 때 발생하는 예외처리.
     * @param snae
     * @param model
     * @return
     */
    @ExceptionHandler(SignUpNotApprovedException.class)
    @ResponseBody
    public ApiResponse handleSignUpNotApprovedException(SignUpNotApprovedException snae) {
    	return new ApiResponse(HttpStatus.UNAUTHORIZED, snae);
    }
    
    /**
     * 파일 업로드 실패 요청 예외처리.
     * @param fufe
     * @return
     */
    @ExceptionHandler(FileUploadFailedException.class)
    @ResponseBody
    public ApiResponse handleFileUploadFailedException(FileUploadFailedException fufe) {
    	return new ApiResponse(HttpStatus.BAD_REQUEST, fufe);
    }
    
    /**
     * 프로젝트 등록 중 파일 관련해서 오류가 발생했을 때 처리하는 핸들러.
     * @param cnpfe
     * @param model
     * @return : 작성하고 있던 페이지에 정보를 담아서 돌려줌.
     */
    @ExceptionHandler(CreateNewProjectFileException.class)
    @ResponseBody
    public ApiResponse handleProjectFailException(CreateNewProjectFileException cnpfe) {
    	return new ApiResponse(HttpStatus.BAD_REQUEST, cnpfe);
    }
    
    /**
     * 프로젝트 지원서를 작성 중 에러 발생시 해결하는 핸들러.
     * @param pafe
     * @param applyProjectVO
     * @param model
     * @return : 원래 작성하고 있던 페이지로 돌려줌.
     */
    @ExceptionHandler(ProjectApplyFailException.class)
    @ResponseBody
    public ApiResponse handleProjectApplyFailException(ProjectApplyFailException pafe) {
    	return new ApiResponse(HttpStatus.BAD_REQUEST, pafe);
    }
    
    /**
     * 프로젝트 등록 중 예외가 발생했을 때 처리하는 핸들러.
     * @param pwfe
     * @param writeProjectVO
     * @param model
     * @return : redirect.
     */
    @ExceptionHandler(ProjectWriteFailException.class)
    @ResponseBody
    public ApiResponse handleProjectWriteFailException(ProjectWriteFailException pwfe) {
    	return new ApiResponse(HttpStatus.BAD_REQUEST, pwfe);
    }
    
    /**
     * 프로젝트 삭제 중 발생하는 예외를 처리하는 핸들러.
     * @param pde
     * @param pjId
     * @param model
     * @return
     */
    @ExceptionHandler(ProjectDeleteException.class)
    @ResponseBody
    public ApiResponse handleProjectDeleteException(ProjectDeleteException pde) {
    	return new ApiResponse(HttpStatus.BAD_REQUEST, pde);
    }
    
    /**
     * 프로젝트 스크랩 중 발생하는 예외를 처리하는 핸들러.
     * @param pse
     * @param projectScrapVO
     * @param model
     * @return
     */
    @ExceptionHandler(ProjectScrapException.class)
    @ResponseBody
    public ApiResponse handleProjectScrapException(ProjectScrapException pse) {
    	return new ApiResponse(HttpStatus.BAD_REQUEST, pse);
    }
    
    /**
     * 결제 요청을 성공적으로 끝냈지만 서버에 결제 정보를 저장하는 도중,
     * 오류가 발생하면 예외를 처리해주는 핸들러이다.
     * @param psse
     * @return
     */
    @ExceptionHandler(PaymentServerSaveException.class)
    @ResponseBody
    public ApiResponse handlePaymentServerSaveException(PaymentServerSaveException psse) {
    	return new ApiResponse(HttpStatus.BAD_REQUEST, psse);
    }
    
    /**
     * 계약금 결제시 발생하는 예외들을 처리해주는 핸들러이다.
     * @author jeong-uijin
     * @param cdpe
     * @return : 결제 예외페이지를 돌려준다.
     */
    @ExceptionHandler(CreateDownPaymentException.class)
    @ResponseBody
    public ApiResponse handleCreateDownPaymentException(CreateDownPaymentException cdpe) {
    	return new ApiResponse(HttpStatus.BAD_REQUEST, cdpe);
    }
    
    /**
     * 세션 만료되거나 없을 때 메인페이지로 이동
     * @param snfe
     * @return
     */
    @ExceptionHandler(SessionNotFoundException.class)
    @ResponseBody
    public ApiResponse SessionNotFoundException(SessionNotFoundException snfe) {
    	return new ApiResponse(HttpStatus.FORBIDDEN, snfe);
    }
    
    /**
     * 결제 관련 오류들을 처리하는 핸들러이다.
     * @author jeong-uijin
     * @param pe
     * @return
     */
    @ExceptionHandler(PaymentException.class)
    @ResponseBody
    public ApiResponse handlePaymentException(PaymentException pe) {
    	return new ApiResponse(HttpStatus.BAD_REQUEST, pe);
    }
    
    /**
     * 산업군 관련한 예외처리.
     * @param ie
     * @return
     */
    @ExceptionHandler(IndustryException.class)
    @ResponseBody
    public ApiResponse handleIndustryException(IndustryException ie) {
    	return new ApiResponse(HttpStatus.BAD_REQUEST, ie);
    }
    
    /**
     * 특정 회원을 찾을 수 없을 때 발생하는 예외를 처리.
     * @param infe
     * @return
     */
    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseBody
    public ApiResponse handleMemberNotFoundException(MemberNotFoundException infe) {
    	return new ApiResponse(HttpStatus.BAD_REQUEST, infe);
    }
    
    @ExceptionHandler(ProjectNotFoundException.class)
    @ResponseBody
    public ApiResponse handleProjectNotFoundException(ProjectNotFoundException pnfe) {
    	return new ApiResponse(HttpStatus.BAD_REQUEST, pnfe);
    }
}
