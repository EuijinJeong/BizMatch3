<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="/css/common.css" />
    <link rel="stylesheet" href="/css/signup_company.css" />
    <link rel="stylesheet" href="/css/before_login_header.css" />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/signup_member_company.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <title>기업형 회원가입 페이지</title>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/common/before_login_header.jsp" %>
    <div class="signupbox">
      <form
        action="/member/signup/company"
        method="post"
        id="signupForm"
        enctype="multipart/form-data"
      >
        <p class="red-word">*은 필수입력사항입니다.</p>
        <c:if test="${not empty error}">
        	<p class="red-word"> ${error}</p>
        </c:if>
        <div class="text-box">
          <p><span class="red-word">*</span>이용자명</p>
          <input id="mbrNm" type="text" name="mbrNm" placeholder="이름 입력" value="${memberCompanySignUpVO.mbrNm }"/>
        </div>

        <div class="btn-box">
          <p><span class="red-word">*</span>이메일주소</p>
          <div>
            <input
              id="emilAddr"
              type="email"
              name="emilAddr"
              placeholder="업무용 이메일 사용을 권장합니다."
              value = "${memberCompanySignUpVO.emilAddr }"
            />
            <button
              type="button"
              id="confirm-email"
              name="emilAddrCheck"
              class="email-submit"
            >
              이메일 주소 인증
            </button>
          </div>
        </div>

        <div class="btn-box">
          <p><span class="red-word">*</span>이메일 주소 인증번호</p>
          <div>
            <div class="input-container">
              <input
                id="authNumField"
                class="authNumField"
                name="emilAddrCnfrmNmbr"
                type="text"
                placeholder="인증번호 6자리 입력 "
              />
              <span class="timer"></span>
            </div>
            <button
              id="confirm-auth-num"
              class="confirm-auth-num"
              type="button"
            >
              인증번호 확인
            </button>
          </div>
        </div>

        <div class="text-box">
          <p><span class="red-word">*</span>비밀번호</p>
          <div id="errorPwd"></div>
          <input
            id="pwd"
            type="password"
            name="pwd"
            placeholder="대소문자 및 특수문자 포함 8자리 이상 입력"
          />
        </div>

        <div class="text-box">
          <p><span class="red-word">*</span>비밀번호 확인</p>
          <div id="errorConfirmPwd"></div>
          <input
            id="confirmPwd"
            type="password"
            name="confirmPwd"
            placeholder="대소문자 및 특수문자 포함 8자리 이상 입력"
          />
        </div>

        <div class="btn-box">
          <p class="cmpMsg-box"><span class="red-word">*</span>사업자 번호</p>
          <span class="cmpMsg"></span>
          <div>
            <input
              id="cmpnyBrn"
              type="text"
              name="cmpnyBrn"
              placeholder="사업자 번호 입력"
              value = "${memberCompanySignUpVO.cmpnyBrn }"
            /><button class="cmpny-brn-confirm" id="cmpnyBrnConfirm">
              사업자 번호 확인
            </button>
          </div>
        </div>

        <div class="text-box">
          <p><span class="red-word">*</span>직원 수</p>
          <input
            id="cmpnyEmplyCnt"
            type="number"
            name="cmpnyEmplyCnt"
            placeholder="직원 수 입력"
            value="${memberCompanySignUpVO.cmpnyEmplyCnt }"
          />
        </div>

        <div class="btn-box">
          <p><span class="red-word">*</span>이용자 전화번호</p>
          <div>
            <input
              id="mbrPhnNum"
              type="tel"
              name="mbrPhnNum"
              placeholder="전화번호 입력"
              value = "${memberCompanySignUpVO.mbrPhnNum }"
            />
          </div>
        </div>

        <div class="text-box">
          <p><span class="red-word">*</span>기업명</p>
          <input
            id="cmpnyNm"
            type="text"
            name="cmpnyNm"
            placeholder="기업명 입력"
            value = "${memberCompanySignUpVO.cmpnyNm }"
          />
        </div>
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

        <div class="text-box">
          <p><span class="red-word">*</span>기업 전화번호</p>
          <input
            id="cmpnyPhnNum"
            type="text"
            name="cmpnyPhnNum"
            placeholder="02-000-0000"
            value = "${memberCompanySignUpVO.cmpnyPhnNum }"
          />
        </div>

        <div class="btn-box">
          <p><span class="red-word">*</span>이용자 첨부파일</p>
          <div class="file-box">
            <div class="addfile">
              <input class="fileList" type="file" name="fileList[0]" />
            </div>
            <button class="file-button" type="button" id="add_attr_file">
              첨부자료 추가
            </button>
          </div>
        </div>

        <div class="text-box">
          <p><span class="red-word">*</span>회사 사이트 주소</p>
          <input
            id="cmpnySiteUrl"
            type="text"
            name="cmpnySiteUrl"
            placeholder="회사 사이트 주소 입력"
            value="${memberCompanySignUpVO.cmpnySiteUrl }"
          />
        </div>
        <div class="text-box">
          <p><span class="red-word">*</span>주요 산업분야</p>
          <%@ include file="/WEB-INF/views/common/category_bar.jsp" %>
        </div>
        <div class="text-box">
          <p><span class="red-word">*</span>관심 산업분야</p>
          <%@ include file="/WEB-INF/views/common/category_bar2.jsp" %>
        </div>
        <div class="check_box">
          <p class="checkbox1">이용약관</p>
          <span class="checkboxMsg"></span>
          <div>
            <input id="ageCheck" type="checkbox" name="agreeOne" value="Y" />
            <p>만 14세 이상입니다.</p>
          </div>
          <div>
            <input id="termsCheck" type="checkbox" name="agreeTwo" value="Y" />
            <p>서비스 이용약관에 동의합니다.</p>
          </div>
          <div>
            <input
              id="privacyCheck"
              type="checkbox"
              name="agreeThree"
              value="Y"
            />
            <p>개인정보 수집 및 이용에 동의합니다.</p>
          </div>
        </div>
        <input class="signupbtn" type="submit" value="가입하기" />
      </form>
    </div>
  </body>
</html>
