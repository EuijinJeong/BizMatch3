package com.ktdsuniversity.edu.bizmatch.project.vo;

import com.ktdsuniversity.edu.bizmatch.common.vo.IndstrInfoVO;

public class ProjectIndustryVO {
	
	private String pjIndstrId; // 프로젝트 산업군 테이블 아이디
	private String pjId; // 프로젝트 아이디
	private String mjrId; //대분류 아이디
	private String smjrId; // 중분류 아이디
	private IndstrInfoVO indstrInfoVO; // 산업군 정보.

	public String getPjIndstrId() {
		return pjIndstrId;
	}
	public void setPjIndstrId(String pjIndstrId) {
		this.pjIndstrId = pjIndstrId;
	}
	public String getPjId() {
		return pjId;
	}
	public void setPjId(String pjId) {
		this.pjId = pjId;
	}
	public String getMjrId() {
		return mjrId;
	}
	public void setMjrId(String mjrId) {
		this.mjrId = mjrId;
	}
	public String getSmjrId() {
		return smjrId;
	}
	public void setSmjrId(String smjrId) {
		this.smjrId = smjrId;
	}
	public IndstrInfoVO getIndstrInfoVO() {
		return indstrInfoVO;
	}
	public void setIndstrInfoVO(IndstrInfoVO indstrInfoVO) {
		this.indstrInfoVO = indstrInfoVO;
	}
	
	
	
	
}
