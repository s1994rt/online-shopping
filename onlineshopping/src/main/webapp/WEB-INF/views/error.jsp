<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url var="css" value="/resources/css" />

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

<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Add custom CSS here -->
<link href="${css}/myapp.css" rel="stylesheet">

<script type="text/javascript">
	window.menu = "${title}";
	window.contextRoot="${contextRoot}";
</script>

</head>

<body>

	<div class="wrapper">
		<!-- Navigation come here -->
		<nav  class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		     <div class="container">
		        <div class="navbar-header">
		            <a href="${contextRoot}/home" class="navbar-brand">Home</a> 
		        
		        </div>
		     
		     </div> 
		</nav>
		<div class="content">
			<div class="container">
			
			   <div class="row">
			       
			       <div class="col-xs-12">
			          <div class="jumbotron">
			            <h1>${errorTitle }</h1>
			            <hr/>
			            <blockquote style="word-wrap:break-word">
			               ${errorDescription}
			            </blockquote>
			            
			              
			           </div>  
			              
			       </div>
			   
			   </div>
			</div>

		</div>
		<!-- Footer -->

		<%@include file="./shared/footer.jsp"%>

	</div>
</body>

</html>