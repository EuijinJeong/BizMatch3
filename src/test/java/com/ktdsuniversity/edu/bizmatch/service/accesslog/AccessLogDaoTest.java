package com.ktdsuniversity.edu.bizmatch.service.accesslog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.bizmatch.accesslog.dao.AccessLogDao;
import com.ktdsuniversity.edu.bizmatch.accesslog.vo.AccessLogVO;
import com.ktdsuniversity.edu.bizmatch.service.ServiceIntegrationBaseTest;

@DisplayName("AccessLogDao - PostgreSQL Mapper 검증 (INTERVAL)")
class AccessLogDaoTest extends ServiceIntegrationBaseTest {

    @Autowired
    private AccessLogDao accessLogDao;

    @Test
    @DisplayName("selectLoginFailCount: NOW() - INTERVAL '1 hour' 쿼리 실행 검증")
    void selectLoginFailCount_withIntervalExpression() {
        int count = accessLogDao.selectLoginFailCount("999.999.999.999");
        assertEquals(0, count);
    }

    @Test
    @DisplayName("insertNewAccessLog: NEXTVAL 시퀀스 + INSERT 검증")
    void insertNewAccessLog_sequenceNextval() {
        AccessLogVO vo = new AccessLogVO();
        vo.setAccessType("TEST");
        vo.setAccessEmail("test@test.com");
        vo.setAccessUrl("/test");
        vo.setAccessMethod("GET");
        vo.setAccessIp("127.0.0.1");
        vo.setLoginSuccessYn("Y");

        int result = accessLogDao.insertNewAccessLog(vo);
        assertNotNull(result);
    }
}
