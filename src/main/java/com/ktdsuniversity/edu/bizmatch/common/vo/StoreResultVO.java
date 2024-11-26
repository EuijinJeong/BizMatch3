package com.ktdsuniversity.edu.bizmatch.common.vo;

public class StoreResultVO {
	
	// 원래 파일 이름.
	private String originFileName;
	// 난독화된 파일 이름.
	private String obfuscatedFileName;
	
	// 파일명은 한번 정해지면 수정하면 안되기 때문에 setter는 만들지 않는다.
	// getter만 생성.
	
	// 멤버변수 초기화는 생성자로 한다.
	public StoreResultVO(String originFileName, String obfuscatedFileName) {
		this.originFileName = originFileName;
		this.obfuscatedFileName = obfuscatedFileName;
	}
	
	public String getOriginFileName() {
		return originFileName;
	}
	
	public String getObfuscatedFileName() {
		return obfuscatedFileName;
	}
	
}
