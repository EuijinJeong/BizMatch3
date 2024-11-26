<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>BizMatch | 마이페이지 수정</title>
    <link rel="stylesheet" href="/css/mypagecompanyedit.css" />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/mypage_company_edit.js"></script>
    <script type="text/javascript" src="/js/category_bar.js"></script>
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c34c0fc9c9f52486b1dfce66356c8efa&libraries=services,clusterer,drawing"
    ></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
    />
  </head>
  <body>
    <%@ include file="/WEB-INF/views/common/after_login_header.jsp" %>
    <div class="mainpage-box">
      <%@ include file="/WEB-INF/views/common/profile_box_edit.jsp" %>
      <main>
        <div class="main-box">
          <section class="sidebar">
            <div class="sidebar-menulist">
              <div class="sidebar-menu" data-target="#introduction">
                내 프로필
              </div>
              <div class="sidebar-menu" data-target="#interesting-industry">
                관심 산업
              </div>
              <div class="sidebar-menu" data-target="#holding-technology">
                보유 기술
              </div>
              <div class="sidebar-menu" data-target="#account">계좌 번호</div>
              <div class="sidebar-menu" data-target="#attachment">
                회사 첨부자료
              </div>
              <div class="sidebar-menu" data-target="#map">회사 위치</div>
              <div class="sidebar-menu">내 프로젝트</div>
            </div>
          </section>

          <section class="myPageList">
            <div class="myPageList-box">
              <div class="introduction" id="introduction">
                회사 소개
                <textarea
                  class="introduction-content"
                  id="cmpnyIntr"
                  name="cmpnyIntr"
                >
${companyVO.cmpnyIntr}</textarea
                >
              </div>
              <div class="interesting-industry" id="interesting-industry">
                관심 산업 <%@ include
                file="/WEB-INF/views/common/category_bar2.jsp" %>
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
                <div class="count-title">회사 계좌 번호</div>
                <c:choose>
                  <c:when test="${not empty companyVO.cmpnyAccuuntNum}">
                    <input
                      id="account-input"
                      type="text"
                      value="${companyVO.cmpnyAccuuntNum}"
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
                회사 첨부자료
                <button class="more-button small" type="button">더 보기</button>
                <div class="attachment-list">
                  <div class="attachment-box"></div>
                  <div class="attachment-box"></div>
                  <div class="attachment-box"></div>
                </div>
              </div>
              <div class="map" id="map">
                회사 위치
                <div class="map-box">
                  <div id="kakao-map" class="kakao-map"></div>
                  <div class="map-detail">
                    <div class="detail-title">상세 주소</div>
                    <div class="detail-address" id="cmpnyAddr">
                      ${companyVO.cmpnyAddr != null ? companyVO.cmpnyAddr :'주소
                      정보가 없습니다'}
                    </div>
                  </div>
                </div>
                <button type="button" class="edit">변경</button>
              </div>
            </div>
          </section>
        </div>
      </main>
    </div>
    <!-- 모달 창 -->
    <div id="reportModal" class="modal">
      <div class="modal-content">
        <button class="close-btn">&times;</button>
        <div class="form-group">
          <div class="com_addr" id="com_addr">
            <p><span class="red-word">*</span>기업주소</p>
            <div class="com_div">
              <input
                type="text"
                id="postcode"
                placeholder="우편번호 입력"
                name="address.postcode"
              />
              <button type="button" id="asd">도로명 주소 찾기</button>
            </div>
            <div class="com_div">
              <input
                type="text"
                id="addr"
                placeholder="도로명 주소 입력"
                name="address.addr"
              />
              <input
                type="text"
                id="detailAddress"
                placeholder="상세주소 입력"
                name="address.detailAddress"
              />
              <input
                type="text"
                id="extraAddress"
                placeholder="참고항목"
                name="address.extraAddress"
              />
            </div>
          </div>
        </div>
        <button type="button" id="save-btn" class="submit-btn">저장</button>
      </div>
    </div>
  </body>
</html>
