<!-- 
 Copyright 2022 Rawbank. All Rights Reserved.
 
 Change Section:
 Programmer				Date
 Krishna				Aug 15, 2022
 Initial Coding.
 
 Created By:
 @author krishna
 @since Aug 15, 2022
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<form:form action="${pageContext.request.contextPath}/approveCardOperation"
	modelAttribute="pendingCardInfo" enctype="multipart/form-data" method="POST"
	id="pendingCardInfoForm"  onSubmit="if(!confirm('Etes-vous sur de cette opération?')){return false;}">
	<!-- Modal -->

	<div class="modal fade" id="approveReloadModal" tabindex="-1"
		role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="modalLabel">
						<spring:message code="form.title.approvePendingCard" />
					</h4>
				</div>
				<div class="modal-body">
					<div class="commentModalchangestatus"></div>
					
					<div class="form-group  d-none">
						<label for=amount><spring:message
								code="label.amount" /></label> <form:input type="text" readonly ="true"
							name="amountNotFormat" class="form-control" id="amountNotFormat" path ="amount" />
					</div>
					<div class="form-group d-none">
						<label for="currency"><spring:message
								code="label.currency" /></label> <form:input type="text" readonly ="true"
							name="currencyNumber" class="form-control" id="currencyNumber"  path ="currency"/>
					</div>
					<div class="form-group d-none">
						<label for="expiryDate"><spring:message
								code="label.expiryDate" /></label> <form:input type="text" readonly ="true"
							name="expirydate" class="form-control" id="expirydate" path ="expiryDate" />
					</div>
					<div class="form-group d-none">
						<label for="institutionNumber"><spring:message
								code="label.institutionNumber" /></label> <form:input type="text" readonly ="true"
							name="institutionNumber" class="form-control" id="institutionNumber" path ="institutionNumber" />
					</div>
					
					<div class="form-group d-none">
						<label for="procCode"><spring:message
								code="label.procCode" /></label> <form:input type="text" readonly ="true"
							name="procCode" class="form-control" id="procCode" path ="proccode" />
					</div>
					<div class="form-group d-none">
						<label for="transactionType"><spring:message
								code="label.transactionType" /></label> <form:input type="text" readonly ="true"
							name="transactionType" class="form-control" id="transactionType"  path ="transactionType"/>
					</div>				
					<div class="form-group">
						<label for="reference"><spring:message
								code="label.reference" /></label> <form:input type="text" readonly="true"
							name="retrievalReference" class="form-control" id="retrievalReference" path ="retrievalReference" />
					</div>
					<div class="form-group">
						<label for="customerName"><spring:message
								code="label.customerName" /></label> <form:input type="text" readonly ="true"
							name="customerName" class="form-control" id="custName" path ="customerName" />
					</div>
					<div class="form-group">
						<label for="transamount"><spring:message
								code="label.amount" /></label> <input type="text" readonly
							name="transamount" class="form-control" id="transamount" />
					</div>
					<div class="form-group d-none"> 
						<label for="cardNumber"><spring:message
								code="label.cardNumber" /></label> <form:input type="text" 
							name="cardNumber" class="form-control" id="cardNumber" readonly ="true" path ="cardNumber" /> 
					</div>				
					<div class="form-group"> 
						<label for="cardNumberMasked"><spring:message
								code="label.cardNumberMasked" /></label> <input type="text" 
							name="cardNumberMasked" class="form-control" id="cardNumberMasked" readonly ="true"  /> 
					</div>
					<div class="form-group">
						<label for="aaSrcAccount"><spring:message code="label.aaSrcAccount" /></label> 
						<form:input type="text" readonly ="true" name="aaSrcAccount" class="form-control" id="aaSrcAccount" path ="aaSrcAccount" />
					</div> 
					<div class="form-group  d-none">
						<label for="aaDestAccount"><spring:message code="label.aaDestAccount" /></label> 
						<form:input type="text" readonly ="true" name="aaDestAccount" class="form-control" id="aaDestAccount" path ="aaDestAccount" />
					</div>
					<div class="form-group"> 
						<label for="changeReason"><spring:message
								code="label.commentApprov" /></label>
						<form:textarea class="form-control" placeholder="Comments" maxlength="100"
							name="commentApprov" id="commentApprov" required="required"   path ="commentApprov"></form:textarea>
					</div>					
				</div>
				<div class="modal-footer">
					<div class="spinner-border"></div>
					<button type="button" class="btn btn-default btn-sm"
						data-dismiss="modal">
						<spring:message code="label.close" />
					</button>
					<button type="submit" id="changeStatusModalButton" 
						class="btn btn-success btn-sm" name ="submit">
						<spring:message code="label.approve" />
					</button>
					<button type="submit" id="changeStatusModalButton"  
						class="btn btn-warning" name ="reject">
						<spring:message code="label.reject" />
					</button>
				</div>
			</div>
		</div>
	</div>
<sec:csrfInput /> 
</form:form>





<form:form action="${pageContext.request.contextPath}/approveCardOperationPrepaidBulk"
	modelAttribute="pendingCardInfo" enctype="multipart/form-data" method="POST"
	id="pendingCardInfoForm"  onSubmit="if(!confirm('Etes-vous sur de cette opération?')){return false;}">
	<!-- Modal -->

	<div class="modal fade" id="approveReloadModalPrepaidBulk" tabindex="-1"
		role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="modalLabel">
						Approbation carte prépayée en masse. Confirmer cette action?
					</h4>
				</div>
				<div class="modal-body">
				
				<div class="form-group">
					
					<form:input type="hidden" readonly ="true" name="approverStatus" class="form-control" id="approverStatus" path ="approverStatus" value="" />
				</div> 
				<div class="form-group">
					
					<form:input type="hidden" readonly ="true" name="changeReason" class="form-control" id="changeReason" path ="changeReason" value=""  />
				</div>
									
				</div>
				<div class="modal-footer">
					<div class="spinner-border"></div>
					<button type="button" class="btn btn-default btn-sm"
						data-dismiss="modal">
						<spring:message code="label.close" />
					</button>
					<button type="submit" id="changeStatusModalButton" 
						class="btn btn-success btn-sm" name ="submit">
						<spring:message code="label.approve" />
					</button>
					
				</div>
			</div>
		</div>
	</div>
<sec:csrfInput /> 
</form:form>



<script type="text/javascript"> 
/*$("#pendingCardInfoForm").on('submit', function (e){
	 e.preventDefault(); 
	 if (window.confirm("Voulez-vous confirmer cette opération?")){
		 $("#pendingCardInfoForm").submit();
	 }
 
})*/
</script>
<!--<script type="text/javascript">
	$("#cardInfoform")
			.on(
					'submit',
					function(e) {
						e.preventDefault();
						 $(".commentModalchangestatus").empty();
						if ($("#satusChangeModal #currentCardStatus").val().trim() === $("#cardStatus option:selected").text().trim()) {

							
							$(".commentModalchangestatus")
									.append(
											"<p class ='alert alert-danger  p-0' >  le statut actuel est identique au statut à pourvoir  </p>");
							return false;
						}
                    
						
						 if ( $("#satusChangeModal #ackReceiptAttachment").val() ===""){
							
							 $(".commentModalchangestatus")
								.append(
										"<p class ='alert alert-danger  p-0' > le fichier n'est pas choisi </p>");
							 return false; 
						 }
						
						e.currentTarget.submit();

					})

	$("#cardStatus").on('change', function(e) {
		$(".commentModalchangestatus").empty();
		$("#changeReason").val('');
	})
</script>-->