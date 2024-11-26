let projectData = []; // 모든 프로젝트 데이터를 저장할 변수
let currentPage = 1; // 현재 페이지 번호
const itemsPerPage = 10; // 페이지당 항목 수

// 금액을 포맷하는 함수 (천 단위 콤마 추가)
function formatCurrency(amount) {
  const parsedAmount = parseInt(amount.toString().replace(/[^0-9]/g, ""), 10);
  return new Intl.NumberFormat().format(parsedAmount);
}

// 프로젝트 카드를 화면에 렌더링하는 함수
function appendProjectCard(data) {
  $("#result").empty(); // 기존 내용을 비움

  const start = (currentPage - 1) * itemsPerPage;
  const end = start + itemsPerPage;
  const paginatedData = data.slice(start, end);

  if (paginatedData.length === 0) {
    $("#result").append("<p>검색 결과가 없습니다.</p>");
    return;
  }

  paginatedData.forEach(function (project) {
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
                  <ul>
                    ${project.projectSkillList
                      .map((skill) => `<li>${skill.prmStk}</li>`)
                      .join("")}
                  </ul>
                </div>
              </div>
            </div>
            <div class="project-footer">
              <div class="button-box">
                <input class="apply" id="apply" data-id="${
                  project.pjId
                }" type="button" value="${
      project.pjStt === 0
        ? "신청하기"
        : project.pjStt === 1
        ? "리뷰 작성하기"
        : project.pjStt === 2
        ? "완료하기"
        : project.pjStt === 3
        ? "신청하기"
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
    $("#result").append(projectCard); // 결과 요소에 추가
  });
}

// 동적 페이지네이션 설정 함수
function setupPagination(totalItems) {
  const totalPages = Math.ceil(totalItems / itemsPerPage);
  $(".page-number-btn").empty(); // 기존 페이지 번호 버튼 비우기

  // 페이지 번호 버튼 생성
  for (let i = 1; i <= totalPages; i++) {
    $(".page-number-btn").append(`
      <div class="number-box ${currentPage === i ? "active" : ""}">
        <a href="javascript:void(0);" class="white-text" data-page="${i}">${i}</a>
      </div>
    `);
  }

  // 이전 및 다음 버튼 설정
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
  appendProjectCard(projectData); // 현재 페이지 데이터 렌더링
  setupPagination(projectData.length); // 페이지네이션 업데이트
}

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

// 페이지 로드 후 실행
$().ready(function () {
  // 전체 프로젝트 정보를 가져오는 메서드
  $.ajax({
    url: "/project/find", // 요청을 보낼 URL
    method: "GET", // HTTP 메서드
    dataType: "json", // 응답을 JSON 형식으로 기대
    success: function (response) {
      projectData = response; // 모든 프로젝트 데이터를 저장
      appendProjectCard(projectData); // 초기 데이터 렌더링
      setupPagination(projectData.length); // 페이지네이션 설정
    },
    error: function (error) {
      console.error("AJAX 요청 오류:", error);
    },
  });

  // $(document).on("click", "#apply", function () {
  //   var pjid = $(this).data("id");
  //   console.log(pjid);

  //   location.href = "/project/apply/" + pjid;
  // });

  $(document).on("click", "#apply", function () {
    var pjid = $(this).data("id"); // 프로젝트 ID 가져오기
    var project = projectData.find((p) => p.pjId === pjid); // 프로젝트 데이터 찾기

    if (!project) {
      console.error("프로젝트를 찾을 수 없습니다.");
      return;
    }

    var url = "";

    switch (project.pjStt) {
      case 0: // 모집중
        url = `/project/apply/${pjid}`;

        break;
      case 1: // 완료 리뷰작성하기로 이동
        // url = `/project/completed/${pjid}`;

        break;
      case 2: // 진행중 완료하기 버튼
        var isProjectComplete = confirm("프로젝트를 완료하시겠습니까?");

        if (isProjectComplete) {
          // url = `/project/in-progress/${pjid}`;
          alert("프로젝트가 완료되었습니다.");
        } else {
          alert("프로젝트 완료가 취소되었습니다.");
        }
        break;
      case 3: // 추가모집중
        url = `/project/apply/${pjid}`;

        break;
      default:
        console.log("알 수 없는 상태입니다.");
        return;
    }

    location.href = url;
  });

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

  // 정렬 기준 클릭 이벤트
  $(".filters span").on("click", function (event) {
    event.preventDefault();
    const orderBy = $(this).data("order");
    $(".filters span").removeClass("active");
    $(this).addClass("active");

    let sortedData = [...projectData];
    if (orderBy === "latest") {
      sortedData.sort((a, b) => new Date(b.rgstrDt) - new Date(a.rgstrDt));
    } else if (orderBy === "deadline") {
      sortedData.sort(
        (a, b) => new Date(a.pjRcrutEndDt) - new Date(b.pjRcrutEndDt)
      );
    } else if (orderBy === "amount") {
      sortedData.sort((a, b) => b.cntrctAccnt - a.cntrctAccnt);
    }

    appendProjectCard(sortedData);
    setupPagination(sortedData.length);
  });

  // 검색 버튼 클릭 이벤트
  $(".search-btn").on("click", function (event) {
    event.preventDefault(); // 기본 폼 제출 방지
    const keyword = $(".search-keyword").val().trim(); // 입력된 키워드 가져오기
    searchProjects(keyword); // 검색 수행
  });
});
