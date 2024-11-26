/**
 * 
 */
$().ready(function(){
	$("#confirm-email").on("click", function () {
		var email = $("#newEmilAddr").val();
		   $.ajax({
		     url: `/email/check/${email}/`,
		     method: "GET",
		   });
		   console.log("email" + email);
		   alert(email + "로 인증번호가 발급되었습니다.");
		
	var timeleft = 300;
	   // var inputBox = $("#authNumField");
	   var timerText = $(".timer");

	   var timer = setInterval(function () {
	     var minutes = Math.floor(timeleft / 60);
	     var seconds = timeleft % 60;
	     //   inputBox.attr(
	     //     "placeholder",
	     //     `${minutes}:${seconds < 10 ? 0 : ""}${seconds}`
	     //   );

	     timerText.text(`${minutes}:${seconds < 10 ? "0" : ""}${seconds}`);

	     timeleft--;

	     if (timeleft < 0) {
	       clearInterval(timer);
	       timerText.text("시간 초과");
	     }
	   }, 1000);
	   });
	   
	   
	   
	   
	   $("#confirm-auth-num").on("click", function (e) {
	       e.preventDefault();
	       var authNumber = $("#authNumField").val();

	       if (!authNumber) {
	         alert("인증번호를 입력해주세요.");
	         return;
	       }

	       $.get(
	         `/email/authnum/samecheck`,
	         {
	           email: $("#newEmilAddr").val(),
	           authNum: $("#authNumField").val(),
	         },
	         function (responseA) {
	           console.log(responseA);
	           if (responseA.response) {
	             console.log(responseA);
	             alert("이메일 인증 성공");
	           } else {
	             alert("이메일 인증에 실패했습니다. 인증번호를 재발급 받아주세요");
	           }
	         }
	       );
	     });
		 
		 
		 $("#newEmilAddr").on("keyup", function () {
		     $.get(
		       "/member/signup/email/available/",
		       { email: $("#newEmilAddr").val() },
		       function (response) {
		         var responseEmail = response.email;
		         var responseAvailable = response.available;

		         if (responseAvailable) {
		           $("#newEmilAddr").addClass("available");
		           $("#newEmilAddr").removeClass("unusable");
		           $("#confirm-email").removeAttr("disabled");
		         } else {
		           $("#newEmilAddr").addClass("unusable");
		           $("#newEmilAddr").removeClass("available");
		           $("#confirm-email").attr("disabled", "disabled");
		         }

		         console.log(responseEmail);
		         console.log(responseAvailable);
		       }
		     );
		   });
		 
		 
});