package com.ktdsuniversity.edu.bizmatch.payment.service.impl;



import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.ktdsuniversity.edu.bizmatch.common.exceptions.payment.CreateDownPaymentException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.payment.PaymentException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.payment.PaymentServerSaveException;
import com.ktdsuniversity.edu.bizmatch.payment.dao.PaymentDao;
import com.ktdsuniversity.edu.bizmatch.payment.service.PaymentService;
import com.ktdsuniversity.edu.bizmatch.payment.vo.AccntVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentRequestVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentReturnVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentUpdateVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentVO;
import com.ktdsuniversity.edu.bizmatch.project.dao.ProjectDao;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.UpdateProjectSttVO;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Value("${app.iamport.apiKey}")
	private String apiKey;
	
	@Value("${app.iamport.apiSecret}")
	private String apiSecret;
	
	private String tokenUrl = "https://api.iamport.kr/users/getToken";
	
	private String cancelUrl = "https://api.iamport.kr/payments/cancel";
	
	public static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
	
	private final RestTemplate restTemplate;
	
	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	public PaymentServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Transactional
	@Override
	public boolean createDownPayment(PaymentRequestVO downPaymentRequestVO) {
		// 프로젝트 정보를 조회한다.
		ProjectVO projectVO = this.projectDao.selectOneProjectInfo(downPaymentRequestVO.getPjId());
//		// 프로젝트 금액과 결제 금액이 동일한지 검사한다.
//		if(downPaymentRequestVO.getCntrctAmt() != this.paymentDao.selectProjectAmount(downPaymentRequestVO.getPjId())) {
//			boolean isCanceled = this.cancelRequest(downPaymentRequestVO, downPaymentRequestVO.getGrntAmt());
//			if(!isCanceled) {
//				throw new CreateDownPaymentException("결제를 취소하는 중 오류가 발생했습니다.");
//			}
//		}
//		
//		// 이메일이랑 결제자 이메일이랑 같은지
//		if(downPaymentRequestVO.getEmilAddr() != projectVO.getOrdrId()) {
//			boolean isCanceled = this.cancelRequest(downPaymentRequestVO, downPaymentRequestVO.getCntrctAmt());
//			if(!isCanceled) {
//				throw new CreateDownPaymentException("결제를 취소하는 중 오류가 발생했습니다.");
//			}
//		}
		
		UpdateProjectSttVO updateProjectSttVO = new UpdateProjectSttVO();
		updateProjectSttVO.setPjId(downPaymentRequestVO.getPjId());
		// 결제가 완료되면 프로젝트 상태를 변경한다.
		updateProjectSttVO.setPjStt(1);
		
		int updateCnt = this.projectDao.updateOneProjectStt(updateProjectSttVO);
		// 만약 데이터베이스에 프로젝트 상태를 변경하다가 오류가 발생하면 결제 취소를 해야한다.
		if(updateCnt == 0) {
			boolean isCanceled =this.cancelRequest(downPaymentRequestVO, downPaymentRequestVO.getCntrctAmt());
			if(!isCanceled) {
				throw new CreateDownPaymentException("결제를 취소하는 중 오류가 발생했습니다.");
			}
		}
		
		// 결제 금액과 결제 요청금액이 일치하지 않는 경우.
//		if(projectVO.getCntrctAccnt() != downPaymentRequestVO.getCntrctAmt()) {
//			// 결제 취소를 요청해야함.
//			boolean isCanceled = this.cancelRequest(downPaymentRequestVO, 0);
//			if(!isCanceled) {
//				throw new PaymentException("결제 취소 요청중 오류가 발생했습니다.");
//			}
//		}
		
		// 결제 정보를 데이터베이스에 저장한다.
		int insertCnt = this.paymentDao.insertNewPaymentInfo(downPaymentRequestVO);
		if(insertCnt == 0) {
			this.cancelRequest(downPaymentRequestVO, downPaymentRequestVO.getCntrctAmt());
			throw new PaymentServerSaveException("결제정보를 서버에 입력하는 중 오류가 발생했습니다.");
		}
		
		// BIZMATCH 계좌에 돈을 업데이트
		if(this.paymentDao.updateAccountBalance(downPaymentRequestVO.getCntrctAmt())<=0) {
			throw new PaymentServerSaveException("결제 에러");
		}
		
		// 프로젝트 상태를 완료로 업데이트.
		return true;
	}

	@Transactional
	@Override
	public boolean createDepositPay(PaymentRequestVO depositPaymentRequestVO) {
		ProjectVO projectVO = this.projectDao.selectProjectInfo(depositPaymentRequestVO.getPjId());
		UpdateProjectSttVO updateProjectSttVO = new UpdateProjectSttVO();
		updateProjectSttVO.setPjId(projectVO.getPjId());
		
		// 프로젝트 상태가 인원 모집중이 완료 되었는데, 보증금 결제를 할려고 하는 경우.
		// 0 - 인원모집중
		// 3- 추가모집중.
//		if(!(projectVO.getPjStt() == 0 || projectVO.getPjStt() == 3)) {
//			boolean isCanceled = this.cancelRequest(depositPaymentRequestVO, deposit);
//			if(!isCanceled) {
//				throw new PaymentException("결제 취소 요청중 오류가 발생했습니다.");
//			}
//		}
//
//		// 보증금 결제자와 프로젝트 발주자의 정보가 다름 오류 발생
//		if(!projectVO.getOrdrId().equals(depositPaymentRequestVO.getEmilAddr())){
//			boolean isCanceled = this.cancelRequest(depositPaymentRequestVO, deposit);
//			if(!isCanceled) {
//				throw new PaymentException("결제 취소 요청중 오류가 발생했습니다.");
//			}
//		}
//		
//		// 보증금 요청 금액과 실제 보증금 금액이 다른 경우.
//		if(deposit != depositPaymentRequestVO.getCntrctAmt()) {
//			boolean isCanceled = this.cancelRequest(depositPaymentRequestVO, deposit);
//			if(!isCanceled) {
//				throw new PaymentException("결제 취소 요청중 오류가 발생했습니다.");
//			}
//		}
		
		// 프로젝트 상태 변경이 서버에서 실패한경우.
		if(this.projectDao.updateOneProjectStt(updateProjectSttVO) == 0) {
			boolean isCanceled = this.cancelRequest(depositPaymentRequestVO, 0);
			if(!isCanceled) {
				throw new PaymentException("결제 취소 요청중 오류가 발생했습니다.");
			}
		}
		
		// 결제 정보를 데이터베이스에 저장한다.
		int insertedCnt = this.paymentDao.updatePayDeposit(depositPaymentRequestVO);
		if(insertedCnt == 0) {
			// 결제 정보를 데이터베이스에 저장하는 중 오류가 발생하면 결제 취소를 해야함.
			boolean isCanceled = this.cancelRequest(depositPaymentRequestVO, 0);
			if(!isCanceled) {
				throw new PaymentException("결제 취소 요청중 오류가 발생했습니다.");
			}
		}
		
		// BIZMATCH 계좌에 돈을 업데이트
		if(this.paymentDao.updateAccountBalance(depositPaymentRequestVO.getCntrctAmt())<=0) {
			throw new PaymentServerSaveException("결제 에러");
		}
		return true;
	}
	
	@Transactional
	@Override
	public boolean updateDownPaymentWhenBrokenDeal(PaymentUpdateVO paymentUpdateVO) {
		// 프로젝트 진행 중 거래가 파기된 경우 아래에 작성.
		// 수수료 제외하고 돌려줘야함.
		int amount = this.calculateDepositPayment(paymentUpdateVO.getCntrctAmt());
		
		// 우리 계좌에서 저 만큼의 돈을 빼야함.
		int updateCnt = this.paymentDao.updateAccountBalance(amount);
		
		if(updateCnt == 0) {
			throw new PaymentException("해당 계좌에 돈이 인출되지 않았습니다.");
		}
		
		PaymentRequestVO paymentRequestVO = new PaymentRequestVO();
		paymentRequestVO.setCntrctAmt(paymentUpdateVO.getCntrctAmt());
		paymentRequestVO.setPjId(paymentUpdateVO.getPjId());
		paymentRequestVO.setPymntId(paymentUpdateVO.getPymntId());
		
		// 아임포트 결제 취소 요청.
		boolean isCanceled = this.cancelRequest(paymentRequestVO, amount);
		if(!isCanceled) {
			throw new PaymentException("카드 가맹사에 결제를 취소하는 요청 중 오류가 발생했습니다.");
		}
		
		return updateCnt > 0;
	}
	
	/**
	 * 아임포트 서버에 결제 취소 요청을 하는 메서드이다.
	 */
	@Override
	public boolean cancelRequest(PaymentRequestVO paymentRequestVO, int amount) {
		Map<String, Object> request = new HashMap<>();
		request.put("imp_uid", paymentRequestVO.getImpUid());
		request.put("amount", amount);
		
		// RequestEntity를 사용하여 헤더를 설정
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", (String) getToken().get("access_token"));
		headers.setContentType(MediaType.APPLICATION_JSON); // JSON 형식으로 전송
		
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
		
		// 요청 URL
		String url = cancelUrl;
		
		// 다른 서버로 요청을 보낸다.
		ResponseEntity<Map> response = this.restTemplate.postForEntity(url, entity, Map.class);
		
		logger.debug(response.getBody().toString());
		
		return response.getStatusCode() == HttpStatus.OK; // 성공 여부 체크
	}
	
	/**
	 * 아임포트 서버에서 토큰을 발급받는 메서드.
	 * @return
	 */
	public Map getToken() {
		Map<String, Object> request = new HashMap<>();
		request.put("imp_key", apiKey);
		request.put("imp_secret", apiSecret);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
		
		ResponseEntity<Map> response = this.restTemplate.postForEntity(tokenUrl, entity, Map.class);
		System.out.println("토큰 결과" + response);
		logger.debug(response.toString());
				
		return (Map)(response.getBody().get("response"));
	}
	
	@Override
	public boolean readIsPaymentDeposit(String pjId) {
		PaymentVO paymentVO = this.paymentDao.selectOneDeposit(pjId);
		
		// 보증금을 납부하지 않은 경우.
		if(paymentVO.getGrntPdDt() == null) {
			return false;
		}
		// 보증금을 납부한 경우.
		return true;
	}

	@Override
	public boolean updatePaymentInfo(PaymentReturnVO paymentReuturnVO) {
		AccntVO accuntVO = this.paymentDao.selectAccountInfo();
		
		// 계좌 잔고보다 돌려줘야할 금액이 더 큰 경우.
		if(accuntVO.getAccntBl() < paymentReuturnVO.getAmount()) {
			throw new PaymentException("계좌 잔고가 부족합니다.");
		}
		
		paymentReuturnVO.setBizAccount(accuntVO.getAccntNm());
		
		if(this.paymentDao.updatePaymentInfoBiz(paymentReuturnVO)<=0) {
			throw new PaymentException("계약금 이체중 서버상의 이유로 문제가 발생했습니다.");
		}
		
		return true;
	}
	
	/**
	 * 수수료를 제외한 돌려줘야하는 보증금을 계산해주는 메서드.
	 * @param amount : 보증금.
	 * @return : 돌려줘야 하는 돈.
	 */
	private int calculateDepositPayment(int amount) {
		// 수수료 제외하고 돌려줘야함.
		amount *= amount * 0.95;
		return amount;
	}
}
