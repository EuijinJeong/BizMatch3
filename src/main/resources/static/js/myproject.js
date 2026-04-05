let projectData = []; // 모든 프로젝트 데이터를 저장할 변수
let currentPage = 1; // 현재 페이지 번호
const itemsPerPage = 10; // 페이지당 항목 수
// 검색 함수
function searchProjects(keyword) {
  const filteredData = projectData.filter(
    (project) =>
      project.pjTtl.includes(keyword) || project.smjrNm.includes(keyword)
  );
  currentPage = 1; // 검색 시 페이지를 처음으로 초기화
  appendProjectCard(filteredData); // 필터링된 데이터 렌더링
  setupPagination(filteredData.length); // 페이지네이션 설정
}
// 금액을 포맷하는 함수 (천 단위 콤마 추가)
function formatCurrency(amount) {
  const parsedAmount = parseInt(
    (amount || 0).toString().replace(/[^0-9]/g, ""),
    10
  );
  return new Intl.NumberFormat().format(parsedAmount);
}
// $(document).ready(function () {
//   $("#my-project").on("click", function () {});
// });

// 프로젝트 카드를 화면에 렌더링하는 함수
function appendProjectCard(data) {
  $("#result").empty();
  const start = (currentPage - 1) * itemsPerPage;
  const end = start + itemsPerPage;
  const paginatedData = data.slice(start, end);
  if (paginatedData.length === 0) {
    $("#result").append("<p>검색 결과가 없습니다.</p>");
    return;
  }
  paginatedData.forEach(function (project) {
    const skillsListHtml = project.projectSkillList
      ? project.projectSkillList
          .map((skill) => `<li>${skill.prmStk}</li>`)
          .join("")
      : "<li>기술 정보 없음</li>";
    const projectCard = `
       <div class="project-card-container">
        <div class="project-card">
          <div class="project-box">
            <div class="project-head">
              <div class="project-head-front">
                ${
                  project.pjStt === 0
                    ? '<div class="status-recruiting">모집중</div>'
                    : project.pjStt === 1
                    ? '<div class="status-done">완료</div>'
                    : project.pjStt === 2
                    ? '<div class="status-ing">진행중</div>'
                    : project.pjStt === 3
                    ? '<div class="status-additional-recruiting">추가모집중</div>'
                    : ""
                }
                <h2 id="pjttl" class="project-title" data-id="${project.pjId}">
                  ${project.pjTtl}
                </h2>
              </div>
              <div class="post-date">등록일자 ${project.rgstrDt}</div>
            </div>
            <div class="project-body">
              <div class="project-body-box">
                <div class="project-body-title">프로젝트 분야</div>
                <div class="project-body-content bold">${project.smjrNm}</div>
              </div>
              <div class="project-body-box">
                <div class="project-body-title">모집 마감일</div>
                <div class="project-body-content">${project.pjRcrutEndDt}</div>
              </div>
              <div class="project-body-box">
                <div class="project-body-title">프로젝트 일정</div>
                <div class="project-body-content">${project.strtDt} ~ ${
      project.endDt
    }</div>
              </div>
              <div class="project-body-box">
                <div class="project-body-title">기술 목록</div>
                <div class="project-body-content">
                 <ul>${skillsListHtml}</ul>
                </div>
              </div>
            </div>
            <div class="project-footer">
              <div class="button-box">
                <input class="apply" id="apply" data-id="${
                  project.pjId
                }" type="button" value="${
      project.pjStt === 0
        ? "지원자 보기"
        : project.pjStt === 1
        ? "리뷰 작성하기"
        : project.pjStt === 2
        ? "완료하기"
        : project.pjStt === 3
        ? "지원자 보기"
        : ""
    }" /> 
              </div>
              <div class="estimated-amount">
                <div>예상 금액</div>
                <div class="bold">${formatCurrency(project.cntrctAccnt)}원</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    `;
    $("#result").append(projectCard);
  });
}
// 프로젝트 카드를 화면에 렌더링하는 함수 for `myApply`
function appendProjectCardForMyApply(data) {
  $("#result").empty();
  const start = (currentPage - 1) * itemsPerPage;
  const end = start + itemsPerPage;
  const paginatedData = data.slice(start, end);
  if (paginatedData.length === 0) {
    $("#result").append("<p>검색 결과가 없습니다.</p>");
    return;
  }
  paginatedData.forEach(function (project) {
    const skillsListHtml = project.projectVO?.projectSkillList
      ? project.projectVO.projectSkillList
          .map(
            (skill) => `<li>${skill.prmStkVO?.prmStk || "기술 정보 없음"}</li>`
          )
          .join("")
      : "<li>기술 정보 없음</li>";
    const projectCard = `
       <div class="project-card-container">
        <div class="project-card">
          <div class="project-box">
            <div class="project-head">
              <div class="project-head-front">
                <div class="status-recruiting">모집중</div>
                <h2 id="pjttl" class="project-title" data-id="${
                  project.projectVO.pjId
                }">
                  ${project.projectVO.pjTtl}
                </h2>
              </div>
              <div class="post-date">등록일자 ${project.projectVO.rgstrDt}</div>
            </div>
            <div class="project-body">
              <div class="project-body-box">
                <div class="project-body-title">프로젝트 분야</div>
                <div class="project-body-content bold">${
                  project.projectVO.projectIndustryVO.indstrInfoVO.indstrNm
                }</div>
              </div>
              <div class="project-body-box">
                <div class="project-body-title">모집 마감일</div>
                <div class="project-body-content">${
                  project.projectVO.pjRcrutEndDt
                }</div>
              </div>
              <div class="project-body-box">
                <div class="project-body-title">프로젝트 일정</div>
                <div class="project-body-content">${
                  project.projectVO.strtDt
                } ~ ${project.projectVO.endDt}</div>
              </div>
              <div class="project-body-box">
                <div class="project-body-title">기술 목록</div>
                <div class="project-body-content">
                  <ul>${skillsListHtml}</ul>
                </div>
              </div>
            </div>
            <div class="project-footer">
              <div class="button-box">
                <input class="apply" id="apply-data" data-id="${
                  project.projectVO.pjId
                }" data-apply="${
      project.pjApplyId
    }" type="button" value="지원서보기" />
              </div>
              <div class="estimated-amount">
                <div>예상 금액</div>
                <div class="bold">${formatCurrency(
                  project.projectVO.cntrctAccnt
                )}원</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    `;
    $("#result").append(projectCard);
  });
}
// 페이지네이션 설정 함수
function setupPagination(totalItems) {
  const totalPages = Math.ceil(totalItems / itemsPerPage);
  $(".page-number-btn").empty();
  for (let i = 1; i <= totalPages; i++) {
    $(".page-number-btn").append(`
      <div class="number-box ${currentPage === i ? "active" : ""}">
        <a href="javascript:void(0);" class="white-text" data-page="${i}">${i}</a>
      </div>
    `);
  }
  $(".pre-page-btn").html(`
    <div><a href="javascript:void(0);" class="white-text" data-page="1">처음</a></div>
    <div><a href="javascript:void(0);" class="white-text" data-page="${Math.max(
      1,
      currentPage - 1
    )}">이전</a></div>
  `);
  $(".next-page-btn").html(`
    <div><a href="javascript:void(0);" class="white-text" data-page="${Math.min(
      totalPages,
      currentPage + 1
    )}">다음</a></div>
    <div><a href="javascript:void(0);" class="white-text" data-page="${totalPages}">마지막</a></div>
  `);
}
// 페이지 변경 함수
function changePage(page) {
  currentPage = page;
  appendProjectCard(projectData);
  setupPagination(projectData.length);
}
// 특정 페이지 이동 시 데이터를 요청하는 함수
function loadProjectData(forMyApply = false) {
  const url = forMyApply
    ? "/project/all/order/applicant"
    : "/project/myproject/orderproject";
  const renderFunction = forMyApply
    ? appendProjectCardForMyApply
    : appendProjectCard;
  $.ajax({
    url: url,
    method: "GET",
    dataType: "json",
    success: function (response) {
      projectData = response;
      if (projectData.length > 0) {
        renderFunction(projectData);
      } else {
        $("#result").append("<p>검색 결과가 없습니다.</p>");
      }
      setupPagination(projectData.length);
    },
    error: function (error) {
      console.error("AJAX 요청 오류:", error);
    },
  });
}
// 페이지 로드 후 실행
$(document).ready(function () {
  // 모달창 부분 - .report 요소가 페이지 로딩 이후에 동적으로 추가되었기 때문에 이벤트 위임 방식
  // 이벤트 위임: 이벤트 위임은 기존에 페이지에 있는 부모 요소에 이벤트를 걸어 두는 방식
  /*
   ****************************************
   */
  // TODO - .bold 선택자 바꿔야함
  $(document).on("click", ".bold", function () {
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

  $("#apply-project").on("click", function () {
    $("#result").empty();
    loadProjectData(true); // 지원 프로젝트 데이터 불러오기
  });

  // 검색 버튼 클릭 이벤트
  $(".search-btn").on("click", function (event) {
    event.preventDefault(); // 기본 폼 제출 방지
    const keyword = $(".search-keyword").val().trim(); // 입력된 키워드 가져오기
    searchProjects(keyword); // 검색 수행
  });

  $("#myApplyProjectList").on("click", function () {
    loadProjectData(true); // 지원한 프로젝트 목록
  });

  $(document).on("click", ".apply", function () {
    var pjid = $(this).data("id"); // 프로젝트 ID 가져오기
    var project = projectData.find((p) => p.pjId === pjid); // 프로젝트 데이터 찾기

    if (!project) {
      console.error("프로젝트를 찾을 수 없습니다.");
      return;
    }

    var url = "";

    switch (project.pjStt) {
      case 0: // 모집즁
        url = "/bizmatch/payment/ask/deposit/" + $(this).data("id");
        break;

      case 1: // 완료상태고 (리뷰작성하는 페이지로 이동)
        // url = ;
        break;

      case 2: // 진행중 (완료하기 버튼)
        var isProjectComplete = confirm("프로젝트를 완료하시겠습니까?");
        if (isProjectComplete) {
          alert("프로젝트가 완료되었습니다.");
          location.href =
            "/bizmatch/payment/ask/downpayment/" + $(this).data("id");
        } else {
          alert("프로젝트 완료가 취소되었습니다.");
        }
        break;

      case 3: // 추가모집중
        url = "/bizmatch/payment/ask/deposit/" + $(this).data("id");
        break;

      default:
        console.log("알 수 없는 상태입니다.");
        return;
    }

    // URL이 설정되었으면 페이지 이동
    if (url) {
      location.href = url;
    }
  });
  $(document).on("click", "#showapplymember", function () {
    location.href = "/bizmatch/payment/ask/deposit/" + $(this).data("id");
  });

  $(document).on("click", "#apply-data", function () {
    location.href =
      "/project/apply/member/view/" +
      $(this).data("id") +
      "/" +
      $(this).data("apply");
  });

  // 전체 프로젝트 데이터 로드 (기본 페이지 로드 시)
  loadProjectData(false);
  // 페이지 번호, 이전, 다음 버튼 클릭 이벤트 설정
  $(document).on(
    "click",
    ".page-number-btn .white-text, .pre-page-btn .white-text, .next-page-btn .white-text",
    function () {
      const page = parseInt($(this).data("page"));
      if (!isNaN(page)) {
        changePage(page);
      }
    }
  );
});
