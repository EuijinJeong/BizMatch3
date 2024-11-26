$().ready(function () {
  $("#content-box-company").on("click", function () {
    location.href = "/member/signup/company";
  });

  $("#content-box-free").on("click", function () {
    location.href = "/member/signup/freelancer";
  });
});
