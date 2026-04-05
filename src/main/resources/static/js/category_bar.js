$().ready(function () {
  // 대분류 클릭하면 해당 대분류에 속한 중분류 값 가져온다.
  $(".level-category").on("change", function (e) {
    // e.preventDefault();
    var optionval = $(".level-category option:selected").val();
    console.log(optionval);
    $.ajax({
      url: `/category/filter/${optionval}`,
      method: "GET",
      dataType: "json",
      success: function (response) {
        console.log(response);

        // 중분류 select 요소 선택
        var $secondLevelCategory = $(".second-level-category");

        // 기존 옵션 제거
        $secondLevelCategory.empty();

        // "중분류" 기본 옵션 추가
        $secondLevelCategory.append('<option value="0">중분류</option>');

        // 받아온 데이터로 옵션 생성하여 추가
        response.forEach(function (item) {
          var option = `<option value="${item.id}">${item.name}</option>`;
          $secondLevelCategory.append(option);
        });
      },
      error: function (error) {
        console.log(error);
      },
    });
  });

  var cmpnyBizCtgry;
  var cmpnyIndstrId;
  $("input, textarea").on("keypress", function (e) {
    if (e.which === 13) {
      e.preventDefault();
    }
  });

  $("#cate_bar1-first").on("keyup", function (e) {
    var value = $(this).val().toLowerCase();
    var option = null;

    $("#cmpnyBizCtgry option").filter(function () {
      var isMatch = $(this).text().toLowerCase().indexOf(value) > -1;
      $(this).toggle(isMatch);
      if (isMatch && !option) {
        option = $(this);
      }

      if (e.keyCode == 13) {
        if (option) {
          $("#cmpnyBizCtgry").val(option.val());
        }
      }
    });

    $("#cmpnyBizCtgry").on("click", function (e) {
      e.preventDefault();
      cmpnyBizCtgry = $("#cmpnyBizCtgry").val();
    });

    $("#cmpnyIndstrId").on("click", function (e) {
      e.preventDefault();
      cmpnyIndstrId = $("#cmpnyIndstrId").val();
    });
  });

  $("#cate_bar1-second").on("keyup", function (e) {
    var value = $(this).val().toLowerCase();
    var option = null;

    $("#cmpnyIndstrId option").filter(function () {
      var isMatch = $(this).text().toLowerCase().indexOf(value) > -1;
      $(this).toggle(isMatch);
      if (isMatch && !option) {
        option = $(this);
      }

      if (e.keyCode == 13) {
        if (option) {
          $("#cmpnyIndstrId").val(option.val());
        }
      }
    });

    $("#cmpnyBizCtgry").on("click", function (e) {
      e.preventDefault();
      cmpnyBizCtgry = $("#cmpnyBizCtgry").val();
    });

    $("#cmpnyIndstrId").on("click", function (e) {
      e.preventDefault();
      cmpnyIndstrId = $("#cmpnyIndstrId").val();
    });
  });

  $("#cate_bar2-first").on("keyup", function (e) {
    var value = $(this).val().toLowerCase();
    var option = null;

    $("#cmpnyBizCtgry2 option").filter(function () {
      var isMatch = $(this).text().toLowerCase().indexOf(value) > -1;
      $(this).toggle(isMatch);
      if (isMatch && !option) {
        option = $(this);
      }

      if (e.keyCode == 13) {
        if (option) {
          $("#cmpnyBizCtgry2").val(option.val());
        }
      }
    });

    $("#cmpnyBizCtgry").on("click", function (e) {
      e.preventDefault();
      cmpnyBizCtgry = $("#cmpnyBizCtgry").val();
    });

    $("#cmpnyIndstrId").on("click", function (e) {
      e.preventDefault();
      cmpnyIndstrId = $("#cmpnyIndstrId").val();
    });
  });

  $("#cate_bar2-second").on("keyup", function (e) {
    var value = $(this).val().toLowerCase();
    var option = null;

    $("#cmpnyIndstrId2 option").filter(function () {
      var isMatch = $(this).text().toLowerCase().indexOf(value) > -1;
      $(this).toggle(isMatch);
      if (isMatch && !option) {
        option = $(this);
      }

      if (e.keyCode == 13) {
        if (option) {
          $("#cmpnyIndstrId2").val(option.val());
        }
      }
    });

    $("#cmpnyBizCtgry").on("click", function (e) {
      e.preventDefault();
      cmpnyBizCtgry = $("#cmpnyBizCtgry").val();
    });

    $("#cmpnyIndstrId").on("click", function (e) {
      e.preventDefault();
      cmpnyIndstrId = $("#cmpnyIndstrId").val();
    });
  });
});
