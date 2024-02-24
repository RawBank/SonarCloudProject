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



<%-- This is a JSP comment 
<%@ include file="modals/viewCardDetails.jsp"%>
<%@ include file="modals/changeStatus.jsp"%>
<%@ include file="modals/changeLimit.jsp"%>
<%@ include file="modals/viewCardTransactions.jsp"%>
<%@ include file="modals/changeCardDetails.jsp"%>
--%>


<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!-- 
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

-->

<!-- 
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

-->

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- <link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">-->




<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/datatables.min.css" rel="stylesheet">

<script src="${pageContext.request.contextPath}/static/scripts/datatables.min.js"></script>


<div class="">
	<div class="">
		<div class="">
			
		</div>
		<div class="panel-body">
			

				<div class="table-responsive">
					<div class="table-wrapper w-100">
						<table class="table table-hover table-bordered border-primary" id="historicCancel">
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
									
									<!-- This is used to match columns of DataTable -->
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
										
										
										
										
										<c:choose>
										    <c:when test="${cancelCard.cancelEventStatus == 'RP'}">
										        <td class="cancelEventStatus success">
													SUCCES
												</td>
										    </c:when>  
										    <c:when test="${cancelCard.cancelEventStatus == 'RR'}">
										        <td class="cancelEventStatus danger">
													REJETER
												</td>
										    </c:when> 
										    <c:when test="${cancelCard.cancelEventStatus == 'RI'}">
										        <td class="cancelEventStatus danger">
													INITIE
												</td>
										    </c:when>   
										    <c:when test="${cancelCard.cancelEventStatus == 'PAA'}">
										        <td class="cancelEventStatus warning">
													RETESTER A
												</td>
										    </c:when>
										    
										    <c:when test="${cancelCard.cancelEventStatus == 'PAB'}">
										        <td class="cancelEventStatus warning">
													RETESTER B
												</td>
										    </c:when>  
										    <c:when test="${cancelCard.cancelEventStatus == 'PAAB'}">
										        <td class="cancelEventStatus warning">
													RETESTER A-B
												</td>
										    </c:when>   
										  
										</c:choose>
										
										
								
												
												
												
												
												
										<c:choose>
										    <c:when test="${cancelCard.typeAnnulationCarte == 'CSP'}">
										         <td class="typeAnnulationCarte"> Carte Sans Provision</td>
										    </c:when>  
										    <c:when test="${cancelCard.typeAnnulationCarte == 'CAP'}">
										        <td class="typeAnnulationCarte"> Carte Avec Provision</td>
										    </c:when>   
										</c:choose>
										
										
										<td hidden="true" class="reqCancelId">${cancelCard.reqCancelId}</td>
										<td hidden="true" class="cardOldStatus">${cancelCard.cardOldStatus}</td>
										<td hidden="true" class="txnDevise">${cancelCard.txnDevise}</td>
										
										<td hidden="true" class="codeAgenceDebitA">${cancelCard.agenceCompteDebitA}</td>
										<td hidden="true" class="codeAgenceCreditA">${cancelCard.agenceCompteCreditA}</td>
										<td hidden="true" class="codeAgenceDebitB">${cancelCard.agenceCompteDebitB}</td>
										<td hidden="true" class="codeAgenceCreditB">${cancelCard.agenceCompteCreditB}</td>
										
										

									</tr>
								</c:forEach>
								
							</tbody>
						</table>
					</div>
				</div>
				
				
		</div>
	</div>
</div>


<script>

$(document).ready(function () {
	
	
	$('#historicCancel').DataTable({
		destroy: true,
		paging: true,
		lengthMenu: [10,15],
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
	$('#link_table_mpgs').DataTable({
		destroy: true,
		paging: true,
		lengthMenu: [10, 20],
		order: [[0, "desc"]],
		columnDefs: [{ 'targets': 0, type: 'date-eu' }],
		"data": dataResponse,
		columns: [
			{ data: 'nomClient' },
			{ data: 'visacaseId' },
			{ data: 'montantVisa' },
			{ data: 'currency' },
			{ data: 'merchant' },
			{ data: 'orderId' },
			{ data: 'gateWayCode' },
			{ data: 'createdOn' }
			
		],
		dom: 'Bfrtip',
		searching: true,
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
	
	*/
});


</script>

<%@ include file="common/footer.jsp"%>