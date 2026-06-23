package com.ktdsuniversity.edu.bizmatch.service.member;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

import com.ktdsuniversity.edu.bizmatch.common.skills.vo.MbrPrmStkVO;
import com.ktdsuniversity.edu.bizmatch.member.service.MemberService;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberPortfolioVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.PrmStkVO;
import com.ktdsuniversity.edu.bizmatch.service.ServiceIntegrationBaseTest;

@DisplayName("MemberService - PostgreSQL Mapper 검증 (MemberDao + MbrPrmStkDao)")
class MemberServiceTest extends ServiceIntegrationBaseTest {

    @MockBean
    private JavaMailSender mailSender;

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("selectAllSkills: PRM_STK 전체 조회")
    void selectAllSkills_shouldReturnList() {
        List<PrmStkVO> result = memberService.selectAllSkills();
        assertNotNull(result);
    }

    @Test
    @DisplayName("selectAllPortfolios: 존재하지 않는 이메일로 빈 리스트 반환")
    void selectAllPortfolios_withNonExistentEmail() {
        List<MemberPortfolioVO> result = memberService.selectAllPortfolios("noexist@test.com");
        assertNotNull(result);
    }

    @Test
    @DisplayName("selectMemberSkills: MbrPrmStkDaoMapper - 스킬 조회 (JOIN)")
    void selectMemberSkills_withNonExistentEmail() {
        List<MbrPrmStkVO> result = memberService.selectMemberSkills("noexist@test.com");
        assertNotNull(result);
    }

    @Test
    @DisplayName("isDuplicatedEmail: 이메일 중복 확인 쿼리 실행")
    void isDuplicatedEmail_withNonExistentEmail() {
        boolean result = memberService.isDuplicatedEmail("definitely_not_exist_12345@test.com");
        assertNotNull(result);
    }
}
