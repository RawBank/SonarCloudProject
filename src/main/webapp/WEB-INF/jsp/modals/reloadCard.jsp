<!-- 
 Copyright 2022 Rawbank. All Rights Reserved.
 
 Change Section:
 Programmer				Date
 Krishna				8/8/2022
 Initial Coding.
 
 Created By:
 @author krishna
 @since August 8, 2022
-->
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script  src="${pageContext.request.contextPath}/static/scripts/jquery-3.5.1.js"></script>

<%-- <c:if test="${message != null }"> --%>
<%-- 				<p class="p-3 mb-2 bg-danger text-white">${message}</p> --%>
<%-- </c:if> --%>
<form:form action="${pageContext.request.contextPath}/reloadCard"
	modelAttribute="reloaddetails" enctype="multipart/form-data" method="POST"
	id="reloaddetailsform">
 
	<!-- Modal -->
	<div class="modal fade" id="reloadModal" tabindex="-1" role="dialog"
		aria-labelledby="modalLabel" aria-hidden="true">
		<div class="modal-dialog modal-02">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="modalLabel">
						<spring:message code="modal.title.reloadCard" />
					</h4>
				</div>
				<div class="modal-body">
					<div class="erroMessage"></div>
					<div class="commentreloadCardModal text-center"></div>
					<div class="row d-none" id="reloadData">
						<div class="col-md-5 col-lg-5 col-sm-12 col-md-offset-0">
							<legend> Details de la carte </legend>
							
							<form:input type="text" readonly="true" name="cscAccountNumber" class="form-control d-none" id="cscAccountNumber" 
							 path ="cscAccountNumber"/>
							<div class="form-group">
								<label for="customerNumber"><spring:message
										code="label.customerNumber" /></label> <form:input type="text" readonly="true"
									name="custNumber" class="form-control" id="custNumber"  path ="clientNumber"/>
							</div>
							<div class="form-group">
								<label for="customerName"><spring:message
										code="label.customerName" /></label> <form:input type="text" readonly="true"
									name="customerName" class="form-control" id="custName" path ="clientName"/>
							</div>
							<div class="form-group d-none">
								<label for="custCardNumber"><spring:message
										code="label.cardNumber" /></label> <form:input type="text" readonly="true"
									name="custCardNumber" class="form-control" id="cardNumber" path ="cardNumber" />
							</div>
							
							<div class="form-group">
								<label for="cardNumberMasked"><spring:message
										code="label.cardNumberMasked" /></label> <input type="text" readonly="true"
									name="cardNumberMasked" class="form-control" id="cardNumberMasked" />
							</div>
							<div class="form-group">
								<label for="currentCardStatus"><spring:message
										code="label.currentCardStatus" /></label> <form:input type="text"
									name="currentCardStatus" class="form-control"
									id="currentCardStatus" value="" readonly="true" path ="cardStatus"  />
							</div>
							<div class="form-group">
								<label for="expiryDate"><spring:message
										code="label.expiryDate" /></label> <form:input type="text"
									name="expiryDateCard" class="form-control" id="expiryDateCard"
									value="" readonly="true" path ="cardExpiredDate" />
							</div>

							<div class="form-group">
								<label for="loadReason"><spring:message
										code="label.reason" /></label>
								<form:textarea class="form-control inputValue" placeholder="Reason"
									name="loadReason" id="loadReason" required="required" path ="reloadComment" maxlength="100"></form:textarea>
							</div>
							<div class="form-group">
								<label for="ackReceiptAttachment"><spring:message
										code="label.attachment" /> [MAX FILE SIZE = 2.5MB]</label> <input type="file"
									class="form-control-file" name="file" id="ackReceiptAttachment" required="required" onchange="validate(this, 'erroMessage', 'reloadModalButton')"  />
							</div>

						</div>
						<div class="seperator"></div>
						<div class="col-md-7 col-lg-7 col-sm-12 col-md-offset-0">
							<legend> Balance de la carte </legend>
							<div class="form-group form-group-sm ">
								<label class="col-md-12 control-label">Max à payer/
									Montant à payer<span class="text-red">*</span>
								</label>
								<div class="col-md-4 p-0">
									<div class="input-group col-md-12">
										<form:input type="number" class="form-control input-show-info"
											id="amountDue" name="amountDue" placeholder="Max à payer"
											title="Montant maximal possible" readonly ="true" path="amountDue" />
										<div class="input-group-addon currency-addon d-none"></div>
									</div>
								</div>
								<div class="col-md-4 p-0 mb-1">
									<div class="input-group col-md-12">
										<form:input type="number" min="0" step="0.1"
											class="form-control bg-white  inputValue"
											id="paidAmount" 	name="paidAmount" placeholder="Montant à payer" value=""
											title="Montant que vous desirez payer" required ="required"  path ="paidAmount"/>
										<div class="input-group-addon currency-addon d-none"></div>
									</div>
								</div>
								<div class="col-md-4 mb-1">
									<form:input type="text" class="form-control input-show-info"
										id="currency" name="currency" placeholder="Devise"
										title="Devise de la carte" readonly="true"  path="currency"/>
								</div>
							</div>
							<div class="form-group mt-1">
								<label class="col-md-12 contr l-label" for="availableAmount"><spring:message
										code="label.availableAmount" /></label>
								<div class="input-group col-md-12 mb-1">
									<form:input class="form-control input-show-info" id="availableAmount" name="availableAmount"
									 placeholder="Montant disponible" required="required" readonly="true" path ="balance"/>
									<div class="input-group-addon currency-addon border-thin-addon"></div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-12 control-label" for="limitBalance"><spring:message
										code="label.limitBalance" /></label>
								<div class="input-group col-md-12 mb-1">
									<form:input class="form-control input-show-info" id="limitAmount"
										name="limitAmount" placeholder="Limite de la carte" required ="required"
										readonly="true" path="limitBalance"/>
									<div class="input-group-addon currency-addon border-thin-addon"></div>
								</div>
                            </div>

							<div class="form-group">
								<label class="col-md-12 control-label" for="pendingAmount"><spring:message
										code="label.pendingAmount" /></label>
								<div class="input-group col-md-12 mb-1">
									<form:input class="form-control input-show-info "
										id="pendingAmount" name="pendingAmount"
										placeholder=" Montant en attente de validation" required ="required"
										readonly="true" path="pendingAmount" />
									<div class="input-group-addon currency-addon border-thin-addon"></div>
								</div>

						   </div>

							<div class="form-group">
								<label for="custAccountList"><spring:message
										code="label.custAccountList" /></label> <form:select
									id="custAccountList" name="custAccountList"
									class="form-control" required="required" onchange="onselectAccountList()" path ="custAccount">
									<form:option value="-1"><spring:message
											code="label.select.accountList" /></form:option>
								</form:select>
							</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="spinner-border"></div>
							<button type="button" class="btn btn-default btn-sm"
								data-dismiss="modal">
								<spring:message code="label.close" />
							</button>
							<button type="submit" id="reloadModalButton"
								class="btn-warning btn-sm">
								<spring:message code="label.send" />
							</button>
						</div>
					</div>
				</div>
			</div>	
