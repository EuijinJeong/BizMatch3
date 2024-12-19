package com.ktdsuniversity.edu.bizmatch.file.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.bizmatch.common.beans.FileHandler;
import com.ktdsuniversity.edu.bizmatch.file.dao.FileDao;

import com.ktdsuniversity.edu.bizmatch.file.vo.ProjectFileVO;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 파일 컨트롤러.
 * @author jeong-uijin
 */
@RestController
@RequestMapping("/api")
public class FileController {

	@Autowired
	private FileHandler fileHandler;
	
	@Autowired
	private FileDao fileDao;
	
	@GetMapping("/project/file/download/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
	    ProjectFileVO file = fileDao.selectProjectFileById(fileId); // 파일 정보 조회

	    if (file == null) {
	        System.out.println("비어있음");
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 파일이 없으면 404 반환
	    }

	    try {
	        // 파일 다운로드 처리
	        return fileHandler.downloadFile(file.getPjAttUrlNonread(), file.getPjAttUrl());
	    } catch (Exception e) {
	        System.err.println("예외 발생: " + e.getMessage());
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 예외 발생시 500 반환
	    }
	}

}
