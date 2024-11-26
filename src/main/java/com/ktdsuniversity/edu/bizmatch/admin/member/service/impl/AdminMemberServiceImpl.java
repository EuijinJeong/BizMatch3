package com.ktdsuniversity.edu.bizmatch.admin.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.bizmatch.admin.member.dao.AdminMemberDao;
import com.ktdsuniversity.edu.bizmatch.admin.member.service.AdminMemberService;
import com.ktdsuniversity.edu.bizmatch.common.email.dao.EmailDao;
import com.ktdsuniversity.edu.bizmatch.common.email.service.EmailService;

@Service
public class AdminMemberServiceImpl implements AdminMemberService {

	@Autowired
	private EmailDao emailDao;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AdminMemberDao adminMemberDao;
	
	@Override
	public boolean handleMemberSignUp(String email) {
		
		int selectCnt = this.emailDao.selectMemberForSignUp(email);
		if (selectCnt == 0) {
			throw new IllegalArgumentException("해당 회원을 찾을 수 없습니다.");
		}
		// 회원가입 승인 이메일을 사용자에게 보냄.
		this.emailService.sendEmailForAlertSignUp(email);
		
		// 회원가입 상태를 승인이라고 업데이트해줌.
		int updateCnt = this.adminMemberDao.updateOneMemberStt(email);
		if(updateCnt == 0) {
			throw new IllegalArgumentException("시스템의 오류로 회원 상태를 업데이트 할 수 없습니다.");
		}
		return true;
	}
}
