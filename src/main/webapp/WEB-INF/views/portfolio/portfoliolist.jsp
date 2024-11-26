<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>포트폴리오</title>
    <link rel="stylesheet" href="/css/portfoliolist.css" />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/portfoliolist.js"></script>
       <link rel="stylesheet" href="/css/pagination.css" />
  </head>
  <body>
    <%@ include file="/WEB-INF/views/common/after_login_header.jsp" %>
    <div class="portfolio-container">
      <div class="portfolio-gallery">
        <div class="result"></div>
      </div>
      <button id="add-btn">등록</button>
		
		<div class="pagenation-box"></div>
					
	<!--  
      <div class="pagination">
        <a href="#">처음</a>
        <a href="#">&laquo;</a>
        <a href="#" class="active">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <a href="#">6</a>
        <a href="#">7</a>
        <a href="#">8</a>
        <a href="#">9</a>
        <a href="#">10</a>
        <a href="#">&raquo;</a>
        <a href="#">끝</a>
      </div>
   -->
    </div>
    <!-- 모달 영역 -->
    <div id="portfolioModal" class="modal">
      <div class="modal-content">
        <span class="close-button">&times;</span>
        <div class="content-box">
          <div class="summary-box">
            <div id="mbrPrtflTtl" class="mbrPrtflTtl"></div>
            <div class="text-line">
              <div class="weight">포트폴리오 상세</div>
              <p id="mbrPrtflText" class="mbrPrtflText" name="mbrPrtflText"></p>
              <div class="attach-file-list">
                첨부파일
                <div id="attList" class="attList"></div>
              </div>
            </div>
            <div class="button-box">
              <button class="edit">수정</button>
              <button class="delete-btn">삭제</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--포트폴리오 등록 모달-->
    <div id="insertModal" class="modal2">
      <div class="modal-content2">
        <button class="close-btn2">&times;</button>
        <form
          action="/member/newportfolio"
          method="post"
          id="addPortfolioForm"
          enctype="multipart/form-data"
        >
          <div class="content-box-area">
            <div class="content-box2">
              <div class="summary-box">
                <div class="about">프로젝트명</div>
                <div class="name">
                  <input
                    id="mbrPrtflTtl"
                    name="mbrPrtflTtl"
                    type="text"
                    value="${memberPortfolioVO.mbrPrtflTtl}"
                  />
                </div>
              </div>
              <div class="text-line">
                프로젝트 상세
                <textarea
                  id="mbrPrtflText"
                  name="mbrPrtflText"
                  type="text"
                  value="${memberPortfolioVO.mbrPrtflText}"
                ></textarea>
                <div class="attach-file-list">
                  <div>첨부파일</div>
                </div>
              </div>
              <div class="image-upload">
                <input class="fileList" type="file" name="attList[0]" />
                <button class="file-button" type="button" id="add_attr_file">
                  첨부자료 추가
                </button>
              </div>
              <input class="signupbtn" type="submit" value="등록하기" />
            </div>
          </div>
        </form>
      </div>
    </div>
  </body>
</html>
