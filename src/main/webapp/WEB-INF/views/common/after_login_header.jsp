<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="/css/after_login_header.css" />
<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/js/header.js"></script>
<div class="header-container">
  <div class="header">
    <div>
      <!-- 이거 로고 클릭하면 메인 페이지로 이동하게 수정하기. -->

      <img
        src="/img/teamLogo.svg"
        alt="logo"
        id="main-logo"
        class="main-logo"
      />
    </div>
    <div class="header-menu">
      <a href="/project/regist">프로젝트 등록</a>
      <a href="/project/findpage">프로젝트 찾기</a>
      <a href="/board/list">공지사항 및 문의 게시판</a>
    </div>
    <div class="header-btn">
      <div class="notification-menu">
        <a href=""
          ><img class="nofication-menu" src="/img/Bell.svg" alt=""
        /></a>

        <div class="notification-list">
          <div class="nofication-header"><p>전체 알람수</p></div>

          <div class="notification-item">
            <p class="notification-msg">알림 1: 새 메시지가 도착했습니다!</p>
          </div>
          <div class="notification-item">
            <p class="notification-msg">알림 2: 업데이트가 필요합니다.</p>
          </div>
          <div class="notification-item">
            <p class="notification-msg">알림 3: 새로운 댓글이 있습니다.</p>
          </div>
          <div class="notification-item">
            <p class="notification-msg">알림 4: 새로운 댓글이 있습니다.</p>
          </div>
          <div class="notification-item">
            <p class="notification-msg">알림 5: 새로운 댓글이 있습니다.</p>
          </div>
          <div class="notification-item">
            <p class="notification-msg">알림 6: 새로운 댓글이 있습니다.</p>
          </div>
          <div class="notification-item">
            <p class="notification-msg">알림 7: 새로운 댓글이 있습니다.</p>
          </div>
          <div class="notification-item">
            <p class="notification-msg">알림 8: 새로운 댓글이 있습니다.</p>
          </div>
          <div class="notification-item">
            <p class="notification-msg">알림 9: 새로운 댓글이 있습니다.</p>
          </div>
        </div>
      </div>
      <div class="notification-mypage-menu">
        <a href=""
          ><img
            src="/img/User.svg"
            alt=""
            class="header-email notification-mypage-menu"
            id="sessionA"
            data-email="${sessionScope._LOGIN_USER_.emilAddr}"
            data-mbrctgry="${sessionScope._LOGIN_USER_.mbrCtgry}"
            data-cmpid="${sessionScope._LOGIN_USER_.cmpId}"
        /></a>
        <div
          class="notification-mypage-list"
          data-membertype="${sessionScope._LOGIN_USER_.mbrCtgry}"
          data-id="${sessionScope._LOGIN_USER_.emilAddr}"
        >
          <div class="notification-mypage-item">
            <p class="notification-mypage-msg my-profile">프로필 관리</p>
          </div>
          <div class="notification-mypage-item">
            <p class="notification-mypage-msg my-info">내 정보 관리</p>
          </div>
          <div class="notification-mypage-item">
            <p class="notification-mypage-msg my-project">프로젝트 관리</p>
          </div>
          <div class="notification-mypage-item">
            <p class="notification-mypage-msg log-out">로그아웃</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
