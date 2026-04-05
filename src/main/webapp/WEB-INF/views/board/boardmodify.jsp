<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/boardwrite.css" />
<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/js/board_modify.js"></script>

</head>
<body>
  <%@ include file="/WEB-INF/views/common/after_login_header.jsp" %>
  <div class="main-box">
 <div class="content-box" data-id="${boardVO.pstId}" >
      
      <div class="title">게시글 작성</div>
      <div class="function-line">
        <button id="delete">
          <img class= "button-image "src="/img/delete.png" />
          <div class="white-text">삭제</div>
        </button>
        
	        <button id="submit">
	          <img class= "button-image " id="save" src="/img/Save.png" />
	          <div class="white-text">저장</div>
	        </button>
        
        <!--  
        <button id ="submit">
          <img class= "button-image " src="/img/upload.png" />
          <div class="white-text" >등록</div>
        </button>
        -->
      </div>
      <div class="post-writing-box">
      
      	<div class= first-line>
        <select id="genre" name="genreSection">
        
        <c:if test ="${loginMemberVO.emilAddr eq 'test@test'}">
          <option value="0" >공지</option>
        </c:if>
          <option value="1">문의</option>
          <!--  
          	<option value="report">신고</option>
          -->
        </select>
        
		<div class="ispublic">
		<input id="ck-box" type="checkbox">
		<label>비공개</label>
		</div>
		
        <input
          type="text"
          placeholder="제목을 입력해주세요"
          id="title"
          class="empty"
          value="${boardVO.pstNm}"
        />
		</div>
		
        <textarea
          contenteditable="true"
          class="writing-place"
          id="content"
          placeholder="본문 작성"
          
        >${boardVO.pstCntnt}</textarea>
       
      </div>
      <!--  
      <div class="function-line">
        <button>
          <img class= "button-image " src="/img/attach_file.png" />
          <div class="white-text">첨부파일</div>
        </button>
        </div>
      -->
      </div>
    </div>
</body>
</html>