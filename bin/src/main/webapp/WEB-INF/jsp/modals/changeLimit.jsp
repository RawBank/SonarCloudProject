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
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%-- <c:if test="${message != null }"> --%>
<%-- 				<p class="p-3 mb-2 bg-danger text-white">${message}</p> --%>
<%-- </c:if> --%>
<form:form action="${pageContext.request.contextPath}/changeCardLimit"
	modelAttribute="cardInfo" enctype="multipart/form-data" method="POST"
	id="cardInfoform">   
	<div class="modal fade" id="limitChangeModal" tabindex="-1"
		role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="modalLabel">
						<spring:message code="modal.title.changeCardLimit" />
					</h4>
				</div>
				<div class="modal-body">
					<div id="erroMessage"></div>
					<div class="commentModalchangestatus"></div>
					<div class="form-group">
						<label for="customerNumber"><spring:message
								code="label.customerNumber" /></label> <input type="text" readonly
							name="customerNumber" class="form-control" id="custNumberField" />
					</div>
					<div class="form-group">
						<label for="customerName"><spring:message
								code="label.customerName" /></label> <input type="text" readonly
							name="customerName" class="form-control" id="custNameField" />
					</div>
					<div class="form-group d-none">
						<label for="custCardNumber"><spring:message
								code="label.cardNumber" /></label> <input type="text" readonly
							name="custCardNumber" class="form-control" id="cardNumberField" />
					</div>
					<div class="form-group">
						<label for="cardNumberMasked"><spring:message
								code="label.cardNumberMasked" /></label> <input type="text" readonly
							name="cardNumberMasked" class="form-control"
							id="cardNumberMasked" />
					</div>
					<div class="form-group">
						<label for="currentCardStatus"><spring:message
								code="label.currentCardStatus" /></label> <input type="text"
							name="currentCardStatus" class="form-control" id="cardStatusx"
							readonly>
					</div>
					<div class="form-group">
						<label for="oldCardLimit"><spring:message
								code="label.oldCardLimit" /></label> <input type="text" readonly
							name="oldCardLimit" class="form-control" id="oldCardLimit" />
					</div>
					<div class="form-group">
						<label for="cardLimit"><spring:message
								code="label.newCardLimit" /></label> <input type="text"
							name="newCardLimit" class="form-control" id="newCardLimit" />
					</div>
					<div class="form-group">
						<label for="changeReason"><spring:message
								code="label.reason" /></label>
						<textarea class="form-control" placeholder="Reason"
							name="changeReason" id="changeReason" required="required"></textarea>
					</div>
					<div class="form-group">
						<label for="ackReceiptAttachment"><spring:message
								code="label.attachment" /></label> <input type="file"
							class="form-control-file" name="file" id="ackReceiptAttachment">
					</div>
				</div>
				<div class="modal-footer">
					<div class="spinner-border"></div>
					<button type="button" class="btn btn-default btn-sm"
						data-dismiss="modal">
						<spring:message code="label.close" />
					</button>
					<button type="submit" id="submit" class="submit btn-primary btn-sm">
						<spring:message code="label.approve" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<sec:csrfInput />
</form:form>
<script>
	$(document)
			.ready(
					function() {
						$(".submit")
								.click(
										function() {
											if ($("#newCardLimit").val() === $(
													"#oldCardLimit").val()) {
												$('#erroMessage')
														.html(
																"New Limit should not be equal to old Limit.")
												return false; //form will not submit and modal will remain open
											}
											return true; //form will get submitted and modal will close due to page being changed/reloaded
										})
					});
</script>
