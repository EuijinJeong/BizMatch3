$().ready(function(){
	
	var boardId = $(".comment-box").data("boardid");
	var commentId = null;
	var buttonType = null;
	$(".create-btn").on("click",function(){

		var text= $(".comment-text").val();
		
		$.post(`/board/view`, 
				{
					pstId : boardId,
					cmmntCntnt : text,
					prntCmmntId : commentId,
					athrId :null
				}, 
				function(){				
					location.reload();
		});
	
	});
	
	$(".recomment-btn").on("click",function(){
		$(this).closest('.one-comment').find('.content').css("display","flex");
		$(".modify-write-box").css("display","none");
		$(".recomment-write-box").css("display","none");
		commentId =$(this).closest(".fuction-line").data("id");
		buttonType = "recomment";
	
		$(this).closest('.one-comment').find('.recomment-write-box').css("display", "flex");
	});
	
	$(".modify-comment-btn").on("click",function(){
		
		$(".recomment-write-box").css("display","none");
		$(".modify-write-box").css("display","none");
		commentId =$(this).closest(".fuction-line").data("id");
		buttonType = "modify";
		
		  $(this).closest('.one-comment').find('.content').css("display","none");
		  $(this).closest('.one-comment')
		  .find('.modify-write-box').css('display', 'flex');
	})
	$(".delete-comment-btn").on("click",function(){
		commentId =$(this).closest(".fuction-line").data("id");
		$.post(`/board/comment/delete/${commentId}`
				,{ }
				
				, function(){				
								location.reload();
							});
	})
	
	
	$(".submit-btn").on("click",function(){
		if(buttonType == "recomment"){
			var text= $(this).closest(".recomment-write-box").find(".recomment-text").val();
				
				$.post(`/board/view`, 
						{
							pstId : boardId,
							cmmntCntnt : text,
							prntCmmntId : commentId,
							athrId :null
						}, 
						function(){				
							location.reload();
				});
		}
		else if(buttonType == "modify"){
			var text= $(this).closest(".modify-write-box").find(".modify-text").val();
				
				$.post(`/board/comment/modify`, 
						{
							cmmntId : commentId,
							cmmntCntnt : text
						
						}, 
						function(){				
							location.reload();
				});
		}

		
	})
	
})