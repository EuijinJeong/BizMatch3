<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%><%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %> <%@ taglib prefix="c"
uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="/css/project_card.css" />
    <link rel="stylesheet" href="/css/myApplyProject.css" />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/myproject.js"></script>
    <title>BizMatch | 프로젝트 지원</title>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/common/after_login_header.jsp" %>

    <div class="main-container">
      <div class="main-container-header">
        <h1 class="main-title1" id="myProjectList">내 프로젝트 목록</h1>

        <h1>/</h1>

        <h1 class="main-title2" id="myApplyProjectList">
          내가 지원한 프로젝트 목록
        </h1>
      </div>
    </div>
    <c:choose>
      <c:when test="${not empty projectList}">
        <c:forEach items="${projectList}" var="project">
          <div class="project-card-container">
            <div class="project-card">
              <div class="project-box">
                <div class="project-head">
                  <div class="project-head-front">
                    <div class="status-recruiting" data-pjid="${project.pjId}">
                      모집중
                    </div>
                    <!-- <div class="status-additional-recruiting">추가모집중</div> -->
                    <!-- <div class="status-ing">진행중</div> -->
                    <!-- <div class="status-done">완료</div> -->
                    <h2 id="pjttl" data-pjid="${project.pjId}">
                      ${project.pjTtl }
                    </h2>
                  </div>
                  <div class="post-date">등록일자 ${project.rgstrDt}</div>
                </div>
                <div class="project-body">
                  <div class="project-body-box">
                    <div class="project-body-title">프로젝트 분야</div>
                    <div class="project-body-content bold">
                      IT·프로그래밍 / 웹사이트·모바일앱 개발
                    </div>
                  </div>
                  <div class="sidebar"></div>
                  <div class="project-body-box">
                    <div class="project-body-title">관련기술</div>
                    <div class="project-body-content">
                      <div class="circle-box">
                        <div class="circle"></div>
                      </div>
                      <div class="language">Java, HTML5</div>
                    </div>
                  </div>
                  <div class="sidebar"></div>
                  <div class="project-body-box">
                    <div class="project-body-title">모집 마감일</div>
                    <div class="project-body-content">
                      ${project.pjRcrutEndDt}
                    </div>
                  </div>
                  <div class="sidebar"></div>
                  <div class="project-body-box">
                    <div class="project-body-title">프로젝트 일정</div>
                    <div class="project-body-content">
                      ${project.strtDt} ~ ${project.endDt}
                    </div>
                  </div>
                </div>
                <div class="project-footer">
                  <div class="button-box">
                    <input
                      class="apply"
                      id="applyview"
                      data-id="${project.pjId}"
                      type="button"
                      value="지원서 보기"
                    />
                  </div>
                  <div class="estimated-amount">
                    <div>예상 금액</div>
                    <div class="half-sidebar"></div>
                    <div class="bold">${project.cntrctAccnt}</div>
                    <span>원</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </c:forEach>
      </c:when>
      <c:otherwise>
        <h1>지원한 프로젝트가 없습니다.</h1>
      </c:otherwise>
    </c:choose>
  </body>
</html>
