$(document).ready(function () {
	var target = location.href.split("/").pop();
  function loadPortfolioList(currPageNo = 0, exposureListSize = 6) {
	console.log(target);
    $.ajax({
      url: `/member/mypage/company/portfolio/content/${target}?currPageNo=${currPageNo}&exposureListSize=${exposureListSize}`,
      method: "GET",
      dataType: "json",
      success: function (response) {
        $(".result").empty();
        const portfolioList = response.portfolioList;
        portfolioList.forEach(function (portfolio) {
          const imgSrc = portfolio.img
            ? portfolio.img
            : "/img/second-section2.svg";
          var portfolioCard = $(`<div class="portfolio-item">
                <a href="/view/portfolio/view/detail/${portfolio.id}">
                  <img class="location-port" src="${imgSrc}"/>
                </a>
                <h3>${portfolio.title}</h3>
                <p class="description">
                  ${portfolio.text}
                </p>
              </div>`);
          $(".result").append(portfolioCard);
        });
		$(".pagenation-box").empty();
        const paginationVO = response.pagination;
        let paginationHtml = '<div class="page-div">';

        if (paginationVO.hasPrevGroup) {
          paginationHtml += `
            <div class="pre-page-btn">
              <div><a href="#" class="pagination-link white-text" data-page="0" data-size="${paginationVO.exposureListSize}">처음</a></div>
              <div><a href="#" class="pagination-link white-text" data-page="${paginationVO.prevGroupStartPageNo}" data-size="${paginationVO.exposureListSize}">이전</a></div>
            </div>
          `;
        }

        paginationHtml += '<div class="page-number-btn">';
        for (let p = paginationVO.groupStartPageNo; p <= paginationVO.groupEndPageNo; p++) {
          const activeClass = paginationVO.currPageNo === p ? 'active' : '';
          paginationHtml += `
            <div class="${activeClass}">
              <a href="#" class="pagination-link white-text" data-page="${p}" data-size="${paginationVO.exposureListSize}">${p + 1}</a>
            </div>
          `;
        }
        paginationHtml += '</div>';

        if (paginationVO.hasNextGroup) {
          paginationHtml += `
            <div class="next-page-btn">
              <div><a href="#" class="pagination-link white-text" data-page="${paginationVO.nextGroupStartPageNo}" data-size="${paginationVO.exposureListSize}">다음</a></div>
              <div><a href="#" class="pagination-link white-text" data-page="${paginationVO.pageCount - 1}" data-size="${paginationVO.exposureListSize}">마지막</a></div>
            </div>
          `;
        }

        paginationHtml += '</div>';
        $(".pagenation-box").append(paginationHtml);
      },
    });
  }

  loadPortfolioList();

  $(document).on("click", ".pagination-link", function (e) {
    e.preventDefault();
    const currPageNo = $(this).data("page");
    const exposureListSize = $(this).data("size");
    loadPortfolioList(currPageNo, exposureListSize);
  });
  
  // 이미지 클릭 시 모달 열기
   $(".result").on("click", ".location-port", function (e) {
     e.preventDefault(); // 기본 링크 클릭 동작 방지
     var clickedImage = $(this);
     // 클릭한 포트폴리오의 데이터를 가져오기
     var portfolioId = clickedImage
       .closest(".portfolio-item")
       .find("a")
       .attr("href")
       .split("/")
       .pop(); // ID 추출
     // AJAX 요청을 통해 포트폴리오 상세 정보 가져오기
     $.ajax({
       url: `/view/portfolio/detail/${portfolioId}`,
       method: "GET",
       dataType: "json",
       success: function (modalResponse) {
         console.log(modalResponse);
         // 데이터 모달에 삽입
         $("#mbrPrtflTtl").text(modalResponse.title);
         $("#mbrPrtflText").text(modalResponse.text);
         // 첨부 파일 리스트
         var attListHtml = "";
         modalResponse.att.forEach(function (attList) {
           attListHtml += `<p>${attList.attUrl}</p>`;
         });
         $("#attList").html(attListHtml);
         // 모달 표시
         $("#portfolioModal").css("display", "block");

         //삭제
         $(".delete-btn")
           .off("click")
           .on("click", function (deleteResponse) {
             deleteResponse.preventDefault();
             if (confirm("정말로 이 포트폴리오를 삭제하겠습니까?")) {
               $("#portfolioModal").css("display", "none");
               $.ajax({
                 url: `/member/delete/portfolio/${portfolioId}`,
                 method: "POST",
                 success: function (successResponse) {
                   console.log(successResponse); // 삭제 후 해당 포트폴리오 카드 제거
                   // $(`[data-id='${portfolioId}']`)
                   //   .closest(".portfolio-item")
                   //   .remove();
                   loadPortfolioList();
                 },
                 error: function (error) {
                   console.log(error);
                 },
               });
             }
             $("#portfolioModal").css("display", "none");
           });

         // 수정 버튼 핸들러
         $(".edit")
           .first()
           .off("click")
           .on("click", function () {
             $("#insertModal #mbrPrtflTtl").val(modalResponse.title);
             $("#insertModal #mbrPrtflText").val(modalResponse.text);

             $("#addPortfolioForm").attr(
               "action",
               `/member/update/portfolio/${portfolioId}`
             );
             $(".signupbtn").val("수정하기");

             $(".attach-file-list").css("display", "none");
             $(".image-upload").css("display", "none");
             $("#portfolioModal").css("display", "none");
             $("#insertModal").css("display", "block");
           });
       },
     });
   });

   // 모달 닫기 이벤트
   $(".close-button").on("click", function () {
     $("#portfolioModal").css("display", "none");
   });
   $(".close-btn2").on("click", function () {
     $("#insertModal").css("display", "none");
     returnToAdd();
   });
   $(window).on("click", function (e) {
     if ($(e.target).is("#portfolioModal")) {
       $("#portfolioModal").css("display", "none");
     }
     if ($(e.target).is("#insertModal")) {
       $("#insertModal").css("display", "none");
       returnToAdd();
     }
   });

   // 폼을 등록 상태로 초기화
   function returnToAdd() {
     $("#addPortfolioForm")[0].reset(); // 폼 필드 초기화
     $("#addPortfolioForm").attr("action", "/member/newportfolio"); // 등록 경로로 설정
     $(".attach-file-list").css("display", "block");
     $(".image-upload").css("display", "block");
     $(".signupbtn").val("등록하기"); // 버튼 텍스트를 등록하기로 설정
   }

   // 모달창 부분 - .report 요소가 페이지 로딩 이후에 동적으로 추가되었기 때문에 이벤트 위임 방식
   // 이벤트 위임: 이벤트 위임은 기존에 페이지에 있는 부모 요소에 이벤트를 걸어 두는 방식
   $(document).on("click", "#add-btn", function () {
     $("#insertModal").css("display", "block");
   });

   $(".close-btn2").on("click", function () {
     $("#insertModal").css("display", "none");
   });

   $(window).on("click", function (e) {
     if ($(e.target).is("#insertModal")) {
       $("#insertModal").css("display", "none");
     }
   });

   // 모달 드래그 이동 이벤트 통합
   function enableDrag(modalSelector) {
     var isDragging = false;
     var offsetX, offsetY;

     $(modalSelector).on("mousedown", function (e) {
       isDragging = true;
       offsetX = e.pageX - $(this).offset().left;
       offsetY = e.pageY - $(this).offset().top;
       $("body").css("user-select", "none");
     });

     $(document)
       .on("mousemove", function (e) {
         if (isDragging) {
           $(modalSelector).offset({
             top: e.pageY - offsetY,
             left: e.pageX - offsetX,
           });
         }
       })
       .on("mouseup", function () {
         isDragging = false;
         $("body").css("user-select", "auto");
       });
   }

   // 드래그 가능하도록 두 개의 모달에 대해 드래그 이벤트 활성화
   enableDrag(".modal-content");
   enableDrag(".modal-content2");
});
