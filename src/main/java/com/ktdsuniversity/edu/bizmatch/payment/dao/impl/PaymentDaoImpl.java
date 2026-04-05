package com.ktdsuniversity.edu.bizmatch.payment.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;
import com.ktdsuniversity.edu.bizmatch.payment.dao.PaymentDao;
import com.ktdsuniversity.edu.bizmatch.payment.vo.AccntVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentHistoryVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentHstryVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentRequestVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentReturnVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentSearchVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.RefundDepositVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.WriteProjectVO;

@Repository
public class PaymentDaoImpl extends SqlSessionDaoSupport implements PaymentDao{
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int insertNewPaymentInfo(PaymentRequestVO downPaymentRequestVO) {
		return getSqlSession().insert(NAMESPACE + ".updateDownPaymentInfo", downPaymentRequestVO);
	}
	@Override
	public int selectProjectAmount(String pjId) {
		return getSqlSession().selectOne(NAMESPACE + ".selectProjectAmount", pjId);
	}
	@Override
	public int updateAccountBalance(int amount) {
		return getSqlSession().update(NAMESPACE + ".updateAccountBalance", amount);
	}

	@Override
	public int updateDeposit(RefundDepositVO refundDepositVO) {
		return this.getSqlSession().update(NAMESPACE+".updateDeposit", refundDepositVO);
	}

	@Override
	public PaymentVO selectOneDeposit(String pjId) {
		return this.getSqlSession().selectOne(NAMESPACE+".selectOneDeposit",pjId);
	}

	@Override
	public int isnertNewPaymentInfo(WriteProjectVO writeProjectVO) {
		return this.getSqlSession().insert(NAMESPACE + ".insertNewPaymentInfoWhenInsertProject", writeProjectVO);
	}

	@Override
	public int updatePayDeposit(PaymentRequestVO paymentRequestVO) {
		return this.getSqlSession().update(NAMESPACE+".updateDepositPaymentInfo", paymentRequestVO);
	}
	
	@Override
	public List<PaymentVO> selectOnePaymentInfo(String pjId) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectOnePaymentInfo", pjId);
	}

	@Override
	public int updatePaymentInfoBiz(PaymentReturnVO paymentReturnVO) {
		return this.getSqlSession().update(NAMESPACE+".updatePaymentInfoBiz", paymentReturnVO);
	}

	@Override
	public AccntVO selectAccountInfo() {
		return this.getSqlSession().selectOne(NAMESPACE+".selectAccountInfo");
	}

	@Override
	public int insertAccntHstry(PaymentHstryVO paymentHstryVO) {
		return this.getSqlSession().insert(NAMESPACE+".insertAccntHstry",paymentHstryVO);
	}

	@Override
	public List<PaymentVO> selectAllPaymentList(MemberVO memberVO) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectAllPaymentList", memberVO);
	}

	@Override
	public List<PaymentHistoryVO> selectPaymentDetailsList(PaymentSearchVO paymentSearchVO) {
		return this.getSqlSession().selectList(NAMESPACE+".selectPaymentDetailsList", paymentSearchVO);
	}
}
