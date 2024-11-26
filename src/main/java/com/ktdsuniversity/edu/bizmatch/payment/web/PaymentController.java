package com.ktdsuniversity.edu.bizmatch.payment.web;

import java.util.HashMap;
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

import com.ktdsuniversity.edu.bizmatch.payment.service.PaymentService;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentRequestVO;
import com.ktdsuniversity.edu.bizmatch.project.service.ProjectService;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectVO;

@Controller

public class PaymentController {
	
	public static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ProjectService projectService;
	
	@GetMapping("/bizmatch/payment/ask/downpayment/error/500")
	public String viewDownPaymentErrorPage() {
		return "/error/payment_error";
	}
	/**
	 * 계약금 결제 페이지 가져오기.
	 * @param pjId
	 * @param model
	 * @return
	 */
	@GetMapping("/bizmatch/payment/ask/downpayment/{pjId}")
	public String paymentDownpaymentPage(@PathVariable String pjId, Model model) {
		ProjectVO projectVO = this.projectService.readOneProjectInfo(pjId);
		model.addAttribute("projectVO",projectVO);
		return "payment/payment_page_downpayment";
	}
	
	/**
	 * 보증금 결제 페이지 가져오기.
	 * @param pjId
	 * @param model
	 * @return
	 */
	@GetMapping("/bizmatch/payment/ask/deposit/{pjId}")
	public String paymentDepositPage(@PathVariable String pjId
									, Model model) {
		// 프로젝트 정보 가져와야함.
		ProjectVO projectVO = this.projectService.readOneProjectInfo(pjId);
		
		// 만약 보증금을 이미 납부했다면 지원기업 리스트 페이지로 리다이렉트
		if(projectVO.getPaymentVO().getGrntPdDt()!= null) {
			model.addAttribute("projectVO", projectVO);
			return "redirect:/project/apply/member/"+projectVO.getPjId();
		}
		// 모델에 담아줘서 반환함.
		model.addAttribute("projectVO", projectVO);
		return "/payment/payment_page_deposit";
	}
	
	/**
	 * 계약금 결제 요청을 하는 컨트롤러.
	 * @param paymentVO
	 * @return
	 */
	@PostMapping("/bizmatch/payment/ask/downpayment")
	@ResponseBody
	public Map<String, Object> askDownPayment(PaymentRequestVO paymentRequestVO) {
		// 결제 종류를 1- 계약금 으로 설정한다.
		paymentRequestVO.toString();
		paymentRequestVO.setPaymentType(1);
		boolean isSuccess = this.paymentService.createDownPayment(paymentRequestVO);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess);
		// TODO: 결제 완료 페이지로 옮기는걸로 수정해야함 -의진-
		resultMap.put("nextUrl", "/");
		
		return resultMap;
	}
	
	/**
	 * 보증금 결제 요청을 하는 컨트롤러.
	 * @param depositPaymentRequestVO
	 * @return
	 */
	@PostMapping("/bizmatch/payment/ask/deposit")
	@ResponseBody
	public Map<String, Object> doPaymentDeposit(PaymentRequestVO depositPaymentRequestVO) {
		
		// 결제 타입 0-> 보증금 결제.
		depositPaymentRequestVO.setPaymentType(0);
		depositPaymentRequestVO.toString();
		
		boolean isSuccess = this.paymentService.createDepositPay(depositPaymentRequestVO);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess);
		resultMap.put("nextUrl", "/project/apply/member/" + depositPaymentRequestVO.getPjId());
		
		return resultMap;
	}
	
	/**
	 * 결제 오류 페이지를 보여주는 컨트롤러.
	 * @return
	 */
	@GetMapping("/bizmatch/payment/ask/deposit/error/500")
	public String viewPaymentErrorPage() {
		return "/error/payment_error";
	}
	
	/**
	 * 사용자가 단숨 변심으로 결제 취소를 하면 그냥 메인페이지 보여줘야함.
	 * @return 메인페이지 Url
	 */
	@GetMapping("/bizmatch/payment/usercancel/")
	public String viewMainPage() {
		return "redirect:/";
	}
}
