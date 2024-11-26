$().ready(function () {
  // 사용자가 비밀번호 및 비밀번호 확인에 값을 입력할 때 아래 함수가 실행된다.
  $("#newPwd, #confirmNewPwd").on("c", function (e) {
    e.preventDefault();

    var pwd = $("#newPwd").val();

    // 대소문자, 특수문자를 포함한 8자리 이상.
    var passwordRegex =
      /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;

    if (!passwordRegex.test(pwd)) {
      $("#newPwd").css("border-color", "red");
      $("#errorConfirmPwd").text(
        "비밀번호는 대소문자, 숫자, 특수문자를 포함해 8자리 이상이어야 합니다."
      );
    } else {
      $("#newPwd").css("border-color", "#DDE2E4");
      $("#errorConfirmPwd").text("");
    }

    var confirmPwd = $("#confirmNewPwd").val();
    if (pwd !== confirmPwd) {
      $("#confirmNewPwd").css("border-color", "red");
      $("#errorConfirmPwd").text("비밀번호가 일치하지 않습니다.");
    } else {
      $("#confirmNewPwd").css("border-color", "#DDE2E4");
      $("#errorConfirmPwd").text("");
    }
  });
});
