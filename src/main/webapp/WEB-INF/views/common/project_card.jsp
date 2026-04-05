<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!-- JSTL을 위한 Directive 선언 -->
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<link rel="stylesheet" href="/css/project_card.css" />
<link rel="stylesheet" href="/css/common.css" />
<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/js/project_card.js"></script>

<c:choose>
  <c:when test="${not empty projectList }">
    <c:forEach items="${projectList}" var="project">
      <div class="project-card-container">
        <div class="project-card">
          <div class="project-box">
            <div class="project-head">
              <div class="project-head-front">
                <div class="status-recruiting">모집중</div>
                <!-- <div class="status-additional-recruiting">추가모집중</div> -->
                <!-- <div class="status-ing">진행중</div> -->
                <!-- <div class="status-done">완료</div> -->
                <h2 id="pjttl" class="project-title" data-id="${project.pjId}">
                  ${project.pjTtl}
                </h2>
              </div>
              <div class="post-date">등록일자 ${project.rgstrDt}</div>
            </div>
            <div class="project-body">
              <div class="project-body-box">
                <div class="project-body-title">프로젝트 분야</div>
                <div class="project-body-content bold">${project.smjrNm }</div>
              </div>
              <div class="sidebar"></div>
              <div class="project-body-box">
                <div class="project-body-title">관련기술</div>
                <div class="project-body-content" style="gap:0.2rem">
                  <div class="circle-box">
                    <div class="circle"></div>
                  </div>
                  <div class="language" style="display:flex; gap:0.3rem;">
                    <c:choose>
                      <c:when test="${not empty project.projectSkillList}">
                        <c:forEach
                          items="${project.projectSkillList}"
                          var="skill">
                          <span>${skill.prmStk}</span><br />
                        </c:forEach>
                      </c:when>
                      <c:otherwise> 기술 정보가 없습니다. </c:otherwise>
                    </c:choose>
                  </div>
                </div>
              </div>
              <div class="sidebar"></div>
              <div class="project-body-box">
                <div class="project-body-title">모집 마감일</div>
                <div class="project-body-content">${project.pjRcrutEndDt}</div>
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
                  id="apply"
                  data-id="${project.pjId}"
                  type="button"
                  value="신청하기"
                />
              </div>
              <div class="estimated-amount">
                <div>예상 금액</div>
                <div class="half-sidebar"></div>
                <div class="bold">${project.cntrctAccnt}원</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </c:forEach>
  </c:when>
</c:choose>