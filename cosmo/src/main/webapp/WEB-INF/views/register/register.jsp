<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="container" style="width: 50%;">
			<form:form modelAttribute="MemberVO" method="POST">
				<div class="form-group col-lg-12">
					
				
				<spring:message code="email.placeholder" var="emailPlaceholder" />
				<spring:message code="Name.placeholder" var="namePlaceholder"  />
				<spring:message code="Password.placeholder" var="pwPlaceholder"/>
				<spring:message code="Phonenum.placeholder" var="phoneNumPlaceholder"/>
				<div class="form-group col-lg-12">
					<label for="email" style="text-align: left"
						class="font-weight-bolder  col-lg-6">E-MAIL</label>
					<form:input class="form-control-lg" path="email"
						type="text" placeholder='${emailPlaceholder }'
						onfocus="this.placeholder=''"
						onblur="this.placeholder='${emailPlaceholder }'" 
						size="60"/>
					<c:if test="${not empty  valid.valid_email}">
						<label class="text-danger">${valid.valid_email}</label>
					</c:if>
				</div>
				<div class="form-group col-lg-12">
					<label for="name" style="text-align: left"
						class="font-weight-bolder  col-lg-6">NAME</label>
					<form:input class="form-control-lg" path="userName"
						type="text" placeholder="${namePlaceholder}" onfocus="this.placeholder=''"
						onblur="this.placeholder='${namePlaceholder }'" size="60"/>
					<c:if test="${not empty  valid.valid_userName}">
						<label class="text-danger">${valid.valid_userName}</label>
					</c:if>
				</div>
				
				<div class="form-group col-lg-12">
					<label for="password" style="text-align: left"
						class="font-weight-bolder  col-lg-6">PASSWORD</label>
					<form:input class="form-control-lg" path="pw"
						type="password" placeholder="${pwPlaceholder }"
						onfocus="this.placeholder=''"
						onblur="this.placeholder='${pwPlaceholder }'" size="60"/>
					<c:if test="${not empty  valid.valid_pw}">
						<label class="text-danger">${valid.valid_pw}</label>
					</c:if>	
				</div>
				
				<div class="form-group col-lg-12">
					<label for="confirmPassword" style="text-align: left"
						style="text-align:left" class="font-weight-bolder  col-lg-6">CONFIRM
						PASSWORD</label>
					<form:input class="form-control-lg" path="confirmPw"
						type="password" placeholder="${phoneNumPlaceholder }"
						onfocus="this.placeholder=''"
						onblur="this.placeholder='${phoneNumPlaceholder }'" size="60"/>
					<c:if test="${not empty  valid.valid_confirmPw}">
						<label class="text-danger">${valid.valid_confirmPw}</label>
					</c:if>
				</div>
				
				<div class="form-group col-lg-12">
					<label for="phoneNumber" style="text-align: left"
						class="font-weight-bolder  col-lg-6">PHONE NUMBER</label>
	
					<form:input class="form-control-lg" path="phoneNum"
						type="text" placeholder="${phoneNumPlaceholder }"
						onfocus="this.placeholder=''"
						onblur="this.placeholder='${phoneNumPlaceholder }'" size="60"/>
					<c:if test="${not empty  valid.valid_phone}">
						<label class="text-danger">${valid.valid_phone}</label>
					</c:if>
				</div>
				
				<div class="form-group col-lg-12" >
				<input
						class="btn btn-dark btn-lg col-3 "
						type="submit" value="회원 가입"> <input
						class="btn btn-dark btn-lg col-3 "
						type="button" onclick="joinCancle()" value="취소">
				</div>
			</div>
			</form:form>
		</div>
</body>
</html>