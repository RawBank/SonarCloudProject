<!-- 
 Copyright 2022 Rawbank. All Rights Reserved.
 
 Change Section:
 Programmer				Date
 Krishna				8/13/2022
 CSC admin portal Project.
 Initial Coding.
 Created By:
 @author krishna
 @since August 13, 2022
-->
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<%@ include file="common/navigation.jsp"%>
<%@ include file="modals/approveOps.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="<c:url value='/static/css/jquery.dataTables.min.css' />">
<link rel="stylesheet"
	href="<c:url value='/static/css/jquery-ui-1.13.1.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/static/css/buttons.dataTables.min-2.2.3.css'/>" />
<link rel="stylesheet" href="<c:url value='/static/css/admin.css'/>" />
<script src="${pageContext.request.contextPath}/static/scripts/admin.js"
	defer="defer"></script>
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
<!-- url("../img/atrium02.jpg") url([[@{/img/DataTableImages/sort_both.png}]]) -->


<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="container" style="width: 90%">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<spring:message code="label.pendingCardDetails" />
		</div>
		<div class="panel-body">
			<c:if test="${reloadMessage != null }">
				<p
					class="${fn:containsIgnoreCase(reloadMessage, 'erreur') ? 'p-3 mb-2 bg-danger text-white':'p-3 mb-2 bg-success text-white'}">${reloadMessage}</p>
			</c:if>
			<c:if test="${message != null }">
				<p class="p-3 mb-2 bg-danger text-white">${message}</p>
			</c:if>
			<c:if test="${pendingApproveReloadList.size() > 0 }">

				<div class="table-responsive">
					<div class="table-wrapper w-100">

						<table class="table table-hover table-bordered border-primary">
							<thead>
								<tr>
									<th width="3%" class="text-center"><input type="checkbox"
										name="select_all" value="1" id="select-all"></th>
									<th width="13%"><spring:message
											code="label.dateheuretrans" /></th>
									<th width="5%" class="d-none"><spring:message
											code="label.amount" /></th>
									<th width="5%" class="d-none"><spring:message
											code="label.currency" /></th>
									<th width="5%" class="d-none"><spring:message
											code="label.expiryDate" /></th>
									<th width="5%" class="d-none"><spring:message
											code="label.institutionNumber" /></th>
									<th width="5%" class="d-none"><spring:message
											code="label.procCode" /></th>
									<th width="5%" class="d-none"><spring:message
											code="label.transactionType" /></th>
									<th width="5%" class="d-none"><spring:message
											code="label.aaSrcAccount" /></th>
									<th width="5%" class="d-none"><spring:message
											code="label.aaDestAccount" /></th>
									<th width="5%"><spring:message code="label.action" /></th>
									<th width="8%"><spring:message code="label.reference" /></th>
									<th width="13%"><spring:message code="label.customerName" /></th>									
									<th class="d-none"><spring:message code="label.cardNumber" /></th>
									<th width="12%"><spring:message code="label.cardNumberMasked" /></th>
									<th width="8%"><spring:message code="label.amount" /></th>
									<th><spring:message code="label.reason" /></th>
									<th><spring:message code="label.submittedUser" /></th>
									<th width="10%"><spring:message
											code="label.approverStatus" /></th>
									<th width="12%"><spring:message
											code="label.acknowledgement" /></th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pendingApproveReloadList}" var="pendingCard">
									<tr>
										<td></td>
										<td class="dateheuretrans">${pendingCard.dateheuretrans}</td>
										<td class="amountNotFormat d-none">${pendingCard.amount}</td>
										<td class="currencyNumber d-none">${fn:split(pendingCard.currency, '-')[0]}</td>
										<td class="expirydate d-none">${pendingCard.expiryDate}</td>
										<td class="institutionNumber d-none">${pendingCard.institutionNumber}</td>
										<td class="procCode d-none">${pendingCard.proccode}</td>
										<td class="transactionType d-none">${pendingCard.transactionType}</td>
										<td class="aaSrcAccount d-none">${pendingCard.aaSrcAccount}</td>
										<td class="aaDestAccount d-none">${pendingCard.aaDestAccount}</td>
										<td class="action">${pendingCard.action}</td>
										<td class="retrievalReference">${pendingCard.retrievalReference}</td>
										<td class="customerName">${pendingCard.customerName}</td>										
										<td class="cardNumber d-none">${pendingCard.cardNumber}</td>
										<td class="cardNumberMasked">${fn:replace(pendingCard.cardNumber, fn:substring(pendingCard.cardNumber, 6,12), "XXXXXX")}</td>										
										<td class="transamount">${pendingCard.amount/100} ${fn:split(pendingCard.currency, '-')[1]}</td>
										<td class="changeReason">${pendingCard.changeReason}</td>
										<td class="submittedUser">${pendingCard.userName}</td>
										<td class="cardOldStatus">${pendingCard.approverStatus}</td>
										<td class="fileName" style="font-size: 0.7em">
											 <a href='${pageContext.request.contextPath}/api/downloadFile/pdf/${pendingCard.fileName}'
											style="color: none; font-weight: bolder"><em
												class='fa fa-book'></em>${pendingCard.fileName}</a>

										</td>
										<td>
											<button id="viewBtn" class="viewBtn btn-primary btn-sm"
												data-toggle="modal" data-target="#approveReloadModal">
												<spring:message code="label.action" />
											</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>

				</div>
			</c:if>
		</div>
	</div>
	<div class=" col-md-12 col-lg-12 mt-2" style="background-color: white">
		<div class="row">
			<div class="commentreportsapprreports d-none"></div>
			<div clas="col-md-10 col-lg-10 ">
				<button class="btn btn-primary btn-sm btnReportsApprover "
					onclick="getActivities('divapprreports','tableapprreports','commentreportsapprreports','getapprouverops')">
					<spring:message code="label.reportsApprover" />
				</button>
			</div>
		</div>
		<hr>
		<div class="col-md-12 col-lg-12 d-none" id="divapprreports"
			style="margin-bottom: 70px">
			<div class="table-responsive">
				<div class="table-wrapper w-100">
					<table id="tableapprreports"
						class="table table-striped table-bordered">
						<thead>
							<tr>
								<th></th>
								<th width="15%" scope="col"><spring:message
										code="label.reference" /></th>

								<th width="15%" scope="col"><spring:message
										code="label.cardNumber" /></th>
								<th width="10%" scope="col"><spring:message
										code="label.amount" /></th>
								<th width="10%" scope="col"><spring:message
										code="label.dateheuretrans" /></th>
								<th width="15%" scope="col"><spring:message
										code="label.dateheuretransApprov" /></th>
								<th width="10%" scope="col"><spring:message
										code="label.initiator" /></th>
								<th width="15%" scope="col"><spring:message
										code="label.validator" /></th>
								<th scope="col">customerName</th>
								<th  scope="col">authCode</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>


	</div>

