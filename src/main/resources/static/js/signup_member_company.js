$().ready(function () {
  // 이메일 중복검사 버튼을 클릭할 때 아래의 코드가 수행된다.
  $("#emilAddr").on("keyup", function () {
    $.get(
      "/member/signup/email/available/",
      { email: $("#emilAddr").val() },
      function (response) {
        var responseEmail = response.email;
        var responseAvailable = response.available;

        if (responseAvailable) {
          $("#emilAddr").addClass("available");
          $("#emilAddr").removeClass("unusable");
          $("#confirm-email").removeAttr("disabled");
        } else {
          $("#emilAddr").addClass("unusable");
          $("#emilAddr").removeClass("available");
          $("#confirm-email").attr("disabled", "disabled");
        }

        console.log(responseEmail);
        console.log(responseAvailable);
      }
    );
  });

  $("#confirm-email").on("click", function (e) {
    var email = $("#emilAddr").val();
	console.log(email);
    $.ajax({
      url: `/email/check/${email}/`,
      method: "GET",
	  success: function(response) {
		alert(email + "로 인증번호가 발급되었습니다.");
	    console.log(email);
	  }
    });
    
    // 타이머
    var timeleft = 300;
    // var inputBox = $("#authNumField");
    var timerText = $(".timer");

    var timer = setInterval(function () {
      var minutes = Math.floor(timeleft / 60);
      var seconds = timeleft % 60;

      timerText.text(`${minutes}:${seconds < 10 ? "0" : ""}${seconds}`);

      timeleft--;

      if (timeleft < 0) {
        clearInterval(timer);
        timerText.text("시간 초과");
      }
    }, 1000);
  });

  $("#confirm-auth-num").on("click", function (e) {
    e.preventDefault();
    var authNumber = $("#authNumField").val();

    if (!authNumber) {
      alert("인증번호를 입력해주세요.");
      return;
    }

    $.get(
      `/email/authnum/samecheck`,
      {
        email: $("#emilAddr").val(),
        authNum: $("#authNumField").val(),
      },
      function (responseA) {
        console.log(responseA);
        if (responseA.response) {
          console.log(responseA);
          alert("이메일 인증 성공");
        } else {
          alert("이메일 인증에 실패했습니다. 인증번호를 재발급 받아주세요");
        }
      }
    );
  });

  // 사용자가 비밀번호 및 비밀번호 확인에 값을 입력할 때 아래 함수가 실행된다.
  $("#pwd, #confirmPwd").on("input", function (e) {
    e.preventDefault();

    var pwd = $("#pwd").val();

    // 대소문자, 특수문자를 포함한 8자리 이상.
    var passwordRegex =
      /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;

    if (!passwordRegex.test(pwd)) {
      $("#pwd").css("border-color", "red");
      $("#errorPwd").text(
        "비밀번호는 대소문자, 숫자, 특수문자를 포함해 8자리 이상이어야 합니다."
      );
    } else {
      $("#pwd").css("border-color", "#dde2e4");
      $("#errorPwd").text("");
    }
	
	var confirmPwd = $("#confirmPwd").val();
    if (pwd !== confirmPwd) {
		$("#confirmPwd").css("border-color", "red");
		$("#errorConfirmPwd").text("비밀번호가 일치하지 않습니다.");
		
    } else {
		$("#errorConfirmPwd").text("");
		$("#confirmPwd").css("border-color", "#dde2e4");
	 
    }
  });

  $("#cmpnyBrnConfirm").on("click", function () {
    var cmpnyBrn = $("#cmpnyBrn").val();
    $.get(`/member/signup/cmpnycheck/${cmpnyBrn}`, {}, function (rp) {
      if (rp.response === true) {
        $("#cmpnyEmplyCnt").removeAttr("readonly");
        $("#cmpnyEmplyCnt").css("background-color", "white");
        $("#cmpnyEmplyCnt").val("");

        $("#cmpnyNm").removeAttr("readonly");
        $("#cmpnyNm").css("background-color", "white");
        $("#cmpnyNm").val("");

        $("#asd").removeAttr("readonly");
        $("#asd").css("background-color", "#F0F0F0");

        $("#addr").removeAttr("readonly");
        $("#addr").css("background-color", "white");

        $("#postcode").removeAttr("readonly");
        $("#postcode").css("background-color", "white");
        $("#postcode").val("");

        $("#detailAddress").removeAttr("readonly");
        $("#detailAddress").css("background-color", "white");

        $("#extraAddress").removeAttr("readonly");
        $("#extraAddress").css("background-color", "white");

        $("#cmpnyPhnNum").removeAttr("readonly");
        $("#cmpnyPhnNum").css("background-color", "white");
        $("#cmpnyPhnNum").val("");

        $("#cmpnySiteUrl").removeAttr("readonly");
        $("#cmpnySiteUrl").css("background-color", "white");
        $("#cmpnySiteUrl").val("");

        $("#industry").removeAttr("readonly");
        $("#industry").css("background-color", "white");

        $("#cmpnyBizCtgry").removeAttr("readonly");
        $("#cmpnyBizCtgry").css("background-color", "white");

        $("#cmpnyIndstrId").removeAttr("readonly");
        $("#cmpnyIndstrId").css("background-color", "white");

        $("#cmpnyBizCtgry2").removeAttr("readonly");
        $("#cmpnyBizCtgry2").css("background-color", "white");

        $("#cmpnyIndstrId2").removeAttr("readonly");
        $("#cmpnyIndstrId2").css("background-color", "white");
      } else {
        $(".cmpMsg").text(
          "직원인증을 위해 이용자 첨부파일에 사원증을 제출해주십시오. "
        );
        $("#cmpnyEmplyCnt").val(rp.response.cmpnyEmplyCnt);
        $("#cmpnyEmplyCnt").attr("readonly");
        $("#cmpnyEmplyCnt").css("background-color", "#DBDBDB");

        $("#cmpnyNm").val(rp.response.cmpnyNm);
        $("#cmpnyNm").attr("readonly");
        $("#cmpnyNm").css("background-color", "#DBDBDB");

        $("#asd").attr("readonly");
        $("#asd").css("background-color", "#DBDBDB");
        $("#addr").attr("readonly");
        $("#addr").css("background-color", "#DBDBDB");

        $("#postcode").val(rp.response.cmpnyAddr);
        $("#postcode").attr("readonly");
        $("#postcode").css("background-color", "#DBDBDB");

        $("#detailAddress").attr("readonly");
        $("#detailAddress").css("background-color", "#DBDBDB");

        $("#extraAddress").attr("readonly");
        $("#extraAddress").css("background-color", "#DBDBDB");

        $("#cmpnyPhnNum").val(rp.response.cmpnyPhnNum);
        $("#cmpnyPhnNum").attr("readonly");
        $("#cmpnyPhnNum").css("background-color", "#DBDBDB");

        $("#cmpnySiteUrl").val(rp.response.cmpnySiteUrl);
        $("#cmpnySiteUrl").attr("readonly");
        $("#cmpnySiteUrl").css("background-color", "#DBDBDB");

        $("#industry").attr("readonly");
        $("#industry").css("background-color", "#DBDBDB");

        $("#cmpnyBizCtgry").attr("readonly");
        $("#cmpnyBizCtgry").css("background-color", "#DBDBDB");
		$("#cmpnyBizCtgry").val(rp.response.mjrId);

        $("#cmpnyIndstrId").attr("readonly");
        $("#cmpnyIndstrId").css("background-color", "#DBDBDB");
		$("#cmpnyIndstrId").val(rp.response.smjrId);
		
        $("#cmpnyBizCtgry2").attr("readonly");
        $("#cmpnyBizCtgry2").css("background-color", "#DBDBDB");

        $("#cmpnyIndstrId2").attr("readonly");
        $("#cmpnyIndstrId2").css("background-color", "#DBDBDB");
      }
    });
  });
  $("#add_attr_file").on("click", function () {
    var fileListSize = $(".fileList").length;
    var newFile = $(
      `<input class="fileList" type="file" name="fileList[${fileListSize}]" />`
    );
    $(".addfile").last().append(newFile);
  });

  // 사업자 번호 인증할 때 호출되는 메서드이다. bizno에서 반환해주는 데이터를 담아준다.
  // 사업자 번호 인증할 때 호출되는 메서드이다.
  $(".cmpny-brn-confirm").on("click", function (e) {
    e.preventDefault();
    // 사업자 등록 번호
    var companyBrn = $("#cmpnyBrn").val();
    $.get(`/bizno/api/ask/${companyBrn}`, function (response) {
      var companyName = response.items[0];
      console.log(companyName);
      if (companyName != null) {
        $("#cmpnyNm").val(companyName.company);
        $("#cmpnyNm").attr("readonly");
        alert("가입이 가능한 사업자번호입니다.");
      }
    });
  });
  // 도로명 주소 찾을 때 사용
  $("#asd").on("click", function () {
    sample6_execDaumPostcode();
  });

  $("input[type=checkbox]").on("change", function () {
      var allChecked = true;

      $("input[type=checkbox]").each(function () {
        if (!$(this).is(":checked")) {
          allChecked = false;
          return false;
        }
      });

      if (!allChecked) {
        $(".checkboxMsg").text("*모든 체크박스를 선택해 주세요.");
      } else {
        $(".checkboxMsg").text("");
      }
    });
});


