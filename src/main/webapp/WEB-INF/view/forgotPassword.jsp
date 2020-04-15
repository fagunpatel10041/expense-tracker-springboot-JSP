<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Expense Tracker</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png"
	href="${pageContext.request.contextPath }/resources/login-registration/images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/login-registration/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/login-registration/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/login-registration/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/login-registration/vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/login-registration/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/login-registration/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/login-registration/vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/login-registration/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/login-registration/css/util.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/login-registration/css/main.css">
<!--===============================================================================================-->
</head>
<body>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form-title"
					style="background-image: url(${pageContext.request.contextPath }/resources/login-registration/images/bg-01.jpg);">
					<span class="login100-form-title-1"> Forgot Password ? </span>
				</div>

				<form:form action="${pageContext.request.contextPath}/sendEmail"
					modelAttribute="theUser" method="POST"
					class="login100-form validate-form">
					
						<c:if test="${param.error != null}">
										
							<i style="color: red;">Invalid Username</i>
		
						</c:if>

						<div class="wrap-input100 validate-input m-b-26"
							data-validate="Username is required">
							<span class="label-input100">Username</span>
							<form:input class="input100" type="text" path="userName"
								name="username" placeholder="Enter username" />
							<span class="focus-input100"></span>
						</div>



						<div class="flex-sb-m w-full p-b-30">
						<div class="contact100-form-checkbox">
							<!-- <input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
							<label class="label-checkbox100" for="ckb1">
								Remember me
							</label> -->
						</div> 

					<c:url var="registrationLink" value="/register/showRegistrationForm">
						
					</c:url>
					
					<c:url var="findUsernameLink" value="/showFindUsernameForm">
						
					</c:url>
					
						<div>
							<a href="${findUsernameLink}" class="txt1">
								Find Username?
							</a>
						</div>

						<div>
							<a href="${registrationLink	}" class="txt1">
								New User?
							</a>
						</div>
						
					</div>

					




					<div class="container-login100-form-btn">
						<button type="submit" class="login100-form-btn">Reset</button>
					</div>

				</form:form>

			</div>
		</div>
	</div>

	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath }/resources/login-registration/vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath }/resources/login-registration/vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath }/resources/login-registration/vendor/bootstrap/js/popper.js"></script>
	<script
		src="resources/login-registration/vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath }/resources/login-registration/vendor/select2/select2.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath }/resources/login-registration/vendor/daterangepicker/moment.min.js"></script>
	<script
		src="resources/login-registration/vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath }/resources/login-registration/vendor/countdowntime/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath }/resources/login-registration/js/main.js"></script>

</body>
</html>