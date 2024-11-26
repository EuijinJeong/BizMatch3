$().ready(function(){
	var id = $(".content-box").data("id");
	
	$("#submit").on("click",function(){
		
		var selected = $("#genre").val();
		var checked = $("#ck-box").is(':checked')?1:0;
		console.log(selected);
		console.log(checked);
		
		$.post("/board/modify", 
			{ pstId : id,
			pstCtgry :selected ,
			pstNm : $("#title").val(),
			pstCntnt : $("#content").val() ,
			isPstOpn : checked },function(){
				location.href = `/board/view/${id}`;
			});	
	});
	
	
	$("#delete").on("click",function(){
		$.post(`/board/delete/${id}`,{},function(){
				location.href = "/board/list";
		});
	});
	
})
