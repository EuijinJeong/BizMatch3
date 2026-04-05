<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link rel="stylesheet" href="/css/category_bar.css" />
    <link rel="stylesheet" href="/css/common.css" />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/category_bar.js"></script>
  </head>
  <body>
    <div class="select_box">
      <!--
      임시방편으로  name값에 cmpnyBizCtgry 부여했으나 원래 값은 cmpnyIndstrId
      cmpnyBizCtgry 유형 선택할 때 부여가 되어야 함
      -->
      <select
        id="cmpnyBizCtgry2"
        name="cmpnyIntrstdIndstrId.mjrId"
        value="cmpnyIntrstdIndstrId"
        class="level-category"
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
        placeholder="Search Categories"
        id="cate_bar2-first"
      />
    </div>
    <div class="select_box">
      <!--
      옵션값 설정 필수
      중분류는 어떤 name값?
      -->
      <select
        id="cmpnyIndstrId2"
        name="cmpnyIntrstdIndstrId.smjrId"
        value="cmpnyIntrstdIndstrId"
        class="second-level-category"
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
        <option value="34">고객 경험 및 사용자 인터페이스 설계</option>
        <option value="35">교육용 소프트웨어 및 e-Learning 시스템</option>
        <option value="36">금융 및 핀테크 애플리케이션</option>
        <option value="37">운영체제 개발 및 유지보수</option>
        <option value="38">데이터베이스 관리 시스템 (DBMS)</option>
        <option value="39">네트워크 운영 소프트웨어</option>
        <option value="40">시스템 모니터링 및 관리 도구</option>
        <option value="41">백업 및 복구 소프트웨어</option>
        <option value="42">보안 소프트웨어 및 바이러스 백신</option>
        <option value="43">게임 콘텐츠 개발 (모바일, PC, 콘솔)</option>
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
        placeholder="Search Categories"
        id="cate_bar2-second"
      />
    </div>
  </body>
</html>
