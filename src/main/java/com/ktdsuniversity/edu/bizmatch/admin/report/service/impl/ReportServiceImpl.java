package com.ktdsuniversity.edu.bizmatch.admin.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.bizmatch.admin.report.dao.ReportDao;
import com.ktdsuniversity.edu.bizmatch.admin.report.service.ReportService;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.admin.AdminException;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.ReviewVO;
import com.ktdsuniversity.edu.bizmatch.report.vo.ReviewReportVO;

@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	private ReportDao reportDao;
	@Override
	public List<ReviewVO> readAllReportReview() {
		return reportDao.selectAllReportReview();
	}

	@Transactional
	@Override
	public boolean deleteReportInfo(List<String> rprtId) {
		List<ReviewReportVO> reviewReportVO = this.reportDao.selectOneReviewReport(rprtId);
		
		if(!(this.reportDao.deleteReportReview(rprtId)>0)) {
			throw new AdminException("신고정보 처리 중 예외 발생");
		}
		
		for(ReviewReportVO tmp : reviewReportVO) {
			if(!(this.reportDao.updateReviewReportCnt(tmp.getCmmntId())>0)){
				throw new AdminException("신고정보 처리 중 예외 발생");
			};
		}
		return true;
	}

	@Override
	public boolean deleteReview(List<String> rvwId) {
		return this.reportDao.deleteReview(rvwId)>0;
	}

	@Override
	public boolean updateCheckReportReview(List<String> rprtIds) {
		return this.reportDao.updateIsRprt(rprtIds)>0;
	}

}
