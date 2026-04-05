package com.ktdsuniversity.edu.bizmatch.payment.service;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentHistoryVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentRequestVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentReturnVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentSearchVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentUpdateVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentVO;

public interface PaymentService {

	/**
	 * 계약금 결제를 요청하는 메서드.
	 * @param paymentRequestVO
	 * @return
	 */
	public boolean createDownPayment(PaymentRequestVO paymentRequestVO);
	
	/**
	 * 보증금 결제를 요청하는 메서드.
	 * @param depositPaymentRequestVO
	 * @return
	 */
	public boolean createDepositPay(PaymentRequestVO depositPaymentRequestVO);
	
	/**
	 * 프로젝트 진행 중 거래가 파기가 된 경우 메소드 실행.
	 * @param paymentUpdateVO : 업데이트하는 결제 정보를 담은 객체.
	 * @return : 업데이트 여부.
	 */
	public boolean updateDownPaymentWhenBrokenDeal(PaymentUpdateVO paymentUpdateVO);
	
	/**
	 * 결제 취소 요청을 하는 메소드.
	 * @param paymentRequestVO
	 * @return
	 */
	public boolean cancelRequest(PaymentRequestVO paymentRequestVO, int amount);
	
	/**
	 * 보증금 납부 여부를 조회하는 메서드.
	 * @param pjId
	 * @return
	 */
	public boolean readIsPaymentDeposit(String pjId);
	
	/**
	 * 모든 프로젝트 끝난 후 수주자 계좌에 돈을 넣어주는 메서드
	 * @param account ==> 돈을 넣어 줄 계좌
	 * @param amount ==> 계약금
	 */
	public boolean updatePaymentInfo(PaymentReturnVO paymentReturnVO);
	
	/**
	 * 한 회원이 발주한 모든 결제 정보를 조회하는 메소드.
	 * @param emailAddr
	 * @return
	 */
	public List<PaymentVO> readAllPaymentInfo(String emailAddr);
	
	/**
	 * 회원이 결제했던 내역을 보여주는 메소드
	 * @param paymentSearchVO
	 * @return
	 */
	public List<PaymentHistoryVO> readPaymentDetails(PaymentSearchVO paymentSearchVO);
}
