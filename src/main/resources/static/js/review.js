$().ready(function () {
  let scr = 0;
  $(".dropdown-options").hide();

  // 드롭다운 열기 및 닫기
  $(".dropdown-selected").on("click", function () {
    $(".dropdown-options").toggle();
  });

  // 옵션 선택 시 선택된 별점 표시
  $(".dropdown-option").on("click", function () {
    const selectedValue = $(this).data("value");
    scr = selectedValue; // 선택한 값으로 scr 업데이트
    const selectedIcons = $(this).html();

    $(".dropdown-selected").html(selectedIcons); // 선택한 별점 아이콘 표시
    $(".dropdown-options").hide(); // 옵션 목록 숨김
  });

  // 드롭다운 외부 클릭 시 닫기
  $(document).on("click", function (e) {
    if (!$(e.target).closest(".custom-dropdown").length) {
      $(".dropdown-options").hide();
    }
  });

  // 리뷰 등록 버튼 클릭 이벤트
  $("#submitReview").on("click", function () {
    const reviewContent = $("#reviewContent").val();
    const projectId = ""; // 필요에 따라 설정

    if (!reviewContent) {
      alert("프런트 리뷰 내용을 입력해주세요.");
      return;
    }

    $.ajax({
      url: `/project/${projectId}/review`,
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify({
        rvwCntnt: reviewContent,
        scr: scr, // 선택한 별점 점수 전송
      }),
      success: function (response) {
        if (response.result) {
          alert("리뷰가 등록되었습니다.");
          $("#reviewContent").val("");
          $(".dropdown-selected").html("별점을 선택해주세요"); // 기본 텍스트로 초기화
          scr = 0; // 별점 값 초기화
        } else {
          alert("리뷰 등록에 실패했습니다.");
        }
      },
      error: function () {
        alert("리뷰 등록 중 오류가 발생했습니다.");
      },
    });
  });
});
