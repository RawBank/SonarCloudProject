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

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
	
<!-- 
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

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


-->

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<link href="${pageContext.request.contextPath}/static/css/datatables.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/scripts/datatables.min.js"></script>



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
		    <c:when test="${not empty messagePartResult}">
		        <p class ='alert alert-warning  p-0' > 
					${messagePartResult}
				</p>
		    </c:when> 
		</c:choose>
	</div>


<div class="">
	<div class="">
		<div class="">
			
		</div>
		<div class="panel-body">
			

				<div class="table-responsive">
					<div class="table-wrapper w-100">
						<table class="table table-hover table-bordered border-primary" id="sortTable">
							<thead>
								<tr>
									
									<th>Nom client</th>
									<th>Num carte</th>
									<th>Nouvelle status</th>
									<th>Initiateur</th>
									<th>Dte initiation</th>
									<th>Validateur</th>
									<th>Dte validation</th>
									<th>Statut CSC</th>
									<th>Statut Ecriture A</th>
									<th>Statut Ecriture B</th>
									<th>Cpt débit A</th>
									<th>Cpt crédit A</th>
									<th>Montant A</th>
									<th>Cpt débit B</th>
									<th>Cpt crédit B</th>
									<th>Montant B</th>
									<th>Status OP</th>
									<th>Type annulation</th>
									<th>Action</th>
									
									<th hidden="true"></th>
									<th hidden="true"></th>
									<th hidden="true"></th>
									<th hidden="true"></th>
									<th hidden="true"></th>
									<th hidden="true"></th>
									<th hidden="true"></th>
									<th hidden="true"></th>
									
								
									
									
								</tr>
							</thead>
							<tbody>
							
								<c:forEach items="${cancelCardsInfo}" var="cancelCard">
									<tr>
										<td hidden="true"  class="customerNumber">${cancelCard.customerNumber}</td>
										<td class="customerName">${cancelCard.customerName}</td>
										<td class="cardNumber">${cancelCard.cardNumber}</td>
										<td class="cardnewStatus">${cancelCard.cardnewStatus}</td>
										<td class="initiator">${cancelCard.initiator}</td>
										<td class="dateInitiator">${cancelCard.dateInitiator}</td>
										<td class="validator">${cancelCard.validator}</td>
										<td class="dateValidation">${cancelCard.dateValidation}</td>
										
										
										<!-- Check Status Op CSC and give color -->
										<c:choose>
										    <c:when test="${cancelCard.statusOpCsc == 'OK'}">
										        <td class="statusOpCsc success">
													${cancelCard.statusOpCsc}
												</td>
										    </c:when>  
										    <c:when test="${cancelCard.statusOpCsc == 'ECHEC'}">
										        <td class="statusOpCsc danger">
													${cancelCard.statusOpCsc}
												</td>
										    </c:when>   
										    <c:otherwise>
										       <td class="statusOpCsc">
													${cancelCard.statusOpCsc}
												</td>
										    </c:otherwise>
										</c:choose>
										
										<!-- Check Status Op AmplitudeA and give color -->
										<c:choose>
										    <c:when test="${cancelCard.statusOpAmplitudeA == 'OK'}">
										        <td class="statusOpAmplitudeA success">
													${cancelCard.statusOpAmplitudeA}
												</td>
										    </c:when>  
										    <c:when test="${cancelCard.statusOpAmplitudeA == 'ECHEC'}">
										        <td class="statusOpAmplitudeA warning">
													${cancelCard.statusOpAmplitudeA}
												</td>
										    </c:when>   
										    <c:otherwise>
												<td class="statusOpAmplitudeA">
													${cancelCard.statusOpAmplitudeA}
												</td>
												
										    </c:otherwise>
										</c:choose>
										
										
										
										<!-- Check Status Op AmplitudeB and give color -->
										<c:choose>
										    <c:when test="${cancelCard.statusOpAmplitudeB == 'OK'}">
										        <td class="statusOpAmplitudeB success">
													${cancelCard.statusOpAmplitudeB}
												</td>
										    </c:when>  
										    <c:when test="${cancelCard.statusOpAmplitudeB == 'ECHEC'}">
										        <td class="statusOpAmplitudeB warning">
													${cancelCard.statusOpAmplitudeB}
												</td>
										    </c:when>   
										    <c:when test="${empty cancelCard.statusOpAmplitudeB}">
										        <td class="statusOpAmplitudeB">
													${cancelCard.statusOpAmplitudeB}
												</td>
										    </c:when>
										</c:choose>
										
										<td class="compteDebitA">${cancelCard.compteDebitA}</td>
										<td class="compteCreditA">${cancelCard.compteCreditA}</td>
										<td class="txnAmountA">${cancelCard.txnAmountA}</td>
										
										<td class="compteDebitB">${cancelCard.compteDebitB}</td>
										<td class="compteCreditB">${cancelCard.compteCreditB}</td>
										<td class="txnAmountB">${cancelCard.txnAmountB}</td>
										
										
										<c:if test="${cancelCard.cancelEventStatus == 'RP'}">
										
											<td class="cancelEventStatus success">
												SUCCES
											</td>
										</c:if>
										<c:if test="${cancelCard.cancelEventStatus == 'RR'}">
										
											<td class="cancelEventStatus danger">
												REJETER
											</td>
										</c:if>
										<c:if test="${cancelCard.cancelEventStatus == 'RI'}">
										     <td class="cancelEventStatus danger">
												INITIE
											</td>
										</c:if>
										<c:if test="${cancelCard.cancelEventStatus == 'PAA'}">
										     <td class="cancelEventStatus warning">
												RETESTER A
											</td>
										</c:if>
										<c:if test="${cancelCard.cancelEventStatus == 'PAB'}">
										     <td class="cancelEventStatus warning">
												RETESTER B
											</td>
										</c:if>
										<c:if test="${cancelCard.cancelEventStatus == 'PAAB'}">
										     <td class="cancelEventStatus warning">
												RETESTER A-B
											</td>
										</c:if>
												
										
										
										
										<c:if test="${cancelCard.typeAnnulationCarte == 'CSP'}">
										     <td class="typeAnnulationCarte"> Carte Sans Provision</td>
										</c:if>
										<c:if test="${cancelCard.typeAnnulationCarte == 'CAP'}">
										     <td class="typeAnnulationCarte"> Carte Avec Provision</td>
										</c:if>
										
										
										<td hidden="true" class="reqCancelId">${cancelCard.reqCancelId}</td>
										<td hidden="true" class="cardOldStatus">${cancelCard.cardOldStatus}</td>
										<td hidden="true" class="txnDevise">${cancelCard.txnDevise}</td>
										
										<td hidden="true" class="codeAgenceDebitA">${cancelCard.agenceCompteDebitA}</td>
										<td hidden="true" class="codeAgenceCreditA">${cancelCard.agenceCompteCreditA}</td>
										<td hidden="true" class="codeAgenceDebitB">${cancelCard.agenceCompteDebitB}</td>
										<td hidden="true" class="codeAgenceCreditB">${cancelCard.agenceCompteCreditB}</td>
										<td>
										
										<c:if test="${cancelCard.typeAnnulationCarte == 'CSP' && cancelCard.cancelEventStatus == 'RI' }">
										<!-- Call modals of Carte Sans Provision -->
											<button 
												class="approveBtn btn-info btn-sm"
												data-toggle="modal" data-target="#approvemodal">
												Approuver
											</button>
											<button
												class="rejectBtn btn-danger btn-sm"
												data-toggle="modal" data-target="#rejectmodal">
												Rejeter
											</button>
										</c:if>
										
										<c:if test="${cancelCard.typeAnnulationCarte == 'CAP' && cancelCard.cancelEventStatus == 'RI'}">
										<!-- Call modals of Carte Avec Provision -->
										     <button 
												class="approveBtn btn-info btn-sm"
												data-toggle="modal" data-target="#approvemodalCardWithProvision">
												Approuver
											</button>
											<button
												class="rejectBtn btn-danger btn-sm"
												data-toggle="modal" data-target="#rejectmodalCardWithProvision">
												Rejeter
											</button>
										</c:if>
										
										
										
										
										
										<c:if test="${cancelCard.typeAnnulationCarte == 'CAP' && cancelCard.statusOpAmplitudeA =='ECHEC' && cancelCard.cancelEventStatus == 'PAA'}">
										<!-- Call modals of Carte Avec Provision -->
										     <button 
												class="approveBtn btn-info btn-sm"
												data-toggle="modal" data-target="#approvemodalCardWithProvisionAmplitudeARetry">
												Réessaie A
											</button>
										</c:if>
										<c:if test="${cancelCard.typeAnnulationCarte == 'CAP' && cancelCard.statusOpAmplitudeB =='ECHEC' && cancelCard.cancelEventStatus == 'PAB'}">
										<!-- Call modals of Carte Avec Provision -->
										     <button 
												class="approveBtn btn-info btn-sm"
												data-toggle="modal" data-target="#approvemodalCardWithProvisionAmplitudeBRetry">
												Réessaie B
											</button>
										</c:if>
										<c:if test="${cancelCard.typeAnnulationCarte == 'CAP' && cancelCard.statusOpAmplitudeA =='ECHEC' && cancelCard.statusOpAmplitudeB =='ECHEC' && cancelCard.cancelEventStatus == 'PAAB'}">
										<!-- Call modals of Carte Avec Provision -->
										     <button 
												class="approveBtn btn-info btn-sm"
												data-toggle="modal" data-target="#approvemodalCardWithProvisionAmplitudeABRetry">
												Réessaie A-B
											</button>
										</c:if>
										<c:if test="${cancelCard.typeAnnulationCarte == 'CSP' && cancelCard.statusOpAmplitudeA =='ECHEC' && cancelCard.cancelEventStatus == 'PAA'}">
										<!-- Call modals of Carte Avec Provision -->
										     <button 
												class="approveBtn btn-info btn-sm"
												data-toggle="modal" data-target="#approvemodalCardWithProvisionAmplitudeARetry">
												Réessaie A
											</button>
										</c:if>
										
										
											
										
										</td>
										

									</tr>
								</c:forEach>
								
							</tbody>
						</table>
					</div>
				</div>
				
				
				
				
			
			<c:forEach items="${cancelCardsInfo}" var="cancelCard">
			<!-- Call of modals of card without and without provision for approval -->
			
				<!-- CONFIRMATION ANNULATION DE LA CARTE SANS PROVISION -->
				<form:form action="${pageContext.request.contextPath}/changeCardStatusCancelApprove"
					modelAttribute="cardCancelInfo" enctype="multipart/form-data" method="POST"
					id="cardInfoform">
					
				
					<!-- CONFIRMATION ANNULATION DE LA CARTE SANS PROVISION -->
					<div class="modal fade" id="approvemodal" tabindex="-1"
						role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="modalLabel">
										CONFIRMATION ANNULATION DE LA CARTE SANS PROVISION
									</h4>
									 <h5 class ="text-danger text-center"><spring:message code="comment.care.changeCardStatus" /></h5>
									  
								</div> 
								<div class="modal-body">
								
									<div class="form-group">
										Nom client <input type="text" readonly
											 class="form-control" id="selecCustomerName"  value="" />
									</div>
									
									
									
									<div class="form-group">
										<input type="hidden" readonly
											name="codeAgenceDebitA" class="form-control" id="selecCodeAgenceDebitA" value="" required/>
									</div>
									<div class="form-group">
										<input type="hidden" readonly
											name="codeAgenceCreditA" class="form-control" id="selecCodeAgenceCreditA" value="" required/>
									</div>
									
									
									
								
									<div class="form-group">
										<input type="hidden" readonly
											name="customerNumber" class="form-control" id="selecCustomerNumber" value="" required/>
									</div>
								
									<div class="form-group">
										<input type="hidden" readonly
											name="reqCancelId" class="form-control" id="selecReqCancelId" value="" required/>
									</div>
									
									<div class="form-group"> 
										<input type="hidden" readonly
											name="cardOldStatus" class="form-control" id="selecCardOldStatus" value="" required/>
									</div>
									
									<div class="form-group">
										Motif Annulation <input type="text" 
											name="cancelComment" class="form-control" id="selecMotifAnnulation" value="" required/>
									</div>
									<div class="form-group">
										Numéro carte <input type="text" readonly
											name="custCardNumber" class="form-control" id="selecCardNumber"  value="" />
									</div>
									<div class="form-group">
										Nouveau status carte <input type="text" readonly
											name="cardStatus" class="form-control" id="selecCardnewStatus"  value="" />
									</div>
									
									
									<div class="form-group">
										Compté à débiter <input type="text" readonly
											name="debitA" class="form-control" id="selecCompteDebitA"  value="" />
									</div>
									
									<div class="form-group">
										Compte à créditer <input type="text" readonly
											name="creditA" class="form-control" id="selecCompteCreditA"  value="" />
									</div>
									<div class="form-group">
										Montant <input type="text" readonly
											name="montantTxnA" class="form-control" id="selecTxnAmountA"  value="" />
									</div>
									<div class="form-group">
										Devise <input type="text" readonly
											name="currency" class="form-control" id="selecTxnDevise"  value="" />
									</div>
									
									
									
									
									
									
								</div>
								<div class="modal-footer">
									<div class="spinner-border"></div>
									<button type="button" class="btn btn-default btn-sm"
										data-dismiss="modal">
										Annuler
									</button>
									<button type="submit" id="changeStatusModalButton"
									 class="btn btn-primary btn-sm">
										Confirmer
									</button>
									
								</div>
							</div>
						</div>
					</div>
					<sec:csrfInput /> 
				</form:form>
				
				
				<!-- REJET ANNULATION DE LA CARTE SANS PROVISION Dialog -->
				<form:form action="${pageContext.request.contextPath}/changeCardStatusCancelReject"
					modelAttribute="cardCancelInfo" enctype="multipart/form-data" method="POST"
					id="cardInfoform">
					
				
					<!-- REJET ANNULATION DE LA CARTE SANS PROVISION Dialog -->
					<div class="modal fade" id="rejectmodal" tabindex="-1"
						role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="modalLabel">
										REJET ANNULATION DE LA CARTE SANS PROVISION
									</h4>
									 <h5 class ="text-danger text-center"><spring:message code="comment.care.changeCardStatus" /></h5>
									  
								</div> 
								<div class="modal-body">
								
									<div class="form-group">
										Nom client <input type="text" readonly
											 class="form-control" id="selecCustomerNameReject"  value="" />
									</div>
									<div class="form-group">
										<input type="hidden" readonly
											name="reqCancelId" class="form-control" id="selecReqCancelIdReject" value="" required/>
									</div>
									<div class="form-group">
										Motif Rejection <input type="text" 
											name="cancelComment" class="form-control" id="selecMotifAnnulationReject" value="" required/>
									</div>
									
									
									<div class="form-group">
										Numéro carte <input type="text" readonly
											name="custCardNumber" class="form-control" id="selecCardNumberReject"  value="" />
									</div>

									
								</div>
								<div class="modal-footer">
									<div class="spinner-border"></div>
									<button type="button" class="btn btn-default btn-sm"
										data-dismiss="modal">
										Annuler
									</button>
									<button type="submit" id="changeStatusModalButton"
									 class="btn btn-primary btn-sm">
										Confirmer
									</button>
									
								</div>
							</div>
						</div>
					</div>
					<sec:csrfInput /> 
				</form:form>
				
				
				
				
				<!-- CONFIRMATION ANNULATION DE LA CARTE AVEC PROVISION -->
				<form:form action="${pageContext.request.contextPath}/changeCardStatusCancelApproveCardWithProvision"
					modelAttribute="cardCancelInfo" enctype="multipart/form-data" method="POST"
					id="cardInfoform">
					
				
					<!-- Modal -->
					<div class="modal fade" id="approvemodalCardWithProvision" tabindex="-1"
						role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="modalLabel">
										CONFIRMATION ANNULATION DE LA CARTE AVEC PROVISION
									</h4>
									 <h5 class ="text-danger text-center"><spring:message code="comment.care.changeCardStatus" /></h5>
									  
								</div> 
								<div class="modal-body">
								
									<div class="form-group">
										Nom client <input type="text" readonly
											 class="form-control" id="selecCustomerName2"  value="" />
									</div>
									
									
									
									<div class="form-group">
										<input type="text" readonly
											name="codeAgenceDebitA" class="form-control" id="selecCodeAgenceDebitA2" value="" required/>
									</div>
									<div class="form-group">
										<input type="text" readonly
											name="codeAgenceCreditA" class="form-control" id="selecCodeAgenceCreditA2" value="" required/>
									</div>
									
									<div class="form-group">
										<input type="text" readonly
											name="codeAgenceDebitB" class="form-control" id="selecCodeAgenceDebitB2" value="" required/>
									</div>
									<div class="form-group">
										<input type="text" readonly
											name="codeAgenceCreditB" class="form-control" id="selecCodeAgenceCreditB2" value="" required/>
									</div>
									
									
									
									
									<div class="form-group">
										<input type="hidden" readonly
											name="customerNumber" class="form-control" id="selecCustomerNumber2" value="" required/>
									</div>
								
									<div class="form-group">
										<input type="hidden" readonly
											name="reqCancelId" class="form-control" id="selecReqCancelId2" value="" required/>
									</div>
									
									<div class="form-group"> 
										<input type="hidden" readonly
											name="cardOldStatus" class="form-control" id="selecCardOldStatus2" value="" required/>
									</div>
									
									<div class="form-group">
										Motif Annulation <input type="text" 
											name="cancelComment" class="form-control" id="selecMotifAnnulation2" value="" required/>
									</div>
									<div class="form-group">
										Numéro carte <input type="text" readonly
											name="custCardNumber" class="form-control" id="selecCardNumber2"  value="" />
									</div>
									<div class="form-group">
										Nouveau status carte <input type="text" readonly
											name="cardStatus" class="form-control" id="selecCardnewStatus2"  value="" />
									</div>
									
									
									<hr>
									<h3>PREMIERE ECRITURE COMPTABLE</h3>
									<div class="form-group">
										Compté à débiter <input type="text" readonly
											name="debitA" class="form-control" id="selecCompteDebitA2"  value="" />
									</div>
									<div class="form-group">
										Compte à créditer <input type="text" readonly
											name="creditA" class="form-control" id="selecCompteCreditA2"  value="" />
									</div>
									<div class="form-group">
										Montant <input type="text" readonly
											name="montantTxnA" class="form-control" id="selecTxnAmountA2"  value="" />
									</div>
									
									
									<hr>
									<h3>DEUXIEME ECRITURE COMPTABLE</h3>
									<div class="form-group">
										Compté à débiter <input type="text" readonly
											name="debitB" class="form-control" id="selecCompteDebitB2"  value="" />
									</div>
									<div class="form-group">
										Compte à créditer <input type="text" readonly
											name="creditB" class="form-control" id="selecCompteCreditB2"  value="" />
									</div>
									<div class="form-group">
										Montant <input type="text" readonly
											name="montantTxnB" class="form-control" id="selecTxnAmountB2"  value="" />
									</div>
									
									
									<div class="form-group">
										Devise <input type="text" readonly
											name="currency" class="form-control" id="selecTxnDevise2"  value="" />
									</div>
									
									
									
									
									
									
								</div>
								<div class="modal-footer">
									<div class="spinner-border"></div>
									<button type="button" class="btn btn-default btn-sm"
										data-dismiss="modal">
										Annuler
									</button>
									<button type="submit" id="changeStatusModalButton"
									 class="btn btn-primary btn-sm">
										Confirmer
									</button>
									
								</div>
							</div>
						</div>
					</div>
					<sec:csrfInput /> 
				</form:form>
				
				
				<!-- REJET ANNULATION DE LA CARTE AVEC PROVISION Dialog -->
				<form:form action="${pageContext.request.contextPath}/changeCardStatusCancelApproveCardWithProvisionReject"
					modelAttribute="cardCancelInfo" enctype="multipart/form-data" method="POST"
					id="cardInfoform">
					
				
					<!-- REJET ANNULATION DE LA CARTE SANS PROVISION Dialog -->
					<div class="modal fade" id="rejectmodalCardWithProvision" tabindex="-1"
						role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="modalLabel">
										REJET ANNULATION DE LA CARTE AVEC PROVISION
									</h4>
									 <h5 class ="text-danger text-center"><spring:message code="comment.care.changeCardStatus" /></h5>
									  
								</div> 
								<div class="modal-body">
								
									<div class="form-group">
										Nom client <input type="text" readonly
											 class="form-control" id="selecCustomerName2Reject"  value="" />
									</div>
									<div class="form-group">
										<input type="hidden" readonly
											name="reqCancelId" class="form-control" id="selecReqCancelId2Reject" value="" required/>
									</div>
									<div class="form-group">
										Motif Rejection <input type="text" 
											name="cancelComment" class="form-control" id="selecMotifAnnulation2Reject" value="" required/>
									</div>
									
									
									<div class="form-group">
										Numéro carte <input type="text" readonly
											name="custCardNumber" class="form-control" id="selecCardNumber2Reject"  value="" />
									</div>

									
								</div>
								<div class="modal-footer">
									<div class="spinner-border"></div>
									<button type="button" class="btn btn-default btn-sm"
										data-dismiss="modal">
										Annuler
									</button>
									<button type="submit" id="changeStatusModalButton"
									 class="btn btn-primary btn-sm">
										Confirmer
									</button>
									
								</div>
							</div>
						</div>
					</div>
					<sec:csrfInput /> 
				</form:form>
				
				
				
				
				<!-- Modal CONFIRMATION REESSAIE PREMIERE ECRITURE AMPLITUDE -->
				<form:form action="${pageContext.request.contextPath}/retryTxnAmplitudeA"
					modelAttribute="cardCancelInfo" enctype="multipart/form-data" method="POST"
					id="cardInfoform">
					
				
					<!-- Modal -->
					<div class="modal fade" id="approvemodalCardWithProvisionAmplitudeARetry" tabindex="-1"
						role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="modalLabel">
										CONFIRMATION REESSAIE PREMIERE ECRITURE AMPLITUDE
									</h4>
									 <h5 class ="text-danger text-center"><spring:message code="comment.care.changeCardStatus" /></h5>
									  
								</div> 
								<div class="modal-body">
								
									<div class="form-group">
										Nom client <input type="text" readonly
											 class="form-control" id="selecCustomerNameRetryA"  value="" />
									</div>
									
									
									
									<div class="form-group">
										<input type="hidden" readonly
											name="codeAgenceDebitA" class="form-control" id="selecCodeAgenceDebitARetryA" value="" required/>
									</div>
									<div class="form-group">
										<input type="hidden" readonly
											name="codeAgenceCreditA" class="form-control" id="selecCodeAgenceCreditARetryA" value="" required/>
									</div>
									
									
									
								
									<div class="form-group">
										<input type="hidden" readonly
											name="customerNumber" class="form-control" id="selecCustomerNumberRetryA" value="" required/>
									</div>
								
									<div class="form-group">
										<input type="hidden" readonly
											name="reqCancelId" class="form-control" id="selecReqCancelIdRetryA" value="" required/>
									</div>
									
									<div class="form-group"> 
										<input type="hidden" readonly
											name="cardOldStatus" class="form-control" id="selecCardOldStatusRetryA" value="" required/>
									</div>
									
									
									<div class="form-group">
										Numéro carte <input type="text" readonly
											name="custCardNumber" class="form-control" id="selecCardNumberRetryA"  value="" />
									</div>
									<div class="form-group">
										Nouveau status carte <input type="text" readonly
											name="cardStatus" class="form-control" id="selecCardnewStatusRetryA"  value="" />
									</div>
									
									
									<div class="form-group">
										Compté à débiter <input type="text" readonly
											name="debitA" class="form-control" id="selecCompteDebitARetryA"  value="" />
									</div>
									
									<div class="form-group">
										Compte à créditer <input type="text" readonly
											name="creditA" class="form-control" id="selecCompteCreditARetryA"  value="" />
									</div>
									<div class="form-group">
										Montant <input type="text" readonly
											name="montantTxnA" class="form-control" id="selecTxnAmountARetryA"  value="" />
									</div>
									<div class="form-group">
										Devise <input type="text" readonly
											name="currency" class="form-control" id="selecTxnDeviseRetryA"  value="" />
									</div>
									
								</div>
								<div class="modal-footer">
									<div class="spinner-border"></div>
									<button type="button" class="btn btn-default btn-sm"
										data-dismiss="modal">
										Annuler
									</button>
									<button type="submit" id="changeStatusModalButton"
									 class="btn btn-primary btn-sm">
										Confirmer
									</button>
									
								</div>
							</div>
						</div>
					</div>
					<sec:csrfInput /> 
				</form:form>
				
				
				
				
				
				
				<!-- Modal CONFIRMATION REESSAIE DEUXIEME ECRITURE AMPLITUDE -->
				<form:form action="${pageContext.request.contextPath}/retryTxnAmplitudeB"
					modelAttribute="cardCancelInfo" enctype="multipart/form-data" method="POST"
					id="cardInfoform">
					
				
					<!-- Modal -->
					<div class="modal fade" id="approvemodalCardWithProvisionAmplitudeBRetry" tabindex="-1"
						role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="modalLabel">
										CONFIRMATION REESSAIE DEUXIEME ECRITURE AMPLITUDE
									</h4>
									 <h5 class ="text-danger text-center"><spring:message code="comment.care.changeCardStatus" /></h5>
									  
								</div> 
								<div class="modal-body">
								
									<div class="form-group">
										Nom client <input type="text" readonly
											 class="form-control" id="selecCustomerName2RetryB"  value="" />
									</div>
									
									
									<div class="form-group">
										<input type="hidden" readonly
											name="codeAgenceDebitB" class="form-control" id="selecCodeAgenceDebitB2RetryB" value="" required/>
									</div>
									<div class="form-group">
										<input type="hidden" readonly
											name="codeAgenceCreditB" class="form-control" id="selecCodeAgenceCreditB2RetryB" value="" required/>
									</div>
									
									<div class="form-group">
										<input type="hidden" readonly
											name="customerNumber" class="form-control" id="selecCustomerNumber2RetryB" value="" required/>
									</div>
								
									<div class="form-group">
										<input type="hidden" readonly
											name="reqCancelId" class="form-control" id="selecReqCancelId2RetryB" value="" required/>
									</div>
									
									<div class="form-group"> 
										<input type="hidden" readonly
											name="cardOldStatus" class="form-control" id="selecCardOldStatus2RetryB" value="" required/>
									</div>
									
									
									<div class="form-group">
										Numéro carte <input type="text" readonly
											name="custCardNumber" class="form-control" id="selecCardNumber2RetryB"  value="" />
									</div>
									<div class="form-group">
										Nouveau status carte <input type="text" readonly
											name="cardStatus" class="form-control" id="selecCardnewStatus2RetryB"  value="" />
									</div>
									
									
									<hr>
									<h3>*********</h3>
									<div class="form-group">
										Compté à débiter <input type="text" readonly
											name="debitB" class="form-control" id="selecCompteDebitB2RetryB"  value="" />
									</div>
									<div class="form-group">
										Compte à créditer <input type="text" readonly
											name="creditB" class="form-control" id="selecCompteCreditB2RetryB"  value="" />
									</div>
									<div class="form-group">
										Montant <input type="text" readonly
											name="montantTxnB" class="form-control" id="selecTxnAmountB2RetryB"  value="" />
									</div>
									
									
									<div class="form-group">
										Devise <input type="text" readonly
											name="currency" class="form-control" id="selecTxnDevise2RetryB"  value="" />
									</div>
									
									
									
								</div>
								<div class="modal-footer">
									<div class="spinner-border"></div>
									<button type="button" class="btn btn-default btn-sm"
										data-dismiss="modal">
										Annuler
									</button>
									<button type="submit" id="changeStatusModalButton"
									 class="btn btn-primary btn-sm">
										Confirmer
									</button>
									
								</div>
							</div>
						</div>
					</div>
					<sec:csrfInput /> 
				</form:form>
				
				
				
				
				<!-- Modal CONFIRMATION REESSAIE PREMIERE et DEUXIEME ECRITURE AMPLITUDE -->
				<form:form action="${pageContext.request.contextPath}/retryTxnAmplitudeAB"
					modelAttribute="cardCancelInfo" enctype="multipart/form-data" method="POST"
					id="cardInfoform">
					
				
					<!-- Modal -->
					<div class="modal fade" id="approvemodalCardWithProvisionAmplitudeABRetry" tabindex="-1"
						role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="modalLabel">
										CONFIRMATION REESSAIE PRIMIERE ET DEUXIEME ECRITURE AMPLITUDE
									</h4>
									 <h5 class ="text-danger text-center"><spring:message code="comment.care.changeCardStatus" /></h5>
									  
								</div> 
								<div class="modal-body">
								
									<div class="form-group">
										Nom client <input type="text" readonly
											 class="form-control" id="selecCustomerName2RetryAB"  value="" />
									</div>
									
									
									
									<div class="form-group">
										<input type="hidden" readonly
											name="codeAgenceDebitA" class="form-control" id="selecCodeAgenceDebitA2RetryAB" value="" required/>
									</div>
									<div class="form-group">
										<input type="hidden" readonly
											name="codeAgenceCreditA" class="form-control" id="selecCodeAgenceCreditA2RetryAB" value="" required/>
									</div>
									
									<div class="form-group">
										<input type="hidden" readonly
											name="codeAgenceDebitB" class="form-control" id="selecCodeAgenceDebitB2RetryAB" value="" required/>
									</div>
									<div class="form-group">
										<input type="hidden" readonly
											name="codeAgenceCreditB" class="form-control" id="selecCodeAgenceCreditB2RetryAB" value="" required/>
									</div>
									
									
									
									
									<div class="form-group">
										<input type="hidden" readonly
											name="customerNumber" class="form-control" id="selecCustomerNumber2RetryAB" value="" required/>
									</div>
								
									<div class="form-group">
										<input type="hidden" readonly
											name="reqCancelId" class="form-control" id="selecReqCancelId2RetryAB" value="" required/>
									</div>
									
									
									<div class="form-group">
										Numéro carte <input type="text" readonly
											name="custCardNumber" class="form-control" id="selecCardNumber2RetryAB"  value="" />
									</div>
									
									
									
									<hr>
									<h3>PREMIERE ECRITURE COMPTABLE</h3>
									<div class="form-group">
										Compté à débiter <input type="text" readonly
											name="debitA" class="form-control" id="selecCompteDebitA2RetryAB"  value="" />
									</div>
									<div class="form-group">
										Compte à créditer <input type="text" readonly
											name="creditA" class="form-control" id="selecCompteCreditA2RetryAB"  value="" />
									</div>
									<div class="form-group">
										Montant <input type="text" readonly
											name="montantTxnA" class="form-control" id="selecTxnAmountA2RetryAB"  value="" />
									</div>
									
									
									<hr>
									<h3>DEUXIEME ECRITURE COMPTABLE</h3>
									<div class="form-group">
										Compté à débiter <input type="text" readonly
											name="debitB" class="form-control" id="selecCompteDebitB2RetryAB"  value="" />
									</div>
									<div class="form-group">
										Compte à créditer <input type="text" readonly
											name="creditB" class="form-control" id="selecCompteCreditB2RetryAB"  value="" />
									</div>
									<div class="form-group">
										Montant <input type="text" readonly
											name="montantTxnB" class="form-control" id="selecTxnAmountB2RetryAB"  value="" />
									</div>
									
									
									<div class="form-group">
										Devise <input type="text" readonly
											name="currency" class="form-control" id="selecTxnDevise2RetryAB"  value="" />
									</div>
									
									
								</div>
								<div class="modal-footer">
									<div class="spinner-border"></div>
									<button type="button" class="btn btn-default btn-sm"
										data-dismiss="modal">
										Annuler
									</button>
									<button type="submit" id="changeStatusModalButton"
									 class="btn btn-primary btn-sm">
										Confirmer
									</button>
									
								</div>
							</div>
						</div>
					</div>
					<sec:csrfInput /> 
				</form:form>
				
				
				
				
				
				
			</c:forEach>
			

		</div>
	</div>
