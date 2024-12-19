package com.ktdsuniversity.edu.bizmatch.common.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.bizmatch.common.exceptions.file.FileUploadFailedException;
import com.ktdsuniversity.edu.bizmatch.common.vo.StoreResultVO;

/**
 * 
 * @Component: Spring Bean을 생성하셈!
 */

@Component
public class FileHandler {

	@Value("${app.multipart.base-dir}")
	private String baseDirectory;

	@Value("${app.multipart.obfuscation.enable}")
	private boolean enableObfuscation;

	@Value("${app.multipart.obfuscation.hide-ext.enable}")
	private boolean enableHideExtention;
	
	/**
	 * 
	 * @param multipartFile
	 * @return
	 */
	public StoreResultVO storeFile(MultipartFile multipartFile) {
		// 1. client가 파일을 전송 했는지 체크한다.
		// 보안상의 이유로 파일이름을 난독화 시킨다.

		// 2. 클라이언트가 파일을 전송했다면
		if (multipartFile != null && !multipartFile.isEmpty()) {
			
			// 3. 파일을 서버 컴퓨터의 특정 폴더로 저장시킨다.
			String obfuscatedFileName = multipartFile.getOriginalFilename();

			if (this.enableObfuscation) {
				String ext = obfuscatedFileName.substring(obfuscatedFileName.lastIndexOf("."));

				obfuscatedFileName = UUID.randomUUID().toString();

				if (!this.enableHideExtention) {
					obfuscatedFileName += ext;
				}
			}

			File uploadFile = new File(this.baseDirectory, obfuscatedFileName);
			if (!uploadFile.getParentFile().exists()) {
				uploadFile.getParentFile().mkdirs();
			}

			try {
				multipartFile.transferTo(uploadFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				throw new RuntimeException("예기치 못한 이유로 업로드에 실패했습니다. 잠시 후 다시 시도해주세요.");
			}
			// 파일을 업로드 했을 때 아래와 같이 반환한다.
			return new StoreResultVO(multipartFile.getOriginalFilename(), obfuscatedFileName);
		}
		// 아니라면 null을 반환한다.
		return null;
	}
	
	/**
	 * 파일 리스트를 저장
	 * 
	 * @param fileList MultupartFile 리스트를 전달하면 
	 * @return List<StoreResultVO>: 저장된 원본이름과 난독화된 이름의 리스트를 반환.
	 */
	public List<StoreResultVO> storeListFile(List<MultipartFile> fileList) {
		if(fileList == null) {
			// 사용자가 전달한 파일리스트가 비어있으면 반환값 없음
		}
		//StoreResultVO 객체 List를 만들 ArrayList 생성
		List<StoreResultVO> storeFileList = new ArrayList<>();
		
		for(MultipartFile file : fileList) {
			//MultipartFile 이 들어있는 fileList를 하나씩 돌아가면서 저장
			if (file != null && !file.isEmpty()) {
				String obfuscatedFileName = file.getOriginalFilename();

				if (this.enableObfuscation) {
					String ext = obfuscatedFileName.substring(obfuscatedFileName.lastIndexOf("."));

					obfuscatedFileName = UUID.randomUUID().toString();

					if (!this.enableHideExtention) {
						obfuscatedFileName += ext;
					}
				}

				File uploadFile = new File(this.baseDirectory, obfuscatedFileName);
				
				if (!uploadFile.getParentFile().exists()) {
					uploadFile.getParentFile().mkdirs();
				}

				try {
					file.transferTo(uploadFile);
				} catch (IllegalStateException | IOException e) {
					throw new FileUploadFailedException("예기치 못한 이유로 업로드에 실패했습니다. 잠시 후 다시 시도해주세요.");
				}
				storeFileList.add(new StoreResultVO(file.getOriginalFilename(), obfuscatedFileName));
			}
		}
		return storeFileList;	
	}
		
		
	

	/**
	 * 
	 * @param fileName
	 * @param originFileName
	 * @return
	 */
	public ResponseEntity<Resource> downloadFile(String fileName, String originFileName) {
	    // 1. 파일이 존재하는지 확인
	    File downloadFile = new File(this.baseDirectory, fileName);
	    
	    if (!downloadFile.exists()) {
	        // 파일이 없으면 404 반환
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    // 2. 파일 이름 인코딩 처리
	    try {
	        originFileName = new String(originFileName.getBytes("UTF-8"), "ISO-8859-1");
	    } catch (UnsupportedEncodingException e) {
	        // 인코딩 예외를 로깅하고, 500 서버 에러 반환
	        
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    System.out.println("오리지널 파일 네임: " + originFileName);
	    
	    HttpHeaders header = new HttpHeaders();
	    header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + originFileName + "\"");

	    InputStreamResource resource = null;

	    try {
	        resource = new InputStreamResource(new FileInputStream(downloadFile));
	    } catch (FileNotFoundException e) {
	        // 파일을 찾을 수 없는 경우 처리
	       
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    return ResponseEntity.ok()
	            .headers(header)
	            .contentLength(downloadFile.length())
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .body(resource);
	}


	/**
	 * 
	 * @param fileName
	 */
	public void deleteFile(String fileName) {
		if(fileName == null) {
			return;
		}
		
		File file = new File(this.baseDirectory, fileName);
		
		if(file.exists() && file.isFile()) {
			file.delete();
		}
	}
}
