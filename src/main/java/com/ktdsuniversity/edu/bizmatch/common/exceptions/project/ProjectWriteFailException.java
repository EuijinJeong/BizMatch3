package com.ktdsuniversity.edu.bizmatch.common.exceptions.project;

import com.ktdsuniversity.edu.bizmatch.project.vo.WriteProjectVO;

public class ProjectWriteFailException extends RuntimeException{

	private static final long serialVersionUID = -6572511167581427077L;

	private WriteProjectVO writeProjectVO;
	public ProjectWriteFailException(String message , WriteProjectVO writeProjectVO) {
		super(message);
		this.writeProjectVO = writeProjectVO;
	}
	public WriteProjectVO getWriteProjectVO() {
		return writeProjectVO;
	}
}
