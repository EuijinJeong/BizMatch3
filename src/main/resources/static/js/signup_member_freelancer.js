$().ready(function () {
  // 도로명 주소 api 호출하는 메서드.
  $("#asd").on("click", function () {
    sample6_execDaumPostcode();
  });

  // 이메일 중복검사 버튼을 클릭할 때 아래의 코드가 수행된다.
  $("#emilAddr").keyup(function () {
    // 유효성 검사 진행.
    if ($(this).val() === "") {
      alert("이메일 값을 입력해주세요.");
      return;
    }
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

  // 이메일 인증번호 날려주는 이벤트.
  $("#confirm-email").on("click", function () {
    var email = $("#emilAddr").val();
    $.ajax({
      url: `/email/check/${email}/`,
      method: "GET",
    });
    console.log("email" + email);
    alert(email + "로 인증번호가 발급되었습니다.");
    var timeleft = 300;
    // var inputBox = $("#authNumField");
    var timerText = $(".timer");
    var timer = setInterval(function () {
      var minutes = Math.floor(timeleft / 60);
      var seconds = timeleft % 60;
      //   inputBox.attr(
      //     "placeholder",
      //     `${minutes}:${seconds < 10 ? 0 : ""}${seconds}`
      //   );
      timerText.text(`${minutes}:${seconds < 10 ? "0" : ""}${seconds}`);
      timeleft--;
      if (timeleft < 0) {
        clearInterval(timer);
        timerText.text("시간 초과");
      }
    }, 1000);
  });

  // 데이터베이스에 인증번호 발급받아서 저장한 값이랑 비교하는거 호출.
  $("#confirm-auth-num").on("click", function (e) {
    e.preventDefault();
    var emil = $("#emilAddr").val();
    var authNum = $("#authNumField").val();
    console.log("인증번호: ", authNum);
    if (!authNum) {
      alert("인증번호를 입력해주세요.");
      return;
    }
    $.get(
      "/email/authnum/samecheck",
      {
        email: emil,
        authNum: authNum,
      },
      function (response) {
        console.log(response);
        if (response.response) {
          alert("이메일 인증 성공");
        } else {
          alert("이메일 인증에 실패하였습니다. 인증번호를 재발급 받아주세요.");
        }
      }
    );

    // 사용자가 비밀번호 및 비밀번호 확인에 값을 입력할 때 아래 함수가 실행된다.
    $("#pwd, #confirmPassword").on("input", function (e) {
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
        $("#pwd").css("border-color", "#DDE2E4");
        $("#errorPwd").text("");
      }
      var confirmPwd = $("#confirmPassword").val();
      if (pwd !== confirmPwd) {
        $("#confirmPassword").css("border-color", "red");
        $("#errorConfirmPwd").text("비밀번호가 일치하지 않습니다.");
      } else {
        $("#confirmPassword").css("border-color", "#DDE2E4");
        $("#errorConfirmPwd").text("");
      }
    });

    // 파일 첨부 버튼 클릭.
    $("#add_attr_file").on("click", function () {
      var fileListSize = $(".fileList").length;
      var newFile = $(
        `<input class="fileList" type="file" name="fileList[${fileListSize}]" />`
      );
      $(".addfile").last().append(newFile);
    });

    $("#search-input").on("keyup", function (e) {
      var value = $(this).val().toLowerCase();
      var option = null;

      $("#level-category option").filter(function () {
        var isMatch = $(this).text().toLowerCase().indexOf(value) > -1;
        $(this).toggle(isMatch);
        if (isMatch && !option) {
          option = $(this);
        }

        if (e.keyCode == 13) {
          if (option) {
            $("#level-category").val(option.val());
          }
        }
      });
    });

    $("#search-input2").on("keyup", function (e) {
      var value = $(this).val().toLowerCase();
      var option = null;

      $("#second-level-category option").filter(function () {
        var isMatch = $(this).text().toLowerCase().indexOf(value) > -1;
        $(this).toggle(isMatch);
        if (isMatch && !option) {
          option = $(this);
        }
      });

      if (e.keyCode == 13) {
        if (option) {
          $("#second-level-category").val(option.val());
        }
      }
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

    $(".signupbtn").on("click", function (e) {
      e.preventDefault();
      $("form").submit();
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
