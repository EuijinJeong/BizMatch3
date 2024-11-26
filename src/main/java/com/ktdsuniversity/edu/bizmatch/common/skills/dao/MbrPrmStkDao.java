package com.ktdsuniversity.edu.bizmatch.common.skills.dao;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.common.skills.vo.MbrPrmStkVO;

public interface MbrPrmStkDao {
	public static final String NAMESPACE = "com.ktdsuniversity.edu.bizmatch.common.skills.dao.MbrPrmStkDao";
	public int insertMbrSkill(MbrPrmStkVO mbrPrmStkVO);
	public List<MbrPrmStkVO> selectMbrSkill(String email);
	public int deleteMbrSkill(String mbrPrmStkId);
	public List<MbrPrmStkVO> selectMbrCmpnySkill(String cmpId);
	
	
}