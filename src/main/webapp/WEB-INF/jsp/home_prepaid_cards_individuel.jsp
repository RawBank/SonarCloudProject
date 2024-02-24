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
<%@ include file="modals/viewCardDetails.jsp"%>
<%@ include file="modals/changeStatus.jsp"%>
<%@ include file="modals/changeLimit.jsp"%>
<%@ include file="modals/viewCardTransactions.jsp"%>
<%@ include file="modals/changeCardDetails.jsp"%>
<%@ include file="modals/reloadCard.jsp"%>
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



<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="container-fluid" style="width: 85%;">
	<div class="panel panel-primary">
		<div class="panel-heading">
			Recharge MC prépayée individuel
		</div>
		<div class="panel-body">
			<form:form
				action="${pageContext.request.contextPath}/customerCardListMCPrepaid"
				modelAttribute="customerInfo">

				<div class="form-group">
					<b>Numéro CSC</b>
					<form:input path="customerNumber" type="text"
						placeholder="${customerNumber}" autofocus="true"
						required="required" />
					<form:button class="btn btn-primary btn-sm">
						<spring:message code="label.searchCards" />
					</form:button>
				</div>
				<c:if test="${reloadMessage != null }">
					<p
						class="${fn:containsIgnoreCase(reloadMessage, 'erreur') ? 'p-3 mb-2 bg-danger text-white':'p-3 mb-2 bg-success text-white'}">${reloadMessage}</p>
				</c:if>
				<sec:csrfInput />
			</form:form>
			<c:if test="${message != null }">
				<p class="p-3 mb-2 bg-danger text-white">${message}</p>
			</c:if>
			<c:if test="${customerInfoList.size() > 0 }">

				<div class="table-responsive">
					<div class="table-wrapper w-100">
						<table class="table table-hover table-bordered border-primary"
							id="sortTable">
							<thead>
								<tr>
									<th class="d-none">cscClientNumber</th>
									<th class="d-none">currency</th>
									<th class="d-none">expiryDate</th>
									
									<th><spring:message code="label.customerNumber" /></th>
									<th><spring:message code="label.cellPhone" /></th>
									<th><spring:message code="label.birthDate" /></th>
									<th width="18%" scope="col"><spring:message
											code="label.accountNumber" /></th>
									<th width="18%" scope="col"><spring:message
											code="label.customerName" /></th>
									<th class="d-none" code="label.cardNumber" ></th>
									<th width="18%" scope="col"><spring:message
											code="label.cardNumberMasked"/></th>
									<th width="15%" scope="col"><spring:message
											code="label.currentCardStatus" /></th>
									<th width="18%" scope="col"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${customerInfoList}" var="customer">
									<tr>
										<td class="cscClientNumber d-none">${customer.cscClientNumber}</td>
										<td class="currency d-none">${customer.currency}</td>
										<td class="expiryDate d-none">${customer.expiryDate}</td>
										<td class="customerNumber">${customer.customerNumber}</td>
										<td class="cellPhone">${customer.privateTel}</td>
										<td class="birthDate" width ="10%">${customer.birthDate}</td>
										<td class="bankAccountNumber" width ="10%">${customer.bankAccountNumber}</td>
										<td class="customerName">${customer.name}</td>
										<td class="cardNumber d-none">${customer.cscNumber}</td>
										<td class="cardNumberMasked">${fn:replace(customer.cscNumber, fn:substring(customer.cscNumber, 6,12), "XXXXXX")}</td>
										<td><span
											class="${customer.currentCardStatus == 'Active' ? 'currentCardStatus  label label-success' : 'currentCardStatus label label-danger'}">
												${customer.currentCardStatus}</span></td>
										<td>
											<button id="viewBtn" class="viewBtn btn-primary btn-sm"
												data-toggle="modal" data-target="#viewModal">
												<spring:message code="label.view" />
											</button>
											<button id="transactionBtn"
												class="transactionBtn  btn-secondary btn-sm"
												data-toggle="modal" data-target="#transactionsModal01">
												<spring:message code="label.transactions" />
											</button> <sec:authorize
												access="hasAnyAuthority('csc_admin_call_center','csc_admin_initiator')">
												<c:choose>
													<c:when
														test="${fn:contains(header.referer, 'searchCards')}">

														<c:choose>
															<c:when test="${customer.currentCardStatus == 'Active' }">
																<button id="activateBtn"
																	class="activateBtn btn-warning btn-sm"
																	data-toggle="modal" data-target="#satusChangeModal">
																	<spring:message code="label.block" />
																</button>
															</c:when>
															<c:otherwise>
																<button id="activateBtn"
																	class="activateBtn btn-success btn-sm"
																	data-toggle="modal" data-target="#satusChangeModal">
																	<spring:message code="label.active" />
																</button>
															</c:otherwise>

														</c:choose>


													</c:when>
													<c:when
														test="${fn:contains(header.referer, 'changeCardLimits')}">
														<button id="changeLimitBtn"
															class="changeLimitBtn btn-warning btn-sm"
															data-toggle="modal" data-target="#limitChangeModal">
															<spring:message code="label.changeLimit" />
														</button>

													</c:when>
													<c:when
														test="${fn:contains(header.referer, 'changeCardDetails')}">
														<button id="changeDetailBtn"
															class="changeDetailBtn btn-warning btn-sm"
															data-toggle="modal" data-target="#detailChangeModal">
															<spring:message code="label.changeDetails" />
														</button>
													</c:when>
													<c:when test="${fn:contains(header.referer, 'homePrepaidCardIndividuel')}">
														<c:if test="${customer.currentCardStatus == 'Active'}">
															<button id="reloadBtn"
																class="reloadBtn btn-warning btn-sm" data-toggle="modal"
																data-target="#reloadModalMcPrepaidIndividual">
																<spring:message code="label.reload" />
															</button>
														</c:if>
														<c:if test="${customer.currentCardStatus != 'Active'}">
															<button id="reloadBtn" disabled="disabled"
																title="La carte n'est pas active pour effectuer la recharge, changer le statut"
																class="reloadBtn btn-warning btn-sm" data-toggle="modal"
																data-target="#reloadModalMcPrepaidIndividual">
																<spring:message code="label.reload" />
															</button>
														</c:if>
													</c:when>
													<c:otherwise> ... </c:otherwise>
												</c:choose>
												<!--                                          button visible only for   csc_admin_initiator role -->
											</sec:authorize>
										</td>
										<td hidden="true" class="phoneNumber">${customer.phoneNumber}</td>
										<td hidden="true" class="cardLimit">${customer.cardLimit}</td>
										<td hidden="true" class="cardCurrentBalance">${customer.cardCurrentBalance}</td>
										<td hidden="true" class="billingAccountBalance">${customer.billingAccountBalance}</td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</c:if>

		</div>
	</div>

	
	<button class="btn btn-primary btn-sm btnReloadInitiated" id="ind"
					onclick="getActivitiesInd('divinitiatorreloadreports','tableinitiatorreloadreports','commentreloadInitiator','getInitiatorActivities')">
					Rapport Recharge MC Prépayée Individuel
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


