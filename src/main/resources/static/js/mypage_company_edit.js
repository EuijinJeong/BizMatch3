$().ready(function () {
  // 초기 선택된 기술 ID 리스트
  let initialSelectedSkillIds = $(".result-skill-add-box .selected-skill")
    .map(function () {
      return $(this).val();
    })
    .get();

  // 이미 선택된 기술에 `selected` 클래스를 추가
  $(".skill-circle").each(function () {
    const skillId = $(this).data("id").toString();
    if (initialSelectedSkillIds.includes(skillId)) {
      $(this).addClass("selected");
    }
  });

  // 도로명 주소 찾을 때 사용
  $("#asd").on("click", function () {
    sample6_execDaumPostcode();
  });

  $("#save-btn").on("click", function () {
    var address =
      $("#addr").val() +
      " " +
      $("#detailAddress").val() +
      " " +
      $("#extraAddress").val();
    $(".detail-address").text(address);
    $("#reportModal").css("display", "none");
  });

  $("#mypageeditbutton").on("click", function () {
    var cmpid = $("#sessionA").data("cmpid");
    var cmpnm = $("#cmpnyNm").val();
    var cmpurl = $("#cmpnyUrl").val();
    var cmpaddr = $("#cmpnyAddr").text().trim();
    var cmpintr = $("#cmpnyIntr").val().trim();
    var selectedSkills = $(".result-skill-add-box .selected-skill")
      .map(function () {
        return { prmStkId: $(this).val() };
      })
      .get();
    var cmpnyAccuntNum = $("#account-input").val().trim();
    var cmpnyBizCtgryId = $("#cmpnyBizCtgry2").val();
    var cmpnyIndstrId = $("#cmpnyIndstrId2").val();
    var emilAddr = $("#sessionA").data("email");
    console.log(emilAddr);

    $.ajax({
      url: `/member/mypage/company/edit`,
      method: "POST",
      dataType: "json",
      contentType: "application/json",
      data: JSON.stringify({
        cmpnyId: cmpid,
        cmpnyNm: cmpnm,
        cmpnySiteUrl: cmpurl,
        cmpnyAddr: cmpaddr,
        cmpnyIntr: cmpintr,
        mbrPrmStkList: selectedSkills,
        cmpnyAccuntNum: cmpnyAccuntNum,
        cmpnyBizCtgryId: cmpnyBizCtgryId,
        cmpnyIndstrId: cmpnyIndstrId,
        emilAddr: emilAddr,
      }),
      success: function (response) {
        location.href = `/member/mypage/company/${cmpid}`;
      },
      error: function (jqXHR, textStatus, errorThrown) {
        // 요청이 실패했을 때 실행될 코드
      },
    });
  });

  // 관심 산업 데이터 가져오기 및 선택 상태로 설정
  $.ajax({
    url: `/member/mypage/company/industry`,
    method: "GET",
    success: function (response) {
      const { cmpnyBizCtgryId, cmpnyIndstrId, mjrNm, smjrNm } = response; // 서버에서 받은 대분류 및 중분류 데이터
      console.log(response);

      // 대분류를 설정 및 비활성화
      $("#cmpnyBizCtgry2 option").each(function () {
        if ($(this).text() === mjrNm) {
          $(this).prop("selected", true);
        }
      });

      // 중분류를 설정 및 비활성화
      $("#cmpnyIndstrId2 option").each(function () {
        if ($(this).text() === smjrNm) {
          $(this).prop("selected", true);
        }
      });
    },
    error: function (error) {
      console.error(
        "관심 산업 데이터를 가져오는 중 오류가 발생했습니다.",
        error
      );
    },
  });

  // 모달창 부분 - .report 요소가 페이지 로딩 이후에 동적으로 추가되었기 때문에 이벤트 위임 방식
  // 이벤트 위임: 이벤트 위임은 기존에 페이지에 있는 부모 요소에 이벤트를 걸어 두는 방식
  $(document).on("click", ".edit", function () {
    $("#reportModal").css("display", "block");
  });

  $(".close-btn").on("click", function () {
    $("#reportModal").css("display", "none");
  });

  $(window).on("click", function (e) {
    if ($(e.target).is("#reportModal")) {
      $("#reportModal").css("display", "none");
    }
  });

  // 모달 드래그 이동
  var isDragging = false;

  $(".modal-content").on("mousedown", function (e) {
    isDragging = true;

    // 마우스 클릭 위치와 .modal-content 요소의 현재 위치 차이를 계산
    // offset(): HTML 요소의 위치를 조절하거나 가져올 때 사용
    offsetX = e.pageX - $(this).offset().left;
    offsetY = e.pageY - $(this).offset().top;

    $("body").css("user-select", "none");
  });

  $(document).on("mousemove", function (e) {
    if (isDragging) {
      $(".modal-content").offset({
        top: e.pageY - offsetY, // 마우스 Y 위치에서 초기 클릭한 위치 차이를 뺀 값을 .modal-content의 새 Y 위치로 설정
        left: e.pageX - offsetX,
      });
    }
  });

  $(document).on("mouseup", function () {
    isDragging = false;
    $("body").css("user-select", "auto");
  });

  // 사이드바 이동
  $(".sidebar-menu").click(function () {
    const targetScroll = $(this).data("target");
    const headerHeight = window.innerHeight * 0.2; // 헤더 높이 고려

    if (targetScroll) {
      $("html, body").animate(
        {
          scrollTop: $(targetScroll).offset().top - headerHeight,
        },
        600
      );
    }
  });

  /// 보유 기술 추가 코드 ///
  $.get("/project/skill", function (data) {
    // 보유 기술 검색
    $("#searchInput").on("input", function () {
      var query = $(this).val().toLowerCase();
      $("#results").empty();

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
});

function sample6_execDaumPostcode() {
  new daum.Postcode({
    oncomplete: function (data) {
      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

      // 각 주소의 노출 규칙에 따라 주소를 조합한다.
      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
      var addr = ""; // 주소 변수
      var extraAddr = ""; // 참고항목 변수

      //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
      if (data.userSelectedType === "R") {
        // 사용자가 도로명 주소를 선택했을 경우
        addr = data.roadAddress;
      } else {
        // 사용자가 지번 주소를 선택했을 경우(J)
        addr = data.jibunAddress;
      }

      // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
      if (data.userSelectedType === "R") {
        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
        if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
          extraAddr += data.bname;
        }
        // 건물명이 있고, 공동주택일 경우 추가한다.
        if (data.buildingName !== "" && data.apartment === "Y") {
          extraAddr +=
            extraAddr !== "" ? ", " + data.buildingName : data.buildingName;
        }
        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
        if (extraAddr !== "") {
          extraAddr = " (" + extraAddr + ")";
        }
        // 조합된 참고항목을 해당 필드에 넣는다.
        document.getElementById("extraAddress").value = extraAddr;
      } else {
        document.getElementById("extraAddress").value = "";
      }

      // 우편번호와 주소 정보를 해당 필드에 넣는다.
      document.getElementById("postcode").value = data.zonecode;
      document.getElementById("addr").value = addr;
      // 커서를 상세주소 필드로 이동한다.
      document.getElementById("detailAddress").focus();
    },
  }).open();
}