<sec:csrfInput />			
</form:form>



<form:form action="${pageContext.request.contextPath}/reloadCardMCindividuel"
	modelAttribute="reloaddetails" enctype="multipart/form-data" method="POST"
	id="reloaddetailsform">
 
	<!-- Modal -->
	<div class="modal fade" id="reloadModalMcPrepaidIndividual" tabindex="-1" role="dialog"
			aria-labelledby="modalLabel" aria-hidden="true">
			<div class="modal-dialog modal-02">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="modalLabel">
							Recharge MC prépayée individuelle
						</h4>
					</div>
					<div class="modal-body">
						<div class="erroMessage"></div>
						<div class="commentreloadCardModal text-center"></div>
						<div class="row d-block" id="reloadDataMCindividuel">
							<div class="col-md-5 col-lg-5 col-sm-12 col-md-offset-0">
								<legend> Détails Recharge MC prépayée individuelle </legend>
								
								<form:input type="text" readonly="true" name="cscAccountNumber" class="form-control d-none" id="cscAccountNumber" 
								 path ="cscAccountNumber"/>
								<div class="form-group">
									<b>Numéro CSC</b>
									<form:input type="text" readonly="true"
										name="custNumber" class="form-control" id="custNumber"  path ="clientNumber"/>
								</div>
								<div class="form-group">
									<label for="customerName"><spring:message
											code="label.customerName" /></label> <form:input type="text" readonly="true"
										name="customerName" class="form-control" id="custName" path ="clientName"/>
								</div>
								<div class="form-group d-none">
									<label for="custCardNumber"><spring:message
											code="label.cardNumber" /></label> <form:input type="text" readonly="true"
										name="custCardNumber" class="form-control" id="cardNumber" path ="cardNumber" />
								</div>
								
								<div class="form-group">
									<label for="cardNumberMasked"><spring:message
											code="label.cardNumberMasked" /></label> <input type="text" readonly="true"
										name="cardNumberMasked" class="form-control" id="cardNumberMasked" />
								</div>
								<div class="form-group">
									<label for="currentCardStatus"><spring:message
											code="label.currentCardStatus" /></label> <form:input type="text"
										name="currentCardStatus" class="form-control"
										id="currentCardStatus" value="" readonly="true" path ="cardStatus"  />
								</div>
								<div class="form-group">
									<label for="expiryDate"><spring:message
											code="label.expiryDate" /></label> <form:input type="text"
										name="expiryDateCard" class="form-control" id="expiryDateCard"
										value="" readonly="true" path ="cardExpiredDate" />
								</div>
	
								<div class="form-group">
									<label for="loadReason"><spring:message
											code="label.reason" /></label>
									<form:textarea class="form-control inputValue" placeholder="Reason"
										name="loadReason" id="loadReason" required="required" path ="reloadComment" maxlength="100"></form:textarea>
								</div>
								<div class="form-group">
									<label for="ackReceiptAttachment"><spring:message
											code="label.attachment" /> [MAX FILE SIZE = 2.5MB]</label> <input type="file"
										class="form-control-file" name="file" id="ackReceiptAttachment" required="required" onchange="validate(this, 'erroMessage', 'reloadModalButton')"  />
								</div>
	
							</div>
							<div class="seperator"></div>
							<div class="col-md-7 col-lg-7 col-sm-12 col-md-offset-0">
								<legend> Balance de la carte MC prépayée individuelle </legend>
								<div class="form-group form-group-sm ">
									<label class="col-md-12 control-label">Max à payer/
										Montant à payer<span class="text-red">*</span>
									</label>
									<div class="col-md-4 p-0">
										<div class="input-group col-md-12">
											<form:input type="number" class="form-control input-show-info"
												id="amountDue" name="amountDue" placeholder="Max à payer"
												title="Montant maximal possible" readonly ="true" path="amountDue" />
											<div class="input-group-addon currency-addon d-none"></div>
										</div>
									</div>
									<div class="col-md-4 p-0 mb-1">
										<div class="input-group col-md-12">
											<form:input type="number" min="0" step="0.1"
												class="form-control bg-white  inputValue"
												id="paidAmount" 	name="paidAmount" placeholder="Montant à payer" value=""
												title="Montant que vous desirez payer" required ="required"  path ="paidAmount"/>
											<div class="input-group-addon currency-addon d-none"></div>
										</div>
									</div>
									<div class="col-md-4 mb-1">
										<form:input type="text" class="form-control input-show-info"
											id="currency" name="currency" placeholder="Devise"
											title="Devise de la carte" readonly="true"  path="currency"/>
									</div>
								</div>
								<div class="form-group mt-1">
									<label class="col-md-12 contr l-label" for="availableAmount"><spring:message
											code="label.availableAmount" /></label>
									<div class="input-group col-md-12 mb-1">
										<form:input class="form-control input-show-info" id="availableAmount" name="availableAmount"
										 placeholder="Montant disponible" required="required" readonly="true" path ="balance"/>
										<div class="input-group-addon currency-addon border-thin-addon"></div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-12 control-label" for="limitBalance"><spring:message
											code="label.limitBalance" /></label>
									<div class="input-group col-md-12 mb-1">
										<form:input class="form-control input-show-info" id="limitAmount"
											name="limitAmount" placeholder="Limite de la carte" required ="required"
											readonly="true" path="limitBalance"/>
										<div class="input-group-addon currency-addon border-thin-addon"></div>
									</div>
	                            </div>
	
								<div class="form-group">
									<label class="col-md-12 control-label" for="pendingAmount"><spring:message
											code="label.pendingAmount" /></label>
									<div class="input-group col-md-12 mb-1">
										<form:input class="form-control input-show-info "
											id="pendingAmount" name="pendingAmount"
											placeholder=" Montant en attente de validation" required ="required"
											readonly="true" path="pendingAmount" />
										<div class="input-group-addon currency-addon border-thin-addon"></div>
									</div>
	
							   </div>
	
	

	
								
							   
							   
								
								<div class="form-group">
									<b>Compte à débiter avec code agence</b>
									<div class="input-group col-md-12 mb-1">
										<form:input class="form-control input-show-info "
											id="custAccount" name="custAccount"
											placeholder=" Compte à débiter" required ="required"
											path="custAccount" />
										
									</div>
	
							   </div>
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<div class="spinner-border"></div>
								<button type="button" class="btn btn-default btn-sm"
									data-dismiss="modal">
									<spring:message code="label.close" />
								</button>
								<button type="submit" id="reloadModalButton"
									class="btn-warning btn-sm">
									<spring:message code="label.send" />
								</button>
							</div>
						</div>
					</div>
				</div>
	<sec:csrfInput />			
	</form:form>










	











