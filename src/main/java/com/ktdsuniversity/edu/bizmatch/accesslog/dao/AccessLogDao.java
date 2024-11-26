package com.ktdsuniversity.edu.bizmatch.accesslog.dao;

import com.ktdsuniversity.edu.bizmatch.accesslog.vo.AccessLogVO;

public interface AccessLogDao {
	public static final String NAMESPACE = "com.ktdsuniversity.edu.bizmatch.accesslog.dao.AccessLogDao";
	
	public int insertNewAccessLog(AccessLogVO AccessLogVO);
	
	public int selectLoginFailCount(String ip);
}
