<%-- <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> --%>
<%-- <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> --%>
<!-- <nav role="navigation" class="navbar navbar-default"> -->
<!-- 	<div class="navbar-collapse"> -->
<!-- 	<ul class="nav navbar-nav"> -->
<%-- 	<li><img alt = "Rawbank logo" src="${pageContext.request.contextPath}/static/img/rawbank.jpeg"/></li> --%>
<%-- 		<li class="active"><a href="${pageContext.request.contextPath}/home"><spring:message code="menu.home" /></a></li> --%>
<%-- 		<sec:authorize access="hasAnyAuthority('csc_admin_call_center','csc_admin_initiator')"> --%>
<%-- 			<li><a href="${pageContext.request.contextPath}/searchCards"><spring:message code="menu.changeStatus" /></a></li> --%>
<%-- 		</sec:authorize> --%>
<%-- 		<sec:authorize access="hasAuthority('csc_admin_initiator')">			 --%>
<%-- 			<li><a href="${pageContext.request.contextPath}/reloadMC"><spring:message code="menu.ReloadMasterCard" /></a></li> --%>
<%-- 		</sec:authorize> --%>
<%-- 		<sec:authorize access="hasAuthority('csc_admin_administrator')">	 --%>
<%-- 			<li><a href="${pageContext.request.contextPath}/approveCards"><spring:message code="menu.Approve" /></a></li> --%>
<%-- 		</sec:authorize>		 --%>
<%-- 		<li><a href="${pageContext.request.contextPath}/logout"><spring:message code="menu.logout" /></a></li> --%>
<%-- 		<li  class="user-name-img"><a href="${pageContext.request.contextPath}/userDetails"> --%>
<%-- 		<img alt = "User logo" src="${pageContext.request.contextPath}/static/img/user.png"/></a></li>		 --%>
<%-- 		<li class="user-name"><p class="p-3 mb-2 bg-success text-white"><spring:message code="app.login.user" />${name}</p></li> --%>
<!-- 	</ul> -->
<!-- 	</div> -->
<!-- </nav> -->


<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>

	
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<nav role="navigation" class="navbar navbar-default">
	<div class="navbar-collapse">
		<ul class="nav navbar-nav" id='navig'>
			<li><img alt="Rawbank logo"
				src="${contextPath}/static/img/rawbank.jpeg" /></li>
			<li class="active"><a
				href="${contextPath}/home"><spring:message
						code="menu.home" /></a></li>
			<sec:authorize
				access="hasAnyAuthority('csc_admin_call_center','csc_admin_initiator')"><!-- In future we may require to 'csc_admin_initiator' -->
				<li><a href="${contextPath}/searchCards"><spring:message
							code="menu.changeStatus" /></a></li>
			</sec:authorize>

			<sec:authorize access="hasAuthority('csc_admin_initiator')">
				<li><a href="${contextPath}/reloadMC"><spring:message
							code="menu.ReloadMasterCard" /></a></li>
<%-- 				<li><a href="${pageContext.request.contextPath}/changeCardLimits"><spring:message --%>
<%-- 							code="menu.changeLimitCard" /></a></li> --%>
			</sec:authorize>

			<sec:authorize access="hasAuthority('csc_admin_administrator')">
				<li><a href="${pageContext.request.contextPath}/approveCards"><spring:message
							code="menu.Approve" /></a></li>
			</sec:authorize>
			
<%-- 			<sec:authorize access="hasAuthority('csc_admin_initiator')"> --%>
<%-- 				<li><a  href="${pageContext.request.contextPath}/changeCardDetails"><spring:message --%>
<%-- 							code="menu.changeCardDetails" /></a></li> --%>
<%-- 			</sec:authorize> --%>
			
			<li><a href="${contextPath}/logout"><spring:message
						code="menu.logout" /></a></li>
			<li class="user-name-img"><a
				href="${contextPath}/userDetails"> <img
					alt="User logo"
					src="${contextPath}/static/img/user.png" /></a></li>
			<li class="user-name"><p class="p-3 mb-2 bg-success text-white">
					<spring:message code="app.login.user" />${name}</p></li>	
					<p class ="d-none" id ="userconnected">${name}</p>	
					<p class ="d-none" id ="role">${role}</p>			
		</ul>
	</div>
	
	
</nav>

<style type="text/css">

</style>
<script type="text/javascript">

	// var header = document.getElementById("navig");
	// var btns = header.getElementsByTagName('li');
	// for (var i = 0; i < btns.length; i++) {
	//   btns[i].addEventListener("click", function() {
	//   var current = document.getElementsByClassName("active");
	//   current[0].className = current[0].className.replace(" active", "");
	//   this.className += " active";
	//   });
	// }
	$(document).ready(function() {
		var pathName = window.location.pathname;
		$("#navig li>a").each(function() {

			if ($(this).parent().attr('class') == 'active') {
				$(this).parent().removeClass('active')
			}
			if ($(this).attr('href') === pathName) {
				$(this).parent().addClass('active');
			}
		});
	})
	//function myFunction() {

	///console.log ("XXXX" +  document.querySelector('#navig li.active'));
	// 	  if (document.querySelector('#navig li.active') !== null) {
	// 	    document.querySelector('#navig li.active').classList.remove('active');
	// 	  }
	// 	  e.target.className = "active";

	//	}
	
	
	
</script>
<!-- <script type="text/javascript"> 
// $(document).ready(function() {
//     window.history.pushState(null, "", window.location.href);        
//     window.onpopstate = function() {
//     window.history.pushState(null, "", window.location.href);
//     };
// });

// //registerOpenTab FUNCTION
// const registerOpenTab = () => {
//   let tabsOpen = 1;
//   while (localStorage.getItem('openTab' + tabsOpen) !== null) {
//     tabsOpen++;
//   }
//   localStorage.setItem('openTab' + tabsOpen, 'open');
//   if (localStorage.getItem('openTab2') !== null) {
// 	 window.location.replace('${pageContext.request.contextPath}/logout');
//       //window.alert('This application is already running in ' + (tabsOpen - 1) + ' other browser tab(s).')
//   }
// }

// // unregisterOpenTab FUNCTION
// const unregisterOpenTab = () => {
//   let tabsOpen = 1;
//   while (localStorage.getItem('openTab' + tabsOpen) !== null) {
//     tabsOpen++;
//   }
//   localStorage.removeItem('openTab' + (tabsOpen - 1));
// }


// // EVENT LISTENERS
// window.addEventListener('load', registerOpenTab);
// window.addEventListener('beforeunload', unregisterOpenTab);
</script> -->