<script  type="text/javascript">
$("#reloaddetailsform").on('submit', function (e){

	 e.preventDefault();
//	 paidAmount
var  paidAmount  = $("#paidAmount").val().trim(); 
var  amountDue =  $("#amountDue").val().trim();
	  if(isNaN(paidAmount) || paidAmount ==="" ){
		  $(".erroMessage").empty().append("<p class ='alert alert-danger  p-0' > le montant saisi est  incorrecte </p>");
		  return  ;
	  }
	  
	  if(parseFloat(paidAmount)> parseFloat(amountDue)){
		  $(".erroMessage").empty().append("<p class ='alert alert-danger  p-0' > le montant  payé est supérieur au montant maximal </p>");
		  return ;
	  }
	  var  custAccount = $("#custAccountList option:selected").val();
	  var balance = parseFloat($("#custAccountList option:selected").text().split(":")[1]);
	  if(custAccount ==="-1" ){
		  $(".erroMessage").empty().append("<p class ='alert alert-danger  p-0' > veuillez choisir un compte pour effectuer cette opération </p>");
		  return ;
	  }
	  
	  if (parseFloat(balance)<parseFloat(paidAmount)){
		  $(".erroMessage").empty().append("<p class ='alert alert-danger  p-0' > le compte choisi à une balance inférieure au montant saisi </p>");
		  return ;
	  }
	  
	  e.currentTarget.submit();
})


  //validate(this.id, 'erroMessage', 'reloadModalButton')



</script>
