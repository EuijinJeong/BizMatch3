<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<!-- <link rel="stylesheet" href="/css/common.css" /> -->
<link rel="stylesheet" href="/css/project_info.css" />
<!--  
<link rel="stylesheet" href="/css/project_inquiry_modal.css" />
<link rel="stylesheet" href="/css/project_inquiry_page.css" />
-->
<link rel="stylesheet" href="/css/pagination.css" />

<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/js/commont_list.js"></script>
<link rel="stylesheet" href="/css/comment_defult.css" />
</head>
<body>
	<%@ include file="/WEB-INF/views/common/after_login_header.jsp"%>
	<div class="card-include" data-projectid ="${projectId}">
		<%@ include file="/WEB-INF/views/common/myproject_card.jsp"%>
	</div>
	
	<div class="main-content-container">
		<div class="main-content">
			<div>
				<h1 class="main-content-title">업무내용</h1>
			</div>
			<div class="main-content-detail">${projectVO.pjDesc }</div>
		</div>
	</div>
	<div class="main-content-container">
		<div class="main-content">
			<div>
				<h1 class="main-content-title">모집요건</h1>
			</div>
			<div class="main-content-detail"></div>
		</div>
	</div>

	<div class="main-content-container">
		<div class="main-content">
			<div>
				<h1 class="main-content-title">근무환경</h1>
			</div>
			<div class="main-content-detail"></div>
		</div>
	</div>
	<div class="main-content-container">
		<div class="main-content">
			<div>
				<h1 class="main-content-title">프로젝트 문의</h1>
				<div class="create-new-comment">
				<button class="new-comment-button">새 문의 작성하기</button>
				</div>
			</div>
				
			<div class="comment-outer-box">
    			<div class="comment-middle-box">
					<div class="comment-linner-box">
						<c:choose>
		
							<c:when test="${not empty comments }">
					
								<c:forEach items="${comments}" var="comment">
									<c:if test="${ comment.isDlt eq '0'}">
									
									<div class="one-comment" style="padding-left: ${comment.lv*1.2}rem">					
										<div class="comment-upperside">
										<div class="comment-left-part">
											<div class="name"> ${comment.mbrNm} (${comment.athrId})</div>
											<div class="content" > ${comment.cmmntCntnt}</div>
										</div>
										<div class="comment-right-part">
											<div class="date-box">
											
												<div class="create-date"> 작성일: ${comment.crtdDt}</div>
											
												<c:if test="${not empty comment.lstModDt }">
													<div class="create-date">  수정일: ${comment.lstModDt}</div>
												</c:if>
											</div>
										
										
											<c:if test="${loginMemberVO.emilAddr eq comment.athrId}">
											<div class="fuction-line" 
												data-id="${comment.pjCmmntId}">
										
												<c:if test ="${not empty comment.cmmntCntnt}">
												<input class="modify-btn" type ="button" data-text="${comment.cmmntCntnt}" value="수정">
												</c:if>
												<c:if test ="$empty comment.cmmntCntnt}">
												<input class="modify-btn" type ="button" data-test="" value="수정">
												</c:if>
												<input class="delete-btn" type ="button" value="삭제">
												<input class="recomment-btn" type ="button" value="답글">

											</div>
											</c:if>
										</div>
										
									</div>
									</div>
									</c:if>
									<c:if test="${ comment.isDlt eq '1'}">
											<div  class="deleted-comment"  style="margin-left: ${(comment.lv-1)*1.2}rem">삭제된 댓글입니다.</div>
									</c:if>
								
									
										
							
								</c:forEach>
							</c:when>
							<c:otherwise>
								<p>없다</p>
							</c:otherwise>
						</c:choose>
					</div>	
				</div>	
			</div>
			
			<c:if test="${paginationVO.groupEndPageNo >0 }">
			
				<div class="page-div">	
					<div class="pre-page-btn">
			
						<c:if test="${paginationVO.hasPrevGroup}">
							<div>
								<a href="/project/info/${paginationVO.searchIdParam}?currPageNo=0&exposureListSize=${paginationVO.exposureListSize}">처음</a>
							</div>
							<div>
								<a href="/project/info/${paginationVO.searchIdParam}?currPageNo=${paginationVO.prevGroupStartPageNo}&exposureListSize=${paginationVO.exposureListSize}">이전</a>
							</div>
						</c:if>
	
					</div>
					<div class="page-number-btn">
				
						<c:forEach begin="${paginationVO.groupStartPageNo}" 
									end="${paginationVO.groupEndPageNo}"
									step = "1"
									var = "p" >
							<div class = "${paginationVO.currPageNo eq p ? 'active':'' }">
								<a href="/project/info/${paginationVO.searchIdParam}?currPageNo=${p}&exposureListSize=${paginationVO.exposureListSize}">
									${p+1}
								</a>
								</div>
						</c:forEach>
					</div>
					<div class="next-page-btn">
		 				<c:if test="${paginationVO.hasNextGroup}">
							<div>
								<a href="/project/info/${paginationVO.searchIdParam}?currPageNo=${paginationVO.nextGroupStartPageNo}&exposureListSize=${paginationVO.exposureListSize}">다음</a>
							</div>
							<div>
								<a href="/project/info/${paginationVO.searchIdParam}?currPageNo=${paginationVO.pageCount-1}&exposureListSize=${paginationVO.exposureListSize}">마지막</a>
							</div>
						</c:if>
					</div>
				</div>
			</c:if>
    	</div>
	</div>

	<dialog id="commentModal" class="commentModal">
    	<div class="modal-content" id="modal-content">
        	<div class="modal-container">
        		<div class="modal-inner-box">
                <div class="close-btn">&times;</div>
                
          		<textarea id="cmmntCntnt" name="cmmntCntnt" placeholder="댓글을 입력하세요..."></textarea>
         		<button class="submit-btn2" >댓글 달기</button>
         		</div>
        	</div>
      	</div>
    </dialog>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

</body>
</html>