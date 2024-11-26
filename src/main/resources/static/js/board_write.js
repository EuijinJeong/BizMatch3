$().ready(function(){
	$("#submit").on("click",function(){
		
		var selected = $("#genre").val();
		var checked = $("#ck-box").is(':checked')?1:0;
		console.log(selected);
		console.log(checked);
		
		$.post("/board/write", 
			{ athrId : "0",
			pstCtgry :selected ,
			pstNm : $("#title").val(),
			pstCntnt : $("#content").val() ,
			isPstOpn : checked },function(response){
				console.log(response);
				if(response.error){
					var boardWirteVO = response.boardWirteVO;
					console.log(boardWirteVO);
					$("#title").val(boardWirteVO.pstNm);
					$("#content").val(boardWirteVO.pstCntnt);
					var error = `<div class="errorMsg" style="display:flex; align-items:center;">${response.error}</div>`
					$(".errorMsg").empty();
					$(".function-line").append(error);
				}
				else{
					location.href = "/board/list";
				}
			});
			
	});
})
