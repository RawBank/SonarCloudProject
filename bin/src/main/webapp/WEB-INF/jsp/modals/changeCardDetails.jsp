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
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%-- <c:if test="${message != null }"> --%>
<%-- 				<p class="p-3 mb-2 bg-danger text-white">${message}</p> --%>
<%-- </c:if> --%>
<form:form action="${pageContext.request.contextPath}/changeCardDetails"
	modelAttribute="cardInfo" enctype="multipart/form-data" method="POST"
	id="cardInfoform">
  
	<!-- Modal -->
	<div class="modal fade" id="detailChangeModal" tabindex="-1"
		role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="modalLabel">
						<spring:message code="modal.title.changeCardDetail" />
					</h4>
				</div>
				<div class="modal-body">
					<div id="erroMessage"></div>
					<div class="commentModalchangeDetails"></div>
					<div class="form-group">
						<label for="customerNumber"><spring:message
								code="label.customerNumber" /></label> <input type="text" readonly
							name="customerNumber" class="form-control" id="custNumberField"
							value="${rawClientResponseModel.clientNumber}" />
					</div>
					<div class="form-group">
						<label for="firstName"><spring:message
								code="label.firstName" /></label> <input type="text" name="firstName"
							class="form-control" id="firstNameField"
							value="${rawClientResponseModel.firstName}" />
					</div>
					<div class="form-group">
						<label for="lastName"><spring:message
								code="label.lastName" /></label> <input type="text" name="lastName"
							class="form-control" id="lastNameField"
							value="${rawClientResponseModel.lastName}" />
					</div>
					<div class="form-group">
						<label for="fathersName"><spring:message
								code="label.fathersName" /></label> <input type="text"
							name="fathersName" class="form-control" id="fathersNameField"
							value="${rawClientResponseModel.fathersName}" />
					</div>
					<div class="form-group">
						<label for="birthDate"><spring:message
								code="label.birthDate" /></label> <input type="text" name="birthDate"
							class="form-control" id="birthDateField"
							value="${rawClientResponseModel.birthDate}" />
					</div>
					<div class="form-group">
						<label for="clientCountry"><spring:message
								code="label.clientCountry" /></label> <select name="clientCountry"
							class="form-control" id="clientCountryField">
							<c:forEach items="${sessionScope.countries}" var="country">
								<c:set var="countryId" value="${fn:split(country, ',')[0]}" />
								<c:set var="countryName" value="${fn:split(country, ',')[1]}" />
								<option value="${countryId}"
									${countryId == rawClientResponseModel.clientCountry ? 'selected' : ''}>${countryName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="nationality"><spring:message
								code="label.nationality" /></label> <select name="nationality"
							class="form-control" id="nationality">
							<c:forEach items="${sessionScope.countries}" var="nationality">
								<c:set var="nationalityId"
									value="${fn:split(nationality, ',')[0]}" />
								<c:set var="nationalityName"
									value="${fn:split(nationality, ',')[1]}" />
								<option value="${nationalityId}"
									${nationalityId == rawClientResponseModel.nationality ? 'selected' : ''}>${nationalityName}</option>
							</c:forEach>
						</select>
					</div>

					<div class="form-group">
						<label for="telPrivate"><spring:message
								code="label.telPrivate" /></label> <input type="text" name="telPrivate"
							class="form-control" id="telPrivateField"
							value="${rawClientResponseModel.telPrivate}" />
					</div>
					<div class="form-group">
						<label for="telWork"><spring:message code="label.telWork" /></label>
						<input type="text" name="telWork" class="form-control"
							id="telWorkField" value="${rawClientResponseModel.telWork}" />
					</div>

					<div class="form-group">
						<label for="mobile1"><spring:message code="label.mobile1" /></label>
						<input type="text" name="mobile1" class="form-control"
							id="mobile1Field" value="${rawClientResponseModel.mobile1}" />
					</div>
					<div class="form-group">
						<label for="mobile2"><spring:message code="label.mobile2" /></label>
						<input type="text" name="mobile2" class="form-control"
							id="mobile2Field" value="${rawClientResponseModel.mobile2}" required />
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
	// $(document).ready(function() {
	// 	  $(".submit").click(
	// 		function() {
	// 	    if ($("#newCardLimit").val() === $("#oldCardLimit").val()) {
	// 	    	 $('#erroMessage').html("New Limit should not be equal to old Limit.")
	// 	      return false; //form will not submit and modal will remain open
	// 	    }
	// 	    return true; //form will get submitted and modal will close due to page being changed/reloaded
	// 	  })
	// 	});

	// {
	//     "clientNumber": "00014393",
	//     "birthDate": "19860619",
	//     "clientCountry": "178",
	//     "fathersName": "Jacques",
	//     "firstName": "HERVE",
	//     "idNumber": null,
	//     "lastName": "MPUNGA",
	//     "mobile1": "243813555901",
	//     "mobile2": "243999967673",
	//     "nationality": "180",
	//     "shortName": "MPUNGA HERVE",
	//     "telPrivate": "243999967673",
	//     "telWork": "243999967673"
	// }
</script>
