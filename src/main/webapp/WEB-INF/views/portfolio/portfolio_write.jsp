<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>BizMatch | 포트폴리오 등록</title>
    <link rel="stylesheet" href="/css/portfolio_write.css" />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/portfolio_write.js"></script>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/common/after_login_header.jsp" %>
    <form
      action="/member/newportfolio"
      method="post"
      id="addPortfolioForm"
      enctype="multipart/form-data"
    >
      <div class="content-box-area">
        <div class="content-box">
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
  </body>
</html>
