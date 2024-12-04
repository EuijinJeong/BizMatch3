package com.ktdsuniversity.edu.bizmatch.common.email.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.bizmatch.common.email.dao.EmailDao;
import com.ktdsuniversity.edu.bizmatch.common.email.service.EmailService;
import com.ktdsuniversity.edu.bizmatch.common.email.vo.EmailVO;
import com.ktdsuniversity.edu.bizmatch.common.email.vo.UserEmailAuthNumVO;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.email.EmailDuplicateException;
import com.ktdsuniversity.edu.bizmatch.common.parser.html.HtmlParser;
import com.ktdsuniversity.edu.bizmatch.common.utils.RequestUtil;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private EmailDao emailDao;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	String from;
	
	@Override
	public void sendEmailForAlertFailSignUp(String email) {
		String host = RequestUtil.getHeader("host");
		String protocol = "http://";
		String domain = protocol + host;
		
		String html = HtmlParser.getHtml(domain + "/mailhtml/member_notice_fail_signup.html");
		
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper simpleMailMessage = new MimeMessageHelper(message, true, "UTF-8");
			simpleMailMessage.setFrom(from);
			simpleMailMessage.setSubject("BizMatch | 회원가입 승인 결과 안내");
			simpleMailMessage.setText(html, true);
			simpleMailMessage.setTo(email);
			mailSender.send(message);
		} catch (RuntimeException | MessagingException re) {
			re.printStackTrace();
		}
	}
	
	@Override
	public void sendEmailForAlertSignUp(String email) {
		String host = RequestUtil.getHeader("host");
		String protocol = "http://";
		String domain = protocol + host;
		
		String url = domain + "/";
		String html = HtmlParser.getHtml(domain + "/mailhtml/member_find_password.html","mainpage", url);
		
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper simpleMailMessage = new MimeMessageHelper(message, true, "UTF-8");
			simpleMailMessage.setFrom(from);
			simpleMailMessage.setSubject("BizMatch | 회원가입을 축하드립니다!");
			simpleMailMessage.setText(html, true);
			simpleMailMessage.setTo(email);
			mailSender.send(message);
		} catch (RuntimeException | MessagingException re) {
			re.printStackTrace();
		}
	}
	
	@Override
	public void sendEmailForFindPwd(String email) {
		
		String host = RequestUtil.getHeader("host");
		String protocol = "http://";
		String domain = protocol + host;
		
		// 전송할 url 정보.
		String url = domain + "/member/resetpwd";
		String html = HtmlParser.getHtml(domain + "/mailhtml/member_find_password.html"
				,"findPassWord" 
				, url);
		
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper simpleMailMessage = new MimeMessageHelper(message, true, "UTF-8");
			simpleMailMessage.setFrom(from);
			simpleMailMessage.setSubject("BizMatch 비밀번호 재설정 링크입니다. 타인 공유 금지");
			simpleMailMessage.setText(html, true);
			simpleMailMessage.setTo(email);
			mailSender.send(message);
		} catch(RuntimeException | MessagingException re) {
			re.printStackTrace();
		}
	}
	
	@Override
	public String sendEmail(String email) {
		
		String host = RequestUtil.getHeader("host");
		String protocol = "http://";
		String domain = protocol + host; 
		
		// 난수 생성.
		String randomNum = Integer.toString(makeRandomNumber());
		String html = HtmlParser.getHtml(domain + "/mailhtml/member_regist_auth_number.html", "authNumber", randomNum);
		
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper simpleMailMessage = new MimeMessageHelper(message, true, "UTF-8");
			simpleMailMessage.setFrom(from);
			simpleMailMessage.setSubject("BizMatch 이메일 인증번호입니다.");
			simpleMailMessage.setText(html, true);
			simpleMailMessage.setTo(email);
			mailSender.send(message);
			
			// 만약 저 send가 성공했다면,
			// 랜덤넘버를 데이터베이스에 저장해서 사용자가 입력한 값과 같은지 비교를 해야한다.
			UserEmailAuthNumVO userEmailAuthNumVO = new UserEmailAuthNumVO();
			userEmailAuthNumVO.setEmilAddrCnfrmNmbr(randomNum);
			userEmailAuthNumVO.setEmilAddr(email);
			System.out.println("인증번호 데베에 저장함.");
			// 데이터베이스에 인증정보를 insert한다.
			this.insertTempEmailAuthNum(userEmailAuthNumVO);
			
			return randomNum;
		}
		catch(RuntimeException | MessagingException re) {
			re.printStackTrace();
		}
		return null;
	}
	
	private int makeRandomNumber() {
		Random rd = new Random();
		int checkNum = rd.nextInt(888888) + 111111;
		return checkNum;
	}
	
	@Override
	public boolean insertTempEmailAuthNum(UserEmailAuthNumVO userEmailAuthNumVO) {
		
		int insertedCnt = this.emailDao.insertTempEmailAuthNum(userEmailAuthNumVO);
		if(insertedCnt == 0) {
			throw new EmailDuplicateException("인증번호가 서버상의 이유로 판별이 불가능합니다. 잠시 후 다시 시도해주세요.");
		}
		return insertedCnt > 0;
	}
	
	@Override
	public boolean isSameTempEmailAuthNum(UserEmailAuthNumVO userEmailAuthNumVO) {
		int searchCnt = this.emailDao.selectTempEmailAuthNum(userEmailAuthNumVO);
		if(searchCnt == 0) {
			return false;
		}
		return searchCnt > 0;
	}

	@Override
	public void deleteTempEmailAuthNum(String email) {
		int deleteCnt = this.emailDao.deleteTempEmailAuthNum(email);
		if(deleteCnt == 0) {
			throw new EmailDuplicateException("서버상의 이유로 인증번호 못지움.");
		}
	}

	@Override
	public boolean sendEmailForEtc(EmailVO emailVO) {
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper simpleMailMessage = new MimeMessageHelper(message, true, "UTF-8");
			simpleMailMessage.setFrom(from);
			simpleMailMessage.setSubject("BizMatch | 안내메일");
			simpleMailMessage.setText(emailVO.getContent(), true);
			simpleMailMessage.setTo(emailVO.getEmilAddr());
			mailSender.send(message);
		} catch (RuntimeException | MessagingException re) {
			re.printStackTrace();
		}
		return true;
	}
}
