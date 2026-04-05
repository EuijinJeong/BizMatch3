<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <title>Review</title>
    <link rel="stylesheet" type="text/css" href="/css/review.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
    />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="/js/review.js"></script>
  </head>
  <body>
    <div class="review-box">
      <div class="custom-dropdown">
        <div class="dropdown-selected">별점을 선택해주세요</div>
        <div class="dropdown-options">
          <div class="dropdown-option" data-value="0.0">
            <span class="star-icons"
              ><i class="far fa-star"></i><i class="far fa-star"></i
              ><i class="far fa-star"></i><i class="far fa-star"></i
              ><i class="far fa-star"></i
            ></span>
          </div>
          <div class="dropdown-option" data-value="0.5">
            <span class="star-icons"
              ><i class="fas fa-star-half-alt"></i><i class="far fa-star"></i
              ><i class="far fa-star"></i><i class="far fa-star"></i
              ><i class="far fa-star"></i
            ></span>
          </div>
          <div class="dropdown-option" data-value="1.0">
            <span class="star-icons"
              ><i class="fas fa-star"></i><i class="far fa-star"></i
              ><i class="far fa-star"></i><i class="far fa-star"></i
              ><i class="far fa-star"></i
            ></span>
          </div>
          <div class="dropdown-option" data-value="1.5">
            <span class="star-icons"
              ><i class="fas fa-star"></i><i class="fas fa-star-half-alt"></i
              ><i class="far fa-star"></i><i class="far fa-star"></i
              ><i class="far fa-star"></i
            ></span>
          </div>
          <div class="dropdown-option" data-value="2.0">
            <span class="star-icons"
              ><i class="fas fa-star"></i><i class="fas fa-star"></i
              ><i class="far fa-star"></i><i class="far fa-star"></i
              ><i class="far fa-star"></i
            ></span>
          </div>
          <div class="dropdown-option" data-value="2.5">
            <span class="star-icons"
              ><i class="fas fa-star"></i><i class="fas fa-star"></i
              ><i class="fas fa-star-half-alt"></i><i class="far fa-star"></i
              ><i class="far fa-star"></i
            ></span>
          </div>
          <div class="dropdown-option" data-value="3.0">
            <span class="star-icons"
              ><i class="fas fa-star"></i><i class="fas fa-star"></i
              ><i class="fas fa-star"></i><i class="far fa-star"></i
              ><i class="far fa-star"></i
            ></span>
          </div>
          <div class="dropdown-option" data-value="3.5">
            <span class="star-icons"
              ><i class="fas fa-star"></i><i class="fas fa-star"></i
              ><i class="fas fa-star"></i><i class="fas fa-star-half-alt"></i
              ><i class="far fa-star"></i
            ></span>
          </div>
          <div class="dropdown-option" data-value="4.0">
            <span class="star-icons"
              ><i class="fas fa-star"></i><i class="fas fa-star"></i
              ><i class="fas fa-star"></i><i class="fas fa-star"></i
              ><i class="far fa-star"></i
            ></span>
          </div>
          <div class="dropdown-option" data-value="4.5">
            <span class="star-icons"
              ><i class="fas fa-star"></i><i class="fas fa-star"></i
              ><i class="fas fa-star"></i><i class="fas fa-star"></i
              ><i class="fas fa-star-half-alt"></i
            ></span>
          </div>
          <div class="dropdown-option" data-value="5.0">
            <span class="star-icons"
              ><i class="fas fa-star"></i><i class="fas fa-star"></i
              ><i class="fas fa-star"></i><i class="fas fa-star"></i
              ><i class="fas fa-star"></i
            ></span>
          </div>
        </div>
      </div>
      <div class="review-background">
        <textarea
          id="reviewContent"
          placeholder="리뷰 내용을 입력해주세요."
        ></textarea>
      </div>
      <div class="button-box">
        <button id="submitReview">리뷰 등록</button>
      </div>
    </div>
  </body>
</html>
