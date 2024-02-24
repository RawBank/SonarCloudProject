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
<%@ include file="common/header.jsp"%>
<%@ include file="common/navigation_no_menus.jsp"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="login-container">
	<div class="row">
		<div class="panel panel-primary">
			<div class="panel-heading"><spring:message code="app.login" /></div>
			<div class="panel-body">
				<form method="POST"
					action="${pageContext.request.contextPath}/login"
					class="form-signin">
					<c:if test="${param.errorCode != null}">
						<p class="p-3 mb-2 bg-danger text-white">${errorMsg}</p>
					</c:if>
					<c:if test="${param.logout != null}">
						<p class="p-3 mb-2 bg-success text-white">${logOutMsg}</p>
					</c:if>
					<c:if test="${param.expired != null}">
						<p class="p-3 mb-2 bg-info text-white"><spring:message code="app.session.expired"/></p>
					</c:if>
					<div class="col-md-12 form-group">
					<spring:message code="app.Username" var="Username"/>
					<spring:message code="app.Password" var="Password"/>
						<input name="username" type="text" class="form-control" placeholder="${Username}" autofocus="true" required="required" /><br>
						<input name="password" type="password" class="form-control" placeholder="${Password}" required="required" /><br>
						<button class="btn btn-md btn-primary btn-block" type="submit"><spring:message code="app.login" /></button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<%@ include file="common/footer.jsp"%>