package com.ktdsuniversity.edu.bizmatch.member.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MemberImageController {

	@Value("${app.multipart.base-dir}")
	private String baseDirPrefix;
	
	@GetMapping("/images/portfolio/img/{imgUrl}/")
	public ResponseEntity<byte[]> showImage(@PathVariable String imgUrl){
		String savePath = baseDirPrefix+imgUrl;
		File file = new File(savePath);
		byte[] result=null;
		ResponseEntity<byte[]> entity=null;
		
		try {
			result = FileCopyUtils.copyToByteArray(file);
			
			HttpHeaders header = new HttpHeaders();
			header.add("Content-type",Files.probeContentType(file.toPath()));
			
			entity = new ResponseEntity<>(result,header,HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
