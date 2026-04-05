$(document).ready(function () {
  var pjId = $("button").data("pjid");
  var applyMemberCount = 0;

  $.get({
    url: `/project/apply/member/check/${pjId}`,
    success: function (response) {
      console.log(response);
      console.log(response.result);

      applyMemberCount = response.result;

      // 지원자 수가 0이면 결제 버튼 비활성화
      if (applyMemberCount === 0) {
        alert("지원자가 없습니다. 결제를 진행할 수 없습니다.");
        $("#paymentBox").attr("disabled", true);
        $("#paymentBox").css("background-color", "gray"); // 버튼 비활성화 스타일
      } else {
        $("#paymentBox").attr("disabled", false); // 결제 버튼 활성화
      }

      // 결제 버튼 클릭 이벤트 처리
      $("#paymentBox").on("click", function () {
        if (applyMemberCount === 0) {
          alert("지원자가 없습니다. 결제를 진행할 수 없습니다.");
          return;
        }

        // 결제 정보 설정
        IMP.init("imp81518761");
        IMP.request_pay(
          {
            pg: "html5_inicis",
            pay_method: "card",
            merchant_uid: `mid_${new Date().getTime()}`, // 주문번호 생성
            name: "정의진짱1",
            amount: 10, // 결제 금액
            buyer_name: "정의진짱",
            buyer_tel: "010-1234-5678",
          },
          function (rsp) {
            if (rsp.success) {
              // 결제 성공 시
              $.post(
                "/bizmatch/payment/ask/deposit",
                {
                  pjId: pjId,
                  emilAddr: rsp.buyer_email,
                  mbrNm: rsp.name,
                  impUid: rsp.imp_uid,
                  paymentType: $("#paymentType").data("paymentType"),
                  cntrctAmt: rsp.paid_amount,
                },
                function (response) {
                  console.log(response); // 응답 데이터 확인

                  // 요청 성공 시 리다이렉트
                  if (response.result) {
                    location.href = response.nextUrl;
                  }
                  // 결제 취소 시
                  else if (
                    decodeURIComponent(response.error_msg) ===
                    "사용자가 결제를 취소하셨습니다"
                  ) {
                    alert("결제가 취소되었습니다."); // 사용자에게 결제 취소 알림
                    location.href = "/bizmatch/payment/usercancel/";
                  } else {
                    location.href = `/bizmatch/payment/ask/deposit/error/500`;
                  }
                }
              ).fail(function (jqXHR, textStatus, errorThrown) {
                // AJAX 요청 실패 처리
                console.error("AJAX 요청 실패:", textStatus, errorThrown);
                alert(
                  "서버와의 통신 중 오류가 발생했습니다. 관리자에게 문의하세요."
                );
                location.href = `/bizmatch/payment/ask/deposit/error/500`;
              });
            } else {
              // 결제 실패 시
              if (rsp.error_msg === "사용자가 결제를 취소하셨습니다") {
                alert("결제가 취소되었습니다."); // 사용자에게 결제 취소 알림
                location.href = "/bizmatch/payment/usercancel/";
              } else {
                alert(rsp.error_msg); // 기타 결제 오류 메시지
                location.href = "error/500";
              }
            }
          }
        );
      });
    },
    error: function (xhr, status, error) {
      console.error("오류 발생:", error);
      alert("지원자 수 확인에 실패했습니다.");
    },
  });
});
