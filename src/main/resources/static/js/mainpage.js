$().ready(function () {
  console.log($("#sessionA").data("email"));
  console.log($("#sessionA").data("cmpid"));

  $("#sign-up").on("click", function () {});

  $("#reg-btn").on("click", function () {
    var isLoggedIn = $(".header-email").data("email");
    if (isLoggedIn) {
      location.href = "/project/regist";
    } else {
      $("#login-modal").css("display", "block");
      $("#overlay").css("display", "block");
    }
  });

  $("#fourth-section-box-qna").on("click", function () {
    var isLoggedIn = $(".header-email").data("email");
    if (isLoggedIn) {
      location.href = "/board/list";
    } else {
      $("#login-modal").css("display", "block");
      $("#overlay").css("display", "block");
    }
  });

  $(".fifth-section-btn").on("click", function (e) {
    var isLoggedIn = $(".header-email").data("email");
    if (isLoggedIn) {
      location.href = "/project/regist";
    } else {
      $("#login-modal").css("display", "block");
      $("#overlay").css("display", "block");
    }
  });
});
