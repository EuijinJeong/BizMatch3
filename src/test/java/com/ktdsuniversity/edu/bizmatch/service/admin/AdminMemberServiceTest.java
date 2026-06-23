package com.ktdsuniversity.edu.bizmatch.service.admin;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.bizmatch.admin.member.service.AdminMemberService;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;
import com.ktdsuniversity.edu.bizmatch.service.ServiceIntegrationBaseTest;

@DisplayName("AdminMemberService - PostgreSQL Mapper 검증")
class AdminMemberServiceTest extends ServiceIntegrationBaseTest {

    @Autowired
    private AdminMemberService adminMemberService;

    @Test
    @DisplayName("readAllMemberList: 전체 회원 목록 조회")
    void readAllMemberList_shouldReturnList() {
        List<MemberVO> result = adminMemberService.readAllMemberList();
        assertNotNull(result);
    }
}
