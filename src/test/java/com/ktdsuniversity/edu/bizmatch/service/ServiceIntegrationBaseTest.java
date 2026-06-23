package com.ktdsuniversity.edu.bizmatch.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles({"dev", "postgresql", "test"})
@Transactional
public abstract class ServiceIntegrationBaseTest {
}