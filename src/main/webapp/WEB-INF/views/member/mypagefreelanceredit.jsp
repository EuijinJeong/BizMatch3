<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>BizMatch | 마이페이지 수정</title>
    <link rel="stylesheet" href="/css/mypagecompanyedit.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
    />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/mypage_freelancer_edit.js"></script>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/common/after_login_header.jsp" %>
    <div class="mainpage-box">
      <%@ include file="/WEB-INF/views/common/freelancer_profile_box_edit.jsp"
      %>
      <main>
        <div class="main-box">
          <section class="sidebar">
            <div class="sidebar-menulist">
              <div class="sidebar-menu" data-target="#introduction">
                내 프로필
              </div>
              <div class="sidebar-menu" data-target="#holding-technology">
                보유 기술
              </div>
              <div class="sidebar-menu" data-target="#account">계좌 번호</div>
              <div class="sidebar-menu" data-target="#attachment">
                포트폴리오
              </div>
              <div class="sidebar-menu">내 프로젝트</div>
            </div>
          </section>
          <section class="myPageList">
            <div class="myPageList-box">
              <div class="introduction" id="introduction">
                소개
                <textarea
                  class="introduction-content"
                  id="mbrIntr"
                  name="mbrIntr"
                >
${memberVO.mbrIntr}</textarea
                >
              </div>
              <div class="holding-technology" id="holding-technology">
                보유 기술
                <div class="skillStack-box">
                  <div class="searchBox">
                    <i class="fa-solid fa-magnifying-glass"></i>
                    <input
                      class="searchInput"
                      type="text"
                      id="searchInput"
                      placeholder="검색할 기술명을 입력해주세요. 예) JAVA"
                      autocomplete="off"
                    />
                  </div>
                  <div class="resultBox">
                    <ul id="results" class="results"></ul>
                  </div>

                  <div class="recommendSkill">
                    추천 기술 스택에서 선택해 보세요!
                    <div class="skill-box-container">
                      <div class="skill-circle-box">
                        <div class="skill-circle" data-id="72">Java</div>
                        <div
                          class="skill-circle"
                          style="width: 5rem"
                          data-id="73"
                        >
                          JavaScript
                        </div>
                        <div class="skill-circle" data-id="158">Vue.js</div>
                        <div class="skill-circle" data-id="125">React</div>
                        <div class="skill-circle" data-id="64">HTML</div>
                      </div>
                      <div class="skill-circle-box">
                        <div class="skill-circle" data-id="18">C</div>
                        <div
                          class="skill-circle"
                          style="width: 4rem"
                          data-id="83"
                        >
                          Kotlin
                        </div>
                        <div
                          class="skill-circle"
                          style="width: 5rem"
                          data-id="6"
                        >
                          Android
                        </div>
                        <div
                          class="skill-circle"
                          style="width: 4rem"
                          data-id="104"
                        >
                          Node.js
                        </div>
                        <div
                          class="skill-circle"
                          style="width: 4rem"
                          data-id="120"
                        >
                          Python
                        </div>
                      </div>
                    </div>

                    <div class="result-skill-add-box">
                      <c:choose>
                        <c:when test="${not empty mbrPrmStkList}">
                          <c:forEach var="tech" items="${mbrPrmStkList}">
                            <div
                              class="skill-item"
                              style="width: 80%; background-color: #ffffff"
                            >
                              <label>${tech.prmStkVO.prmStk}</label>
                              <input
                                class="selected-skill"
                                name="${tech.prmStkVO.prmStk}"
                                value="${tech.prmStkVO.prmStkId}"
                                type="hidden"
                              />
                              <span
                                class="remove-skill"
                                style="float: right; cursor: pointer"
                                >x</span
                              >
                            </div>
                          </c:forEach>
                        </c:when>
                        <c:otherwise>
                          <div>기술을 검색, 선택해 주세요.</div>
                        </c:otherwise>
                      </c:choose>
                    </div>
                  </div>
                </div>
              </div>
              <div class="account" id="account">
                <div class="count-title">개인 계좌 번호</div>
                <c:choose>
                  <c:when test="${not empty memberVO.accntNum}">
                    <input
                      id="account-input"
                      type="text"
                      value="${memberVO.accntNum}"
                    />
                  </c:when>
                  <c:otherwise>
                    <input
                      id="account-input"
                      type="text"
                      placeholder="계좌번호를 입력해주세요"
                    />
                  </c:otherwise>
                </c:choose>
              </div>
              <div class="attachment" id="attachment">
                첨부자료
                <button class="more-button small" type="button">
                  <a href="/member/mypage/freelancer/portfolio">더 보기</a>
                </button>
                <div class="portfolio-gallery">
                  <div class="result"></div>
                </div>
              </div>
            </div>
          </section>
        </div>
      </main>
    </div>
  </body>
</html>
