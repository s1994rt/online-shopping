<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />


<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>Online Shopping ${title}</title>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.css" rel="stylesheet">

<!-- Add custom CSS here -->
<link href="${css}/myapp.css" rel="stylesheet">

<script type="text/javascript">
	window.menu = "${title}";
</script>

</head>

<body>

	<div class="wrapper">
		<!-- Navigation come here -->
		<%@include file="./shared/navar.jsp"%>
		<div class="content">
			<c:if test="${clickOnHome== true}">
				<%@include file="home.jsp"%>
			</c:if>
			<c:if test="${clickOnAbout== true}">
				<%@include file="about.jsp"%>
			</c:if>
			<c:if test="${clickOnContact== true}">
				<%@include file="contact.jsp"%>
			</c:if>
			<c:if test="${clickOnViewProducts==true or clickOnCategoryProducts==true }">
			  <%@include file="productList.jsp" %>
			</c:if>

		</div>
		<!-- Footer -->

		<%@include file="./shared/footer.jsp"%>

		<!-- JavaScript -->
		<script src="${js}/jquery.js"></script>
		<script src="${js}/bootstrap.js"></script>
		<script src="${js}/myapp.js"></script>
	</div>
</body>

</html>