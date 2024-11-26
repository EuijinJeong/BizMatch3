<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%><%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/project_register.js"></script>
    <script type="text/javascript" src="/js/category_bar.js"></script>
    <link rel="stylesheet" href="/css/project_register.css" />
    <script
      src="https://kit.fontawesome.com/3361d77996.js"
      crossorigin="anonymous"
    ></script>
    <title>BizMatch | 프로젝트 등록</title>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/common/after_login_header.jsp" %>
    <form action="/project/write" method="post" enctype="multipart/form-data">
      <div class="project-register-page">
        <div class="project-register-area">
          <h1 class="project-register-title">프로젝트 등록하기</h1>
          <br />
          <div class="project-register">
            <div id="error" class="error-message"></div>
            <div class="project-category">
              <div class="project-section-num">01</div>
              <div class="project-section-name">프로젝트 카테고리</div>
              <div class="level-category-area">
                <select
                  name="firstIndstrId"
                  class="level-category"
                  id="level-category"
                >
                  <option value="0">대분류</option>
                  <option value="1">IT 컨설팅과 기타 서비스</option>
                  <option value="2">데이터 처리와 아웃소싱 서비스</option>
                  <option value="3">인터넷 서비스와 인프라</option>
                  <option value="4">애플리케이션 소프트웨어</option>
                  <option value="5">시스템 소프트웨어</option>
                  <option value="6">게임 소프트웨어</option>
                  <option value="7">디자인 및 미디어</option>
                  <option value="8">카탈로그 소매 및 인터넷 소매</option>
                  <option value="9">마케팅 및 광고 서비스</option>
                  <option value="10">번역 및 통역 서비스</option>
                  <option value="11">헬스케어 및 의료 솔루션</option>
                  <option value="12">교육 및 학습 서비스</option>
                </select>
                <input
                  type="text"
                  class="search-input"
                  id="search-input"
                  placeholder="Search Categories"
                />
              </div>
              <div></div>
              <div class="level-category-area">
                <select
                  name="secondIndstrId"
                  class="second-level-category"
                  id="second-level-category"
                >
                  <option value="0">중분류</option>
                  <option value="13">기술 자문 및 전략 컨설팅</option>
                  <option value="14">정보화 기획 및 시스템 설계</option>
                  <option value="15">디지털 전환 및 혁신 서비스</option>
                  <option value="16">비즈니스 프로세스 개선 컨설팅</option>
                  <option value="17">규제 준수 및 인증 지원</option>
                  <option value="18">프로젝트 관리 및 PMO 서비스</option>
                  <option value="19">데이터 분석 및 처리 서비스</option>
                  <option value="20">클라우드 데이터 관리</option>
                  <option value="21">백오피스 아웃소싱 (HR, 회계)</option>
                  <option value="22">고객 지원 및 콜센터 운영</option>
                  <option value="23">IT 아웃소싱 및 관리 서비스</option>
                  <option value="24">프로세스 자동화 (RPA) 서비스</option>
                  <option value="25">웹사이트 및 온라인 플랫폼 구축</option>
                  <option value="26">클라우드 인프라 및 서버 관리</option>
                  <option value="27">네트워크 설계 및 보안 관리</option>
                  <option value="28">콘텐츠 관리 시스템 (CMS)</option>
                  <option value="29">검색 최적화 및 디지털 마케팅</option>
                  <option value="30">소셜 미디어 및 커뮤니티 운영</option>
                  <option value="31">웹 및 모바일 앱 개발</option>
                  <option value="32">ERP, CRM 등 비즈니스 애플리케이션</option>
                  <option value="33">전자상거래 솔루션 개발</option>
                  <option value="34">
                    고객 경험 및 사용자 인터페이스 설계
                  </option>
                  <option value="35">
                    교육용 소프트웨어 및 e-Learning 시스템
                  </option>
                  <option value="36">금융 및 핀테크 애플리케이션</option>
                  <option value="37">운영체제 개발 및 유지보수</option>
                  <option value="38">데이터베이스 관리 시스템 (DBMS)</option>
                  <option value="39">네트워크 운영 소프트웨어</option>
                  <option value="40">시스템 모니터링 및 관리 도구</option>
                  <option value="41">백업 및 복구 소프트웨어</option>
                  <option value="42">보안 소프트웨어 및 바이러스 백신</option>
                  <option value="43">
                    게임 콘텐츠 개발 (모바일, PC, 콘솔)
                  </option>
                  <option value="44">가상 현실 및 증강 현실 게임</option>
                  <option value="45">인터랙티브 미디어 및 메타버스 개발</option>
                  <option value="46">게임 디자인 및 그래픽 제작</option>
                  <option value="47">게임 운영 및 리워드 시스템</option>
                  <option value="48">시각 디자인 및 브랜드 구축</option>
                  <option value="49">3D 모델링 및 애니메이션</option>
                  <option value="50">영상 제작 및 편집</option>
                  <option value="51">그래픽 디자인 및 인쇄물 제작</option>
                  <option value="52">디지털 일러스트 및 캐릭터 디자인</option>
                  <option value="53">홍보 및 마케팅 콘텐츠 제작</option>
                  <option value="54">온라인 쇼핑몰 구축 및 운영</option>
                  <option value="55">디지털 카탈로그 제작 및 관리</option>
                  <option value="56">상품 촬영 및 콘텐츠 생성</option>
                  <option value="57">물류 및 재고 관리 솔루션</option>
                  <option value="58">고객 리뷰 및 피드백 관리</option>
                  <option value="59">전자상거래 마케팅 전략 수립</option>
                  <option value="60">디지털 광고 및 배너 제작</option>
                  <option value="61">브랜드 전략 및 시장 분석</option>
                  <option value="62">소셜 미디어 캠페인 관리</option>
                  <option value="63">콘텐츠 마케팅 및 블로그 운영</option>
                  <option value="64">인플루언서 및 바이럴 마케팅</option>
                  <option value="65">오프라인 및 이벤트 마케팅</option>
                  <option value="66">전문 문서 번역 및 현지화</option>
                  <option value="67">영상 자막 및 더빙 서비스</option>
                  <option value="68">비즈니스 및 법률 통역</option>
                  <option value="69">다국어 고객 지원 서비스</option>
                  <option value="70">의료 소프트웨어 개발 (EMR, EHR)</option>
                  <option value="71">원격 의료 및 진료 플랫폼</option>
                  <option value="72">병원 관리 시스템</option>
                  <option value="73">헬스케어 데이터 분석</option>
                  <option value="74">학습 관리 시스템 (LMS)</option>
                  <option value="75">온라인 강의 및 교육 콘텐츠 개발</option>
                  <option value="76">교육용 앱 및 소프트웨어</option>
                </select>
                <input
                  type="text"
                  class="search-input"
                  id="search-input2"
                  placeholder="Search Categories"
                />
              </div>
            </div>
            <div class="project-title">
              <div class="project-section-num">02</div>
              <div class="project-section-name">제목</div>
              <input
                type="text"
                class="project-title-input"
                placeholder="제목을 입력하세요"
                value="${writeProjectVO.pjTtl}"
                name="pjTtl"
              />
            </div>
            <div class="project-schedule">
              <div class="project-section-num">03</div>
              <div class="project-section-name">프로젝트 일정</div>
              <div>
                <label for="date" class="label">시작일</label>
                <input
                  type="date"
                  id="strt-date"
                  name="strtDt"
                  class="date-input"
                  value="${writeProjectVO.strtDt}"
                />
              </div>
              <div>
                <label for="date" class="label" id="finish-date">종료일</label>
                <input
                  type="date"
                  id="end-date"
                  name="endDt"
                  class="date-input"
                  value="${writeProjectVO.endDt}"
                />
              </div>
            </div>
            <div class="project-skill-size" style="margin-top: 1.2rem">
              <div class="project-section-num">04</div>

              <div class="project-section-name" style="padding-top: 1.3rem">
                보유 기술
              </div>
              <div style="padding-top: 1.3rem">
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
                        <div class="skill-circle" data-id="18">C#</div>
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
                      <div>기술을 검색, 선택해 주세요.</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="project-contents">
              <!-- <div class="project-contents-area"> -->
              <div class="project-section-num">05</div>
              <div class="project-section-name">상세 설명</div>
              <div class="project-contents-input-area">
                <div>
                  <textarea
                    id=""
                    name="pjDesc"
                    class="project-contents-input"
                    placeholder="프로젝트 내용 작성 추천 예시.
                    프로젝트 목표: 특정 목표를 달성하기 위한 시스템 또는 플랫폼 개발
                    예: 재고 관리 자동화 시스템 개발, 고객 피드백 분석 툴 제작 등
                    필요사항: 프로젝트 수행에 필요한 기술과 작업
                    예: 기획, UI/UX 디자인, 프론트엔드 및 백엔드 개발, 데이터베이스 설계, API 연동, QA 테스트 등
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
${writeProjectVO.pjDesc}</textarea
                  >
                </div>
              </div>
              <!-- </div> -->
            </div>
            <div class="project-price">
              <div class="project-section-num">06</div>
              <div class="project-section-name">프로젝트 입찰가격</div>
              <div>
                <label for="amount"></label>
                <input
                  type="number"
                  id="amount"
                  name="cntrctAccnt"
                  min="0"
                  step="100"
                  class="project-amount"
                  placeholder="최소 1,000,000"
                  value="${writeProjectVO.cntrctAccnt}"
                />
                <span>원</span>
              </div>
            </div>
            <div>
              <div class="project-schedule">
                <div class="project-section-num">07</div>
                <div class="project-section-name">프로젝트 모집일</div>
                <div>
                  <label for="date" class="label">모집일</label>
                  <input
                    type="date"
                    id="date"
                    name="pjRcrutStrtDt"
                    class="date-input"
                    value="${writeProjectVO.pjRcrutStrtDt}"
                  />
                </div>
                <div>
                  <label for="date" class="label" id="finish-date"
                    >종료일</label
                  >
                  <input
                    type="date"
                    id="date"
                    name="pjRcrutEndDt"
                    class="date-input"
                    value="${writeProjectVO.pjRcrutEndDt}"
                  />
                </div>
              </div>
            </div>
            <div class="important-message-area">
              <div class="important-message">
                프로젝트 모집 기간은 최소 7일입니다.
              </div>
            </div>
            <div class="file-attatchment">
              <div class="project-section-num">08</div>
              <div class="project-section-name">첨부파일</div>
              <div class="btn-box">
                <p><span class="red-word">*</span></p>
                <div>
                  <input type="file" id="fileInput" name="fileList" multiple />
                  <label for="fileSelect">선택한 파일:</label>
                  <select id="fileSelect"></select>
                  <button id="removeButton" type="button">삭제</button>
                </div>
              </div>
            </div>

            <div class="important-message-area">
              <div class="important-message">
                기획서, 요구사항 정의서, 참고 자료 등
              </div>
            </div>
            <!-- </div> -->
            <div class="project-team-size">
              <div class="project-section-num">09</div>
              <div class="project-section-name">프로젝트 인원</div>
              <div>
                <label for="people"></label>
                <input
                  type="number"
                  id="people"
                  name="pjRcrutCnt"
                  min="1"
                  max="100"
                  step="1"
                  value="1"
                  class="project-member-count"
                  placeholder=""
                  value="${writeProjectVO.pjRcrutCnt}"
                />
                <span>명</span>
              </div>
            </div>
          </div>
          <div class="btn-area">
            <input class="project-register-btn" type="submit" value="등록" />

            <!-- <button class="cancle-btn" type="button"><span>취소</span></button> -->
          </div>
        </div>
      </div>
    </form>
  </body>
</html>
