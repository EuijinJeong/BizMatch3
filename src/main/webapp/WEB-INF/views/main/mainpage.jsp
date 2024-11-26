<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>로그인전 메인페이지</title>
    <link rel="stylesheet" href="/css/before_login_header.css" />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/mainpage.js"></script>
    <link rel="stylesheet" href="/css/mainpage.css" />
  </head>
  <body>
    <!-- "${sessionScope._LOGIN_USER_}" -->
    <c:choose>
      <c:when test="${empty sessionScope._LOGIN_USER_}">
        <%@ include file="/WEB-INF/views/common/before_login_header.jsp" %>
      </c:when>
      <c:otherwise>
        <%@ include file="/WEB-INF/views/common/after_login_header.jsp" %>
      </c:otherwise>
    </c:choose>
    <div class="container">
      <div class="reg">
        <div class="reg-ment">
          <div class="reg-title-ment">
          	<div>프로젝트 등록하기</div>
          </div>
          
          <div class="reg-small-ment">
            <div>지금 프로젝트를 등록해서 나에게 </div>
            <div>필요한 전문가를 찾아보세요.</div>
          </div>
          <div class="reg-btn-area">
            <button class="reg-btn" id="reg-btn">프로젝트 등록하기</button>
          </div>
        </div>
        <div class="img-box"><img src="/img/Illustration.svg" alt="" /></div>
      </div>
    </div>
    <div class="second-section">
      <div class="second-section-box">
        <p class="second-section-title">
          BizMatch에서 아웃소싱 고민을 해결해보세요!
        </p>

        <div class="cards">
          <div class="card">
            <div class="card-text">
              <h3>맞춤형 전문가와 매칭</h3>
            </div>
            <div class="card-img">
              <img src="/img/second-section1.svg" alt="" />
            </div>
            <div class="card-caption">
              <p>업종과 프로젝트에 맞는</p>
              <p>전문가 신속 연결</p>
              <p>필요한 서비스와</p>
              <p>맞춤형 지원 제공</p>
            </div>
          </div>
          <div class="card">
            <div class="card-text">
              <h3>기업 맞춤형 지원</h3>
            </div>
            <div class="card-img">
              <img src="/img/second-section2.svg" alt="" />
            </div>
            <div class="card-caption">
              <p>기업 특정 요구에 맞춘</p>
              <p>다양한 아웃소싱 솔루션</p>
              <p>효율적인 업무 처리 가능</p>
            </div>
          </div>
          <div class="card">
            <div class="card-text">
              <h3>실시간 프로젝트 관리</h3>
            </div>
            <div class="card-img">
              <img src="/img/second-section3.svg" alt="" />
            </div>
            <div class="card-caption">
              <p>프로젝트 진행 상황</p>
              <p>한눈에 확인</p>
              <p>필요한 변경 사항 즉시 반영</p>
              <p>실시간 관리 시스템 제공</p>
            </div>
          </div>
          <div class="card">
            <div class="card-text">
              <h3>안전한 거래 및 결제 시스템</h3>
            </div>
            <div class="card-caption">
              <p>철저한 보안</p>
              <p>신뢰성 있는 결제 시스템</p>
              <p>안전한 거래 환경 보장</p>
            </div>
            <div class="card-img">
              <img src="/img/second-section4.svg" alt="" />
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="third-section">
      <div class="third-section-box">
        <p class="third-section-title">왜 BizMatch 인가요?</p>
        <div class="third-section-cards">
          <div class="third-section-card">
            <div class="third-section-card-header">
              <p class="third-section-card-header-title1">시간 절약</p>
            </div>
            <div class="third-section-card-body">
              <p>전문가 탐색 시간 단축</p>
              <p>빠르게 프로젝트 시작</p>
            </div>
          </div>
          <div class="third-section-card">
            <div class="third-section-card-header">
              <p class="third-section-card-header-title2">
                신뢰할 수 있는 네트워크
              </p>
            </div>
            <div class="third-section-card-body">
              <p>다양한 산업군 인증된 전문가</p>
              <p>안정적인 거래</p>
            </div>
          </div>
          <div class="third-section-card">
            <div class="third-section-card-header">
              <p class="third-section-card-header-title3">유연한 가격 책정</p>
            </div>
            <div class="third-section-card-body">
              <p>예산에 맞는 합리적인 가격</p>
              <p>최상의 결과 도출</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="fourth-section">
      <div class="fourth-section-container">
        <p class="fourth-section-title">자주 묻는 질문 ( FAQ )</p>
      </div>
      <div class="fourth-section-box">
        <div class="card1">
          <div class="card-text">
            <span class="fourth-section-text">
              1. 플랫폼에서 제공하는 서비스는 어떤 것인가요?
            </span>
          </div>
          <div class="card-caption1">
            <p>
              A. 다양한 프로젝트 등록, 기업과의 매칭, 지원 관리 및 결제 시스템
              등을 제공합니다.
            </p>
          </div>
        </div>
        <div class="fourth-section-box2"></div>
        <div class="card1">
          <div class="card-text">
            <span class="fourth-section-text">
              2. 프로젝트 등록 후 어떻게 지원 기업을 선택하나요?
            </span>
          </div>
          <div class="card-caption1">
            <p>
              A. 등록한 프로젝트에 관심 있는 기업이 지원하면, 해당 기업들의
              프로필과 제안을 검토하여 선택할 수 있습니다.
            </p>
          </div>
        </div>
        <div class="fourth-section-box2"></div>
        <div class="card1">
          <div class="card-text">
            <span class="fourth-section-text">
              3. 결제는 어떻게 진행되나요?
            </span>
          </div>
          <div class="card-caption1">
            <p>
              A. 플랫폼 내에서 제공하는 안전한 결제 시스템을 통해, 계약 체결 후
              정해진 금액을 결제할 수 있습니다.
            </p>
          </div>
        </div>
        <div class="fourth-section-box2"></div>
        <div class="card1">
          <div class="card-text">
            <span class="fourth-section-text">
              4. 분쟁이 발생했을 때 어떻게 해결되나요?
            </span>
          </div>
          <div class="card-caption1">
            <p>
              A. 고객 지원팀에 문의하거나, 플랫폼 내 분쟁 해결 프로세스를 통해
              중재를 요청할 수 있습니다.
            </p>
          </div>
        </div>

        <div class="fourth-section-box-qna-area">
          <p class="fourth-section-box-qna" id="fourth-section-box-qna">
            질문 모두 보기 >
          </p>
        </div>
      </div>
    </div>
    <div class="fifth-section">
      <div class="fifth-section-container">
        <div class="fifth-section-title">
          <p>지금 바로 등록하여</p>
          <p>비즈니스의 새로운 기회를 만나보세요!</p>
        </div>
        <div class="fifth-section-btn-area">
          <button class="fifth-section-btn">프로젝트 등록하기</button>
        </div>
      </div>
      <div class="pageup-btn-area">
        <img src="./pageupbtn.svg" alt="" class="pageup-btn" />
      </div>
    </div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
  </body>
</html>
