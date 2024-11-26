$().ready(function () {
  loadPortfolioList();

  var cmpid = location.href.split("/").pop();
  console.log(cmpid);
  // 지도 로드 부분
  // 회사 주소를 AJAX로 가져옴
  $(".edit-button").on("click", function () {
    location.href = `/member/mypage/company/edit/${cmpid}`;
  });

  $.ajax({
    url: `/member/mypage/company/address/${cmpid}`,
    method: "GET",
    success: function (response) {
      if (response && response.cmpnyAddr) {
        var cmpnyAddr = response.cmpnyAddr;
        loadKakaoMap(cmpnyAddr); // 회사 주소를 사용해 지도 로드
      } else {
        console.error("회사 주소를 찾을 수 없습니다.");
      }
    },
    error: function (error) {
      console.error("회사 주소를 가져오는 중 오류가 발생했습니다.", error);
    },
  });

  // 사이트 주소 이동
  $(".homepage").on("click", function () {
    let url = $(this).data("url");

    // URL에 https 없으면 추가
    if (!/^https?:\/\//i.test(url)) {
      url = "https://" + url;
    }

    window.open(url, "_blank");
  });

  // 관심 산업 검색창 감추기
  $(".search-input").css("display", "none");

  // 관심 산업 데이터 가져오기 및 선택 상태로 설정
  $.ajax({
    url: `/member/mypage/company/industry/${cmpid}`,
    method: "GET",
    success: function (response) {
      const { cmpnyBizCtgryId, cmpnyIndstrId, mjrNm, smjrNm } = response; // 서버에서 받은 대분류 및 중분류 데이터
      console.log(response);

      // 대분류를 설정 및 비활성화
      $("#cmpnyBizCtgry2 option").each(function () {
        if ($(this).text() === mjrNm) {
          $(this).prop("selected", true);
          $("#cmpnyBizCtgry2").prop("disabled", true); // select box 비활성화
        }
      });

      // 중분류를 설정 및 비활성화
      $("#cmpnyIndstrId2 option").each(function () {
        if ($(this).text() === smjrNm) {
          $(this).prop("selected", true);
          $("#cmpnyIndstrId2").prop("disabled", true); // select box 비활성화
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

  // 보유 기술이 "보유 기술 정보가 없습니다."인 경우 .no-tech 클래스를 추가
  // $(".tech").each(function () {
  //   if ($(this).text().trim() === "보유 기술 정보가 없습니다.") {
  //     $(this).removeClass("tech");
  //     $(this).addClass("no-tech");
  //   }
  // });

  // 모달창 부분 - .report 요소가 페이지 로딩 이후에 동적으로 추가되었기 때문에 이벤트 위임 방식
  // 이벤트 위임: 이벤트 위임은 기존에 페이지에 있는 부모 요소에 이벤트를 걸어 두는 방식
  $(document).on("click", ".report", function () {
    // 클릭된 리뷰의 cmmntId 가져온다
    const cmmntId = $(this).closest(".review").attr("data-cmmntId");

    // cmmntId reportForm에 설정
    $("#reportForm").data("cmmntId", cmmntId);

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

  // 리뷰 신고
  $("#reportForm").on("submit", function (e) {
    e.preventDefault();

    const cmmntId = $(this).data("cmmntId");
    console.log("제출할 cmmntId:", cmmntId);

    const rprtCtgry = parseInt($("#reportCategory").val(), 10);
    const rprtCntnt = $("#reportContent").val();

    if (!cmmntId) {
      alert("신고할 리뷰 ID가 없습니다.");
      return;
    }

    $.ajax({
      url: `/review/${cmmntId}/reviewreport`,
      method: "POST",
      contentType: "application/json",
      data: JSON.stringify({ rprtCtgry: rprtCtgry, rprtCntnt: rprtCntnt }),
      success: function (response) {
        if (response.result) {
          alert("신고가 정상적으로 접수되었습니다");
          $("#reportModal").css("display", "none");
        } else {
          alert("신고를 실패했습니다");
        }
      },
    });
  });

  // // 리뷰 정렬
  // $(".sort-option").on("click", function () {
  //   $(".sort-option").removeClass("active");
  //   $(this).addClass("active");

  //   const orderBy = $(this).hasClass("late-date") // 현재 클릭한 버튼이 "late-date" 클래스가 있는지 확인
  //     ? "late-date"
  //     : $(this).hasClass("high-rate") // "late-date" 클래스가 없으면 "high-rate" 클래스가 있는지 확인
  //     ? "high-rate"
  //     : "low-rate";

  //   // AJAX로 정렬된 리뷰 리스트 가져오기
  //   $.ajax({
  //     url: `/member/mypage/company/reviews`,
  //     method: "GET",
  //     data: { orderBy },
  //     success: function (reviews) {
  //       displayReviews(reviews);
  //     },
  //     error: function (error) {
  //       console.error("리뷰를 불러오는 중 오류가 발생했습니다.", error);
  //     },
  //   });
  // });

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
});

// function displayPortfolios(portfolio) {}

// function displayReviews(reviews) {
//   const reviewListContainer = $(".review-box-list");

//   reviewListContainer.find(".review, .no-review-message").remove();

//   if (reviews.length === 0) {
//     const noReviewHtml = `<div class="no-review-message">등록된 리뷰가 없습니다.</div>`;
//     reviewListContainer.append(noReviewHtml);
//     return;
//   }

//   reviews.forEach(function (review) {
//     let starsHtml = "";
//     const fullStars = Math.floor(review.scr); // 정수 부분만을 추출하여 fullStar 개수 확인
//     const halfStar = review.scr % 1 >= 0.5; // 소수점 이하를 계산하여 0.5 이상이면 halfStar로 설정

//     // fullStar 개수만큼 <i> 태그를 반복하여 추가
//     starsHtml += '<i class="fas fa-star"></i> '.repeat(fullStars);

//     // halfStar가 있을 경우 추가
//     if (halfStar) {
//       starsHtml += '<i class="fas fa-star-half-alt"></i> ';
//     }

//     // 나머지 빈 별 추가 (최대 5개로 제한)
//     const emptyStars = 5 - fullStars - (halfStar ? 1 : 0); // fullStar와 halfStar 뺀 나머지 emptyStar 개수를 계산
//     starsHtml += '<i class="far fa-star"></i> '.repeat(emptyStars);

//     const reviewHtml = `
//       <div class="review" data-cmmntId="${review.rvwId}">
//         <div class="review-image">
//           <img src="/img/profile.svg" alt="profile-img" />
//         </div>
//         <div class="review-id">${review.emilAddr}</div>
//         <div class="review-content">${review.rvwCntnt}</div>
//         <div class="star-date">
//           <div class="star">${starsHtml}</div>
//           <div class="date">${review.rvwDt}</div>
//         </div>
//         <div class="report-button">
//           <button class="report">신고</button>
//         </div>
//       </div>`;
//     reviewListContainer.append(reviewHtml);
//   });
// }

// 지도 부분
function loadKakaoMap(cmpnyAddr) {
  if (!window.kakao || !window.kakao.maps || !kakao.maps.services) {
    console.error("Kakao Maps API가 로드되지 않았습니다.");
    return;
  }
  var geocoder = new kakao.maps.services.Geocoder();

  geocoder.addressSearch(cmpnyAddr, function (result, status) {
    if (status === kakao.maps.services.Status.OK) {
      var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
      var container = document.getElementById("kakao-map");
      var options = {
        center: coords,
        level: 3,
      };
      var map = new kakao.maps.Map(container, options);

      // 마커 생성
      var marker = new kakao.maps.Marker({
        map: map,
        position: coords,
      });

      // 지도 중심 좌표를 설정
      map.setCenter(coords);
    } else {
      console.error(
        "Geocode가 성공적으로 수행되지 않았습니다. 상태: " + status
      );
    }
  });
}

function loadPortfolioList() {
  // 최신 3개의 포트폴리오 리스트를 가지고 오는 요청을 하는 메소드.
  var cmpid = $("#cmpidbox").data("cmpid");
  $.ajax({
    url: "/member/mypage/company/portfolio/content/" + cmpid,
    method: "GET",
    data: {
      currPageNo: 1, // 현재 페이지 번호 (원하는 페이지 번호로 변경 가능)
      exposureListSize: 3, // 노출할 리스트의 크기 (최대 3개)
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
        portfolioList.forEach(function (portfolio) {
          const imgSrc = portfolio.img
            ? portfolio.img
            : "/img/second-section2.svg";
          const portfolioCard = $(`
            <div class="portfolio-item">
              <a href="/view/portfolio/view/detail/${portfolio.id}">
                <img class="location-port" src="${imgSrc}"/>
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
