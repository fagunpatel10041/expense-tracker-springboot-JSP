<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en">
	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- Meta -->
		<meta name="description" content="Responsive Bootstrap4 Dashboard Template">
		<meta name="author" content="ParkerThemes">
		<link rel="shortcut icon" href="img/fav.png" />

		<!-- Title -->
		<title>Expense Tracker - Datatables</title>
		


		<!-- *************
			************ Common Css Files *************
			************ -->
		<!-- Bootstrap css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
		
		<!-- Icomoon Font Icons css -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/fonts/style.css">

		<!-- Main css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/main.css">


		<!-- *************
			************ Vendor Css Files *************
			************ -->
		<!-- Datepickers css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/vendor/daterange/daterange.css" />


		<!-- Data Tables -->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/vendor/datatables/dataTables.bs4.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/vendor/datatables/dataTables.bs4-custom.css" />


	</head>
	<body>

		<div class="container">



			<!-- *************
				************ Header section start *************
				************* -->


			<!-- Header start -->
			<header class="header">
				<!-- Row start -->
				<div class="row gutters">
					<div class="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-6">
						<a href="index.html" class="logo">Expense Tracker</a>
					</div>
					<div class="col-xl-8 col-lg-8 col-md-6 col-sm-6 col-6">
						<!-- Header actions start -->
						<ul class="header-actions">
							<li class="dropdown">
								<a href="#" id="userSettings" class="user-settings" data-toggle="dropdown" aria-haspopup="true">
									<span class="user-name"><security:authentication property="principal.username" /></span>
								</a>
							</li>
						</ul>
						<!-- Header actions end -->
					</div>
				</div>
				<!-- Row end -->
			</header>
			<!-- Header end -->



				<!-- Navigation start -->
		<nav class="navbar navbar-expand-lg custom-navbar">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#retailAdminNavbar" aria-controls="retailAdminNavbar"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"> <i></i> <i></i> <i></i>
				</span>
			</button>
			<div class="collapse navbar-collapse" id="retailAdminNavbar">
				<ul class="navbar-nav m-auto">
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/dashboard">
							<i class="icon-devices_other nav-icon"></i> Dashboard
					</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle " href="#"
						id="appsDropdown" role="button" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="icon-dollar-sign nav-icon"></i> Expenses
					</a>
						<ul class="dropdown-menu dropdown-menu-right"
							aria-labelledby="appsDropdown">
							<c:url var="addExpenseLink" value="/expenses/showFormForAdd">
							</c:url>
							<li><a class="dropdown-item" href="${addExpenseLink}">Add
									Expense</a></li>
							<c:url var="manageExpensesLink" value="/expenses/showManageExpenses/">
							</c:url>
							<li><a class="dropdown-item" href="${manageExpensesLink}">Manage
									Expense</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle active-page" href="#" id="formsDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <i class="icon-edit1 nav-icon"></i>
							Expense Report
					</a>
					
					<c:url var="exportReportLink" value="/expenses/showExportReportForm"></c:url>
					
					
						<ul class="dropdown-menu dropdown-menu-right"
							aria-labelledby="formsDropdown">
							<li><a class="dropdown-item" href="${exportReportLink}">Weekly</a>
							</li>
							<li><a class="dropdown-item" href="${exportReportLink}">Monthly</a>
							</li>
						</ul></li>
					<li class="nav-item dropdown"><a class="nav-link " href="${pageContext.request.contextPath}/profile"> <i
							class="icon-user1 nav-icon"></i> Profile
					</a></li>
					<li class="nav-item dropdown"><a class="nav-link " href="${pageContext.request.contextPath}/user/showFormForChangePassword"> <i
							class="icon-vpn_key nav-icon"></i> Change Password
					</a></li>
					<!-- Add a logout button -->
	

					 <li class="nav-item dropdown"><a class="nav-link " href="#"
						id="uiElementsDropdown" role="button" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="icon-log-out1 nav-icon"></i> <form:form
								action="${pageContext.request.contextPath}/logout" method="POST">
								<input type="submit" value="Logout">
							</form:form>
					</a></li> 

				</ul>
			</div>
		</nav>
		<!-- Navigation end -->

			<!-- *************
				************ Header section end *************
				************* -->







			<!-- *************
				************ Main container start *************
				************* -->
			<div class="main-container">


				<!-- Page header start -->
				<div class="page-title">
					<div class="row gutters">
						<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
							<h5 class="title">Datatables</h5>
						</div>
						<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
							<div class="daterange-container">
								<div class="date-range">
									<div id="reportrange">
										<i class="icon-calendar cal"></i>
										<span class="range-text"></span>
										<i class="icon-chevron-down arrow"></i>
									</div>
								</div>
								<a href="#" data-toggle="tooltip" data-placement="top" title="Download CSV" class="download-reports">
									<i class="icon-download1"></i>
								</a>
							</div>
						</div>
					</div>
				</div>
				<!-- Page header end -->


				<!-- Content wrapper start -->
				<div class="content-wrapper">

					<!-- Row start -->
					<div class="row gutters">
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
							
							<div class="card">
								<div class="card-body">
									
									<!--*************************
										*************************
										*************************
										Basic table start
									*************************
									*************************
									*************************-->
									<div class="table-responsive">
										<table id="basicExample" class="table m-0">
											<thead>
												<tr>
													<th>Expense Item</th>
													<th>Category</th>
													<th>Cost</th>
													<th>Date of Expense</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
											
											
											
											<c:forEach var="tempExpenses" items="${theExpenses}" >
											
											<c:url var="updateExpenseLink" value="/expenses/showFormForUpdate">
												<c:param name="expenseId" value="${tempExpenses.id}"></c:param>
											</c:url>
											<c:url var="deleteExpenseLink" value="/expenses/deleteExpense">
													<c:param name="expenseId" value="${tempExpenses.id}"></c:param>
											</c:url>
											
												<tr>
													<td>${tempExpenses.expenseItem}</td>
													<td>${tempExpenses.category.name}</td>
													<td>$ ${tempExpenses.expenseCost}</td>
													<td>${tempExpenses.expenseDate}</td>
													<td> <a href="${updateExpenseLink}" style="color: green">Update</a> | <a href="${deleteExpenseLink}" style="color: red">Delete</a></td>
												</tr>
												
												
											</c:forEach>
												
											</tbody>
										</table>
									</div>
									<!--*************************
										*************************
										*************************
										Basic table end
									*************************
									*************************
									*************************-->

								</div>
							</div>

						</div>

					</div>
					<!-- Row end -->

				</div>
				<!-- Content wrapper end -->


			</div>
			<!-- *************
				************ Main container end *************
				************* -->


		</div>

		<!-- *************
				************ Required JavaScript Files *************
			************* -->
		<!-- Required jQuery first, then Bootstrap Bundle JS -->
		<script src="${pageContext.request.contextPath }/resources/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath }/resources/js/bootstrap.bundle.min.js"></script>
		<script src="${pageContext.request.contextPath }/resources/js/moment.js"></script>


		<!-- *************
				************ Vendor Js Files *************
			************* -->
		<!-- Daterange -->
		<script src="${pageContext.request.contextPath }/resources/vendor/daterange/daterange.js"></script>

		<!-- Data Tables -->
		<script src="${pageContext.request.contextPath }/resources/vendor/datatables/dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath }/resources/vendor/datatables/dataTables.bootstrap.min.js"></script>
		
		<!-- Custom Data tables -->
		<script src="${pageContext.request.contextPath }/resources/vendor/datatables/custom/custom-datatables.js"></script>
		<script src="${pageContext.request.contextPath }/resources/vendor/datatables/custom/fixedHeader.js"></script>


		<!-- Main Js Required -->
		<script src="${pageContext.request.contextPath }/resources/js/main.js"></script>

	</body>
</html>