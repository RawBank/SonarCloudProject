<!-- 
 Copyright 2022 Rawbank. All Rights Reserved.
 
 Change Section:
 Programmer				Date
 Krishna				6/21/2022
 Initial Coding.
 
 Created By:
 @author krishna
 @since Jun 21, 2022
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- <script src="webjars/jquery/1.9.1/jquery.min.js"></script> -->
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<!-- <link href="webjars/bootstrap/4.6.1/css/bootstrap.min.css" rel="stylesheet"> -->
 
<form:form action="${pageContext.request.contextPath}/changeCardStatus"
	modelAttribute="cardInfo" enctype="multipart/form-data" method="POST"
	id="cardInfoform">

	<!-- Modal -->
	<div class="modal fade" id="satusChangeModal" tabindex="-1"
		role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="modalLabel">
						<spring:message code="form.title.changeCardStatus" />
					</h4>
					 <h5 class ="text-danger text-center"><spring:message code="comment.care.changeCardStatus" /></h5>
					  <p class ="text-center"> <input type ="checkbox" id ="checkboxConfirmStatut"/> Confirmer avant de changer le statut</p>
				</div> 
				<div class="modal-body">
					<div class="commentModalchangestatus"></div>
					<div class="form-group">
						<label for="expiryDate"><spring:message
								code="label.expiryDate" /></label> <input type="text" readonly
							name="expiryDate" class="form-control" id="expiryDate" />
					</div>
					<div class="form-group">
						<label for="customerNumber"><spring:message
								code="label.customerNumber" /></label> <input type="text" readonly
							name="customerNumber" class="form-control" id="custNumber" />
					</div>
					<div class="form-group">
						<label for="customerName"><spring:message
								code="label.customerName" /></label> <input type="text" readonly
							name="customerName" class="form-control" id="custName" />
					</div>
					<div class="form-group d-none">
						<label for="custCardNumber"><spring:message
								code="label.cardNumber" /></label> <input type="text" readonly
							name="custCardNumber" class="form-control" id="cardNumber" />
					</div>
					
					<div class="form-group">
						<label for="cardNumberMasked"><spring:message
								code="label.cardNumberMasked" /></label> <input type="text" readonly
							name="cardNumberMasked" class="form-control" id="cardNumberMasked" />
					</div>
							
					<div class="form-group"> 
						<label for="currentCardStatus"><spring:message
								code="label.currentCardStatus" /></label> <input type="text" 
							name="currentCardStatus" class="form-control" id="currentCardStatus" value="" readonly="readonly" > 
					</div>
					<div class="form-group">
						<label for="cardStatus"><spring:message
								code="label.changeStatus" /></label> <select class="form-control"
							name="cardStatus" id="cardStatus" style ="border: 1px solid  #337ab7">
							<option  value="-1">Select</option>
							<c:forEach items="${codeCardActives}" var="cardActives"
								varStatus="loop">
								<option   value="${cardActives.numCode}">${cardActives.description}</option>
							</c:forEach>

						</select>
					</div>
					<div class="form-group">
						<label for="changeReason"><spring:message
								code="label.reason" /></label>
						<textarea class="form-control" placeholder="Reason"
							name="changeReason" id="changeReason" required="required"></textarea>
					</div>
					<div class="form-group">
						<label for="ackReceiptAttachment"><spring:message
								code="label.attachment" /> [MAX FILE SIZE = 2.5MB]</label> <input type="file"
							class="form-control-file" name="file" id="ackReceiptAttachment" onchange="validate(this, 'commentModalchangestatus', 'changeStatusModalButton')"/>
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




<script type="text/javascript">

//Filter display status on select old
/*
$('#cardStatus').focus(function() {
	 $("#cardStatus option").each(function(){
		 var currentStatus  = $("#satusChangeModal #currentCardStatus").val().trim() ;
		 var roleUser  =  $("#role").text().trim();
		   
		        if (this.text.trim() !=="Select"){
		        	  this.style.display = "none"; 
		   		 if (currentStatus  ==="Active"){
		   			
		   			  if((this.text.trim() ==="Temporary blocked" || this.text.trim() ==="Blocked") && roleUser !=="csc_admin_call_center"){
		   				  this.style.display = "block";
		   			  }else if (this.text.trim() ==="Temporary blocked" && roleUser ==="csc_admin_call_center"){
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

*/

// Filter display status on select new
$('#cardStatus').focus(function() {
	 $("#cardStatus option").each(function(){
		 var currentStatus  = $("#satusChangeModal #currentCardStatus").val().trim() ;
		 var roleUser  =  $("#role").text().trim();
		   
		        if (this.text.trim() !=="Select"){
		        	  this.style.display = "none"; 
		   		 if (currentStatus  ==="Active"){
		   			
		   			  if((this.text.trim() ==="Temporary blocked") && roleUser !=="csc_admin_call_center"){
		   				  this.style.display = "block";
		   			  }else if (this.text.trim() ==="Temporary blocked" && roleUser ==="csc_admin_call_center"){
		   				 this.style.display = "block";
		   			  }
		   			   
		   			  
		   			  
		   		  } else if (currentStatus  ==="InActive"){
		   			  if((this.text.trim() ==="Active" || this.text.trim()==="Temporary blocked")
		   					  && roleUser !=="csc_admin_call_center"){
		   				  this.style.display = "block";
		   			  }
		   			  
		   		  } else if (currentStatus  ==="Temporary blocked"){
		   			  if((this.text.trim() ==="Active") && roleUser !=="csc_admin_call_center"){
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



	$("#cardInfoform")
			.on('submit',function(e) {
						e.preventDefault();
						 $(".commentModalchangestatus").empty();
						 if (!$("#checkboxConfirmStatut").is(":checked")) {
							 $(".commentModalchangestatus").empty().append("<p class ='alert alert-danger  p-0' > Confirmer que vous avez vérifié les informations fournies par le client, cochez au dessus </p>");
						return false;	 
						 }
						  
						if ($("#satusChangeModal #currentCardStatus").val().trim() === $("#cardStatus option:selected").text().trim()) {	
							$(".commentModalchangestatus").append("<p class ='alert alert-danger  p-0' >  le statut actuel est identique au statut à pourvoir   </p>");
							return false;
						} else if ($("#cardStatus option:selected").val().trim() ==="-1"){
							$(".commentModalchangestatus").append("<p class ='alert alert-danger  p-0' >  le  statut à pourvoir  n'est pas correct  </p>");
							return false;
						}
						 if ( $("#satusChangeModal #ackReceiptAttachment").val() ===""){
							
							 $(".commentModalchangestatus").append("<p class ='alert alert-danger  p-0' > le fichier n'est pas choisi </p>");
							 return false; 
						 }
						   
						 if(confirm("Assurez-vous bien d'avoir toutes les informations  \n"
								 +" Vous êtes entrain de changer le statut  : "+$("#satusChangeModal #currentCardStatus").val().trim()+"  =>  en statut : " + $("#cardStatus option:selected").text().trim() + " \n")){
							 e.currentTarget.submit();
						 }

					})

	$("#cardStatus").on('change', function(e) {
		$(".commentModalchangestatus").empty();
		$("#changeReason").val('');
	})
</script>