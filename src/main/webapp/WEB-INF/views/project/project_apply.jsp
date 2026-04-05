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
    <c:url var="applyUrl" value="/project/apply/${pjId}" />
    <form action="${applyUrl}" method="post" enctype="multipart/form-data">
      <div class="project-register-page">
        <div class="project-register-area">
          <h1>프로젝트 지원하기</h1>
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
              />
            </div>
            <div class="important-message-area">
              <div id="error-title" class="error-message"></div>
            </div>
            <div class="project-contents">
              <div class="project-section-num">02</div>
              <div class="project-section-name">지원 내용</div>
              <div class="project-contents-input-area">
                <textarea
                  id=""
                  name="pjApplyDesc"
                  class="project-contents-input"
                  placeholder="프로젝트 내용 작성 추천 예시
                    프로젝트 목표: 특정 목표를 달성하기 위한 시스템 또는 플랫폼 개발
                    예: 재고 관리 자동화 시스템 개발, 고객 피드백 분석 툴 제작 등
                    필요사항: 프로젝트 수행에 필요한 기술과 작업
                    예: 기획, UI/UX 디자인, 프론트엔드 및 백엔드 개발, 데이터베이스 설계, API 연동, QA 테스트 
                    주요 기능: 프로젝트에서 구현될 주요 기능 및 특징
                    예: 사용자 로그인/회원가입, 실시간 알림 기능, 데이터 시각화 대시보드, 관리자 페이지 등
                    산출물: 프로젝트 완료 시 제공될 결과물
                    예: 소스 코드, 디자인 파일 (Adobe XD, Figma 등), 시스템 매뉴얼, 테스트 결과 보고서 등
                    필수 조건: 프로젝트 수행 시 반드시 충족해야 할 사항
                    예: 반응형 웹 디자인, 성능 최적화, 다국어 지원, 보안 인증 등
                    기대 효과: 프로젝트 완료 후 예상되는 효과
                    예: 업무 효율성 향상, 비용 절감, 사용자 경험 개선 등
                    기타 요청 사항: 추가적으로 고려할 특수 요구사항
                    예: 특정 기술 스택 사용, 유지보수 계획, 협업 툴 사용 (Jira, Trello 등)"
                >
 ${applyProjectVO.pjApplyDesc}</textarea
                >
              </div>
            </div>
            <div class="important-message-area">
              <div id="error-description" class="error-message"></div>
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
              <div id="error-file" class="error-message"></div>
              <div class="important-message">
                기획서, 요구사항 정의서, 참고 자료 등
              </div>
            </div>
          </div>
          <div class="btn-area">
            <input class="project-apply-btn" value="지원하기" type="submit" />
            <!-- <button class="cancle-btn" type="button"><span>취소</span></button> -->
          </div>
        </div>
      </div>
    </form>
  </body>
</html>
