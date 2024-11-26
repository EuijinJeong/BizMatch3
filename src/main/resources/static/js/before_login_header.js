$().ready(function () {
  $("#loginBtn").on("click", function () {
    $("#login-modal").css("display", "block");
    $("#overlay").css("display", "block");
  });

  $("#modal-close-btn").on("click", function () {
    $("#overlay").css("display", "none");
    $("#login-modal").css("display", "none");
  });

  $("#overlay").on("click", function () {
    $("#overlay").css("display", "none");
    $("#login-modal").css("display", "none");
  });

  $("#login-input-email").on("focus", function () {
    $(this).attr("placeholder", "");
  });

  $("#login-input-email").on("blur", function () {
    $(this).attr("placeholder", "이메일을 입력하세요.");
  });

  $("#login-input-pwd").on("focus", function () {
    $(this).attr("placeholder", "");
  });

  $("#login-input-pwd").on("blur", function () {
    $(this).attr("placeholder", "비밀번호를 입력하세요.");
  });

  $("#sign-up").on("click", function () {
    location.href = "/member/select/membertype";
  });
});
