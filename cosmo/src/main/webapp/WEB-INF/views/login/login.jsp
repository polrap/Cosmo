<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Insert title here</title>
</head>
<body>
	
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<div class="container">
		<div class="card text-center">
			<div class="card" >
				<div class="card-body">
					<form:form modelAttribute="MemberVO" method="POST">
						<form:errors />
						<div>
							<c:if test="${error}">
							<p>${exception }</p>
							</c:if>
						</div>
						<div>
							<label class="font-weight-bolder"style="text-align: left"> <spring:message code="email" />
							<form:input class="form-control-lg col-lg-14"	path="email" />
							</label>
						</div>

						<div>
							<label class="font-weight-bolder" style="text-align: left"> 
							<spring:message code="password" />
							<form:password class="form-control-lg col-lg-14"	path="pw" /> 
							</label>
						</div>
						<div>
							<input type="checkbox" name="remember">
						</div>
						<input class="btn btn-dark btn-lg col-3" type="submit" value="<spring:message code="login.btn"/>">
					</form:form>
					<br>
				</div>
			</div>
		</div>
	</div>
</body>
</html>