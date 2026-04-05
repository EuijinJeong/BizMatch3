$().ready(function () {
  // 수정 페이지 이동
  var email = $("#sessionA").data("email");
  var emailUrl = location.href.split("/").pop();
  // 수정 버튼 눌렀을 때 클릭이벤트.
  $("#mypageeditbutton").on("click", function () {
    location.href = `/member/mypage/freelancer/edit/${email}`;
  });

  $(".introduction-content").text($("#sessionA").data("mbrintr"));

  loadPortfolioList();

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

  // 보유 기술이 "보유 기술 정보가 없습니다."인 경우 .no-tech 클래스를 추가
  // $(".tech").each(function () {
  //   if ($(this).text().trim() === "보유 기술 정보가 없습니다.") {
  //     $(this).removeClass("tech");
  //     $(this).addClass("no-tech");
  //   }
  // });
});

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

function loadPortfolioList() {
  // 최신 3개의 포트폴리오 리스트를 가지고 오는 요청을 하는 메소드.
  $.ajax({
    url: "/member/mypage/company/portfolio/content/"+$("#moreemail").data("email"),
    method: "GET",
	data: {
	    currPageNo: 0,          // 현재 페이지 번호 (원하는 페이지 번호로 변경 가능)
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
          const imgSrc = portfolio.img
            ? portfolio.img
            : "/img/second-section2.svg";
          var portfolioCard = $(`
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
