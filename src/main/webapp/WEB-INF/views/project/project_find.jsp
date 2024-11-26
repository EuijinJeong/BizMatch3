<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!-- JSTL을 위한 Directive 선언 -->
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="/css/projectfind.css" />
    <link rel="stylesheet" href="/css/project_card.css" />
        <link rel="stylesheet" href="/css/pagination.css" />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="/css/pagination.css" />
	<script type="text/javascript" src="/js/projectfind.js"></script>
  <title>BizMatch | 프로젝트 찾기</title>
  </head>
  <body>
    
      <%@ include file="/WEB-INF/views/common/after_login_header.jsp" %>

      <div class="projectfind-page">
        <div class="container">
          <div class="container-box">
          <h1  class="container-title">프로젝트 찾기</h1>
          <!-- <div class="border-line"></div> -->
          <form action="/project/find" method="get">
          <div class="project-find">
            <div class="search-box">
              <select class="search-type" name="searchType">
                <option value="entire"  ${searchBoardVO.searchType == "entire" ? "selected" : ""}>전체</option>
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
          <div style="display: flex; justify-content: space-between;" >
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
        <!-- 여기에 모든 프로젝트 카드 리스트 나올꺼임.  -->
        <div id="result"> 
        </div>
        
        
        <div class="pagenation pagenation-ajax page-div">
          <!--searchProjectVO -->
          <div class="pre-page-btn">
            <c:if test="${searchProjectVO.hasPrevGroup}">
              <div>
                <a class="white-text"
                data-url="/project/find" 
                   data-page-no="0" 
                   data-exposure-list-size="${searchProjectVO.exposureListSize}" 
                   href="javascript:void(-1)">처음</a>
              </div>
              <div>
                <a data-url="/project/find" 
                class="white-text"
                  data-page-no="${searchProjectVO.prevGroupStartPageNo}" 
                  data-exposure-list-size="${searchProjectVO.exposureListSize}" 
                  href="javascript:void(-1)">이전</a>
              </div>
            </c:if>
          </div>
          <div class="page-number-btn">
            <c:forEach begin="${searchProjectVO.groupStartPageNo}"
                      end= "${searchProjectVO.groupEndPageNo}"
                      step = "1"
                      var = "p" >
              <div class = "number-box ${searchProjectVO.currPageNo eq p ? 'active':'' }">
                <a data-url="/project/find" 
                class="white-text"
                  data-page-no="${p}" 
                  data-exposure-list-size="${searchProjectVO.exposureListSize}" 
                  href="javascript:void(-1)">
                  ${p+1}
                </a>
              </div>
            </c:forEach>
          </div>
          <div class="next-page-btn">
             <c:if test="${searchProjectVO.hasNextGroup}">
              <div>
                <a data-url="/project/find" 
                class="white-text"
                data-page-no="${searchProjectVO.nextGroupStartPageNo}" 
                data-exposure-list-size="${searchProjectVO.exposureListSize}" 
                href="javascript:void(-1)">다음</a>
              </div>
              <div>
                <a data-url="/project/find" 
                class="white-text"
                  data-page-no="${searchProjectVO.pageCount-1}" 
                  data-exposure-list-size="${searchProjectVO.exposureListSize}" 
                  href="javascript:void(-1)">마지막</a>
              </div>
            </c:if>
          </div>
        </div>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
      </div>  
  </body>
</html>