</div>
<%@ include file="common/footer.jsp"%>
<script>
	$(document)
			.ready(
					function() {

						var title01 = "Ongoing";
						var table = $(".table")
								.DataTable(
										{
											'lengthMenu' : [ 5, 10, 15, 20 ],
											'order' : [ [ 1, "desc" ] ],
											'aoColumnDefs' : [ {
												'bSortable' : false,
												'aTargets' : [ 0 ], /* 1st one, start by the right */
												'searchable' : false,
												'orderable' : false,
												'className' : 'dt-body-center',
												'render' : function(data, type,
														full, meta) {
													return '<input type="checkbox" name="id[]" value="'
															+ $('<div/>').text(
																	data)
																	.html()
															+ '">';
												}

											} ]

											,
											language : {
												"decimal" : "",
												"emptyTable" : "Pas de données disponibles",
												"info" : "Lignes _START_ à _END_ sur _TOTAL_ ",
												"infoEmpty" : "Aucune donnée disponible",
												"infoFiltered" : "",
												"infoPostFix" : "",
												"thousands" : ",",
												"lengthMenu" : "Afficher  _MENU_  lignes",
												"loadingRecords" : "Chargement...",
												"processing" : "Traitement en cours ...",
												"search" : "Chercher:",
												"zeroRecords" : "Aucune donnée disponible",
												"paginate" : {
													"first" : "Premier",
													"last" : "Dernier",
													"next" : "Suivant",
													"previous" : "Précédent"
												},
												"aria" : {
													"sortAscending" : ": Trier par ordre croissant",
													"sortDescending" : ": Trier par ordre décroissant"
												}
											}
										});

						$(".viewBtn")
								.click(
										function() {

											var cardNumber = $(this).parents(
													"tr").find(".cardNumber")
													.text().trim();											
											var cardNumberMasked = $(this).parents("tr")                
											.find(".cardNumberMasked").text();
											var transamount = $(this).parents(
													"tr").find(".transamount")
													.text().trim();
											var aaSrcAccount = $(this).parents(
													"tr").find(".aaSrcAccount")
													.text().trim();
											var aaDestAccount = $(this)
													.parents("tr").find(
															".aaDestAccount")
													.text().trim();
											//var newCardLimit = $(this).parents("tr").find(".newCardLimit").text().trim();
											var customerName = $(this).parents(
													"tr").find(".customerName")
													.text().trim();
											var fileName = $(this)
													.parents("tr").find(
															".fileName").text()
													.trim();
											//console.log(fileName);

											$('#amountNotFormat').val(
													$(this).parents("tr").find(
															".amountNotFormat")
															.text().trim());
											$('#currencyNumber').val(
													$(this).parents("tr").find(
															".currencyNumber")
															.text().trim());
											$('#expirydate').val(
													$(this).parents("tr").find(
															".expirydate")
															.text().trim());
											$('#institutionNumber')
													.val(
															$(this)
																	.parents(
																			"tr")
																	.find(
																			".institutionNumber")
																	.text()
																	.trim());
											$('#procCode').val(
													$(this).parents("tr").find(
															".procCode").text()
															.trim());
											$('#transactionType').val(
													$(this).parents("tr").find(
															".transactionType")
															.text().trim());

											$('#retrievalReference')
													.val(
															$(this)
																	.parents(
																			"tr")
																	.find(
																			".retrievalReference")
																	.text()
																	.trim());
											$('#cardNumber').val(cardNumber);
											$('#cardNumberMasked').val(cardNumberMasked);
											$('#transamount').val(transamount);
											$('#aaSrcAccount')
													.val(aaSrcAccount);
											$('#aaDestAccount').val(
													aaDestAccount);

											//$('#newCardLimit').val(newCardLimit);  
											$('#custName').val(customerName);
											$('#commentApprov').val("");
											$('#fileName').val(fileName);
										});

						// Handle click on "Select all" control
						$('#select-all').on(
								'click',
								function() {
									// Get all rows with search applied
									var rows = table.rows({
										'search' : 'applied'
									}).nodes();
									// Check/uncheck checkboxes for all rows in the table
									$('input[type="checkbox"]', rows).prop(
											'checked', this.checked);
								});
					})
</script>