<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>리뷰 상세페이지</title>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
    />
    <link rel="stylesheet" type="text/css" href="/css/morereviewlist.css" />
    <link rel="stylesheet" href="/css/after_login_header.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
    />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/morereviewlist.js"></script>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/common/after_login_header.jsp" %>
    <div class="review-list" id="review-list">
      <div class="review-title">
        <div class="review-tag">리뷰</div>
        <input type="hidden" id="orderBy" value="${orderBy}" />
        <div class="sort-options">
          <button
            class="sort-option late-date ${orderBy == 'late-date' ? 'active' : ''}"
          >
            최신순
          </button>
          <button
            class="sort-option high-rate ${orderBy == 'high-rate' ? 'active' : ''}"
          >
            별점 높은순
          </button>
          <button
            class="sort-option low-rate ${orderBy == 'low-rate' ? 'active' : ''}"
          >
            별점 낮은순
          </button>
        </div>
      </div>
      <!-- 리뷰 리스트 반복 출력 -->
      <!-- <div class="review-box-list">
        <c:choose>
          <c:when test="${not empty reviewList}">
            <c:forEach var="review" items="${reviewList}">
              <div class="review" data-cmmntId="${review.rvwId}">
                <div class="review-image">
                  <img src="/img/profile.svg" alt="profile-img" />
                </div>
                <div class="review-id">${review.emilAddr}</div>
                <div class="review-content">${review.rvwCntnt}</div>
                <div class="star-date">
                  <div class="star"> -->
      <!-- 전체 별 -->
      <!-- <c:forEach var="i" begin="1" end="${review.scr}" step="1">
                      <i class="fas fa-star"></i>
                    </c:forEach> -->
      <!-- 반 별 -->
      <!-- <c:if test="${review.scr % 1 >= 0.5}">
                      <i class="fas fa-star-half-alt"></i>
                    </c:if> -->
      <!-- 빈 별 -->
      <!-- <c:forEach
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
            </c:forEach>
          </c:when>
          <c:otherwise>
            <div class="no-review-message">등록된 리뷰가 없습니다</div>
          </c:otherwise>
        </c:choose>
      </div> -->
      <div class="review-box-list"></div>
    </div>
    <c:if test="${paginationVO.groupEndPageNo > 0}">
      <div class="page-div pagenation-ajax">
        <div class="pre-page-btn">
          <c:if test="${paginationVO.hasPrevGroup}">
            <div>
              <a
                data-url="/member/mypage/company/reviews"
                data-page-no="0"
                data-exposure-list-size="${memberPaginationVO.exposureListSize}"
                href="javascript:void(-1)"
                >처음</a
              >
            </div>
            <div>
              <a
                data-url="/member/mypage/company/reviews"
                data-page-no="${memberPaginationVO.prevGroupStartPageNo}"
                data-exposure-list-size="${memberPaginationVO.exposureListSize}"
                href="javascript:void(-1)"
                >>이전</a
              >
            </div>
          </c:if>
        </div>
        <div class="page-number-btn">
          <c:forEach
            begin="${paginationVO.groupStartPageNo}"
            end="${paginationVO.groupEndPageNo}"
            step="1"
            var="p"
          >
            <div class="${paginationVO.currPageNo eq p ? 'active':'' }">
              <a
                data-url="/member/mypage/company/reviews"
                data-page-no="${p}"
                data-exposure-list-size="${memberPaginationVO.exposureListSize}"
                href="javascript:void(-1)"
                >${p+1}</a
              >
            </div>
          </c:forEach>
        </div>
        <div class="next-page-btn">
          <c:if test="${paginationVO.hasNextGroup}">
            <div>
              <a
                data-url="/member/mypage/company/reviews"
                data-page-no="${memberPaginationVO.nextGroupStartPageNo}"
                data-exposure-list-size="${memberPaginationVO.exposureListSize}"
                href="javascript:void(-1)"
                >다음</a
              >
            </div>
            <div>
              <a
                data-url="/member/mypage/company/reviews"
                data-page-no="${memberPaginationVO.pageCount-1}"
                data-exposure-list-size="${memberPaginationVO.exposureListSize}"
                href="javascript:void(-1)"
                >마지막</a
              >
            </div>
          </c:if>
        </div>
      </div>
    </c:if>
  </body>
</html>
