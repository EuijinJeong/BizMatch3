$().ready(function () {
  console.log("스크립트 실행 중"); // 확인용
  var cmpnyId = window.location.pathname.split("/").pop();
  console.log("회사 ID:", cmpnyId);

  pagination(function (response) {
    displayReviews(response.reviewList);
  });

  let orderBy = "late-date";
  $("#orderBy").val(orderBy);

  // 초기 AJAX 호출로 리뷰 목록 로드
  callList(
    `/member/mypage/company/reviews/${cmpnyId}`,
    { orderBy },
    0,
    10,
    function (response) {
      console.log("초기 로드 Response:", response);
      displayReviews(response.reviewList);
    }
  );

  // 리뷰 정렬
  $(".sort-option").on("click", function () {
    $(".sort-option").removeClass("active");
    $(this).addClass("active");

    // 정렬 기준 설정 및 #orderBy의 값 변경
    let orderBy;
    if ($(this).hasClass("late-date")) {
      orderBy = "late-date";
    } else if ($(this).hasClass("high-rate")) {
      orderBy = "high-rate";
    } else {
      orderBy = "low-rate";
    }
    $("#orderBy").val(orderBy);

    callList(
      `/member/mypage/company/reviews/${cmpnyId}`,
      { orderBy },
      0,
      10,
      function (response) {
        console.log("Response", response);
        displayReviews(response.reviewList);
      }
    );

    // AJAX로 정렬된 리뷰 리스트 가져오기
    // $.ajax({
    //   url: `/member/mypage/company/reviews`,
    //   method: "GET",
    //   data: { sort: sortCriterion },
    //   success: function (reviews) {
    //     displayReviews(reviews);
    //   },
    //   error: function (error) {
    //     console.error("리뷰를 불러오는 중 오류가 발생했습니다.", error);
    //   },
    // });
  });
});

function displayReviews(reviews) {
  console.log("수신한 리뷰 목록:", reviews);
  const reviewListContainer = $(".review-box-list");

  reviewListContainer.find(".review, .no-review-message").remove();

  if (reviews.length === 0) {
    const noReviewHtml = `<div class="no-review-message">등록된 리뷰가 없습니다.</div>`;
    reviewListContainer.append(noReviewHtml);
    return;
  }

  // 별 채우기 위한 반복문.
  reviews.forEach(function (review) {
    let starsHtml = "";
    const fullStars = Math.floor(review.scr); // 정수 부분만을 추출하여 fullStar 개수 확인
    const halfStar = review.scr % 1 >= 0.5; // 소수점 이하를 계산하여 0.5 이상이면 halfStar로 설정

    // fullStar 개수만큼 <i> 태그를 반복하여 추가
    starsHtml += '<i class="fas fa-star"></i>'.repeat(fullStars);

    // halfStar가 있을 경우 추가
    if (halfStar) {
      starsHtml += '<i class="fas fa-star-half-alt"></i>';
    }

    // 나머지 빈 별 추가 (최대 5개로 제한)
    const emptyStars = 5 - fullStars - (halfStar ? 1 : 0); // fullStar와 halfStar 뺀 나머지 emptyStar 개수를 계산
    starsHtml += '<i class="far fa-star"></i>'.repeat(emptyStars);

    const reviewHtml = `
        <div class="review" data-cmmntId="${review.rvwId}">
          <div class="review-image">
            <img src="/img/profile.svg" alt="profile-img" />
          </div>
          <div class="review-id">${review.emilAddr}</div>
          <div class="review-content">${review.rvwCntnt}</div>
          <div class="star-date">
            <div class="star">${starsHtml}</div>
            <div class="date">${review.rvwDt}</div>
          </div>
          <div class="report-button">
            <button class="report">신고</button>
          </div>
        </div>`;
    reviewListContainer.append(reviewHtml);
  });
}
