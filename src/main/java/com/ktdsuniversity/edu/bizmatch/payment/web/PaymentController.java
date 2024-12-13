package com.ktdsuniversity.edu.bizmatch.payment.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.bizmatch.common.vo.ApiResponse;
import com.ktdsuniversity.edu.bizmatch.payment.service.PaymentService;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentHistoryVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentRequestVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentSearchVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentVO;
import com.ktdsuniversity.edu.bizmatch.project.service.ProjectService;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectVO;

@RestController
@RequestMapping("/api")
public class PaymentController {
	
	public static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ProjectService projectService;

	/**
	 * 보증금 결제 정보를 가져오는 컨트롤러.
	 * @param pjId
	 * @param model
	 * @return
	 */
	@GetMapping("/bizmatch/payment/ask/deposit/{pjId}")
	public ApiResponse paymentDepositPage(@PathVariable String pjId) {
		ProjectVO projectVO = this.projectService.readOneProjectInfo(pjId);
		return new ApiResponse(projectVO);
	}
	
	/**
	 * 계약금 결제 정보를 가져오는 컨트롤러.
	 * @param pjId
	 * @param model
	 * @return
	 */
	@GetMapping("/bizmatch/payment/ask/downpayment/{pjId}")
	public ApiResponse paymentDownpaymentPage(@PathVariable String pjId) {
		ProjectVO projectVO = this.projectService.readOneProjectInfo(pjId);
		return new ApiResponse(projectVO);
	}
	
	/**
	 * 보증금 결제 요청을 하는 컨트롤러.
	 * @param depositPaymentRequestVO
	 * @return
	 */
	@PostMapping("/bizmatch/payment/ask/deposit")
	public ApiResponse doPaymentDeposit(PaymentRequestVO depositPaymentRequestVO) {
		
		// 결제 타입 0-> 보증금 결제.
		depositPaymentRequestVO.setPaymentType(0);
		depositPaymentRequestVO.toString();
		
		boolean isSuccess = this.paymentService.createDepositPay(depositPaymentRequestVO);
		return new ApiResponse(isSuccess);
	}
	
	/**
	 * 계약금 결제 요청을 하는 컨트롤러.
	 * @param paymentVO
	 * @return
	 */
	@PostMapping("/bizmatch/payment/ask/downpayment")
	public ApiResponse askDownPayment(PaymentRequestVO paymentRequestVO) {
		paymentRequestVO.toString();
		paymentRequestVO.setPaymentType(1);
		boolean isSuccess = this.paymentService.createDownPayment(paymentRequestVO);
		
		return new ApiResponse(isSuccess);
	}
	
	/**
	 * 결제 내역을 가지고 오는 컨트롤러.
	 * @param memberVO
	 * @return
	 */
	@GetMapping("/get/paymentlist")
	public ApiResponse getPaymentList(Authentication memberVO) {
		List<PaymentVO> paymentList = this.paymentService.readAllPaymentInfo(memberVO.getName());
		return new ApiResponse(paymentList);
	}

	/**
	 * 결제 내역 상세 정보를 가져오는 컨트롤러.
	 * @param memberVO
	 * @param paymentSearchVO
	 * @return
	 */
	@GetMapping("/payment/details")
	public ApiResponse getPaymentDetails(Authentication memberVO, PaymentSearchVO paymentSearchVO) {
		List<PaymentHistoryVO> paymentHistoryList = this.paymentService.readPaymentDetails(paymentSearchVO);
		return new ApiResponse(paymentHistoryList);
	}
	
}

