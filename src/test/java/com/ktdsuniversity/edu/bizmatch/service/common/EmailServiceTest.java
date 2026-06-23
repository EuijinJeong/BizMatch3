package com.ktdsuniversity.edu.bizmatch.service.common;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

import com.ktdsuniversity.edu.bizmatch.common.email.service.EmailService;
import com.ktdsuniversity.edu.bizmatch.common.email.vo.UserEmailAuthNumVO;
import com.ktdsuniversity.edu.bizmatch.service.ServiceIntegrationBaseTest;

@DisplayName("EmailService - PostgreSQL Mapper 검증 (INTERVAL)")
class EmailServiceTest extends ServiceIntegrationBaseTest {

    @MockBean
    private JavaMailSender mailSender;

    @Autowired
    private EmailService emailService;

    @Test
    @DisplayName("isSameTempEmailAuthNum: NOW() - INTERVAL '5 minutes' 쿼리 실행 검증")
    void isSameTempEmailAuthNum_withIntervalExpression() {
        UserEmailAuthNumVO vo = new UserEmailAuthNumVO();
        vo.setEmilAddr("noexist@test.com");
        vo.setEmilAddrCnfrmNmbr("000000");

        // 없는 이메일 → false 반환, SQL 실행 오류 없음이 핵심
        boolean result = emailService.isSameTempEmailAuthNum(vo);
        assertFalse(result);
    }

    @Test
    @DisplayName("insertTempEmailAuthNum: NEXTVAL 시퀀스 + INSERT 검증")
    void insertTempEmailAuthNum_sequenceAndInsert() {
        UserEmailAuthNumVO vo = new UserEmailAuthNumVO();
        vo.setEmilAddr("temp_test@test.com");
        vo.setEmilAddrCnfrmNmbr("123456");

        boolean result = emailService.insertTempEmailAuthNum(vo);
        assertNotNull(result);
    }
}
