<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="/css/project_apply.css" />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/project_apply.js"></script>
    <title>BizMatch | 프로젝트 지원</title>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/common/after_login_header.jsp" %>
    <c:url var="applyUrl" value="/project/view/applyinfo/${pjApplyId}" />
    <form action="${applyUrl}" method="get" enctype="multipart/form-data">
      <div class="project-register-page">
        <div class="project-register-area">
          <h1>프로젝트 지원</h1>
          <div class="project-register">
            <div class="project-title">
              <div class="project-section-num">01</div>
              <div class="project-section-name">제목</div>
              <input
                type="text"
                class="project-title-input"
                placeholder="제목을 입력하세요"
                value="${applyProjectVO.pjApplyTtl}"
                name="pjApplyTtl"
                readonly
              />
            </div>
            <div class="project-contents">
              <div class="project-section-num">02</div>
              <div class="project-section-name">지원 내용</div>
              <div class="project-contents-input-area">
                <textarea
                  name="pjApplyDesc"
                  class="project-contents-input"
                  placeholder="프로젝트 내용 작성 예시..."
                  readonly
                >
${applyProjectVO.pjApplyDesc}</textarea
                >
              </div>
            </div>
            <div class="important-message-area">
              <div class="important-message">
                전화번호, 이메일 등 개인정보 입력 금지
              </div>
            </div>
            <div class="file-attatchment">
              <div class="project-section-num">03</div>
              <div class="project-section-name">첨부파일</div>
              <div class="btn-box">
                <div>
                  <input type="file" id="fileInput" name="fileList" multiple />
                  <label for="fileSelect">선택한 파일:</label>
                  <select id="fileSelect"></select>
                  <button id="removeButton" type="button">삭제</button>
                </div>
              </div>
            </div>
            <div class="important-message-area">
              <div class="error">${error}</div>
              <div class="important-message">
                기획서, 요구사항 정의서, 참고 자료 등
              </div>
            </div>
          </div>
          <div class="btn-area" style="gap: 2rem">
            <input type="hidden" name="pjId" value="${applyProjectVO.pjId}" />
            <input
              type="hidden"
              name="pjApplyId"
              value="${applyProjectVO.pjApplyId}"
            />
            <!-- 추가 -->
            <input
              type="hidden"
              name="emilAddr"
              value="${applyProjectVO.emilAddr}"
            />
          </div>
        </div>
      </div>
    </form>
  </body>
</html>
