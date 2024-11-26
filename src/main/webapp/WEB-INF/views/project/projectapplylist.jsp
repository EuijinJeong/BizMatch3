<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <!-- <meta charset="UTF-8" /> -->
    <title>프로젝트 지원 기업 리스트</title>
    <link rel="stylesheet" href="/css/projectapplylist.css" />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/project_apply_list.js"></script>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/common/after_login_header.jsp" %>
    <!-- <div class="project-info"> -->
    <%@ include file="/WEB-INF/views/common/project_card.jsp" %>

    <section class="company-list">
      <div
        class="apply-header"
        data-pjid="${projectVO.pjId}"
        style="display: flex; justify-content: space-between"
      >
        <h2 style="display: inline-block">지원 기업 리스트</h2>
        <form
          action="/project/update/addrecruitment/${projectVO.pjId}"
          method="POST"
        >
          <button
            class="addRecruitment"
            type="submit"
            data-pjid="${projectVO.pjId}"
          >
            추가모집
          </button>
        </form>
      </div>

      <c:forEach var="applyProject" items="${applyProjectVOList}">
        <form id="form-${applyProject.pjId}" method="post">
          <div class="company pending">
            <h2
              class="company-name"
              id="company-name"
              data-id="${applyProject.pjId}"
              data-apply="${applyProject.pjApplyId}"
            >
              기업명: ${applyProject.pjApplyTtl}
            </h2>
            <p>지원일: ${applyProject.pjApplyRgstrDt}</p>
            <p>지원 상태: 심사 중</p>
            <div class="button-group">
              <c:if test="${applyProject.memberVO.mbrCtgry =='1'}">
                <button
                  type="button"
                  class="btn view-details"
                  id="detail-btn"
                  data-index="${applyProject.memberVO.emilAddr}"
                  data-type="${applyProject.memberVO.mbrCtgry}"
                >
                  상세 보기
                </button>
                <div class="btn-group-right">
                  <button
                    type="button"
                    class="btn accept"
                    data-form-id="${applyProject.pjId}"
                    data-action-url="/project/apply/member/${applyProject.pjId}"
                  >
                    수락
                  </button>
                  <button
                    type="button"
                    class="btn reject"
                    data-form-id="${applyProject.pjId}"
                    data-action-url="/project/apply/delete/${applyProject.pjApplyId}"
                  >
                    거절
                  </button>
                </div>
              </c:if>
              <c:if test="${applyProject.memberVO.mbrCtgry =='0'}">
                <button
                  type="button"
                  class="btn view-details"
                  id="detail-btn"
                  data-index="${applyProject.memberVO.cmpId}"
                  data-type="${applyProject.memberVO.mbrCtgry}"
                  name="cmpId"
                >
                  상세 보기
                </button>
                <div class="btn-group-right">
                  <button
                    type="button"
                    class="btn accept"
                    data-form-id="${applyProject.pjId}"
                    data-action-url="/project/apply/member/${applyProject.pjId}"
                  >
                    수락
                  </button>
                  <button
                    type="button"
                    class="btn reject"
                    data-form-id="${applyProject.pjId}"
                    data-action-url="/project/apply/delete/${applyProject.pjApplyId}"
                  >
                    거절
                  </button>
                </div>
              </c:if>
            </div>
          </div>
        </form>
      </c:forEach>
    </section>
  </body>
</html>
