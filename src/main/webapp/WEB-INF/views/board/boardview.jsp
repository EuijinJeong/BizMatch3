<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link rel="stylesheet" href="/css/boardview.css" />
    <link rel="stylesheet" href="/css/pagination.css" />
    <link rel="stylesheet" href="/css/comment_defult.css" />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/board_view.js"></script>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/common/after_login_header.jsp" %>
    
    <div class="main-box">
    <div class="content-box">
	      <div class="title">${boardVO.pstNm}</div>
	
	      <div class="post-info">
	        <div class="post-type">
	     
	          	<c:if test = "${boardVO.pstCtgry eq '0'}">
		              	<div class="type-deco">공지</div>
		              </c:if>
			          <c:if test = "${boardVO.pstCtgry eq '1'}">
		              	<div class= "blue-box">문의</div>
		              </c:if>
		     
	          <div class="author">작성자: ${boardVO.mbrNm}</div>
	        </div>
	        <div class="times">
	
	          <div>마지막 수정일: ${boardVO.lstModDt }</div>
	        </div>
	      </div>
	      <div class="main-content">${boardVO.pstCntnt}</div>
	      <c:choose>
	      	<c:when test="${boardVO.athrId eq loginInfo.emilAddr}">
	      	      <div class="function-line">
	        <a class="modify-btn" href="/board/modify/${boardVO.pstId}">수정</a>
	      </div>
	      
	      	</c:when>
	      </c:choose>
			
			<div class="comment-box" data-boardid="${boardVO.pstId}">
				<div class="write-box">
					<textarea class="comment-text" ></textarea>
					<button class="create-btn" >등록</button>
				</div>
				<div class="list-box">
						<c:choose>
		
							<c:when test="${not empty comments }">
					
								<c:forEach items="${comments}" var="comment">
					
									<c:if test="${ comment.isDlt eq '0'}">
										
									
									<div class="one-comment" style="margin-left: ${(comment.lv-1)*1.2}rem">					
										<div class="comment-upperside">
											<div class="comment-left-part">
												<div class="name"> ${comment.mbrNm} (${comment.athrId})</div>
												<div class="content"> ${comment.cmmntCntnt}</div>
												<div class="modify-write-box">
												<textarea class="modify-text" >${comment.cmmntCntnt}</textarea>
											<button class="submit-btn" >등록</button>
										</div>
										
										</div>
										<div class="comment-right-part">
											<div class="date-box">
											
												<div class="create-date">${comment.lstModDt}</div>
											
											</div>
											<c:if test ="${comment.athrId eq loginInfo.emilAddr}">
											<div class="fuction-line" 
												data-id="${comment.cmmntId}">
										
												<input class="modify-comment-btn" type ="button" value="수정">
												<input class="delete-comment-btn" type ="button" value="삭제">
												<input class="recomment-btn" type ="button" value="답글">
											
											</div>
											</c:if>
										</div>
										</div>
										<div class="recomment-write-box">
											<textarea class="recomment-text" ></textarea>
											<button class="submit-btn" >등록</button>
										</div>
									</div>
									</c:if>
								
									<c:if test="${ comment.isDlt eq '1'}">
										<div class="deleted-comment" style="margin-left: ${(comment.lv-1)*1.2}rem">삭제된 댓글입니다.</div>
									</c:if>
										
							
								</c:forEach>
							</c:when>
							<c:otherwise>
								<div>댓글이 존재하지 않습니다.</div>
							</c:otherwise>
						</c:choose>
	     			
				</div>
			
			<c:choose>
		
							<c:when test="${paginationVO.groupEndPageNo > 0 }">
					
			<div class="page-div">	
			<div class="pre-page-btn">
		
				<c:if test="${paginationVO.hasPrevGroup}">
					<div>
						<a class ="white-text" href="/board/view${boardVO.pstNm}?currPageNo=0&exposureListSize=${paginationVO.exposureListSize}">처음</a>
					</div>
					<div>
						<a class ="white-text" href="/board/view${boardVO.pstNm}?currPageNo=${paginationVO.prevGroupStartPageNo}&exposureListSize=${paginationVO.exposureListSize}">이전</a>
					</div>
				</c:if>

			</div>
			<div  class="page-number-btn">
			
				<c:forEach begin="${paginationVO.groupStartPageNo}" 
					  			end= "${paginationVO.groupEndPageNo}"
					  			step = "1"
					  			var = "p" >
					<div class = "${paginationVO.currPageNo eq p ? 'active':'' }">
						<a class ="white-text" href="/board/view${boardVO.pstNm}?currPageNo=${p}&exposureListSize=${paginationVO.exposureListSize}">
							${p+1}
						</a>
					</div>
				</c:forEach>
				 
				 
				
			</div>
			<div class="next-page-btn">
	 			<c:if test="${paginationVO.hasNextGroup}">
					<div>
						<a class ="white-text" href="/board/view${boardVO.pstNm}?currPageNo=${paginationVO.nextGroupStartPageNo}&exposureListSize=${paginationVO.exposureListSize}">다음</a>
					</div>
					<div>
						<a class ="white-text" href="/board/view${boardVO.pstNm}?currPageNo=${paginationVO.pageCount-1}&exposureListSize=${paginationVO.exposureListSize}">마지막</a>
					</div>
				</c:if>
			</div>
		</div>
		
		</c:when>
		</c:choose>
			</div>
	    </div>
	    
	    
	    
    </div>
  </body>
</html>
