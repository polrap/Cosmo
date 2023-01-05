<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/login.js"></script>
	<div class="card" style="width: 18rem;">
  <img src="..." class="card-img-top" alt="...">
  <div class="card-body">
    <h5 class="card-title">로그인이 필요한 화면 입니다.</h5>
    <p class="card-text">로그인 페이지로 이동 하시겠습니까?</p>
    <input type="button" onclick="confirmPopupButton()" class="btn btn-primary" value="확인"/>
    <input type="button" class="btn btn-primary" value="취소"/>
  </div>
</div>
</body>
</html>