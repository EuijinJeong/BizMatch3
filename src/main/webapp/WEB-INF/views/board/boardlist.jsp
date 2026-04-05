<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link rel="stylesheet" href="/css/boardlist.css" />
    <link rel="stylesheet" href="/css/pagination.css" />
  </head>
  <body>
    <%@ include file="/WEB-INF/views/common/after_login_header.jsp" %>
    <div class="main-box">
    <div class="content-box">
      <h2 class="main-title">통합게시판</h2>
      <div class="function-line">
      
        <a class="write-btn" href="/board/write">글쓰기</a>
        
      </div>
      <div class="post-list-box">
        <div class="subject-line">
          <div>종류</div>
          <div>제목</div>
          <div>작성자</div>
          <div>공개여부</div>
          <div>수정일</div>
          <div>조회수</div>
        </div>
        
        <c:choose>
        <c:when test="${not empty paginationBoardList }">
	        <c:forEach items="${paginationBoardList}" var="line">
	            <div class="subject-line" >
	              <c:if test = "${line.pstCtgry eq '0'}">
	              	<div><span class="red-box">공지</span></div>
	              </c:if>
		          <c:if test = "${line.pstCtgry eq '1'}">
	              	<div class= "blue-box">문의</div>
	              </c:if>
		          <div ><a class="title" href="/board/view/${line.pstId}">${line.pstNm}</a></div>
		          <div>${line.mbrNm}</div>
		          <c:if test = "${line.isPstOpn eq '0'}">
	              	<div>공개</div>
	              </c:if>
		          <c:if test = "${line.isPstOpn eq '1'}">
				   		<div>비공개</div>
				   </c:if>
		          <div>${line.lstModDt }</div>
		          <div>${line.pstHt }</div>
	            </div>
	            
	            
	        </c:forEach>
        </c:when>
        </c:choose>
        
 
        <c:choose>
		<c:when test="${paginationBoardList.size()<5}">
		
		  <c:forEach begin="0" end="${10 - paginationBoardList.size()-1}" var="line">
            <div class="subject-line">
	          <div></div>
	          <div></div>
	          <div></div>
	          <div></div>
	          <div></div>
	          <div></div>
            </div>
        </c:forEach>
		</c:when>
        </c:choose>
      </div>
      			<c:choose>
		
							<c:when test="${paginationVO.groupEndPageNo > 0 }">
	
		<div class="page-div">	
			<div class="pre-page-btn">
		
				<c:if test="${paginationVO.hasPrevGroup}">
					<div>
						<a class="white-text" href="/board/list?currPageNo=0&exposureListSize=${paginationVO.exposureListSize}">처음</a>
					</div>
					<div>
						<a class="white-text" href="/board/list?currPageNo=${paginationVO.prevGroupStartPageNo}&exposureListSize=${paginationVO.exposureListSize}">이전</a>
					</div>
				</c:if>

			</div>
			<div class="page-number-btn">
			
				<c:forEach begin="${paginationVO.groupStartPageNo}" 
					  			end= "${paginationVO.groupEndPageNo}"
					  			step = "1"
					  			var = "p" >
					<div class = "${paginationVO.currPageNo eq p ? 'active':'' }">
						<a class= "white-text"
						href="/board/list?currPageNo=${p}&exposureListSize=${paginationVO.exposureListSize}">
							${p+1}
						</a>
					</div>
				</c:forEach>
				 
				 
				
			</div>
			<div class="next-page-btn">
	 			<c:if test="${paginationVO.hasNextGroup}">
					<div class = "number-box">
						<a class= "white-text" 
						href="/board/list?currPageNo=${paginationVO.nextGroupStartPageNo}&exposureListSize=${paginationVO.exposureListSize}">다음</a>
					</div>
					<div class = "number-box">
						<a class="white-text" 
						href="/board/list?currPageNo=${paginationVO.pageCount-1}&exposureListSize=${paginationVO.exposureListSize}">마지막</a>
					</div>
				</c:if>
			</div>
		</div>
		</c:when>
		</c:choose>
    </div>

    </div>
    </body>
</html>
