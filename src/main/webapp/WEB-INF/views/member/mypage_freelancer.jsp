<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="/css/mypage_company.css" />
    <title>BizMatch | 마이페이지</title>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
    />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/mypage_freelancer.js"></script>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/common/after_login_header.jsp" %>
    <div class="mainpage-box">
      <%@ include file="/WEB-INF/views/common/freelancer_profile_box.jsp" %>
      <main>
        <div class="main-box">
          <section class="sidebar">
            <div class="sidebar-menulist">
              <div class="sidebar-menu" data-target="#introduction">
                내 프로필
              </div>
              <div class="sidebar-menu" data-target="#holding-technology">
                보유 기술
              </div>
              <div class="sidebar-menu" data-target="#attachment">
                포트폴리오
              </div>
              <div class="sidebar-menu" data-target="#review-list">리뷰</div>
              <div class="sidebar-menu">내 프로젝트</div>
            </div>
          </section>
          <section class="myPageList">
            <div class="myPageList-box">
              <div class="introduction" id="introduction">
                소개
                <div class="introduction-content">${memberVO.mbrIntr}</div>
              </div>
              <div class="holding-technology" id="holding-technology">
                보유 기술
                <div class="holding-technology-list">
                  <c:choose>
                    <c:when test="${not empty mbrPrmStkList}">
                      <c:forEach var="tech" items="${mbrPrmStkList}">
                        <div class="tech">${tech.prmStkVO.prmStk}</div>
                      </c:forEach>
                    </c:when>
                    <c:otherwise>
                      <div class="no-tech">보유 기술 정보가 없습니다</div>
                    </c:otherwise>
                  </c:choose>
                </div>
              </div>
              <div class="attachment" id="attachment">
                첨부자료
                <button class="more-button small" type="button">
                  <a
                    href="/member/mypage/freelancer/portfolio/${memberVO.emilAddr}"
                    >더 보기</a
                  >
                </button>
                <div class="portfolio-gallery">
                  <div class="result"></div>
                </div>
              </div>
              <div class="review-list" id="review-list">
                <div class="review-title">
                  <div class="review-tag">리뷰</div>
                </div>
                <!-- 리뷰 리스트 반복 출력 -->
                <div class="review-box-list">
                  <c:choose>
                    <c:when test="${not empty reviewList}">
                      <c:forEach
                        var="review"
                        items="${reviewList}"
                        varStatus="status"
                      >
                        <c:if test="${status.index < 5}">
                          <div class="review" data-cmmntId="${review.rvwId}">
                            <div class="review-image">
                              <img src="/img/profile.svg" alt="profile-img" />
                            </div>
                            <div class="review-id">${review.emilAddr}</div>
                            <div class="review-content">${review.rvwCntnt}</div>
                            <div class="star-date">
                              <div class="star">
                                <!-- 전체 별 -->
                                <c:forEach
                                  var="i"
                                  begin="1"
                                  end="${review.scr}"
                                  step="1"
                                >
                                  <i class="fas fa-star"></i>
                                </c:forEach>
                                <!-- 반 별 -->
                                <c:if test="${review.scr % 1 >= 0.5}">
                                  <i class="fas fa-star-half-alt"></i>
                                </c:if>
                                <!-- 빈 별 -->
                                <c:forEach
                                  var="i"
                                  begin="1"
                                  end="${5 - review.scr}"
                                  step="1"
                                >
                                  <i class="far fa-star"></i>
                                </c:forEach>
                              </div>
                              <div class="date">${review.rvwDt}</div>
                            </div>
                            <div class="report-button">
                              <button class="report">신고</button>
                            </div>
                          </div>
                        </c:if>
                      </c:forEach>
                    </c:when>
                    <c:otherwise>
                      <div class="no-review-message">
                        등록된 리뷰가 없습니다.
                      </div>
                    </c:otherwise>
                  </c:choose>
                </div>
                <div
                  class="more-button-box"
                  id="moreemail"
                  data-email="${memberVO.emilAddr}"
                >
                  <a
                    href="/member/mypage/freelancer/reviewlist/${memberVO.emilAddr}"
                    ><button class="more-button" type="button">더 보기</button>
                  </a>
                </div>
              </div>
            </div>
          </section>
        </div>
      </main>
    </div>
    <!-- 모달 창 -->
    <div id="reportModal" class="modal">
      <div class="modal-content">
        <button class="close-btn">&times;</button>
        <h2 class="modal-title">리뷰 신고</h2>
        <form id="reportForm">
          <div class="form-group">
            <label for="reportCategory" class="form-label">신고 유형</label>
            <select id="reportCategory" name="rprtCtgry" class="form-select">
              <option value="inappropriate post">부적절한 게시물</option>
              <option value="swear-language">비방언어</option>
              <option value="advertisement">광고</option>
              <option value="etc">기타</option>
            </select>
          </div>

          <div class="form-group">
            <label for="reportContent" class="form-label">신고 내용</label>
            <textarea
              id="reportContent"
              name="rprtCntnt"
              class="form-textarea"
              placeholder="신고 사유를 상세히 기입해주세요"
            ></textarea>
          </div>
          <button type="submit" class="submit-btn">신고 제출</button>
        </form>
      </div>
    </div>
  </body>
</html>
