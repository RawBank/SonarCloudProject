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
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Modal -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                 <h4 class="modal-title" id="modalLabel"><spring:message code="label.cardInformation"/></h4>
            </div>
            <div class="modal-body">
            <div class="form-group">
					<label for="customerName"><spring:message code="label.customerName"/></label>
					<input type="text" readonly name="customerName" class="form-control" id="customerName">
				</div>
				<div class="form-group">
					<label for="phoneNumber"><spring:message code="label.phoneNumber"/></label> 
					<input type="text" readonly name="phoneNumber" class="form-control" id="phoneNumber">
				</div>
            	<div class="form-group">
					<label for="bankAccountNumber"><spring:message code="label.accountNumber"/></label> 
					<input type="text" readonly name="bankAccountNumber" class="form-control" id="bankAccountNumber">
				</div>
				<div class="form-group">
					<label for="billingAccountBalance"><spring:message code="label.accountBalance"/></label>
					<input type="text" readonly name="billingAccountBalance" class="form-control" id="billingAccountBalance">
				</div>
				<div class="form-group d-none">
					<label for="custCardNumber"><spring:message code="label.cardNumber"/></label> 
					<input type="text" readonly name="custCardNumber" class="form-control" id="custCardNumber">
				</div>
				<div class="form-group">  
					<label for="cardNumberMasked"><spring:message code="label.cardNumberMasked"/></label> 
					<input type="text" readonly name="cardNumberMasked" class="form-control" id="cardNumberMasked">
				</div>
				
				<div class="form-group">
					<label for="currentCardStatus"><spring:message code="label.currentCardStatus"/></label> 
					<input type="text" readonly name="currentCardStatus" class="form-control" id="currentCardStatus">
				</div>
				<div class="form-group">
					<label for="cardLimit"><spring:message code="label.cardLimit"/></label>
					<input type="text" readonly name="cardLimit" class="form-control" id="cardLimit">
				</div>		
				<div class="form-group">
					<label for="cardCurrentBalance"><spring:message code="label.cardBalance"/></label>
					<input type="text" readonly name="cardCurrentBalance" class="form-control" id="cardCurrentBalance">
				</div>
			</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="label.close"/></button>
            </div>
        </div>
    </div>
</div>