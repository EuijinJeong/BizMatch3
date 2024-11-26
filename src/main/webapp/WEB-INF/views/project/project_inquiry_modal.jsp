<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 모달</title>
<link rel="stylesheet" href="/css/project_inquiry_modal.css" />
</head>
<body>
	<div class="modal" id="commentModal">
      <div class="modal-content">
        <span class="close-btn">&times;</span>
        <div>
          <div class="inquiry-comment-section">
            <!--
            <div class="inquiry-comment-block">
              
              <div class="inquiry-comment-content-area">
                <div>
                  <div class="comment-registration-date">
                    <span>등록일자 2024.08.25</span>
                  </div>
                </div>
                
                <div class="comment-text-area">
                  <div class="comment-text">추가모집 예정은 없나요?</div>
                </div>
                
              </div> 
            </div>
            -->
          </div>
          <textarea id="content" placeholder="댓글을 입력하세요..."></textarea>
          <button class="submit-btn">댓글 달기</button>
        </div>
      </div>
    </div>
</body>
</html>