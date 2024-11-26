<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="/css/project_card.css" />
    <link rel="stylesheet" href="/css/myproject.css" />
    <link rel="stylesheet" href="/css/projectfind.css" />
    <link rel="stylesheet" href="/css/project_card.css" />
    <link rel="stylesheet" href="/css/pagination.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
    />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/myproject.js"></script>
    <title>BizMatch | 프로젝트 지원</title>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/common/after_login_header.jsp" %>
    <div class="projectfind-page">
      <div class="container">
        <div class="container-box">
          <h2 class="container-title" id="my-project">내 프로젝트</h2>
          <h2 class="container-title">/</h2>
          <h2 class="container-title" id="apply-project">지원 프로젝트</h2>
          <form action="/project/find" method="get">
            <div class="project-find">
              <div class="search-box">
                <select class="search-type" name="searchType">
                  <option value="entire" ${searchBoardVO.searchType == "entire" ? "selected" : ""}>전체</option>
                  <option value="pjTtl" ${searchBoardVO.searchType == "pjTtl" ? "selected" : ""}>제목</option>
                  <option value="pjDesc" ${searchBoardVO.searchType == "pjDesc" ? "selected" : ""}>내용</option>
                </select>
                <input
                  type="text"
                  name="searchKeyword"
                  class="search-keyword"
                  placeholder="어떤 프로젝트를 찾으시나요?"
                />
                <input type="hidden" name="orderBy" id="orderBy" value="latest" />
                <button type="submit" class="search-btn">검색</button>
              </div>
            </div>
          </form>
        </div>
        <div style="display: flex; justify-content: space-between;">
          <div class="menu">
            <ul class="categories">
              <li><a href="#">전체</a></li>
              <li><a href="#">IT·프로그래밍</a></li>
              <li><a href="#">디자인</a></li>
              <li><a href="#">마케팅</a></li>
              <li><a href="#">영상·사진·음향</a></li>
              <li><a href="#">기획</a></li>
            </ul>
          </div>
          <div class="filters">
            <span id="latest" data-order="latest" class="latest active">최신순</span>
            <span id="deadline" data-order="deadline" class="deadline">마감임박순</span>
            <span id="amount" data-order="amount" class="amount">금액높은순</span>
          </div>
        </div>
      </div>
    </div>

    <!-- 여기에 모든 프로젝트 카드 리스트 나올꺼임. -->
    <div id="result"></div>

    <div class="pagenation pagenation-ajax page-div">
      <!--searchProjectVO -->
      <div class="pre-page-btn">
        <c:if test="${searchProjectVO.hasPrevGroup}">
          <div>
            <a class="white-text" data-url="/project/find" data-page-no="0" data-exposure-list-size="${searchProjectVO.exposureListSize}" href="javascript:void(-1)">처음</a>
          </div>
          <div>
            <a data-url="/project/find" class="white-text" data-page-no="${searchProjectVO.prevGroupStartPageNo}" data-exposure-list-size="${searchProjectVO.exposureListSize}" href="javascript:void(-1)">이전</a>
          </div>
        </c:if>
      </div>

      <div class="page-number-btn">
        <c:forEach begin="${searchProjectVO.groupStartPageNo}" end="${searchProjectVO.groupEndPageNo}" step="1" var="p">
          <div class="number-box ${searchProjectVO.currPageNo eq p ? 'active' : ''}">
            <a data-url="/project/find" class="white-text" data-page-no="${p}" data-exposure-list-size="${searchProjectVO.exposureListSize}" href="javascript:void(-1)">
              ${p + 1}
            </a>
          </div>
        </c:forEach>
      </div>

      <div class="next-page-btn">
        <c:if test="${searchProjectVO.hasNextGroup}">
          <div>
            <a data-url="/project/find" class="white-text" data-page-no="${searchProjectVO.nextGroupStartPageNo}" data-exposure-list-size="${searchProjectVO.exposureListSize}" href="javascript:void(-1)">다음</a>
          </div>
          <div>
            <a data-url="/project/find" class="white-text" data-page-no="${searchProjectVO.pageCount - 1}" data-exposure-list-size="${searchProjectVO.exposureListSize}" href="javascript:void(-1)">마지막</a>
          </div>
        </c:if>
      </div>
    </div>
    <!-- 리뷰 등록 모달창 -->
    <div id="reportModal" class="modal">
      <div class="modal-content">
        <div class="review-box">
          <div class="custom-dropdown">
            <div class="dropdown-selected">별점을 선택해주세요</div>
            <div class="dropdown-options">
              <div class="dropdown-option" data-value="0.0">
                <span class="star-icons"
                  ><i class="far fa-star"></i><i class="far fa-star"></i
                  ><i class="far fa-star"></i><i class="far fa-star"></i
                  ><i class="far fa-star"></i
                ></span>
              </div>
              <div class="dropdown-option" data-value="0.5">
                <span class="star-icons"
                  ><i class="fas fa-star-half-alt"></i
                  ><i class="far fa-star"></i><i class="far fa-star"></i
                  ><i class="far fa-star"></i><i class="far fa-star"></i
                ></span>
              </div>
              <div class="dropdown-option" data-value="1.0">
                <span class="star-icons"
                  ><i class="fas fa-star"></i><i class="far fa-star"></i
                  ><i class="far fa-star"></i><i class="far fa-star"></i
                  ><i class="far fa-star"></i
                ></span>
              </div>
              <div class="dropdown-option" data-value="1.5">
                <span class="star-icons"
                  ><i class="fas fa-star"></i
                  ><i class="fas fa-star-half-alt"></i
                  ><i class="far fa-star"></i><i class="far fa-star"></i
                  ><i class="far fa-star"></i
                ></span>
              </div>
              <div class="dropdown-option" data-value="2.0">
                <span class="star-icons"
                  ><i class="fas fa-star"></i><i class="fas fa-star"></i
                  ><i class="far fa-star"></i><i class="far fa-star"></i
                  ><i class="far fa-star"></i
                ></span>
              </div>
              <div class="dropdown-option" data-value="2.5">
                <span class="star-icons"
                  ><i class="fas fa-star"></i><i class="fas fa-star"></i
                  ><i class="fas fa-star-half-alt"></i
                  ><i class="far fa-star"></i><i class="far fa-star"></i
                ></span>
              </div>
              <div class="dropdown-option" data-value="3.0">
                <span class="star-icons"
                  ><i class="fas fa-star"></i><i class="fas fa-star"></i
                  ><i class="fas fa-star"></i><i class="far fa-star"></i
                  ><i class="far fa-star"></i
                ></span>
              </div>
              <div class="dropdown-option" data-value="3.5">
                <span class="star-icons"
                  ><i class="fas fa-star"></i><i class="fas fa-star"></i
                  ><i class="fas fa-star"></i
                  ><i class="fas fa-star-half-alt"></i
                  ><i class="far fa-star"></i
                ></span>
              </div>
              <div class="dropdown-option" data-value="4.0">
                <span class="star-icons"
                  ><i class="fas fa-star"></i><i class="fas fa-star"></i
                  ><i class="fas fa-star"></i><i class="fas fa-star"></i
                  ><i class="far fa-star"></i
                ></span>
              </div>
              <div class="dropdown-option" data-value="4.5">
                <span class="star-icons"
                  ><i class="fas fa-star"></i><i class="fas fa-star"></i
                  ><i class="fas fa-star"></i><i class="fas fa-star"></i
                  ><i class="fas fa-star-half-alt"></i
                ></span>
              </div>
              <div class="dropdown-option" data-value="5.0">
                <span class="star-icons"
                  ><i class="fas fa-star"></i><i class="fas fa-star"></i
                  ><i class="fas fa-star"></i><i class="fas fa-star"></i
                  ><i class="fas fa-star"></i
                ></span>
              </div>
            </div>
          </div>
          <div class="review-background">
            <textarea
              id="reviewContent"
              placeholder="리뷰 내용을 입력해주세요."
            ></textarea>
          </div>
          <div class="modal-button-box">
            <button id="submitReview">리뷰 등록</button>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>