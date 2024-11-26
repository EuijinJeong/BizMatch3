package com.ktdsuniversity.edu.bizmatch.common.exceptions.file;

/**
 * 파일 업로드 실패와 관련된 예외 처리 클래스.
 * @author jeong-uijin
 */
public class FileUploadFailedException extends RuntimeException{

	private static final long serialVersionUID = 5910945274039596101L;
	
	public FileUploadFailedException(String message) {
		super(message);
	}
}
