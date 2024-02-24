<!-- 
 Copyright 2022 Rawbank. All Rights Reserved.
 
 Change Section:
 Programmer				Date
 Krishna				6/20/2022
 Initial Coding.
 
 Created By:
 @author krishna
 @since Jun 20, 2022
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="common/header.jsp"%>
<%@ include file="common/navigation.jsp"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="container">
	<div class="panel panel-primary">
		<div class="panel-heading"><spring:message code="app.home.header" /></div>
		<div class="panel-body">
			<spring:message code="app.welcome" /> ${name}...!!
		</div>
	</div>
</div>
<%@ include file="common/footer.jsp"%>