</div>


<script>
// buttons of reports


						
	$(document)
			.ready(
					function() {
					
						

						$(".viewBtn").click(
								function() {
									var custName = $(this).parents("tr").find(
											".customerName").text();
									var cardNumber = $(this).parents("tr")               
											.find(".cardNumber").text();
									var cardNumberMasked = $(this).parents("tr")                
									.find(".cardNumberMasked").text();
									var cardStatus = $(this).parents("tr")
											.find(".currentCardStatus").text()
											.trim();
									var bankAccountNumber = $(this).parents(
											"tr").find(".bankAccountNumber")
											.text();
									var phoneNumber = $(this).parents("tr")
											.find(".phoneNumber").text();
									var cardLimit = $(this).parents("tr").find(
											".cardLimit").text();
									var cardCurrentBalance = $(this).parents(
											"tr").find(".cardCurrentBalance")
											.text();
									var billingAccountBalance = $(this)
											.parents("tr").find(
													".billingAccountBalance")
											.text();
									$('#customerName').val(custName);
									$('#cardNumberMasked').val(cardNumberMasked);
									$('#custCardNumber').val(cardNumber);
									$('#currentCardStatus').val(cardStatus);
									$('#bankAccountNumber').val(bankAccountNumber);

									$('#phoneNumber').val(phoneNumber);
									$('#cardLimit').val(cardLimit);
									$('#cardCurrentBalance').val(
											cardCurrentBalance);
									$('#billingAccountBalance').val(
											billingAccountBalance);
								});
						$(".activateBtn").click(
								function() {
									 var expiryDate = $(this).parents("tr").find(
										".expiryDate").text();
									var custName = $(this).parents("tr").find(
											".customerName").text();
									var cardNumber = $(this).parents("tr")
											.find(".cardNumber").text();
									var cardNumberMasked = $(this).parents("tr")                
									.find(".cardNumberMasked").text();
									var custNumber = $(this).parents("tr")
											.find(".customerNumber").text();
									var cardStatus = $(this).parents("tr")
											.find(".currentCardStatus").text()
											.trim();
									$('#satusChangeModal #expiryDate').val(
											expiryDate);
									$('#satusChangeModal #custName').val(
											custName);
									$('#satusChangeModal #cardNumber').val(
											cardNumber);
									$('#satusChangeModal #cardNumberMasked').val(
											cardNumberMasked);									
									$('#satusChangeModal #custNumber').val(
											custNumber);
									$("#satusChangeModal #currentCardStatus")
											.val(cardStatus.trim());
									
									$("#changeStatusModalButton").prop("disabled", cardStatus==="Blocked" ? true: false);
									
									 cardStatus==="Blocked" ? $("#satusChangeModal .commentModalchangestatus").empty().
						   			append("<p class ='alert alert-danger  p-0' > Vous pouvez changer le statut de cette carte car la carte a un statut qui 'Blocked' n'est pas pris en charge par votre profil </p>")
						   			: $("#satusChangeModal .commentModalchangestatus").empty();
								
									/*$('#satusChangeModal #cardStatus').prop(
											"selectedIndex",
											cardStatus.trim() === 'Active' ? 1
													: 2);*/

								});

						$(function() {
							$("#dateFrom").datepicker({
								"dateFormat" : "yy-mm-dd",
								changeYear : true,
								language : 'fr'
							});
							$("#dateTo").datepicker({
								"dateFormat" : "yy-mm-dd",
								changeYear : true,
								language : "fr",
								regional : "fr",
								locale : 'fr'
							});
						});

						// $('#sortTable').DataTable();  currentCardStatus

						$(".transactionBtn")
								.click(
										function() {
											var custName = $(this)
													.parents("tr").find(
															".customerName")
													.text();
											var cardNumber = $(this).parents(
													"tr").find(".cardNumber")
													.text();
											var cardStatus = $(this).parents(
													"tr").find(
													".currentCardStatus")
													.text().trim();
											var bankAccountNumber = $(this)
													.parents("tr")
													.find(".bankAccountNumber")
													.text();
											var phoneNumber = $(this).parents(
													"tr").find(".phoneNumber")
													.text();
											var cardLimit = $(this).parents(
													"tr").find(".cardLimit")
													.text();
											var cardCurrentBalance = $(this)
													.parents("tr")
													.find(".cardCurrentBalance")
													.text();
											var billingAccountBalance = $(this)
													.parents("tr")
													.find(
															".billingAccountBalance")
													.text();
											$(
													'#transactionsModal01 #customerName')
													.val(custName);
											$(
													'#transactionsModal01 #custCardNumber')
													.val(cardNumber);
											$(
													'#transactionsModal01 #currentCardStatus')
													.val(cardStatus);
											$(
													'#transactionsModal01 #bankAccountNumber')
													.val(bankAccountNumber);

											$(
													'#transactionsModal01 #phoneNumber')
													.val(phoneNumber);
											$('#transactionsModal01 #cardLimit')
													.val(cardLimit);
											$(
													'#transactionsModal01 #cardCurrentBalance')
													.val(cardCurrentBalance);
											$(
													'#transactionsModal01 #billingAccountBalance')
													.val(billingAccountBalance);
										});

						$(".changeLimitBtn")
								.click(
										function() {
											var custName = $(this)
													.parents("tr").find(
															".customerName")
													.text();
											var cardNumber = $(this).parents(
													"tr").find(".cardNumber")
													.text();
											var custNumber = $(this).parents(
													"tr").find(
													".customerNumber").text();
											var cardNumberMasked = $(this).parents("tr")                
											.find(".cardNumberMasked").text();											
											var oldCardLimit = $(this).parents(
													"tr").find(".cardLimit")
													.text();
											var currentStatus = $(this)
													.parents("tr")
													.find(".currentCardStatus")
													.text();
											$('#custNameField').val(custName);
											$('#cardNumberField').val(
													cardNumber);
											$('#cardNumberMasked').val(cardNumberMasked);											
											$('#custNumberField').val(
													custNumber);
											$('#oldCardLimit')
													.val(oldCardLimit);
											$('#cardStatusx').val(
													currentStatus.trim());
										});

						$(".changeDetailBtn").click(
								function() {
									// table 
									var custNumber = $(this).parents("tr")
											.find(".customerNumber").text();
									var clientDetailModel = JSON.stringify($(
											".rawClientResponseModel").val());

								})

						$(".reloadBtn").click(
								async  function() { 
									
									 $(".erroMessage").empty();
									var cardNumber = $(this).parents("tr").find(".cardNumber").text(); 
									var cscClientNumber = $(this).parents("tr").find(".cscClientNumber").text();  
									var currency = $(this).parents("tr").find(".currency").text(); 
									var custNumber = $(this).parents("tr").find(".customerNumber").text();
									//console.log(custNumber);
									
									 $("#reloadDataMCindividuel").addClass("d-block");
									//console.log(cardNumber +' ' + cscClientNumber + ' '+ currency);
									
									const accountList = JSON.parse(JSON.stringify(await accountListCall(custNumber, currency, "erroMessage")));
								    // console.log(accountList);
									
									$(".commentreloadCardModal").empty().append("<img src='/adminPortal/static/img/wait-001.gif' width='40' height='40' alt='Veuillez patienter...'  />");
									$.ajax(
											{
												method: "POST",
												url: "/adminPortal/api/getreloaddetails/"+cardNumber+"/"+currency+"/"+cscClientNumber
										
									}).done(function (response, statustext, status){
										
									 var resp = JSON.parse(JSON.stringify(response));
									  //console.log(resp);
									  $("#reloadData").removeClass("d-none");
									  $('#reloadModalMcPrepaidIndividual #custNumber').val(custNumber);
									  $('#reloadModalMcPrepaidIndividual .currency-addon').empty().append('<label>' + currency + '</label>');
									  $('#reloadModalMcPrepaidIndividual #custName').val(resp.clientName);
									  $('#reloadModalMcPrepaidIndividual #cardNumber').val(resp.cardNumber);
									  $('#reloadModalMcPrepaidIndividual #cardNumberMasked').val((resp.cardNumber).replace((resp.cardNumber).substring(6,12),"XXXXXX"));  
									  $('#reloadModalMcPrepaidIndividual #currentCardStatus').val(resp.cardStatus);
									  $('#reloadModalMcPrepaidIndividual #expiryDateCard').val(resp.cardExpiredDate);
									  $('#reloadModalMcPrepaidIndividual #loadReason').val(resp.reloadComment);
									 
									    //Math.abs .toFixed(2)
									  $('#reloadModalMcPrepaidIndividual #amountDue').val((parseFloat(resp.limitBalance)-Math.abs(parseFloat(resp.pendingAmount))-parseFloat(resp.balance)).toFixed(2));
									  $('#reloadModalMcPrepaidIndividual #currency').val(resp.currency);
									  $('#reloadModalMcPrepaidIndividual #availableAmount').val(resp.balance);
									  $('#reloadModalMcPrepaidIndividual #limitAmount').val(resp.limitBalance);
									  $('#reloadModalMcPrepaidIndividual #pendingAmount').val(resp.pendingAmount);
									  $('#reloadModalMcPrepaidIndividual #cscAccountNumber').val(resp.cscAccountNumber);
									   //cscAccountNumber
									   // adding list of   accounts belong to the customer
									    //$("#target").val($("#target option:first").val());

									
									   
									   /*
									   var   valFirstAccountList = $("#custAccountList option:first").val();
									   var   textFirstAccountList = $("#custAccountList option:first").text();
									   //console.log (valFirstAccountList + ' ' + textFirstAccountList);
									   $('#custAccountList').empty();
									   $('#custAccountList').append( $('<option class ="text-danger"></option>').val(valFirstAccountList).html(textFirstAccountList));
									  $.each(accountList, function(val, text) {
								            $('#custAccountList').append( $('<option class ="text-danger"></option>').val(text).html(text) )
								            });
									  */
									  
									}).fail(
									  function(jqXHR, textStatus){
										  
										  if (jqXHR.status == 412 || jqXHR.status  == 0 ) {
									            window.location.href ="/adminPortal/login?errorCode=" + jqXHR.status;
									            return; 
									        }
										  $(".erroMessage").empty().append("<p class ='alert alert-danger  p-0'  style ='padding:3px'> erreur pour recevoir les données de reload</p>");;
									  }	
									).always(function(){
										
										$(".commentreloadCardModal").empty();
									});
									
								})
   // adding call
   

			    async function  accountListCall(custNumber, currency, erroMessage) {
					//
						return new Promise(function(resolve, reject){
							$.ajax({
								method: "POST",
								url: "/adminPortal/api/getAccounts/"+custNumber +"/"+currency,
								beforeSend: function() {
									//console.log("ready to send");
								},
								success: function(data) {
									resolve(data) // Resolve promise and when success
								},
								error: function(err) {
									var error = JSON.parse(JSON.stringify(err));
									// console.log (" error " +  error)
									$("." + erroMessage).empty().append("<p class ='text-error  p-0'> Erreur to accounts   " + error.status + "</p>");
									       if (error.status == 412){
									    	   window.location.href ="/adminPortal/login?errorCode="+ error.status;
									    	   return;
									       }
									// console.log( )
									reject(err) // Reject the promise and go to catch()
					
									return false;
								}
							})
						});
				}
										 
});
	
	

</script>

<%@ include file="common/footer.jsp"%>