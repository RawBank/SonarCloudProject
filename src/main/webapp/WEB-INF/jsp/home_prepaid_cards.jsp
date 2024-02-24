<!-- 
 Copyright 2022 Rawbank. All Rights Reserved.
 
 Change Section:
 Programmer				Date
 Krishna				6/20/2022
 Initial Coding.
 th:value="${searchValue}"
 Created By:
 @author krishna
 @since Jun 20, 2022
 
 adminPortal/src/main/webapp/static/scripts
-->

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<%@ include file="common/navigation.jsp"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>



<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<!--  -->
<link rel="stylesheet"
	href="<c:url value='/static/css/jquery.dataTables.min.css' />">
<link rel="stylesheet"
	href="<c:url value='/static/css/jquery-ui-1.13.1.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/static/css/buttons.dataTables.min-2.2.3.css'/>" />
<link rel="stylesheet" href="<c:url value='/static/css/admin.css'/>" />
<script src="${pageContext.request.contextPath}/static/scripts/admin.js"></script>

<script
	src="${pageContext.request.contextPath}/static/scripts/jquery-3.5.1.js"></script>


<script
	src="${pageContext.request.contextPath}/static/scripts/jquery-ui-1.13.1.js"></script>
<script
	src="${pageContext.request.contextPath}/static/scripts/jquery.dataTables.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/scripts/date-eu-1.10.11.js"></script>


<script
	src="${pageContext.request.contextPath}/static/scripts/dataTables.buttons.min-2.2.3.js"></script>
<script
	src="${pageContext.request.contextPath}/static/scripts/jszip.min-3.1.3.js"></script>
<script
	src="${pageContext.request.contextPath}/static/scripts/pdfmake-0.1.53.min.js"></script>

<script
	src="${pageContext.request.contextPath}/static/scripts/vfs_fonts-0.1.53.js"></script>

<script
	src="${pageContext.request.contextPath}/static/scripts/buttons.html5.min-2.2.3.js"></script>

<script
	src="${pageContext.request.contextPath}/static/scripts/buttons.print.min-2.2.3.js"></script>






<div class="container-fluid" style="width: 85%;">



<div class="loadingicon">
	<img src="${pageContext.request.contextPath}/static/img/wait-001.gif" alt="Girl in a jacket" width="500" height="500"
	
	style= "
	
	position: absolute;
	margin-left: auto;
	margin-right: auto;
	left: 0;
	right: 0;
	
	"
	> 
</div>
	
	<div id="messageCover">
		<c:choose>
			
		
		    
		    <c:when test="${not empty message}">
		        <p class ='alert alert-success  p-0' > 
					${message}
				</p>
		    </c:when> 
		    <c:when test="${not empty messageError}">
		        <p class ='alert alert-danger  p-0' > 
					${messageError}
				</p>
		    </c:when> 
		</c:choose>
	</div>
	
	
	<h2>RECHARGE MC PREPAYEE EN MASSE</h2><br />
	
	<h3>Joindre le fichier de recharge en masse</h3>
		<form method="POST" action="${pageContext.request.contextPath}/upload_bulk_file"
			 enctype="multipart/form-data" onsubmit="return do_something()">
			<input type="file" name="file" required id="firstfile" />
			
			<hr>
			
			<h3>Pièce justificative</h3>
			<input type="file" name="file" required id="secondfile"/>
			
			<hr>
			
			<input type="submit" value="Soumettre" id ="manageMessage"/>
		</form>
		
	<h3>File Upload Status</h3>
	Status: ${message}
	
	
	<hr>
	
	<button class="btn btn-primary btn-sm btnReloadInitiated" id="blk"
					onclick="getActivitiesBlk('divinitiatorreloadreports','tableinitiatorreloadreports','commentreloadInitiator','getInitiatorActivities')">
					Rapport Recharge MC Prépayée en Masse 
	</button>
	
	<div class=" col-md-12 col-lg-12 mt-2" style="background-color: white">
		
		<hr>
		<div class="col-md-12 col-lg-12 d-none" id="divinitiatorreloadreports"
			style="margin-bottom: 70px">
			<div class="table-responsive">
				<div class="table-wrapper w-100">
					<table id="tableinitiatorreloadreports"
						class="table table-striped table-bordered" cellspacing="0"
						width="100%">
						<thead>
							<tr>
								<th></th>
								<th width="15%" scope="col"><spring:message
										code="label.reference" /></th>

								<th width="15%" scope="col"><spring:message
										code="label.cardNumber" /></th>
								<th width="7%" scope="col"><spring:message
										code="label.amount" /></th>
								<th width="20%" scope="col"><spring:message
										code="label.dateheuretrans" /></th>
								<th width="20%" scope="col"><spring:message
										code="label.dateheuretransApprov" /></th>
								<th width="10%" scope="col"><spring:message
										code="label.initiator" /></th>
								<th width="10%" scope="col"><spring:message
										code="label.validator" /></th>
								<th width="10%" scope="col"> Status</th>
								<th scope="col">customerName</th>
								<th scope="col">authCode</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>

		<div class="col-md-12 col-lg-12 d-none" id="divChangeStatusReports"
			style="margin-bottom: 70px">
			<div class="table-responsive">
				<div class="table-wrapper w-100">
					<table id="tableChangeStatusReports"
						class="table table-striped table-bordered" cellspacing="0"
						width="100%">
						<thead>
							<tr>
								<th width="11%" scope="col"><spring:message
										code="label.cardNumber" /></th>

								<th width="10%" scope="col"><spring:message
										code="label.oldStatus" /></th>
								<th width="15%" scope="col"><spring:message
										code="label.newStatus" /></th>
								<th width="10%" scope="col"><spring:message
										code="label.currentStatus" /></th>
								<th width="15%" scope="col"><spring:message
										code="label.dateChangeStatus" /></th>
								<th width="10%" scope="col"><spring:message
										code="label.initiator" /></th>
								<%-- 								<th width="10%" scope="col"><spring:message --%>
								<%-- 										code="label.validator" /></th> --%>
								<th width="25%" scope="col"><spring:message
										code="label.fileName" /></th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>

	</div>
	
		
	
	
	<!--  
	<h3>Liste des fichiers</h3>
	 
	 <table class="table table-bordered table-responsive">
	 	<thead>
	   	 	<tr>
	       
	        <th>Nom fichier</th>
	    	</tr>
	    </thead>
	    
	    <tbody>
		    <c:forEach items="${fichiers}" var="filename">
		        <tr>
		            <td>${filename}</td>
		          
		        </tr>
		    </c:forEach>
		</tbody>
	</table>
	
	-->
	
	<br><br><br><br>
</div>


<script type="text/javascript">


function do_something(){ 
	$('.loadingicon').css('display', 'inline'); 
	
	$('#firstfile').css('display', 'none'); 
	$('#secondfile').css('display', 'none'); 
	$('#manageMessage').css('display', 'none'); 
	
	
	// submit the form
	 return true; 
}

 $('.loadingicon').css('display', 'none'); 




</script>

<%@ include file="common/footer.jsp"%>