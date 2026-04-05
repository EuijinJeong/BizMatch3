<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
          <c:choose>
            <c:when test="${empty sessionScope._LOGIN_USER_.cmpId }">
              <div class="name">
                <h2>${sessionScope._LOGIN_USER_.mbrNm}</h2>
              </div>
            </c:when>
            <c:otherwise>
              <div class="name"><h2>${companyVO.cmpnyNm}</h2></div>
            </c:otherwise>
          </c:choose>
          <div class="star">
            <!-- 전체 별 -->
            <c:forEach var="i" begin="1" end="${averageRate}" step="1">
              <i class="fas fa-star"></i>
            </c:forEach>
            <!-- 반 별 -->
            <c:if test="${averageRate % 1 >= 0.5}">
              <i class="fas fa-star-half-alt"></i>
            </c:if>
            <!-- 빈 별 -->
            <c:forEach var="i" begin="1" end="${5 - averageRate}" step="1">
              <i class="far fa-star"></i>
            </c:forEach>
          </div>
          <c:choose>
            <c:when test="${empty sessionScope._LOGIN_USER_.cmpId }">
              <div class="category">
                ${sessionScope._LOGIN_USER_.mjrId}>${sessionScope._LOGIN_USER_.smjrId}
              </div>
              <div class="homepage-button">
                <div class="homepage" data-url="${companyVO.cmpnySiteUrl}">
                  ${companyVO.cmpnySiteUrl}
                </div>
                <div class="button-box">
                  <button class="edit-button" id="mypageeditbutton">
                    수정
                  </button>
                </div>
              </div>
            </c:when>
            <c:otherwise>
              <div class="category">
                <c:choose>
                  <c:when
                    test="${not empty mbrIndstrVO && not empty mbrIndstrVO.mjrNm && not empty mbrIndstrVO.smjrNm}"
                  >
                    ${mbrIndstrVO.mjrNm} > ${mbrIndstrVO.smjrNm}
                  </c:when>
                  <c:otherwise> 소속 산업 정보가 없습니다. </c:otherwise>
                </c:choose>
              </div>
              <div class="homepage-button">
                <div class="homepage" data-url="${companyVO.cmpnySiteUrl}">
                  ${companyVO.cmpnySiteUrl}
                </div>

                <div class="button-box">
                  <button class="edit-button" id="mypageeditbutton">
                    수정
                  </button>
                </div>
              </div>
            </c:otherwise>
          </c:choose>
        </div>
      </div>
    </section>
  </body>
</html>
