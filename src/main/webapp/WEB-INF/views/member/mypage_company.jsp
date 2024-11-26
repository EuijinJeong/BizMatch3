<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<!-- 마이페이지 - 기업버전입니다. -->
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="/css/mypage_company.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
    />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/mypage_company.js"></script>
    <!-- services와 clusterer, drawing 라이브러리 불러오기 -->
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c34c0fc9c9f52486b1dfce66356c8efa&libraries=services,clusterer,drawing"
    ></script>
    <title>BizMatch | 마이페이지</title>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/common/after_login_header.jsp" %>
    <div class="mainpage-box" id="cmpidbox" data-cmpid="${companyVO.cmpnyId}">
      <%@ include file="/WEB-INF/views/common/profile_box.jsp" %>
      <main>
        <div class="main-box">
          <section class="sidebar">
            <div class="sidebar-menulist">
              <div class="sidebar-menu" data-target="#introduction">
                내 프로필
              </div>
              <div class="sidebar-menu" data-target="#interesting-industry">
                관심 산업
              </div>
              <div class="sidebar-menu" data-target="#holding-technology">
                보유 기술
              </div>
              <div class="sidebar-menu" data-target="#attachment">
                회사 첨부자료
              </div>
              <div class="sidebar-menu" data-target="#map">회사 위치</div>
              <div class="sidebar-menu" data-target="#review-list">리뷰</div>
              <div class="sidebar-menu">내 프로젝트</div>
            </div>
          </section>
          <section class="myPageList">
            <div class="myPageList-box">
              <div class="introduction" id="introduction">
                회사 소개
                <div class="introduction-content">${companyVO.cmpnyIntr}</div>
              </div>
              <div class="interesting-industry" id="interesting-industry">
                관심 산업 <%@ include
                file="/WEB-INF/views/common/category_bar2.jsp" %>
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
                회사 첨부자료
                <button class="more-button small" type="button">
                  <a
                    href="/member/mypage/company/portfolio/${companyVO.cmpnyId }"
                    >더 보기</a
                  >
                </button>
                <div class="portfolio-gallery">
                  <div class="result"></div>
                </div>
              </div>
              <div class="map" id="map">
                회사 위치
                <div class="map-box">
                  <div id="kakao-map" class="kakao-map"></div>
                  <div class="map-detail">
                    <div class="detail-title">상세 주소</div>
                    <div class="detail-address">
                      ${companyVO.cmpnyAddr != null ? companyVO.cmpnyAddr :
                      '주소 정보가 없습니다'}
                    </div>
                  </div>
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

                <div class="more-button-box">
                  <a
                    href="/member/mypage/company/reviewlist/${companyVO.cmpnyId}"
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
    <!-- 첨부파일 모달입니다. -의진- -->
    <!-- <link rel="stylesheet" href="/css/project_inquiry_modal.css" /> -->
    <!-- <div class="modal" id="commentModal">
      <div class="modal-content">
        <span class="close-btn">&times;</span>
        <div>
          <div class="inquiry-comment-section">
            <div class="inquiry-comment-block">
              <div class="inquiry-comment-content-area">
                <div class="file-input">
                  <label for="fileInput" class="file-label">파일 첨부:</label>
                  <input
                    type="file"
                    id="fileInput"
                    class="file-input"
                    multiple
                  />
                </div>
              </div>
            </div>
          </div>
          <textarea
            placeholder="첨부파일 관련 상세 설명을 입력해주세요."
          ></textarea>
          <button class="submit-btn">등록</button>
        </div>
      </div>
    </div> -->
  </body>
</html>
