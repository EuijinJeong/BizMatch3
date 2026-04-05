
package com.ktdsuniversity.edu.bizmatch.project.vo;

import com.ktdsuniversity.edu.bizmatch.member.vo.PrmStkVO;

public class ProjectSkillVO {

	private String pjPrmStkId; // 프로젝트 주요 기술 아이디
	private String pjId;
	private String prmStkId;
	private String prmStk; // 기술 이름
	private PrmStkVO prmStkVO;

	public String getPjPrmStkId() {
		return pjPrmStkId;
	}

	public void setPjPrmStkId(String pjPrmStkId) {
		this.pjPrmStkId = pjPrmStkId;
	}

	public String getPjId() {
		return pjId;
	}

	public void setPjId(String pjId) {
		this.pjId = pjId;
	}		

	public String getPrmStkId() {
		return prmStkId;
	}

	public void setPrmStkId(String prmStkId) {
		this.prmStkId = prmStkId;
	}

	public PrmStkVO getPrmStkVO() {
		return prmStkVO;
	}

	public void setPrmStkVO(PrmStkVO prmStkVO) {
		this.prmStkVO = prmStkVO;
	}

	public String getPrmStk() {
		return prmStk;
	}

	public void setPrmStk(String prmStk) {
		this.prmStk = prmStk;
	}

	@Override
	public String toString() {
		return "ProjectSkillVO{" + "prmStk='" + prmStk + '\'' + 
				'}';
	}

}
