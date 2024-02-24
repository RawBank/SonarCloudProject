<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script src="${pageContext.request.contextPath}/static/scripts/datepicker-fr.js"></script>
<script>
	$(function() {
		$("#dateFrom").datepicker({
			"dateFormat" : "yy-mm-dd",
			changeYear : true,
			language : 'fr',
			maxDate : new Date(),
			onSelect : function(selectedDate) {
				var date = $(this).datepicker("getDate");
				$("#dateTo").datepicker("option", "minDate", selectedDate);
			}
		}//, $.datepicker.regional['fr']

		);
		$("#dateTo").datepicker({
			"dateFormat" : "yy-mm-dd",
			changeYear : true,
			language : 'fr',
			maxDate : new Date(),
			onSelect : function(selectedDate) {
				var date = $(this).datepicker("getDate");
				$("#dateFrom").datepicker("option", "maxDate", selectedDate);
			}

		})//, $.datepicker.regional['fr']);
	});
</script>



<div class="modal fade" id="transactionsModal01" tabindex="-1"
	role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
	<div class="modal-dialog modal-01">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="modalLabel">
					<spring:message code="label.transactionInformation" />
				</h4>
			</div>
			<div class="modal-body">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<spring:message code="label.customerCardTransactions" />
					</div>
					<div class="panel-body">

						<div class="col-md-12 col-lg-12 col-md-offset-0">
							<h6 class="titre1" id="transactionsDetails">
								<spring:message code="label.transactionsDetails" />
							</h6>
							<hr>
							<div class="alertMessage col-md-8 collg-8 text-center"></div>
							<div class="pl-1">
								<div class="col-md-10 col-lg-10 col-md-offset-1"></div>
								<div class="row" style="margin-bottom: 20px">
									<div class="col-md-6 col-lg-3">
										<div class="form-group form-group-sm">
											<label class="col-md-12 control-label"><spring:message
													code="label.From" /></label>
											<div class="col-md-12 col-lg-12">
												<input class="form-control" type="text" id="dateFrom"
													name="dateFrom" placeholder="YYYY-MM-DD" autocomplete="off"
													required="required"><img
													class="ui-datepicker-trigger"
													src="${pageContext.request.contextPath}/static/img/calendar.gif"
													alt="..." title="...">
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-3">
										<div class="form-group form-group-sm">
											<label class="col-md-12 control-label"><spring:message
													code="label.To" /></label>
											<div class="col-md-12 col-lg-12">
												<input class="form-control " type="text" id="dateTo"
													name="dateTo" placeholder="YYYY-MM-DD" autocomplete="off"
													required="required"><img
													class="ui-datepicker-trigger"
													src="${pageContext.request.contextPath}/static/img/calendar.gif"
													alt="..." title="...">
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-3">
										<div class="form-group form-group-sm">
											<label class="col-md-12 control-label"><spring:message
													code="label.cardList" /></label>
											<div class="col-md-12 col-lg-12">
												<select id="cardNumber01" name="cardNumber01"
													class="form-control" required="required"
													onchange="setbuttonTransactiListActive()">
													<option value="-1"><spring:message
															code="label.select.cardList" /></option>
													<c:forEach items="${customerInfoList}" var="customer"> 
									                   <!--  merge two methods below to  mask  the card number (PAN) as per PCI-DSS advice -->
														<option value="${customer.cscNumber}">${fn:replace(customer.cscNumber, fn:substring(customer.cscNumber, 6,12), "XXXXXX")}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

									<div class="col-md-6 col-lg-3">
										<div class="form-group form-group-sm">
											<label class="col-md-12 control-label" style="color: white">.</label>
											<div class="col-md-12 col-lg-12">
												<button class="btn btn-primary btn-md" disabled="disabled"
													id="btnGetTransList" type="button"
													onclick="getTransactionList()">Voir les
													Transactions</button>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12 col-lg-12 d-none" id="divTransactions02"
									style="margin-top: 14px;">
									<legend>
										<spring:message code="label.transactionpending" />
									</legend>
									<hr>
									<div class="table-responsive">
										<div class="table-wrapper w-100">
											<table id="TransactionsPending"
												class="table table-striped table-bordered" cellspacing="0"
												width="100%">
												<thead>
													<tr>
														<th width="15%" scope="col"><spring:message
																code="label.numLine" /></th>
														<th width="20%" scope="col"><spring:message
																code="label.codeAuth" /></th>
														<th width="20%" scope="col"><spring:message
																code="label.amount" /></th>
														<th width="20%" scope="col"><spring:message
																code="label.reason" /></th>
														<th width="25%" scope="col"><spring:message
																code="label.date" /></th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
								</div>
								<hr>
								<div class="col-md-12 col-lg-12 d-none" id="divTransactions01">
									<legend>
										<spring:message code="label.transactionsdone" />
									</legend>
									<hr>
									<div class="table-responsive">
										<div class="table-wrapper w-100">
											<table id="TransactionsCleared"
												class="table table-striped table-bordered" cellspacing="0"
												width="100%">
												<thead>
													<tr>
														<th width="15%" scope="col"><spring:message
																code="label.numLine" /></th>
														<th width="20%" scope="col"><spring:message
																code="label.codeAuth" /></th>
														<th width="20%" scope="col"><spring:message
																code="label.amount" /></th>
														<th width="20%" scope="col"><spring:message
																code="label.reason" /></th>
														<th width="25%" scope="col"><spring:message
																code="label.date" /></th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="label.close" />
				</button>
			</div>
		</div>
	</div>
</div>
