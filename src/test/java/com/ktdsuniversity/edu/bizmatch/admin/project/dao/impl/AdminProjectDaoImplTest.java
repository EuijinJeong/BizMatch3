package com.ktdsuniversity.edu.bizmatch.admin.project.dao.impl;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.context.annotation.Import;

import com.ktdsuniversity.edu.bizmatch.admin.project.dao.AdminProjectDao;

@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(AdminProjectDaoImpl.class)
public class AdminProjectDaoImplTest {
	
	@Autowired
	public AdminProjectDao adminProjectDao;
	
	
	@Test
	public void deleteListProject() {
		List<String> pjIdList = List.of("PJ-20241105-000138","PJ-20241105-000156","PJ-20241106-000174");
		
		int i = adminProjectDao.deleteProject(pjIdList);
		System.out.println("teetetetetetet"+i);
	}

}
