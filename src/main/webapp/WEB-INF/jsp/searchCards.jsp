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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<!--  -->
<link rel="stylesheet" href="<c:url value='/static/css/jquery.dataTables.min.css' />">
<link rel="stylesheet" href="<c:url value='/static/css/jquery-ui-1.13.1.css'/>" />
<link rel="stylesheet" href="<c:url value='/static/css/buttons.dataTables.min-2.2.3.css'/>" />
<link rel="stylesheet" href="<c:url value='/static/css/admin.css'/>" />


<script src="${pageContext.request.contextPath}/static/scripts/admin.js"></script>

<script src="${pageContext.request.contextPath}/static/scripts/jquery-3.5.1.js"></script>


<script src="${pageContext.request.contextPath}/static/scripts/jquery-ui-1.13.1.js"></script>
<script src="${pageContext.request.contextPath}/static/scripts/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/scripts/date-eu-1.10.11.js"></script>


<script src="${pageContext.request.contextPath}/static/scripts/dataTables.buttons.min-2.2.3.js"></script>
<script src="${pageContext.request.contextPath}/static/scripts/jszip.min-3.1.3.js"></script>
<script src="${pageContext.request.contextPath}/static/scripts/pdfmake-0.1.53.min.js"></script>

<script src="${pageContext.request.contextPath}/static/scripts/vfs_fonts-0.1.53.js"></script>

<script src="${pageContext.request.contextPath}/static/scripts/buttons.html5.min-2.2.3.js"></script>

<script src="${pageContext.request.contextPath}/static/scripts/buttons.print.min-2.2.3.js"></script>