</div>


<script>

//sortTable
$('#sortTable').DataTable({
	destroy: true,
	paging: true,
	lengthMenu: [7],
	order: [[0, "desc"]],
	ordering: false,
	language: {
		"decimal": "",
		"emptyTable": "Pas de données disponibles",
		"info": "Lignes _START_ à _END_ sur _TOTAL_ ",
		"infoEmpty": "Aucune donnée disponible",
		"infoFiltered": "",
		"infoPostFix": "",
		"thousands": ",",
		"lengthMenu": "Afficher  _MENU_  lignes",
		"loadingRecords": "Chargement...",
		"processing": "Traitement en cours ...",
		"search": "Chercher:",
		"zeroRecords": "Aucune donnée disponible",
		"paginate": {
			"first": "Premier",
			"last": "Dernier",
			"next": "Suivant",
			"previous": "Précédent"
		},
		"aria": {
			"sortAscending": ": Trier par ordre croissant",
			"sortDescending": ": Trier par ordre décroissant"
		}
	}
});


	/*
	$("#manageMessage").on('click', function (e){
		// e.preventDefault(); 
		 //alert("Hey");
		// $("#messageCover").show();
		 $('#messageCover').css('display', 'inline'); 
	});
	*/
	
	
	

	$(function() {

	  $("#dialog").dialog({
	     autoOpen: false,
	     modal: true
	   });

	  $("#myButton").on("click", function(e) {
	      e.preventDefault();
	      $("#dialog").dialog("open");
	  });

	});
	
	
	
	
	
	
	
	
	
	
	 $(".approveBtn").on("click", function(e) {
	    
		 //card without details approve process start 
		 var selectedCustomerNumber = $(this).parents("tr").find(".customerNumber").text(); 
		 var selectedCustomerName = $(this).parents("tr").find(".customerName").text();
	     var selectedCardNumber = $(this).parents("tr").find(".cardNumber").text(); 
	     var selectedCardnewStatus = $(this).parents("tr").find(".cardnewStatus").text(); 
	     var selectedReqCancelId = $(this).parents("tr").find(".reqCancelId").text(); 
	     var selectedCardOldStatus = $(this).parents("tr").find(".cardOldStatus").text(); 
	     var selectedCompteDebitA = $(this).parents("tr").find(".compteDebitA").text(); 
	     var selectedCompteCreditA = $(this).parents("tr").find(".compteCreditA").text(); 
	     var selectedTxnAmountA = $(this).parents("tr").find(".txnAmountA").text(); 
	     var selectedTxnDevise = $(this).parents("tr").find(".txnDevise").text(); 
	     var selectedCodeAgenceDebitA = $(this).parents("tr").find(".codeAgenceDebitA").text(); 
	     var selectedCodeAgenceCreditA = $(this).parents("tr").find(".codeAgenceCreditA").text(); 
	     

	     $("#selecCustomerNumber").val(selectedCustomerNumber);
	     $("#selecCustomerName").val(selectedCustomerName);
	     $("#selecCardNumber").val(selectedCardNumber);
	     $("#selecCardnewStatus").val(selectedCardnewStatus);
	     $("#selecReqCancelId").val(selectedReqCancelId);
	     $("#selecCardOldStatus").val(selectedCardOldStatus);
	     $("#selecCompteDebitA").val(selectedCompteDebitA);
	     $("#selecCompteCreditA").val(selectedCompteCreditA);
	     $("#selecTxnAmountA").val(selectedTxnAmountA);
	     $("#selecTxnDevise").val(selectedTxnDevise);
	     $("#selecCodeAgenceDebitA").val(selectedCodeAgenceDebitA);
	     $("#selecCodeAgenceCreditA").val(selectedCodeAgenceCreditA);
	     
	     
	  
	     
	     //card with details  approve process start
	     var selectedCustomerNumber2 = $(this).parents("tr").find(".customerNumber").text(); 
	     var selectedCustomerName2 = $(this).parents("tr").find(".customerName").text();
	     var selectedCardNumber2 = $(this).parents("tr").find(".cardNumber").text();
	     var selectedCardnewStatus2 = $(this).parents("tr").find(".cardnewStatus").text(); 
	     var selectedReqCancelId2 = $(this).parents("tr").find(".reqCancelId").text(); 
	     var selectedCardOldStatus2 = $(this).parents("tr").find(".cardOldStatus").text(); 
	     var selectedCompteDebitA2 = $(this).parents("tr").find(".compteDebitA").text(); 
	     var selectedCompteCreditA2 = $(this).parents("tr").find(".compteCreditA").text(); 
	     var selectedTxnAmountA2 = $(this).parents("tr").find(".txnAmountA").text(); 
	     var selectedTxnDevise2 = $(this).parents("tr").find(".txnDevise").text(); 
	     var selectedCompteDebitB2 = $(this).parents("tr").find(".compteDebitB").text(); 
	     var selectedCompteCreditB2 = $(this).parents("tr").find(".compteCreditB").text(); 
	     var selectedTxnAmountB2 = $(this).parents("tr").find(".txnAmountB").text(); 
	     var selectedCodeAgenceDebitA2 = $(this).parents("tr").find(".codeAgenceDebitA").text(); 
	     var selectedCodeAgenceCreditA2 = $(this).parents("tr").find(".codeAgenceCreditA").text(); 
	     var selectedCodeAgenceDebitB2 = $(this).parents("tr").find(".codeAgenceDebitB").text(); 
	     var selectedCodeAgenceCreditB2 = $(this).parents("tr").find(".codeAgenceCreditB").text(); 
	     
	     
	     $("#selecCustomerNumber2").val(selectedCustomerNumber2);
	     $("#selecCustomerName2").val(selectedCustomerName2);
	     $("#selecCardNumber2").val(selectedCardNumber2);
	     $("#selecCardnewStatus2").val(selectedCardnewStatus2);
	     $("#selecReqCancelId2").val(selectedReqCancelId2);
	     $("#selecCardOldStatus2").val(selectedCardOldStatus2);
	     $("#selecCompteDebitA2").val(selectedCompteDebitA2);
	     $("#selecCompteCreditA2").val(selectedCompteCreditA2);
	     $("#selecTxnAmountA2").val(selectedTxnAmountA2);
	     $("#selecTxnDevise2").val(selectedTxnDevise2);
	     $("#selecCompteDebitB2").val(selectedCompteDebitB2);
	     $("#selecCompteCreditB2").val(selectedCompteCreditB2);
	     $("#selecTxnAmountB2").val(selectedTxnAmountB2);
	     $("#selecCodeAgenceDebitA2").val(selectedCodeAgenceDebitA2);
	     $("#selecCodeAgenceCreditA2").val(selectedCodeAgenceCreditA2);
	     $("#selecCodeAgenceDebitB2").val(selectedCodeAgenceDebitB2);
	     $("#selecCodeAgenceCreditB2").val(selectedCodeAgenceCreditB2);
	     
	     
	     
	     
	     //RetryA
	     var selectedCustomerNumberRetryA = $(this).parents("tr").find(".customerNumber").text(); 
		 var selectedCustomerNameRetryA = $(this).parents("tr").find(".customerName").text();
	     var selectedCardNumberRetryA = $(this).parents("tr").find(".cardNumber").text(); 
	     var selectedCardnewStatusRetryA = $(this).parents("tr").find(".cardnewStatus").text(); 
	     var selectedReqCancelIdRetryA = $(this).parents("tr").find(".reqCancelId").text(); 
	     var selectedCardOldStatusRetryA = $(this).parents("tr").find(".cardOldStatus").text(); 
	     var selectedCompteDebitARetryA = $(this).parents("tr").find(".compteDebitA").text(); 
	     var selectedCompteCreditARetryA = $(this).parents("tr").find(".compteCreditA").text(); 
	     var selectedTxnAmountARetryA = $(this).parents("tr").find(".txnAmountA").text(); 
	     var selectedTxnDeviseRetryA = $(this).parents("tr").find(".txnDevise").text(); 
	     var selectedCodeAgenceDebitARetryA = $(this).parents("tr").find(".codeAgenceDebitA").text(); 
	     var selectedCodeAgenceCreditARetryA = $(this).parents("tr").find(".codeAgenceCreditA").text(); 
	     
	     
	     
	     $("#selecCustomerNumberRetryA").val(selectedCustomerNumberRetryA);
	     $("#selecCustomerNameRetryA").val(selectedCustomerNameRetryA);
	     $("#selecCardNumberRetryA").val(selectedCardNumberRetryA);
	     $("#selecCardnewStatusRetryA").val(selectedCardnewStatusRetryA);
	     $("#selecReqCancelIdRetryA").val(selectedReqCancelIdRetryA);
	     $("#selecCardOldStatusRetryA").val(selectedCardOldStatusRetryA);
	     $("#selecCompteDebitARetryA").val(selectedCompteDebitARetryA);
	     $("#selecCompteCreditARetryA").val(selectedCompteCreditARetryA);
	     $("#selecTxnAmountARetryA").val(selectedTxnAmountARetryA);
	     $("#selecTxnDeviseRetryA").val(selectedTxnDeviseRetryA);
	     $("#selecCodeAgenceDebitARetryA").val(selectedCodeAgenceDebitARetryA);
	     $("#selecCodeAgenceCreditARetryA").val(selectedCodeAgenceCreditARetryA);
	     
	     
	     //RetryB
	     var selectedCustomerNumber2RetryB = $(this).parents("tr").find(".customerNumber").text(); 
	     var selectedCustomerName2RetryB = $(this).parents("tr").find(".customerName").text();
	     var selectedCardNumber2RetryB = $(this).parents("tr").find(".cardNumber").text();
	     var selectedCardnewStatus2RetryB = $(this).parents("tr").find(".cardnewStatus").text(); 
	     var selectedReqCancelId2RetryB = $(this).parents("tr").find(".reqCancelId").text(); 
	     var selectedCardOldStatus2RetryB = $(this).parents("tr").find(".cardOldStatus").text(); 
	     var selectedTxnDevise2RetryB = $(this).parents("tr").find(".txnDevise").text(); 
	     var selectedCompteDebitB2RetryB = $(this).parents("tr").find(".compteDebitB").text(); 
	     var selectedCompteCreditB2RetryB = $(this).parents("tr").find(".compteCreditB").text(); 
	     var selectedTxnAmountB2RetryB = $(this).parents("tr").find(".txnAmountB").text(); 
	     var selectedCodeAgenceDebitB2RetryB = $(this).parents("tr").find(".codeAgenceDebitB").text(); 
	     var selectedCodeAgenceCreditB2RetryB = $(this).parents("tr").find(".codeAgenceCreditB").text(); 
	     
	     
	     $("#selecCustomerNumber2RetryB").val(selectedCustomerNumber2RetryB);
	     $("#selecCustomerName2RetryB").val(selectedCustomerName2RetryB);
	     $("#selecCardNumber2RetryB").val(selectedCardNumber2RetryB);
	     $("#selecCardnewStatus2RetryB").val(selectedCardnewStatus2RetryB);
	     $("#selecReqCancelId2RetryB").val(selectedReqCancelId2RetryB);
	     $("#selecCardOldStatus2RetryB").val(selectedCardOldStatus2RetryB);
	     $("#selecTxnDevise2RetryB").val(selectedTxnDevise2RetryB);
	     $("#selecCompteDebitB2RetryB").val(selectedCompteDebitB2RetryB);
	     $("#selecCompteCreditB2RetryB").val(selectedCompteCreditB2RetryB);
	     $("#selecTxnAmountB2RetryB").val(selectedTxnAmountB2RetryB);
	     $("#selecCodeAgenceDebitB2RetryB").val(selectedCodeAgenceDebitB2RetryB);
	     $("#selecCodeAgenceCreditB2RetryB").val(selectedCodeAgenceCreditB2RetryB);
	     
	     
	     
	   //RetryAB
	     var selectedCustomerNumber2RetryAB = $(this).parents("tr").find(".customerNumber").text(); 
	     var selectedCustomerName2RetryAB = $(this).parents("tr").find(".customerName").text();
	     var selectedCardNumber2RetryAB = $(this).parents("tr").find(".cardNumber").text();
	     var selectedCardnewStatus2RetryAB = $(this).parents("tr").find(".cardnewStatus").text(); 
	     var selectedReqCancelId2RetryAB = $(this).parents("tr").find(".reqCancelId").text(); 
	     var selectedCardOldStatus2RetryAB = $(this).parents("tr").find(".cardOldStatus").text(); 
	     var selectedCompteDebitA2RetryAB = $(this).parents("tr").find(".compteDebitA").text(); 
	     var selectedCompteCreditA2RetryAB = $(this).parents("tr").find(".compteCreditA").text(); 
	     var selectedTxnAmountA2RetryAB = $(this).parents("tr").find(".txnAmountA").text(); 
	     var selectedTxnDevise2RetryAB = $(this).parents("tr").find(".txnDevise").text(); 
	     var selectedCompteDebitB2RetryAB = $(this).parents("tr").find(".compteDebitB").text(); 
	     var selectedCompteCreditB2RetryAB = $(this).parents("tr").find(".compteCreditB").text(); 
	     var selectedTxnAmountB2RetryAB = $(this).parents("tr").find(".txnAmountB").text(); 
	     var selectedCodeAgenceDebitA2RetryAB = $(this).parents("tr").find(".codeAgenceDebitA").text(); 
	     var selectedCodeAgenceCreditA2RetryAB = $(this).parents("tr").find(".codeAgenceCreditA").text(); 
	     var selectedCodeAgenceDebitB2RetryAB = $(this).parents("tr").find(".codeAgenceDebitB").text(); 
	     var selectedCodeAgenceCreditB2RetryAB = $(this).parents("tr").find(".codeAgenceCreditB").text(); 
	     
	     
	     $("#selecCustomerNumber2RetryAB").val(selectedCustomerNumber2RetryAB);
	     $("#selecCustomerName2RetryAB").val(selectedCustomerName2RetryAB);
	     $("#selecCardNumber2RetryAB").val(selectedCardNumber2RetryAB);
	     $("#selecCardnewStatus2RetryAB").val(selectedCardnewStatus2RetryAB);
	     $("#selecReqCancelId2RetryAB").val(selectedReqCancelId2RetryAB);
	     $("#selecCardOldStatus2RetryAB").val(selectedCardOldStatus2RetryAB);
	     $("#selecCompteDebitA2RetryAB").val(selectedCompteDebitA2RetryAB);
	     $("#selecCompteCreditA2RetryAB").val(selectedCompteCreditA2RetryAB);
	     $("#selecTxnAmountA2RetryAB").val(selectedTxnAmountA2RetryAB);
	     $("#selecTxnDevise2RetryAB").val(selectedTxnDevise2RetryAB);
	     $("#selecCompteDebitB2RetryAB").val(selectedCompteDebitB2RetryAB);
	     $("#selecCompteCreditB2RetryAB").val(selectedCompteCreditB2RetryAB);
	     $("#selecTxnAmountB2RetryAB").val(selectedTxnAmountB2RetryAB);
	     $("#selecCodeAgenceDebitA2RetryAB").val(selectedCodeAgenceDebitA2RetryAB);
	     $("#selecCodeAgenceCreditA2RetryAB").val(selectedCodeAgenceCreditA2RetryAB);
	     $("#selecCodeAgenceDebitB2RetryAB").val(selectedCodeAgenceDebitB2RetryAB);
	     $("#selecCodeAgenceCreditB2RetryAB").val(selectedCodeAgenceCreditB2RetryAB);
	     
	     
	     
	  });
	
	 
	 
	 
	
	 $(".rejectBtn").on("click", function(e) {
		 
		//Rejec card without provision
		 var selectedCustomerNameReject = $(this).parents("tr").find(".customerName").text();
	     var selectedCardNumberReject = $(this).parents("tr").find(".cardNumber").text(); 
	     var selectedReqCancelIdReject = $(this).parents("tr").find(".reqCancelId").text(); 

	     $("#selecCustomerNameReject").val(selectedCustomerNameReject);
	     $("#selecCardNumberReject").val(selectedCardNumberReject);
	     $("#selecReqCancelIdReject").val(selectedReqCancelIdReject);
	     
	     
	   //Rejec card with provision
		 var selectedCustomerName2Reject = $(this).parents("tr").find(".customerName").text();
	     var selectedCardNumber2Reject = $(this).parents("tr").find(".cardNumber").text(); 
	     var selectedReqCancelId2Reject = $(this).parents("tr").find(".reqCancelId").text(); 

	     $("#selecCustomerName2Reject").val(selectedCustomerName2Reject);
	     $("#selecCardNumber2Reject").val(selectedCardNumber2Reject);
	     $("#selecReqCancelId2Reject").val(selectedReqCancelId2Reject);
	
		 
	 });
	
	
	

</script>

<%@ include file="common/footer.jsp"%>