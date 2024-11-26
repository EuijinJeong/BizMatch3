$().ready(function () {
  $("input, textarea").on("keypress", function (e) {
    if (e.which === 13) {
      e.preventDefault();
    }
  });

  $(".project-register-btn").on("click", function (e) {
    e.preventDefault();
    $("form").submit();
  });

  $("#search-input").on("keyup", function (e) {
    var value = $(this).val().toLowerCase();
    var option = null;

    $("#level-category option").filter(function () {
      var isMatch = $(this).text().toLowerCase().indexOf(value) > -1;
      $(this).toggle(isMatch);
      if (isMatch && !option) {
        option = $(this);
      }

      if (e.keyCode == 13) {
        if (option) {
          $("#level-category").val(option.val());
        }
      }
    });
  });

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

  $("#search-input2").on("keyup", function (e) {
    var value = $(this).val().toLowerCase();
    var option = null;

    $("#second-level-category option").filter(function () {
      var isMatch = $(this).text().toLowerCase().indexOf(value) > -1;
      $(this).toggle(isMatch);
      if (isMatch && !option) {
        option = $(this);
      }
    });

    if (e.keyCode == 13) {
      if (option) {
        $("#second-level-category").val(option.val());
      }
    }
  });

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

  /// 보유 기술 추가 코드 ///
  $.get(`/project/skill`, function (data) {
    // 보유 기술 검색
    $("#searchInput").on("input", function () {
      var query = $(this).val().toLowerCase();
      $("#results").empty();

      console.log(data);
      // 이미 추가된 기술을 제외하고 검색된 결과 표시
      var filteredResults = data
        .filter((item) => item.prmStk.toLowerCase().includes(query))
        .filter(
          (item) =>
            $(".result-skill-add-box").find(
              `.selected-skill[value='${item.prmStkId}']`
            ).length === 0 // 이미 추가된 기술은 제외
        );

      // 필터링된 결과를 li 목록으로 추가
      filteredResults.forEach((item) => {
        $("#results").append(
          `<li data-value="${item.prmStkId}" style="cursor:pointer">${item.prmStk}</li>`
        );
      });
    });

    // 보유 기술 검색창 focus 시 전체 리스트 보이기
    $("#searchInput").on("focus", function () {
      $("#results").show();
      $("#results").empty();

      // 이미 추가된 기술 제외한 전체 데이터 표시
      var filteredData = data.filter(
        (item) =>
          $(".result-skill-add-box").find(
            `.selected-skill[value='${item.prmStkId}']`
          ).length === 0 // 이미 추가된 기술은 제외
      );

      filteredData.forEach((item) => {
        $("#results").append(
          `<li data-value="${item.prmStkId}" style="cursor:pointer">${item.prmStk}</li>`
        );
      });
    });

    // li 클릭 이벤트를 문서에 위임
    $(document).on("click", "#results li", function () {
      var selectedSkill = $(this).text().trim();
      var prmStkId = $(this).data("value");
      var selectedSkillHTML = `<div class="skill-item" style="width:80%; background-color:#ffffff;"><label>${selectedSkill}</label><input class="selected-skill" name="prmStkId" value="${prmStkId}" type="hidden"/><span class="remove-skill" style="float:right; cursor:pointer;">x</span></div>`;

      // 선택한 기술이 이미 추가되어 있는지 확인
      if (
        $(".result-skill-add-box").find(`.selected-skill[value='${prmStkId}']`)
          .length === 0
      ) {
        $(".result-skill-add-box").append(selectedSkillHTML);
        console.log(
          "li>>" +
            $(".result-skill-add-box .skill-item input[name='prmStkId']").val()
        );
        // $(".skill-circle").toggleClass("selected");
        $(this).remove(); // 추가 후 검색 결과에서 제거
      } else {
        alert(`${selectedSkill}은(는) 이미 추가되어 있습니다.`);
      }
    });

    // focusout으로 포커스 벗어나면 리스트 숨기기
    $("#searchInput").on("focusout", function () {
      setTimeout(() => {
        $("#results").hide();
      }, 100); // 잠시 딜레이를 줘서 클릭 동작을 먼저 수행
    });
  });

  // 선택한 추천 기술 스택 배경색 토글
  $(".skill-circle").on("click", function () {
    $(this).toggleClass("selected");

    var selectedSkill = $(this).text().trim();
    var prmStkId = $(this).data("id"); // .skill-circle 요소의 data-id 값 가져오기

    if (!prmStkId) {
      console.error("prmStkId 값이 정의되지 않았습니다.");
      return;
    }

    // 선택한 기술이 이미 추가되어 있는지 확인
    if ($(this).hasClass("selected")) {
      // 기술이 이미 추가되어 있으면, 추가하지 않고 경고창 표시 후 종료
      if (
        $(".result-skill-add-box").find(`.selected-skill[value='${prmStkId}']`)
          .length > 0
      ) {
        alert(`${selectedSkill}은(는) 이미 추가되어 있습니다.`);
        $(this).removeClass("selected"); // 중복 추가 방지를 위해 선택 상태 해제
        return;
      }

      // 기술 추가
      var selectedSkillHTML = `<div class="skill-item" style="width:80%; background-color:#ffffff;"><label>${selectedSkill}</label><input class="selected-skill" name="prmStkId" value="${prmStkId}" type="hidden"/><span class="remove-skill" style="float:right; cursor:pointer;">x</span></div>`;
      $(".result-skill-add-box").append(selectedSkillHTML);
      console.log(
        $(".result-skill-add-box .skill-item input[name='prmStkId']").val()
      );
    } else {
      // 선택 해제 시 기술 제거
      $(".result-skill-add-box")
        .find(`.selected-skill[value='${prmStkId}']`)
        .closest("div")
        .remove();
    }
  });

  $(document).on("click", ".remove-skill", function () {
    var skillItem = $(this).closest(".skill-item");
    var prmStkId = skillItem.find(".selected-skill").val();

    skillItem.remove();

    $(`.skill-circle[data-id='${prmStkId}']`).removeClass("selected");
  });

  $("form").on("submit", function (e) {
    var isValid = true;

    // 산업분류 값이 비어있는 경우
    var industryCategory = $("#level-category").val();
    var secondindustry = $("#second-level-category").val();

    if (industryCategory === "" || secondindustry === "") {
      $("#second-level-category").css("border-color", "red");
      $("#error").text("산업군은 필수 입력 사항입니다.");
      isValid = false;
    } else {
      $("#second-level-category").css("border-color", "");
    }

    // 제목이 비어있는 경우
    var title = $(".project-title-input").val().trim();
    if (title === "") {
      $(".project-title-input").css("border-color", "red");
      $("#error").text("프로젝트 제목은 필수 입력 사항입니다.");
      isValid = false;
    } else {
      $(".project-title-input").css("border-color", "");
    }

    // 프로젝트 시작일 검증.
    var startDt = $("#strt-date").val().trim();
    if (startDt === "") {
      $("#strt-date").css("border-color", "red");
      $("#error").text("프로젝트 시작일은 필수 입력 사항입니다.");
      isValid = false;
    } else {
      $(".project-title-input").css("border-color", "");
    }

    // 프로젝트 종료일 검증.
    var endDt = $("#end-date").val().trim();
    if (endDt === "") {
      $("#end-date").css("border-color", "red");
      $("#error").text("프로젝트 종료일은 필수 입력 사항입니다.");
      isValid = false;
    } else {
      $("#end-date").css("border-color", "");
    }

    // 프로젝트 상세설명 검증.
    var projectDesc = $(".project-contents-input").val().trim();
    if (projectDesc === "") {
      $(".project-contents-input").css("border-color", "red");
      $("#error").text("프로젝트 설명은 필수 입력 사항입니다.");
      isValid = false;
    } else {
      $(".project-contents-input").css("border-color", "");
    }

    // 프로젝트 금액이 비어있는 경우.
    var projectPrice = $("#amount").val();
    if (projectPrice === "") {
      $("#amount").css("border-color", "red");
      $("#error").text("프로젝트 금액은 필수 입력 사항입니다.");
      isValid = false;
    } else {
      $("#amount").css("border-color", "");
    }

    // 프로젝트 계약금이 100만원보다 적은 경우.
    if (projectPrice < 1000000) {
      $("#amount").css("border-color", "red");
      $("#error").text("프로젝트 계약금은 1,000,000원 이상이어야 합니다.");
      isValid = false;
    } else {
      $("#amount").css("border-color", "");
    }

    // 프로젝트 인원 모집 시작일이 비어있는 경우.
    var recuritStartDt = $("#date").val().trim();
    if (recuritStartDt === "") {
      $("#date").css("border-color", "red");
      $("#error").text("프로젝트 모집 시작일은 필수 입력 사항입니다.");
      isValid = false;
    } else {
      $("#date").css("border-color", "");
    }

    // 프로젝트 인원 모집 종료일이 비어있는 경우.
    var recuritStartDt = $("#date").val().trim();
    if (recuritStartDt === "") {
      $("#date").css("border-color", "red");
      $("#error").text("프로젝트 모집 시작일은 필수 입력 사항입니다.");
      isValid = false;
    } else {
      $("#date").css("border-color", "");
    }

    // 첨부파일이 없는 경우
    var fileAtt = $("#fileInput").get(0).files.length;
    if (fileAtt === 0) {
      $("#error").text("첨부파일은 필수 입력 사항입니다.");
      isValid = false;
    }

    var recuritCnt = $("#people").val();
    if (recuritCnt === "" || recuritCnt == 0) {
      $("#error").text("모집인원은 필수 입력 사항입니다.");
      isValid = false;
    }

    if (!isValid) {
      return false;
    }
  });
});
