<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %> <%@ taglib prefix="c"
uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link
      type="stylesheet"
      type="text/css"
      href="/css/before_login_header.css"
    />
    <link type="stylesheet" href="/css/common.css" />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/header.js"></script>
    <script type="text/javascript" src="/js/before_login_header.js"></script>
  </head>
  <body>
    <div class="header-container">
      <div class="header">
        <div>
          <a href="/">
            <img src="/img/teamLogo.svg" alt="logo" id="main-logo" />
          </a>
        </div>
        <div class="header-btn">
          <div><button class="login" id="loginBtn">로그인</button></div>
          <div><button class="sign-up" id="sign-up">회원가입</button></div>
        </div>
      </div>
    </div>
    <div id="overlay" class="overlay"></div>
    <div class="login-modal" id="login-modal">
      <div class="login-modal-container">
        <span class="modal-close-btn" id="modal-close-btn">x</span>
        <div class="login-modal-img-area">
          <img src="/img/teamLogo.svg" alt="" />
        </div>
        <div class="login-modal-btns">
          <div class="signin_box">
            <c:if test="${not empty error }">
              <div class="errorMsg">${error}</div>
            </c:if>
            <form action="/member/signin" method="post">
              <div class="same_box">
                <input
                  type="email"
                  placeholder=" "
                  id="login-input-email"
                  name="emilAddr"
                  required
                />
                <label for="login-input-email">이메일</label>
              </div>

              <div class="same_box">
                <input
                  type="password"
                  placeholder=" "
                  id="login-input-pwd"
                  name="pwd"
                  required
                />
                <label for="login-input-pwd">비밀번호</label>
                <div>${ex_message}</div>
              </div>

              <div class="same_box">
                <button class="signin_button">로그인</button>
              </div>
              <div class="same_box"><button>Sign up with Google</button></div>
            </form>
          </div>
        </div>

        <ul class="account-menu">
          <li class="account-menu-text">
            <a href="/member/findpwd">비밀번호 찾기</a>
          </li>
          <li>/</li>
          <li class="account-menu-text">
            <a href="/member/select/membertype">회원가입 </a>
          </li>
        </ul>
      </div>
    </div>
  </body>
</html>
