package com.ktdsuniversity.edu.bizmatch.payment.dao;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.AccntVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentHstryVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentRequestVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentReturnVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.RefundDepositVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.WriteProjectVO;

public interface PaymentDao {
	
	public String NAMESPACE = "com.ktdsuniversity.edu.bizmatch.payment.dao.PaymentDao";
	
	/**
	 * 새로운 결제정보를 추가하는 메서드.
	 * @param paymentVO
	 * @return
	 */
	public int insertNewPaymentInfo(PaymentRequestVO paymentRequestVO);
	
	/**
	 * 프로젝트를 등록할 때 새로운 결제정보를 등록하는 쿼리문을 호출하는 메서드.
	 * @param writeProjectVO
	 * @return
	 */
	public int isnertNewPaymentInfo(WriteProjectVO writeProjectVO);

	/**
	 * 프로젝트의 금액을 조회하는 메소드.
	 * @param pjId : 프로젝트 아이디.
	 * @return : 조회한 컬럼의 개수.
	 */
	public int selectProjectAmount(String pjId);
	
	/**
	 * BizMatch 통장 잔고를 변경하는 쿼리문을 수행하는 메서드.
	 * @param amount : 변경할 금액.
	 * @return : 업데이트된 컬럼의 개수.
	 */
	public int updateAccountBalance(int amount);
	
	/**
	 * 보증급 환급 해주는 메소드
	 * @param 
	 * @return
	 */
	public int updateDeposit(RefundDepositVO RefunddepositVO);
	
	/**
	 * 보증금 결제 시 결제 현황을 업데이트
	 * @return
	 */
	public int updatePayDeposit(PaymentRequestVO paymentRequestVO);
	
	/**
	 * 보증금 결제 정보를 가져오는 메소드 
	 * @param pjId 프로젝트 아이디를 통해 해당 프로젝트에 대한 결제 정보를 가져온다.
	 * @return
	 */
	public PaymentVO selectOneDeposit(String pjId);
	
	/**
	 * 프로젝트에 해당하는 결제 정보를 조회하는 쿼리문을 호출하는 메서드.
	 * @param pjId
	 * @return
	 */
	public List<PaymentVO> selectOnePaymentInfo(String pjId);
	
	/**
	 * 프로젝트 완료 후 수주자의 계좌에 돈을 넣어주는 메소드
	 * @param paymentReturnVO
	 * @return
	 */
	public int updatePaymentInfoBiz(PaymentReturnVO paymentReturnVO);
	
	/**
	 * 우리 계좌 정보 가져오는 메소드
	 * @return
	 */
	public AccntVO selectAccountInfo();
	
	/**
	 * 결제내역 남기는 메서드
	 * @param paymentHstryVO
	 * @return
	 */
	public int insertAccntHstry(PaymentHstryVO paymentHstryVO);
	
	/**
	 * 내가 결제했던 결제 내역을 조회하는 쿼리문을 호출하는 메서드.
	 * @param memberVO
	 * @return
	 */
	public List<PaymentVO>selectAllPaymentList(MemberVO memberVO);
}