<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="container-fluid" style="width: 85%;">

	


	<div class="panel panel-primary">
		<div class="panel-heading">CSC
			<spring:message code="label.customerCardDetails" />
		</div>
		<div class="panel-body">
			<form:form
				action="${pageContext.request.contextPath}/customerCardList"
				modelAttribute="customerInfo">

				<div class="form-group">
					<spring:message code="label.customerNumber" var="customerNumber" />
					<form:label path="customerNumber">
						<spring:message code="label.customerNumber" />:</form:label>
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
							<thead>Card details
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
											</button><br>
											<button id="transactionBtn"
												class="transactionBtn  btn-secondary btn-sm"
												data-toggle="modal" data-target="#transactionsModal01">
												<spring:message code="label.transactions" />
											</button><br>
											<sec:authorize
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
																<button id="activateBtnCancelCardWithoutProvision"
																	class="activateBtnCancelCardWithoutProvision btn-danger btn-sm"
																	data-toggle="modal" data-target="#cancelCardWithoutProvision">
																	Annulation carte sans Provision
																</button>
																<button id="activateBtnCancelCardWithProvision"
																	class="activateBtnCancelCardWithProvision btn-info btn-sm"
																	data-toggle="modal" data-target="#cancelCardWithProvision">
																	Annulation carte Avec Provision
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
													<c:when test="${fn:contains(header.referer, 'reloadMC')}">
														<c:if test="${customer.currentCardStatus == 'Active'}">
															<button id="reloadBtn"
																class="reloadBtn btn-warning btn-sm" data-toggle="modal"
																data-target="#reloadModal">
																<spring:message code="label.reload" />
															</button>
														</c:if>
														<c:if test="${customer.currentCardStatus != 'Active'}">
															<button id="reloadBtn" disabled="disabled"
																title="La carte n'est pas active pour effectuer la recharge, changer le statut"
																class="reloadBtn btn-warning btn-sm" data-toggle="modal"
																data-target="#reloadModal">
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
				
				
				
				
			<!-- Modal annulation carte sans provision -->	
			
				<form:form action="${pageContext.request.contextPath}/changeCardStatusCancel"
					modelAttribute="cardInfo" enctype="multipart/form-data" method="POST"
					id="cardInfoform">
					
					<!-- Modal -->
					<div class="modal fade" id="cancelCardWithoutProvision" tabindex="-1"
						role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="modalLabel">
										ANNULATION DE LA CARTE SANS PROVISION
									</h4>
									 <h5 class ="text-danger text-center"><spring:message code="comment.care.changeCardStatus" /></h5>
									  
								</div> 
								<div class="modal-body">
									<div class="commentModalchangestatus"></div>
									<div class="form-group">
										<label for="expiryDate"><spring:message
												code="label.expiryDate" /></label> <input type="text" readonly
											name="expiryDate" class="form-control" id="expiryDateBtnCancelCardWithoutProvision"  />
									</div>
									<div class="form-group">
										<label for="customerNumber"><spring:message
												code="label.customerNumber" /></label> <input type="text" readonly
											name="customerNumber" class="form-control" id="custNumberBtnCancelCardWithoutProvision"   />
									</div>
									<div class="form-group">
										<label for="customerName"><spring:message
												code="label.customerName" /></label> <input type="text" readonly
											name="customerName" class="form-control" id="custNameBtnCancelCardWithoutProvision"  />
									</div>
								
									
										<div class="form-group d-none">
											<label for="custCardNumber"><spring:message
													code="label.cardNumber" /></label> <input type="text" readonly
												name="custCardNumber" class="form-control" id="cardNumberBtnCancelCardWithoutProvision" />
										</div>
										
										<div class="form-group">
											<label for="cardNumberMasked"><spring:message
													code="label.cardNumberMasked" /></label> <input type="text" readonly
												name="cardNumberMasked" class="form-control" id="cardNumberMaskedBtnCancelCardWithoutProvision" />
										</div>
										
										
										
											
									<div class="form-group"> 
										<label for="currentCardStatus"><spring:message
												code="label.currentCardStatus" /></label> <input type="text" 
											name="cardOldStatus" class="form-control" id="currentCardStatusBtnCancelCardWithoutProvision"  readonly="readonly" > 
									</div>
									<div class="form-group">
										<label for="cardStatus"><spring:message
												code="label.changeStatus" /></label> <select class="form-control"
											name="cardStatus" id="cardStatuscancel" style ="border: 1px solid  #337ab7" required>
											<option  value="-1">Select</option>
											<!--
											<c:forEach items="${codeCardActives}" var="cardActives"
												varStatus="loop">
												<option   value="${cardActives.numCode}">${cardActives.description}</option>
											</c:forEach>
											-->
											
											<c:forEach items="${codeCardActives}" var="cardActives"
												varStatus="loop">
												
													<c:if test="${cardActives.numCode == '004'}">
														<option   value="${cardActives.numCode}">${cardActives.description}</option>
													</c:if>
											</c:forEach>
				
										</select>
									</div>
									<div class="form-group">
										<label for="changeReason"><spring:message
												code="label.reason" /></label>
										<textarea class="form-control" placeholder="Reason"
											name="changeReason" id="changeReason" required="required"></textarea>
									</div>
									
									
									
									
									<div class="form-group col-md-6 col-sm-6 col-lg-6">
										Compte à débiter <input type="text"
											name="debitA" class="form-control" id="debitA" value="" required/>
									</div>
									<div class="form-group col-md-6 col-sm-6 col-lg-6 ">
								      Code Agence cpt débit
								      <input type="text" name ="codeAgenceDebitA" class="form-control" id="codeAgenceDebitA" placeholder="code agence">
								    </div>
									
									
									<div class="form-group col-md-6 col-sm-6 col-lg-6">
										Compte à créditer <input type="text"
											name="creditA" class="form-control" id="creditA" value="" required/>
									</div>
									<div class="form-group col-md-6 col-sm-6 col-lg-6 ">
								      Code Agence cpt crédit 
								      <input type="text" name ="codeAgenceCreditA" class="form-control" id="codeAgenceCreditA" placeholder="code agence">
								    </div>
								    
								    
									
									<div class="form-group">
										Montant <input type="text" 
											name="montantTxnA" class="form-control" id="montantTxnA" value="" required/>
									</div>
									<div class="form-group">
										Dévise 
											<select name="currency"  class="form-control" id="currency">
											  <option value="USD">USD</option>
	
											</select>
									</div>
									
									
									
									<div class="form-group">
										<label for="ackReceiptAttachment"><spring:message
												code="label.attachment" /> [MAX FILE SIZE = 2.5MB]</label> <input type="file"
											class="form-control-file" name="file" id="ackReceiptAttachment" onchange="validate(this, 'commentModalchangestatus', 'changeStatusModalButton')" required/>
									</div>
									
								</div>
								<div class="modal-footer">
									<div class="spinner-border"></div>
									<button type="button" class="btn btn-default btn-sm"
										data-dismiss="modal">
										<spring:message code="label.close" />
									</button>
									<button type="submit" id="changeStatusModalButton"
									 class="btn btn-primary btn-sm">
										<spring:message code="label.approve" />
									</button>
								</div>
							</div>
						</div>
					</div>
					<sec:csrfInput /> 
				</form:form>
			
			
			
			<!-- Modal annulation carte avec provision -->	
			
				<form:form action="${pageContext.request.contextPath}/changeCardStatusCancelWithProvision"
					modelAttribute="cardInfo" enctype="multipart/form-data" method="POST"
					id="cardInfoform">
					
					<!-- Modal -->
					<div class="modal fade" id="cancelCardWithProvision" tabindex="-1"
						role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="modalLabel">
										ANNULATION DE LA CARTE AVEC PROVISION
									</h4>
									 <h5 class ="text-danger text-center"><spring:message code="comment.care.changeCardStatus" /></h5>
									  
								</div> 
								<div class="modal-body">
									<div class="commentModalchangestatus"></div>
									<div class="form-group">
										<label for="expiryDate"><spring:message
												code="label.expiryDate" /></label> <input type="text" readonly
											name="expiryDate" class="form-control" id="expiryDateBtnCancelCardWithProvision"  />
									</div>
									<div class="form-group">
										<label for="customerNumber"><spring:message
												code="label.customerNumber" /></label> <input type="text" readonly
											name="customerNumber" class="form-control" id="custNumberBtnCancelCardWithProvision"   />
									</div>
									<div class="form-group">
										<label for="customerName"><spring:message
												code="label.customerName" /></label> <input type="text" readonly
											name="customerName" class="form-control" id="custNameBtnCancelCardWithProvision"  />
									</div>
								
									
										<div class="form-group d-none">
											<label for="custCardNumber"><spring:message
													code="label.cardNumber" /></label> <input type="text" readonly
												name="custCardNumber" class="form-control" id="cardNumberBtnCancelCardWithProvision" />
										</div>
										
										<div class="form-group">
											<label for="cardNumberMasked"><spring:message
													code="label.cardNumberMasked" /></label> <input type="text" readonly
												name="cardNumberMasked" class="form-control" id="cardNumberMaskedBtnCancelCardWithProvision" />
										</div>
										
										
										
											
									<div class="form-group"> 
										<label for="currentCardStatus"><spring:message
												code="label.currentCardStatus" /></label> <input type="text" 
											name="cardOldStatus" class="form-control" id="currentCardStatusBtnCancelCardWithProvision"  readonly="readonly" > 
									</div>
									<div class="form-group">
										<label for="cardStatus"><spring:message
												code="label.changeStatus" /></label> <select class="form-control"
											name="cardStatus" id="cardStatuscancel" style ="border: 1px solid  #337ab7" required>
											<option  value="-1">Select</option>
											<c:forEach items="${codeCardActives}" var="cardActives"
												varStatus="loop">
												
													<c:if test="${cardActives.numCode == '004'}">
														<option   value="${cardActives.numCode}">${cardActives.description}</option>
													</c:if>
											</c:forEach>
				
										</select>
									</div>
									<div class="form-group">
										<label for="changeReason"><spring:message
												code="label.reason" /></label>
										<textarea class="form-control" placeholder="Reason"
											name="changeReason" id="changeReason" required="required"></textarea>
									</div>
									
									
									
									<h3>PREMIERE ECRITURE COMPTABLE</h3>
									<div class="form-group col-md-6 col-sm-6 col-lg-6">
										Compte à débiter <input type="text"
											name="debitA" class="form-control" id="debitA" value="" required/>
									</div>
									<div class="form-group col-md-6 col-sm-6 col-lg-6 ">
								      Code Agence cpt débit
								      <input type="text" name ="codeAgenceDebitA" class="form-control" id="codeAgenceDebitA" placeholder="code agence">
								    </div>
								    
								    
									
									<div class="form-group col-md-6 col-sm-6 col-lg-6">
										Compte à créditer <input type="text"
											name="creditA" class="form-control" id="creditA" value="" required/>
									</div>
									<div class="form-group col-md-6 col-sm-6 col-lg-6 ">
								      Code Agence cpt crédit
								      <input type="text" name ="codeAgenceCreditA" class="form-control" id="codeAgenceCreditA" placeholder="code agence">
								    </div>
									
									
									
									<div class="form-group">
										Montant <input type="text" 
											name="montantTxnA" class="form-control" id="montantTxnA" value="" required/>
									</div>
									
									<hr>
									
									<h3>DEUXIEME ECRITURE COMPTABLE</h3>
									<div class="form-group col-md-6 col-sm-6 col-lg-6">
										Compte à débiter <input type="text"
											name="debitB" class="form-control" id="debitB" value="" required/>
									</div>
									<div class="form-group col-md-6 col-sm-6 col-lg-6 ">
								      Code Agence cpt débit
								      <input type="text" name ="codeAgenceDebitB" class="form-control" id="codeAgenceDebitB" placeholder="code agence">
								    </div>
									
									
									
									
									<div class="form-group col-md-6 col-sm-6 col-lg-6">
										Compte à créditer <input type="text"
											name="creditB" class="form-control" id="creditB" value="" required/>
									</div>
									<div class="form-group col-md-6 col-sm-6 col-lg-6 ">
								      Code Agence cpt crédit
								      <input type="text" name ="codeAgenceCreditB" class="form-control" id="codeAgenceCreditB" placeholder="code agence">
								    </div>
									
									
									
									<div class="form-group">
										Montant <input type="text" 
											name="montantTxnB" class="form-control" id="montantTxnB" value="" required/>
									</div>
									
									
									
									
									<div class="form-group">
										Dévise 
											<select name="currency"  class="form-control" id="currency">
											  <option value="USD">USD</option>
	
											</select>
									</div>
									
									
									
									<div class="form-group">
										<label for="ackReceiptAttachment"><spring:message
												code="label.attachment" /> [MAX FILE SIZE = 2.5MB]</label> <input type="file"
											class="form-control-file" name="file" id="ackReceiptAttachment" onchange="validate(this, 'commentModalchangestatus', 'changeStatusModalButton')" required/>
									</div>
									
								</div>
								<div class="modal-footer">
									<div class="spinner-border"></div>
									<button type="button" class="btn btn-default btn-sm"
										data-dismiss="modal">
										<spring:message code="label.close" />
									</button>
									<button type="submit" id="changeStatusModalButton"
									 class="btn btn-primary btn-sm">
										<spring:message code="label.approve" />
									</button>
								</div>
							</div>
						</div>
					</div>
					<sec:csrfInput /> 
				</form:form>
			
			
			
			
			
			</c:if>

		</div>
	</div>
	<div class=" col-md-12 col-lg-12 mt-2" style="background-color: white">
		<div class="row">
			<div class="commentChangeStatusReport01 d-none"></div>
			<div class="commentreloadInitiator d-none"></div>
			<div clas="col-md-10 col-lg-10">
				<button class="btn btn-primary btn-sm btnChangeStatusReports d-none"
					onclick="getChangeStatusReports01()">
					<spring:message code="label.changeStatusReports" />
				</button>
				<button class="btn btn-primary btn-sm btnReloadInitiated  d-none" id="psp"
					onclick="getActivities('divinitiatorreloadreports','tableinitiatorreloadreports','commentreloadInitiator','getInitiatorActivities')">
					<spring:message code="label.reloadInitiate" />
				</button>
			</div>
		</div>
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

