package com.ktdsuniversity.edu.bizmatch.project.review.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.bizmatch.project.dao.ProjectDao;
import com.ktdsuniversity.edu.bizmatch.project.review.dao.ReviewDao;
import com.ktdsuniversity.edu.bizmatch.project.review.service.ReviewService;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.DeleteReviewVO;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.ReviewVO;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.WriteReviewVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.UpdateProjectSttVO;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Override
	public List<ReviewVO> selectAllReviews(String pjId) {
		return this.reviewDao.selectAllReviews(pjId);
	}
	
	@Override
	public ReviewVO selectOneReview(String rvwId) {
		return this.reviewDao.selectOneReview(rvwId);
	}
	
	@Transactional
	@Override
	public boolean insertOneReview(WriteReviewVO writeReviewVO) {
		int insertCount = this.reviewDao.insertOneReview(writeReviewVO);
		if(insertCount == 0) {
			throw new IllegalArgumentException("리뷰 등록이 서버상의 이유로 실패했습니다.");
		}
		// 리뷰 작성하고 프로젝트 상태 업데이트 되어야 하는거 아닌가...?
		UpdateProjectSttVO updateProjectSttVO = new UpdateProjectSttVO();
		updateProjectSttVO.setPjStt(4);
		updateProjectSttVO.setPjId(writeReviewVO.getPjId());
		int updatedCnt = this.projectDao.updateOneProjectStt(updateProjectSttVO);
		
		if(updatedCnt == 0) {
			throw new IllegalArgumentException("서버상의 이유로 프로젝트 상태 업데이트에 실패했습니다.");
		}
		return insertCount > 0;
	}
	
	@Transactional
	@Override
	public boolean updateReviewReportCount(String rvwId) {
		int updateCount = this.reviewDao.updateReviewReportCount(rvwId);
		return updateCount > 0;
	}
	
	@Transactional
	@Override
	public boolean deleteOneReview(DeleteReviewVO deleteReviewVO) {
		int deleteCount = this.reviewDao.deleteOneReview(deleteReviewVO);
		return deleteCount > 0;
	}

	@Override
	public List<ReviewVO> readReviewListSortedHighRate(String cmpId) {
		return this.reviewDao.selectReviewListSortedByHighRate(cmpId);
	}

	@Override
	public List<ReviewVO> readReviewListSortLowRate(String cmpId) {
		return this.reviewDao.selectReviewListSortedByLowRate(cmpId);
	}
}
