<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>BizMatch - 프로젝트 문의</title>

<link rel="stylesheet" href="/css/project_inquiry_page.css" />
<link rel="stylesheet" href="/css/project_inquiry_card.css" />
<link rel="stylesheet" href="/css/project_inquiry_modal.css" />

<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/js/commont_list.js"></script>

</head>
<body>
	<%@ include file="/WEB-INF/views/common/after_login_header.jsp" %>
	<div class="project-inquiry-page">
   
     <div class="project-inquiry-area">
        <%@ include file="/WEB-INF/views/common/project_card.jsp" %> 
      </div>  
    </div>
    <div ><input class="show-model-button" type="button" value="문의 댓글 등록하기"></div>
    <!-- 문의 댓글 영역 -->
    <div class="inquiry-comment-area">
    	<div class="inquiry-comment-section">
			<div class="reply-box">
				<c:choose>
		
					<c:when test="${not empty comments }">
				
						<c:forEach items="${comments}" var="comment">
				
				
							<div class="one-comment" style="padding-left: ${comment.lv*3}rem">					
								<div class="comment-left-part">
									<div class="name"> ${comment.mbrNm} (${comment.athrId})</div>
									<div class="content"> ${comment.cmmntCntnt}</div>
								</div>
								<div class="comment-right-part">
									<div class="date-box">
									
										<div class="create-date"> 작성일: ${comment.crtdDt}</div>
									
										<c:if test="${not empty comment.lstModDt }">
											<div class="create-date">  수정일: ${comment.lstModDt}</div>
										</c:if>
									</div>
							
									<div class="fuction-line" 
										 data-id="${comment.pjCmmntId}">
										<input class="modify-btn" type ="button" value="수정">
										<input class="delete-btn" type ="button" value="삭제">
										<input class="recomment-btn" type ="button" value="답글">

									</div>
								</div>
							</div>
						
						
							<c:if test="${ comment.isDlt eq '1'}">
								<div>삭제된 댓글입니다.</div>
							</c:if>
								
					
						</c:forEach>
					</c:when>
					<c:otherwise>
						<p>없다</p>
					</c:otherwise>
				</c:choose>
			</div>	
		</div>	

		<div class="page-div">	
			<div class="pre-page-btn">
		
				<c:if test="${paginationVO.hasPrevGroup}">
					<div>
						<a href="/comment/list?currPageNo=0&exposureListSize=${paginationVO.exposureListSize}">처음</a>
					</div>
					<div>
						<a href="/comment/list?currPageNo=${paginationVO.prevGroupStartPageNo}&exposureListSize=${paginationVO.exposureListSize}">이전</a>
					</div>
				</c:if>

			</div>
			<div class="page-number-btn">
			
				<c:forEach begin="${paginationVO.groupStartPageNo}" 
					  			end= "${paginationVO.groupEndPageNo}"
					  			step = "1"
					  			var = "p" >
					<div class = "${paginationVO.currPageNo eq p ? 'active':'' }">
						<a href="/comment/list?currPageNo=${p}&exposureListSize=${paginationVO.exposureListSize}">
							${p+1}
						</a>
					</div>
				</c:forEach>
				 
				 
				
			</div>
			<div class="next-page-btn">
	 			<c:if test="${paginationVO.hasNextGroup}">
					<div>
						<a href="/comment/list?currPageNo=${paginationVO.nextGroupStartPageNo}&exposureListSize=${paginationVO.exposureListSize}">다음</a>
					</div>
					<div>
						<a href="/comment/list?currPageNo=${paginationVO.pageCount-1}&exposureListSize=${paginationVO.exposureListSize}">마지막</a>
					</div>
				</c:if>
			</div>
		</div>
    </div>

<!-------------------------------  -->
        <dialog id="commentModal" >
      <div class="modal-content" id="modal-content">

        <div class="modal-container">
                 <div class="close-btn">&times;</div>
          <textarea id="cmmntCntnt" name="cmmntCntnt" placeholder="댓글을 입력하세요..."></textarea>
          <button class="submit-btn" >댓글 달기</button>
        </div>
      </div>
    </dialog>
   
    
 
</body>
</html>