function sample6_execDaumPostcode() {
  new daum.Postcode({
    oncomplete: function (data) {
      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

      // 각 주소의 노출 규칙에 따라 주소를 조합한다.
      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
      var addr = ""; // 주소 변수
      var extraAddr = ""; // 참고항목 변수

      //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
      if (data.userSelectedType === "R") {
        // 사용자가 도로명 주소를 선택했을 경우
        addr = data.roadAddress;
      } else {
        // 사용자가 지번 주소를 선택했을 경우(J)
        addr = data.jibunAddress;
      }

      // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
      if (data.userSelectedType === "R") {
        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
        if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
          extraAddr += data.bname;
        }
        // 건물명이 있고, 공동주택일 경우 추가한다.
        if (data.buildingName !== "" && data.apartment === "Y") {
          extraAddr +=
            extraAddr !== "" ? ", " + data.buildingName : data.buildingName;
        }
        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
        if (extraAddr !== "") {
          extraAddr = " (" + extraAddr + ")";
        }
        // 조합된 참고항목을 해당 필드에 넣는다.
        document.getElementById("extraAddress").value = extraAddr;
      } else {
        document.getElementById("extraAddress").value = "";
      }

      // 우편번호와 주소 정보를 해당 필드에 넣는다.
      document.getElementById("postcode").value = data.zonecode;
      document.getElementById("addr").value = addr;
      // 커서를 상세주소 필드로 이동한다.
      document.getElementById("detailAddress").focus();
    },
  }).open();
}