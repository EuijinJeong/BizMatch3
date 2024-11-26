<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/project_card.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/common/after_login_header.jsp" %>
<div class="project-list">
	<c:forEach begin="1" end="10" step="1" >
		<%@ include file="/WEB-INF/views/common/project_card.jsp" %>
	</c:forEach>
</div>
</body>
</html>