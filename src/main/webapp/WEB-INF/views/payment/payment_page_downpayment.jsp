<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@
taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <title>BizMatch | 계약금 납부</title>
    <link rel="stylesheet" type="text/css" href="/css/common.css" />
    <link rel="stylesheet" type="text/css" href="/css/payment_page.css" />
    <link rel="stylesheet" type="text/css" href="/css/after_login_header.css" />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
    <script type="text/javascript" src="/js/downpayment.js"></script>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/common/after_login_header.jsp" %>
    <div class="border-line"></div>
    <div class="project-card">
      <div class="project-box">
        <div class="project-head">
          <div class="project-head-front">
            <div class="status">모집중</div>
            <h2>${projectVO.pjTtl}</h2>
          </div>
          <div class="post-date">등록일자 ${projectVO.rgstrDt}</div>
        </div>
        <div class="project-body">
          <div class="project-body-box">
            <div class="project-body-title">프로젝트 분야</div>
            <div class="project-body-content bold">${projectVO.smjrNm}</div>
          </div>
          <div class="project-body-box">
            <div class="project-body-title">관련기술</div>
            <div class="project-body-content">
              <div class="circle-box">
                <div class="circle"></div>
              </div>
              <c:forEach var="skills" items="${projectVO.projectSkillList}">
                <div class="language">${skills.prmStkVO.prmStk}</div>
              </c:forEach>
            </div>
          </div>
          <div class="project-body-box">
            <div class="project-body-title">모집 마감일</div>
            <div class="project-body-content">${projectVO.pjRcrutEndDt}</div>
          </div>
          <div class="project-body-box">
            <div class="project-body-title">프로젝트 일정</div>
            <div class="project-body-content">
              ${projectVO.strtDt} ~ ${projectVO.endDt}
            </div>
          </div>
        </div>
      </div>
      <div class="payment-box">
        <div class="payment-text">
          <div class="payment-box-word1" data-paymentType="0" id="paymentType">
            계약금
          </div>
          <div class="payment-box-word2">
            ${projectVO.paymentVO.cntrctAmt}원
          </div>
        </div>
        <button
          id="paymentBox"
          class="payment-box-btn"
          data-pjid="${projectVO.pjId }"
        >
          납부하기
        </button>
      </div>
    </div>
  </body>
</html>
