$().ready(function () {
  var btnId = null;
  var boardId = $(".card-include").data("projectid");
  var commentId = null;

  $(window).on("beforeunload", function () {
    sessionStorage.setItem("scrollPosition", $(window).scrollTop());
  });

  // 페이지가 로드될 때 스크롤 위치 복원
  $(window).on("load", function () {
    var scrollPosition = sessionStorage.getItem("scrollPosition");
    if (scrollPosition) {
      $(window).scrollTop(parseInt(scrollPosition));
      sessionStorage.removeItem("scrollPosition"); // 복원 후 삭제
    }
  });


  $(".new-comment-button").on("click", function () {
    btnId = "new";
    $("#commentModal").css("display", "flex");
    $("#commentModal")[0].showModal();
    $(".modal-container").css("display", "block");
  });

  $(".close-btn").on("click", function () {
    $("#commentModal").css("display", "none");
    $("#commentModal")[0].close();
    $(".modal-container").css("display", "none");
    $("#cmmntCntnt").text("");
  });

  $(".submit-btn2").on("click", function () {
    $(".modal-container").css("display", "none");
    $("#commentModal").css("display", "none");
  });

  $(".delete-btn").on("click", function () {
    commentId = $(this).closest(".fuction-line").data("id");

    $.get(`/project/info/delete/${commentId}`, {}, function () {
      location.reload(); // 새로고침
    });
  });

  $(".modify-btn").on("click", function () {
    btnId = "modify";
    commentId = $(this).closest(".fuction-line").data("id");
    var tmp = $(this).data("text");
    $("#cmmntCntnt").text(tmp);
    $("#commentModal").css("display", "flex");
    $("#commentModal")[0].showModal();
    $(".modal-container").css("display", "block");
  });

  $(".recomment-btn").on("click", function () {
    btnId = "recomment";
    commentId = $(this).closest(".fuction-line").data("id");
    $("#commentModal").css("display", "flex");
    $("#commentModal")[0].showModal();
    $(".modal-container").css("display", "block");
  });

  $(".submit-btn2").on("click", function () {
    var text = $("#cmmntCntnt").val();

    if (btnId === "modify") {
      $.post(
        "/project/info/modify",
        {
          pjCmmntId: commentId,
          cmmntCntnt: text,
        },
        function () {
          location.reload();
        }
      );
    } else if (btnId === "new") {
      $.post(
        "/project/info/write",
        {
          pjId: boardId,
          prntCmmntId: null,
          cmmntCntnt: text,
        },
        function () {
          location.reload();
        }
      );
    } else if (btnId === "recomment") {
      $.post(
        "/project/info/write",
        {
          pjId: boardId,
          prntCmmntId: commentId,
          cmmntCntnt: text,
        },
        function () {
          location.reload();
        }
      );
    }
    $("#commentModal").css("display", "none");
    $("#commentModal")[0].close();
    $(".modal-container").css("display", "none");
    $("#cmmntCntnt").text("");
  });
});