//************* START Filter card status and keep only blocked == Cancel *********
$('#cardStatuscancel').focus(function() {
	 $("#cancelCardWithoutProvision option").each(function(){
		 var currentStatus  = $("#cancelCardWithoutProvision #currentCardStatus").val().trim() ;
		 var roleUser  =  $("#role").text().trim();
		   
		        if (this.text.trim() !=="Select"){
		        	  this.style.display = "none"; 
		   		 if (currentStatus  ==="Active"){
		   			
		   			  if((this.text.trim() ==="Blocked") && roleUser !=="csc_admin_call_center"){
		   				  this.style.display = "block";
		   			  }
		   			   
		   			  
		   			  
		   		  } else if (currentStatus  ==="InActive"){
		   			  if((this.text.trim() ==="Active" || this.text.trim() ==="Blocked" || this.text.trim()==="Temporary blocked")
		   					  && roleUser !=="csc_admin_call_center"){
		   				  this.style.display = "block";
		   			  }
		   			  
		   		  } else if (currentStatus  ==="Temporary blocked"){
		   			  if((this.text.trim() ==="Active" || this.text.trim() ==="Blocked") && roleUser !=="csc_admin_call_center"){
		   				  this.style.display = "block";
		   			  }else if (this.text.trim() ==="Active" && roleUser ==="csc_admin_call_center"){
		   				this.style.display = "block";
		   				  
		   			  }
		   			  
		   		  }else  if (currentStatus  ==="Blocked"){
		   			  this.style.display = "none";
		   			 
		   	    }
		   		 
		        }
		 
		 }
	 );
	 
});
	


	$(document)
			.ready(
					function() {
						// print the buttons based on Window.location.pathname 
                           $(".btnChangeStatusReports").addClass("d-none");
                           $(".btnReloadInitiated").addClass("d-none");
                           
                           
						if (window.location.pathname.endsWith("searchCards")) {
							$(".changeLimitBtn").addClass("d-none");
							$(".activateBtn").removeClass("d-none");
							$(".btnChangeStatusReports").removeClass("d-none");

						} else if (window.location.pathname
								.endsWith("changeCardLimits")) {
							$(".changeLimitBtn").removeClass("d-none"); 
							$(".activateBtn").addClass("d-none");
						} else if (window.location.pathname.endsWith("reloadMC")){
							$(".btnReloadInitiated").removeClass("d-none");
						}
						
						

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
						
						
						$(".activateBtnCancelCardWithoutProvision").click(
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
									$('#cancelCardWithoutProvision #expiryDateBtnCancelCardWithoutProvision').val(
											expiryDate);
									$('#cancelCardWithoutProvision #custNameBtnCancelCardWithoutProvision').val(
											custName);
									$('#cancelCardWithoutProvision #cardNumberBtnCancelCardWithoutProvision').val(
											cardNumber);
									$('#cancelCardWithoutProvision #cardNumberMaskedBtnCancelCardWithoutProvision').val(
											cardNumberMasked);									
									$('#cancelCardWithoutProvision #custNumberBtnCancelCardWithoutProvision').val(
											custNumber);
									$("#cancelCardWithoutProvision #currentCardStatusBtnCancelCardWithoutProvision")
											.val(cardStatus.trim());
									
									$("#changeStatusModalButton").prop("disabled", cardStatus==="Blocked" ? true: false);
									
									 cardStatus==="Blocked" ? $("#cancelCardWithoutProvision .commentModalchangestatus").empty().
						   			append("<p class ='alert alert-danger  p-0' > Vous pouvez changer le statut de cette carte car la carte a un statut qui 'Blocked' n'est pas pris en charge par votre profil </p>")
						   			: $("#cancelCardWithoutProvision .commentModalchangestatus").empty();
								
									/*$('#satusChangeModal #cardStatus').prop(
											"selectedIndex",
											cardStatus.trim() === 'Active' ? 1
													: 2);*/

								});
						
						
						$(".activateBtnCancelCardWithProvision").click(
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
									$('#cancelCardWithProvision #expiryDateBtnCancelCardWithProvision').val(
											expiryDate);
									$('#cancelCardWithProvision #custNameBtnCancelCardWithProvision').val(
											custName);
									$('#cancelCardWithProvision #cardNumberBtnCancelCardWithProvision').val(
											cardNumber);
									$('#cancelCardWithProvision #cardNumberMaskedBtnCancelCardWithProvision').val(
											cardNumberMasked);									
									$('#cancelCardWithProvision #custNumberBtnCancelCardWithProvision').val(
											custNumber);
									$("#cancelCardWithProvision #currentCardStatusBtnCancelCardWithProvision")
											.val(cardStatus.trim());
									
									$("#changeStatusModalButton").prop("disabled", cardStatus==="Blocked" ? true: false);
									
									 cardStatus==="Blocked" ? $("#cancelCardWithoutProvision .commentModalchangestatus").empty().
						   			append("<p class ='alert alert-danger  p-0' > Vous pouvez changer le statut de cette carte car la carte a un statut qui 'Blocked' n'est pas pris en charge par votre profil </p>")
						   			: $("#cancelCardWithoutProvision .commentModalchangestatus").empty();
								
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
									
									 $("#reloadData").addClass("d-none");
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
									  $('#reloadModal #custNumber').val(custNumber);
									  $('#reloadModal .currency-addon').empty().append('<label>' + currency + '</label>');
									  $('#reloadModal #custName').val(resp.clientName);
									  $('#reloadModal #cardNumber').val(resp.cardNumber);
									  $('#reloadModal #cardNumberMasked').val((resp.cardNumber).replace((resp.cardNumber).substring(6,12),"XXXXXX"));  
									  $('#reloadModal #currentCardStatus').val(resp.cardStatus);
									  $('#reloadModal #expiryDateCard').val(resp.cardExpiredDate);
									  $('#reloadModal #loadReason').val(resp.reloadComment);
									 
									    //Math.abs .toFixed(2)
									  $('#reloadModal #amountDue').val((parseFloat(resp.limitBalance)-Math.abs(parseFloat(resp.pendingAmount))-parseFloat(resp.balance)).toFixed(2));
									  $('#reloadModal #currency').val(resp.currency);
									  $('#reloadModal #availableAmount').val(resp.balance);
									  $('#reloadModal #limitAmount').val(resp.limitBalance);
									  $('#reloadModal #pendingAmount').val(Math.abs(resp.pendingAmount));
									  $('#reloadModal #cscAccountNumber').val(resp.cscAccountNumber);
									   //cscAccountNumber
									   // adding list of   accounts belong to the customer
									    //$("#target").val($("#target option:first").val());


									   var   valFirstAccountList = $("#custAccountList option:first").val();
									   var   textFirstAccountList = $("#custAccountList option:first").text();
									   //console.log (valFirstAccountList + ' ' + textFirstAccountList);
									   $('#custAccountList').empty();
									   $('#custAccountList').append( $('<option class ="text-danger"></option>').val(valFirstAccountList).html(textFirstAccountList));
									  $.each(accountList, function(val, text) {
								            $('#custAccountList').append( $('<option class ="text-danger"></option>').val(text).html(text) )
								            });
									  
									  
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