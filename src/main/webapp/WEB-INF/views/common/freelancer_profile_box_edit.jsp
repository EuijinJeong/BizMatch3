<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="/css/profile_box.css" />
    <title>BizMatch | 마이페이지</title>
  </head>
  <body>
    <section class="profile">
      <div class="profile-box">
        <div class="img">
          <img src="/img/profile.svg" alt="profile-img" />
        </div>
        <div class="information">
          <h2>${sessionScope._LOGIN_USER_.mbrNm}</h2>
          <div class="category">
            ${memberMyPageIndsryVO.mjrNm}>${memberMyPageIndsryVO.smjrNm}
          </div>
          <div class="homepage-button">
            <div class="button-box">
              <button class="edit-button" id="mypageeditbutton">저장</button>
            </div>
          </div>
        </div>
      </div>
    </section>
  </body>
</html>
