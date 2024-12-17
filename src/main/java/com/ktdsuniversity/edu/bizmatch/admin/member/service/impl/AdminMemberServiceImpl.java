package com.ktdsuniversity.edu.bizmatch.admin.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.bizmatch.admin.member.dao.AdminMemberDao;
import com.ktdsuniversity.edu.bizmatch.admin.member.service.AdminMemberService;
import com.ktdsuniversity.edu.bizmatch.common.email.service.EmailService;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.admin.AdminException;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;

@Service
public class AdminMemberServiceImpl implements AdminMemberService {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AdminMemberDao adminMemberDao;

	@Transactional
	@Override
	public boolean updateMemberSignupStt(List<String> email) {
		// 회원의 활성화 상태를 업데이트 해준다.
		int updatedCnt = this.adminMemberDao.updateMemberSttActive(email);
		if(updatedCnt == 0) {
			new AdminException("서버상의 이유로 상태 업데이트에 실패했습니다.");
		}
		return true;
	}

	@Transactional
	@Override
	public boolean updateMemberSignupSttToRefuse(List<String> emailList) {
		// 회원의 정보를 먼저 조회한다. 
		int deletedCnt = this.adminMemberDao.deleteMember(emailList);
		if(deletedCnt == 0) {
			new AdminException("서버상의 이유로 상태 업데이트에 실패했습니다.");
		}
		
		// 회원가입 거절 이메일을 날려야함.
		for (String email : emailList) {
			this.emailService.sendEmailForAlertFailSignUp(email);
		}
		
		return true;
	}

	@Override
	public List<MemberVO> readAllMemberList() {
		List<MemberVO> memberList = this.adminMemberDao.selectMemberList();
		if(memberList == null) {
			new AdminException("서버상의 이유로 회원 조회가 불가능합니다.");
		}
		return memberList;
	}

	@Override
	public boolean updateMemberPnlty(List<String> email) {
		boolean isSuccess = this.adminMemberDao.updateMemberPnlty(email) > 0;
		
		if(!isSuccess) {
			throw new AdminException("서버 오류 잠시 후 다시 시도");
		}
		return true;
	}

	@Override
	public boolean updateMemberIsqt(List<String> email) {
		boolean isSuccess = this.adminMemberDao.updateMemberIsqt(email) > 0;
		
		if(!isSuccess) {
			throw new AdminException("서버 오류 잠시 후 다시 시도");
		}
		
		return true;
	}
}
