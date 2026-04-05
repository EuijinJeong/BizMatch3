$().ready(function () {
  // 파일 추가
  $("#fileInput").on("change", function () {
    const files = this.files;
    if (files.length === 0) {
      alert("선택된 파일이 없습니다.");
      return;
    }
    $.each(files, function (index, file) {
      $("#fileSelect").append(
        $("<option>", {
          text: file.name,
          value: file.name,
        })
      );
    });
  });

  // 파일 삭제
  $("#removeButton").on("click", function () {
    const selectedIndex = $("#fileSelect").prop("selectedIndex");
    if (selectedIndex !== -1) {
      $("#fileSelect option").eq(selectedIndex).remove();
    } else {
      alert("삭제할 파일을 선택해주세요.");
    }
  });

  $("form").on("submit", function (e) {
    var isValid = true;
    // 제목이 비어있는 경우
    var title = $(".project-title-input").val().trim();
    if (title === "") {
      $(".project-title-input").css("border-color", "red");
      $("#error-title").text("프로젝트 제목은 필수 입력 사항입니다.");
      isValid = false;
    } else {
      $(".project-title-input").css("border-color", "");
    }
    // 상세 설명이 비어있는 경우
    var contents = $(".project-contents-input").val().trim();
    if (contents === "") {
      $(".project-contents-input").css("border-color", "red");
      $("#error-description").text("프로젝트 상세설명은 필수 입력 사항입니다.");
      isValid = false;
    } else {
      $(".project-contents-input").css("border-color", "");
    }
    // 첨부파일이 없는 경우
    var fileAtt = $("#fileInput").get(0).files.length;
    if (fileAtt === 0) {
      $("#error-file").text("첨부파일은 필수 입력 사항입니다.");
      isValid = false;
    }
    // 에러 메시지가 있을 경우 표시
    if (!isValid) {
      e.preventDefault();
      error.html(errorMessages.join("<br>")).css("color", "red"); // 여러 메시지를 줄바꿈으로 표시
    }
  });

  // 수정 버튼 클릭 이벤트
  $("#apply-edit-btn").on("click", function (e) {
    e.preventDefault();
    var formData = new FormData();

    // 필수 데이터 추가
    formData.append("pjId", $("#apply-edit-btn").data("pjid"));
    formData.append("pjApplyId", $("input[name='pjApplyId']").val()); // 추가
    formData.append("emilAddr", $("input[name='emilAddr']").val()); // 추가
    formData.append("pjApplyTtl", $("input[name='pjApplyTtl']").val());
    formData.append("pjApplyDesc", $("textarea[name='pjApplyDesc']").val());

    // 파일 리스트 추가
    var files = $("#fileInput")[0].files;
    $.each(files, function (index, file) {
      formData.append("fileList", file);
    });

    // AJAX 요청
    $.ajax({
      url: "/project/apply/edit",
      type: "POST",
      data: formData,
      contentType: false,
      processData: false,
      success: function (response) {
        if (response.success) {
          alert("수정되었습니다.");
          window.location.href = "/project/all/order/recipient";
        } else {
          alert("수정에 실패했습니다.");
        }
      },
      error: function (xhr, status, error) {
        console.log("Status: " + xhr.status);
        console.log("Response: " + xhr.responseText);
        alert("서버와의 통신에 문제가 발생했습니다.");
      },
    });
  });

  $(".project-cancel-btn").on("click", function () {
    window.location.href = "/project/all/order/recipient";
  });
});
