// 이거 프로젝트 카드 지원하기 누르면 지원 페이지로 가는거임.
$().ready(function () {
  $(".project-title").on("click", function () {
    var pjId = $(this).data("pjid");
    location.href = `/project/info/${pjId}`;
    console.log(pjId);
  });
  
});
