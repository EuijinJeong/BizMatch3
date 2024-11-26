package com.ktdsuniversity.edu.bizmatch.file.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.bizmatch.common.beans.FileHandler;


/**
 * 파일 컨트롤러.
 * @author jeong-uijin
 */
@RestController
public class FileController {

	@Autowired
	private FileHandler fileHandler;
	
	/**
	 * 특정한 파일을 다운로드 요청을 처리하는 컨트롤러.
	 * @param fileId
	 * @return
	 */
	@GetMapping("/file/download/{fileId}")
	public String downloadFile(@RequestParam String fileId) {
		this.fileHandler.storeFile(null);
		return new String();
	}
}
