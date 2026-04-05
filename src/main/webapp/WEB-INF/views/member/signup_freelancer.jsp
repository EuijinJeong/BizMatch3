<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="/css/signup_freelancer.css" />
    <link rel="stylesheet" href="/css/common.css" />
    <link rel="stylesheet" href="/css/before_login_header.css" />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script
      type="text/javascript"
      src="/js/signup_member_freelancer.js"
    ></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <title>프리랜서형 회원가입 페이지</title>
  </head>
  <body>
    <body>
      <%@ include file="/WEB-INF/views/common/before_login_header.jsp"%>
      <form
        action="/member/signup/freelancer"
        method="post"
        id="signupForm"
        enctype="multipart/form-data"
      >
        <div class="header"></div>
        <div class="signupbox">
          <p class="red-word">*은 필수입력사항입니다.</p>
          <c:if test="${not empty error }">
          	<p class="red-word">${error }</p>
          </c:if>
          
          <div class="text-box">
            <p><span class="red-word">*</span>이용자명</p>
            <input
              id="mbrNm"
              type="text"
              name="mbrNm"
              placeholder="이름 입력"
              value="${memberSignUpVO.mbrNm}"
            />
          </div>

          <div class="text-box">
            <p><span class="red-word">*</span>생년월일</p>
            <input
              id="brthDt"
              type="date"
              name="brthDt"
              value="${memberSignUpVO.brthDt}"
            />
          </div>

          <div class="com_addr">
            <p><span class="red-word">*</span>주소</p>
            <div class="com_div">
              <input
                type="text"
                id="postcode"
                placeholder="우편번호 입력"
                name="addr.postcode"
              />
              <button type="button" id="asd">도로명 주소 찾기</button>
            </div>
            <div class="com_div">
              <input
                type="text"
                id="addr"
                placeholder="도로명 주소 입력"
                name="addr.addr"
              />
              <input
                type="text"
                id="detailAddress"
                placeholder="상세주소 입력"
                name="addr.detailAddress"
              />
              <input
                type="text"
                id="extraAddress"
                placeholder="참고항목"
                name="addr.extraAddress"
              />
            </div>
          </div>

          <div class="btn-box">
            <p>
              <span class="red-word">*</span>이용자 전화번호<span
                class="red-word"
              ></span>
            </p>
            <div>
              <input
                id="mbrPhnNum"
                type="tel"
                name="mbrPhnNum"
                placeholder="전화번호 입력"
                value="${memberSignUpVO.mbrPhnNum}"
              />
            </div>
          </div>

          <div class="btn-box">
            <p><span class="red-word">*</span>이메일주소</p>
            <div>
              <input
                id="emilAddr"
                type="email"
                name="emilAddr"
                placeholder="업무용 이메일 사용을 권장합니다."
                value="${memberSignUpVO.emilAddr}"
              />
              <button type="button" id="confirm-email" class="email-submit">
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
                  type="text"
                  placeholder="인증번호 6자리 입력 "
                  name="emilAddrCnfrmNmbr"
                  value="${memberSignUpVO.emilAddrCnfrmNmbr}"
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
              value="${memberSignUpVO.pwd}"
            />
          </div>

          <div class="text-box">
            <p><span class="red-word">*</span>비밀번호 확인</p>
            <div id="errorConfirmPwd"></div>
            <input
              id="confirmPassword"
              type="password"
              name="confirmPassword"
              placeholder="대소문자 및 특수문자 포함 8자리 이상 입력"
              value="${memberSignUpVO.confirmPwd}"
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
            <p><span class="red-word">*</span>주요 산업분야</p>
            <%@ include file="/WEB-INF/views/common/category_bar3.jsp"%>
          </div>
          <div class="check_box">
            <p class="checkbox1">이용약관</p>
            <span class="checkboxMsg"></span>
            <div>
              <input id="agree1" name="agree1" value="Y" type="checkbox" />
              <p>만 14세 이상입니다.</p>
            </div>
            <div>
              <input id="agree2" name="agree2" value="Y" type="checkbox" />
              <p>서비스 이용약관에 동의합니다.</p>
            </div>
            <div>
              <input id="agree3" name="agree3" value="Y" type="checkbox" />
              <p>개인정보 수집 및 이용에 동의합니다.</p>
            </div>
          </div>
          <input class="signupbtn" type="submit" value="가입하기" />
        </div>
      </form>
    </body>
  </body>
</html>
