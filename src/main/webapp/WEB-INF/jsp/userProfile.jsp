<!-- 
 Copyright 2022 Rawbank. All Rights Reserved.
 
 Change Section:
 Programmer				Date
 Krishna				7/1/2022
 Initial Coding.
 
 Created By:
 @author krishna
 @since July 1, 2022
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="common/header.jsp"%>
<%@ include file="common/navigation.jsp"%>
<%@ include file="modals/viewCardDetails.jsp"%>
<%@ include file="modals/changeStatus.jsp"%>
<!-- <script src="webjars/jquery/1.9.1/jquery.min.js"></script> -->
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="container">
	<div class="panel panel-primary">
		<div class="panel-heading"><spring:message code="header.user.profile"/></div>
		<div class="panel-body">
			<div class="row">
				<div class="col-sm-3">
					<p class="mb-0"><spring:message code="profile.userName"/></p>
				</div>
				<div class="col-sm-9">
					<p class="text-muted mb-0">${loginResponse.userName}</p>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-3">
					<p class="mb-0"><spring:message code="profile.firstName"/></p>
				</div>
				<div class="col-sm-9">
					<p class="text-muted mb-0">${loginResponse.firstName}</p>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-3">
					<p class="mb-0"><spring:message code="profile.lastName"/></p>
				</div>
				<div class="col-sm-9">
					<p class="text-muted mb-0">${loginResponse.lastName}</p>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-3">
					<p class="mb-0"><spring:message code="profile.emailId"/></p>
				</div>
				<div class="col-sm-9">
					<p class="text-muted mb-0">${loginResponse.email}</p>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-3">
					<p class="mb-0"><spring:message code="profile.roles"/></p>
				</div>
				<div class="col-sm-9">
					<p class="text-muted mb-0">
						<c:forEach items="${loginResponse.roles}" var="role">
							<td>${role.roleName} (${role.application}), </td>
						</c:forEach>
					</p>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="common/footer.jsp"%>