
<%@ include file="common/header.jsp"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div class="jumbotron alert-danger text-center m-auto">
		<h1>Error 500.</h1>
		<h1>This page is not loaded as well .</h1>
		<p>
			<a href="${pageContext.request.contextPath}/login">Login</a>
		</p>
	</div>
</div>