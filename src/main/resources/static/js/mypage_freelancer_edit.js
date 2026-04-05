$().ready(function () {
	var email = location.href.split("/").pop();
	console.log(email);
  loadPortfolioList();

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

  // 마이페이지 수정 내용 저장
  $("#mypageeditbutton").click(function () {
    var mbrIntr = $("#mbrIntr").val().trim();
    var selectedSkills = $(".result-skill-add-box .selected-skill")
      .map(function () {
        return { prmStkId: $(this).val() };
      })
      .get();
    var accntNum = $("#account-input").val().trim();

    $.ajax({
      url: `/member/mypage/freelancer/edit`,
      method: "POST",
      dataType: "json",
      contentType: "application/json",
      data: JSON.stringify({
        mbrIntr: mbrIntr,
        mbrPrmStkList: selectedSkills,
        accntNum: accntNum,
      }),
      success: function (response) {
        location.href = "/member/mypage/freelancer/"+email+"/";
      },
    });
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

function loadPortfolioList() {
  // 최신 3개의 포트폴리오 리스트를 가지고 오는 요청을 하는 메소드.
  var email = location.href.split("/").pop();
  $.ajax({
    url: "/member/mypage/company/portfolio/content/"+email,
    method: "GET",
	data: {
	  currPageNo: 1,          // 현재 페이지 번호 (원하는 페이지 번호로 변경 가능)
	  exposureListSize: 3     // 노출할 리스트의 크기 (최대 3개)
	},
	dataType: "json",
	success: function (response) {
	  const portfolioList = response.portfolioList;
      $(".result").empty();

      if (portfolioList.length === 0) {
        $(".result").append(
          '<div class="no-portfolio">포트폴리오 정보가 없습니다</div>'
        );
      } else {
        portfolioList.slice(0, 3).forEach(function (portfolio) {
          var portfolioCard = $(`
              <div class="portfolio-item">
                <a href="/view/portfolio/view/detail/${portfolio.id}">
                  <img class="location-port" src="${portfolio.img}" style="object-fit: cover; width: 100%; height: 100%;" />
                </a>
              </div>
            `);
          $(".result").append(portfolioCard);
        });
      }
    },
    error: function () {
      $(".result")
        .empty()
        .append(
          '<div class="no-portfolio">포트폴리오 정보를 불러오는 중 오류가 발생했습니다.</div>'
        );
    },
  });
}
