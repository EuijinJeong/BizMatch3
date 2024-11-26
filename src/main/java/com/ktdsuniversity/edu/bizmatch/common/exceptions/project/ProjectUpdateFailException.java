package com.ktdsuniversity.edu.bizmatch.common.exceptions.project;

import com.ktdsuniversity.edu.bizmatch.project.vo.ModifyProjectVO;

public class ProjectUpdateFailException extends RuntimeException {

	private static final long serialVersionUID = 5846337278571409766L;

	public ProjectUpdateFailException(String message, ModifyProjectVO modifyProjectVO) {
		super(message);
	}
}
