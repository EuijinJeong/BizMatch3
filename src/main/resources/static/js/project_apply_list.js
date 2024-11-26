$().ready(function () {
  $("#detail-btn").on("click", function () {
    var type = $(this).data("type");
    var id = $(this).data("index");
    if (type === 1) {
      location.href = `/member/mypage/freelancer/${id}/`;
    } else if (type === 0) {
      location.href = `/member/mypage/company/${id}`;
    }
  });

  $(".btn.accept, .btn.reject").on("click", function () {
    var formId = $(this).data("form-id");
    var actionUrl = $(this).data("action-url");
    $("#form-" + formId)
      .attr("action", actionUrl)
      .submit();
  });

  $(".company-name").on("click", function () {
    var pjId = $(this).data("id");
    var applyId = $(this).data("apply");
    location.href = `/project/apply/member/view/${pjId}/${applyId}`;
  });

  $("#accept").on("click", function () {
    var isAccept = confirm(
      "지원자 수락을 확정하시겠습니까? 진행하시려면 '확인'을 눌러주세요."
    );
    if (isAccept) {
      alert("프로젝트 수락이 완료되었습니다.");
    } else {
      alert("프로젝트 수락이 취소되었습니다.");
    }
    var pjId = $(this).data("id");
    var applyId = $(this).data("apply");
    location.href = `/project/apply/member/view/${pjId}/${applyId}`;
  });

  $(".addRecruitment").on("click", function (e) {
    var isrecruitment = confirm(
      "추가모집을 확정하시겠습니까? 진행하시려면 '확인'을 눌러주세요."
    );
    if (isrecruitment) {
      alert("추가모집이 완료되었습니다.");
    } else {
      alert("추가모집이 취소되었습니다.");
    }
    // e.preventDefault(); // 기본 폼 제출 동작 막기

    // var pjId = $(this).data("pjid");

    // $.ajax({
    //   url: `/project/update/addrecruitment/${pjId}`,
    //   type: "POST",
    //   success: function (response) {
    //     if (response.result) {
    //       alert("추가 모집이 성공적으로 업데이트되었습니다.");
    //       // 필요한 경우 UI 업데이트 또는 페이지 리로드
    //       location.reload(); // 예시로 페이지 리로드
    //     } else {
    //       alert("추가 모집 업데이트에 실패했습니다.");
    //     }
    //   },
    //   error: function (xhr, status, error) {
    //     console.error("에러 발생:", error);
    //     alert("서버와의 통신 중 오류가 발생했습니다.");
    //   },
    // });
  });
});
