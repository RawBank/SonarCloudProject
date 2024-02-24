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
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<nav role="navigation" class="navbar navbar-default">
	<div class="navbar-collapse">
		<div class="nav navbar-nav">
			<img src="${pageContext.request.contextPath}/static/img/rawbank.jpeg" />
			<li  class="lang-icon">
			<a href="${pageContext.request.contextPath}/language?lang=en"><img src="${pageContext.request.contextPath}/static/img/US.jpg"/></a>
			</li>
			<li  class="lang-icon-fr">
			<a href="${pageContext.request.contextPath}/language?lang=fr"><img src="${pageContext.request.contextPath}/static/img/French.jpg"/></a>				
			</li>
			<li class="lang-label">
			<p class="p-3 mb-2 bg-success text-white"><spring:message code="lang.change"/></p>
			</li>
		</div>
	</div>
</nav>