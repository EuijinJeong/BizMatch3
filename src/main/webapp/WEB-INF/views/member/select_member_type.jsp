<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="/css/before_login_header.css" />
    <link rel="stylesheet" href="/css/select_member_type.css" />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/select_member_type.js"></script>
    <title>BizMatch | 회원유형 선택 페이지</title>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/common/before_login_header.jsp" %>
    <div class="title">
      <h1>회원 유형을 선택해주세요</h1>
    </div>
    <div class="select-container">
      <div class="select-type">
        <div class="content-box" id="content-box-company">
          <img
            src="/img/Company-amico 1.png"
            alt="기업 사진"
            class="box-image"
          />
          <h2>기업형</h2>
          <p>사업자 등록증이 있는 경우</p>
        </div>
        <div class="content-box" id="content-box-free">
          <img
            src="/img/Personal finance-rafiki 1.png"
            alt="프리랜서 사진"
            class="box-image"
          />
          <h2>개인형</h2>
          <p>사업자 등록증이 없는 경우</p>
        </div>
      </div>
    </div>
  </body>
</html>
