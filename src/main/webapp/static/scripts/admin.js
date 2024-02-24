/**
* Copyright 2022 Rawbank. All Rights Reserved.
* 
 * Change Section:
* Programmer               Date
*
* 
 * 
 * Created By:
* @author jacquesk
* @since 8 août 2022
* 
 */


let onselectAccountList = () => {
	$(".erroMessage").empty();

	if ($("#custAccountList option:selected").val() === "-1") {
		$(".erroMessage").empty().append("<p class ='alert alert-danger  p-0'  >Veuillez choisir un compte à débiter </p>");
		return;
	}

	var balance = parseFloat($("#custAccountList option:selected").text().split(":")[1]);
	if (balance === 0) {
		$(".erroMessage").empty().append("<p class ='alert alert-danger  p-0'>La Balance  du compte choisi  est insuffisante pour faire l'opération</p>");
	}

}

let setbuttonTransactiListActive = () => {
	//($(this).children(':selected').val() ==='-1' ? : '')
	var card_value = $("#cardNumber01 option:selected").val().trim();

	if (card_value === "-1") {
		$("#btnGetTransList").prop("disabled", true);
		return false;
	}
	$("#btnGetTransList").prop("disabled", false);

}



let getTransactionList = () => {
	var dateFrom = $("#dateFrom").val().trim();
	var dateTo = $("#dateTo").val().trim();
	var card_value = $("#cardNumber01 option:selected").val().trim();
	$(".alertMessage").empty();
	$(".alertMessage").fadeIn(3000);

	$("#divTransactions01").addClass("d-none");
	$("#divTransactions02").addClass("d-none");
	if (dateFrom.length === 0 || dateTo.length === 0 || card_value === -1) {

		$(".alertMessage").append("<p class ='alert alert-danger' style ='padding:3px'> Choisir la période et la carte </p>");


		return false;
	}


	if (new Date(dateTo) - new Date(dateFrom) < 0) {

		$(".alertMessage").append("<p class ='alert alert-danger  p-0' style ='padding:3px' > La date de la fin doit être supérieure ou égale à la date début</p>");
		return false;

	}

	if (dateFrom.length !== 0 && dateTo.length !== 0 && card_value !== "-1") {



		$.ajax({
			url: "/adminPortal/api/getTransactionsList?dateFrom=" + dateFrom + "&dateTo=" + dateTo + "&card=" + card_value,
			"type": "POST",
			"dataType": "json",
			"contentType": "application/json",

			beforeSend: function() {
				$(".alertMessage").empty().append("<img src='/adminPortal/static/img/wait-001.gif' width='40' height='40' alt='Veuillez patienter...'  />");
			},
			success: function(response) {

				var title01 = card_value + "-" + dateFrom + "-" + dateTo + "Trx";
				// 
				var sizeCleared = (JSON.parse(JSON.stringify(response.clearedTransactions))).length;
				var sizePending = (JSON.parse(JSON.stringify(response.pendingTransactions))).length;

				//console.log("sizeCleared = " + sizeCleared + " sizePending = " + sizePending);

				$("#transactionsDetails").addClass("d-none");

				if (sizeCleared === 0 && sizePending === 0) {
					$(".alertMessage").empty().append("<p class ='alert alert-danger  p-0' style ='padding:3px' > Transactions non disponibles durant cette période</p>");
					return false;
				}

				if (sizeCleared > 0) {

					console.log("sizeCleared = " + sizeCleared);
					$("#divTransactions01").removeClass("d-none");
				}
				if (sizePending > 0) {
					console.log(" sizePending = " + sizePending);
					$("#divTransactions02").removeClass("d-none");
				}
				// table apurée start
				$('#TransactionsCleared').DataTable({
					data: response.clearedTransactions,
					destroy: true,
					paging: true,
					lengthMenu: [5, 10, 15, 20],
					order: [[4, "desc"]],
					dom: 'Blfrtip',
					buttons: [
						{
							extend: 'pdfHtml5',
							text: '<i class="fa fa-file-pdf-o"></i> Export pdf',
							titleAttr: 'PDF',
							pageSize: 'LEGAL',
							className: 'btn btn-danger btn-sm',
							title: title01 + " - RAWBANK",
							exportOptions: {
								columns: ':visible',
							},
							filename: title01,
							customize: function(doc) {
								doc.styles.tableHeader.fillColor = 'orange';
								doc.styles.tableHeader.fontsize = 10;

							}
						},
						{
							extend: 'csvHtml5',
							text: '<i class="fa fa-file-text-o"></i> Export csv',
							className: 'btn btn-info btn-sm',
							title: title01 + " - RAWBANK",
							exportOptions: {
								columns: ':visible',
							},
							filename: title01,

						},
						{
							extend: 'excelHtml5', text: '<i class="fa fa-file-excel-o"></i> Export excel',
							className: 'btn btn-success btn-sm',
							title: title01 + " - RAWBANK",
							exportOptions: {
								columns: ':visible',
							},
							filename: title01,
						}
					],
					searching: true,

					"columns": [

						{
							"data": "incr",
							"width": "15%",
							"name": "incr"

						},
						{
							"data": "authCode",
							"width": "20%",
							"name": "authCode"
						},

						{
							"data": "accountAmount",
							"width": "15%",
							"name": "accountAmount",
							"className": "text-right",
							render: function(data, type, row) {
								return $.fn.dataTable.render.number('.', ',', 2, " ").display(data) + row.issuingCurrency;

							}

						},
						{
							"data": "merchantName",
							"width": "20%",
							"name": "merchantName"
						},


						{
							"data": "transactionDate",
							"width": "25%",
							"name": "transactionDate", render: function(data, type, row) {
								return data + ' ' + row.transactionTime;
							}
						},

					]

					,


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

				// table apurée end 

				$('#TransactionsPending').DataTable({
					data: response.pendingTransactions,
					destroy: true,
					paging: true,
					lengthMenu: [5, 10, 15, 20],
					order: [[4, "desc"]],
					dom: 'Blfrtip',
					buttons: [
						{
							extend: 'pdfHtml5',
							text: '<i class="fa fa-file-pdf-o"></i> Export pdf',
							titleAttr: 'PDF',
							pageSize: 'LEGAL',
							className: 'btn btn-default btn-sm',
							title: title01 + " - RAWBANK",
							exportOptions: {
								columns: ':visible',
								format: {
									body: function(data, row, column, node) {
										//data = $('<p>' + data + '</p>').text();
										//  return $.isNumeric(data.replace(',', '.')) ? data.replace(',', '.') : data;
										if (column === 3) {
											var arr = data.split(',');
											arr[0] = arr[0].toString().replace(/[\.]/g, "");
											//join the pieces together with a period if not empty
											if (arr[0] > '' || arr[1] > '') {
												data = arr[0] + '.' + arr[1];
											} else {
												return '';
											}
										}
										return data;
									}
								}
							},
							filename: title01,
							customize: function(doc) {
								doc.styles.tableHeader.fillColor = 'orange';
								doc.styles.tableHeader.fontsize = 10;

							}
						},
						{
							extend: 'csvHtml5',
							text: '<i class="fa fa-file-text-o"></i> Export csv',
							className: 'btn btn-default btn-sm',
							title: title01 + " - RAWBANK",
							exportOptions: {
								columns: ':visible',
							},
							filename: title01,

						},
						{
							extend: 'excelHtml5', text: '<i class="fa fa-file-excel-o"></i> Export excel',
							className: 'btn btn-default btn-sm',
							title: title01 + " - RAWBANK",
							exportOptions: {
								columns: ':visible',
							},
							filename: title01,
						}
					],
					searching: true,

					"columns": [

						{
							"data": "incr",
							"width": "15%",
							"name": "incr"

						},
						{
							"data": "authCode",
							"width": "20%",
							"name": "authCode"
						},

						{
							"data": "accountAmount",
							"width": "15%",
							"name": "accountAmount",
							"className": "text-right",
							render: function(data, type, row) {
								return $.fn.dataTable.render.number('.', ',', 2, " ").display(data) + row.issuingCurrency;

							}

						},
						{
							"data": "merchantName",
							"width": "20%",
							"name": "merchantName"
						},


						{
							"data": "transactionDate",
							"width": "25%",
							"name": "transactionDate", render: function(data, type, row) {
								return data + ' ' + row.transactionTime;
							}
						},

					]

					,


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
				// table transactions pending 
			},
			error: function(error) {
				var response = JSON.parse(error.responseText);
				//	$("#btnChangeStatus").prop("disabled", true);
				$(".alertMessage").empty().append("<p class ='alert alert-danger p-0'  style ='padding:3px'> Erreur " + response.errorMessage + "</p>");
                  console.log(response.errorMessage)
			},
			complete: function() {
				$(".alertMessage").empty();
				//$("#imgwait001").css('display', 'none');
			}
		});




	}

	//RawCardStatusChanges
}

//Recharge initié psp
let getActivities = (idDiv, idTable, cComment, partURI) => {
	


	var myElement = document.getElementById('psp');
	var myId = myElement.getAttribute( 'id' );
	
	
	

	$("#" + idDiv).addClass("d-none");
	$("." + cComment).removeClass("d-none");
	$("." + cComment).empty();
	var userconnected = $("#userconnected").text().trim().split(" ")[0];

	var dynamicPart = partURI === "getapprouverops" ? userconnected : userconnected + "/RELOAD"
//getActivities('divapprreports','tableapprreports','commentreportsapprreports','getapprouverops')
	$.ajax({
		method: "POST",
		url: "/adminPortal/api/" + partURI + "/" + dynamicPart + "/" + myId,
		contentType: "application/json",
		dataType: "json", 
		async: true
	}
	).done(function(resp) {
		var sizeActivities = (JSON.parse(JSON.stringify(resp))).length;
		if (sizeActivities === 0) {
			$("." + cComment).empty().append("<p class ='alert alert-danger  p-0'  style ='padding:3px'>  pas d'opérations disponibles </p>");
		} else {
			var title01 = "report";
			$("#" + idDiv).removeClass("d-none");
			var tablereport = $('#' + idTable).DataTable({
				data: resp,
				destroy: true,
				paging: true,
				lengthMenu: [5, 10, 15, 20],
				searching: true,
				order: [[4, 'desc']],
				dom: 'Blfrtip',
				buttons: [
					{
						extend: 'pdfHtml5',
						text: '<i class="fa fa-file-pdf-o"></i> Export pdf',
						titleAttr: 'PDF',
						orientation: 'landscape',
						pageSize: 'LEGAL',
						className: 'btn btn-default btn-sm',
						title: title01 + " - RAWBANK",
						exportOptions: {
							columns: ':visible',
						},
						filename: title01,
						customize: function(doc) {
							doc.styles.tableHeader.fillColor = 'orange';
							doc.styles.tableHeader.fontsize = 10;

						}
					},
					{
						extend: 'csvHtml5',
						text: '<i class="fa fa-file-text-o"></i> Export csv',
						className: 'btn btn-default btn-sm',
						title: title01 + " - RAWBANK",
						exportOptions: {
							columns: ':visible',
						},
						filename: title01,

					},
					{
						extend: 'excelHtml5', text: '<i class="fa fa-file-excel-o"></i> Export excel',
						className: 'btn btn-default btn-sm',
						title: title01 + " - RAWBANK",
						exportOptions: {
							columns: ':visible',
						},
						filename: title01,
					}
				],
				"columns": [
					{
						className: 'dt-control',
						orderable: false,
						data: null,
						defaultContent: '',
					},
					{
						"data": "retrievalReference",
						"width": "15%",
						"name": "retrievalReference"

					},
					{
						"data": "cardNumber",
						"width": "15%",
						"name": "cardNumber"

					},
					{
						"data": "transamount",
						"width": "15%",
						"name": "transamount",

						render: function(data, type, row) {
							return parseFloat(data)/100 + " " + row.currency;
						}

					},
					{
						"data": "dateheuretrans",
						"width": "15%",
						"name": "dateheuretrans"

					}
					,
					{
						"data": "dateheuretransApproval",
						"width": "15%",
						"name": "dateheuretransApproval"
					}
					,
					{
						"data": "initiator",
						"width": "15%",
						"name": "initiator"
					}
					,
					{
						"data": "approuver",
						"width": "15%",
						"name": "approuver"
					},
					{
						"data": "approuverStatus",
						"width": "15%",
						"name": "approuverStatus"
					},
					 // add for filter
					  { "data": "customerName","name": "customerName", "visible": true } ,
		 			  { "data": "authCode","name": "authCode", "visible": false } 
				],
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
			// 
			// Add event listener for opening and closing details
			$('#' + idTable + ' tbody').on('click', 'td.dt-control', function() {
				 //
				
				var tr = $(this).closest('tr');
				var row = tablereport.row(tr);

				if (row.child.isShown()) {
					// This row is already open - close it
					row.child.hide();
					tr.removeClass('shown');
				} else {
					// Open this row 
					if (row.data()){
					row.child(format(row.data())).show();
					tr.addClass('shown');
					}
				
				}
			});

		}


	}).fail(function(jqXHR) {

		if (jqXHR.status == 412 || jqXHR.status == 0) {
			window.location.href = "/adminPortal/login?errorCode=" + jqXHR.status;
			return;
		}
		$("." + cComment).empty().append("<p class ='alert alert-danger  p-0'  style ='padding:3px'> erreur pour recevoir les données</p>");
	}).always();
}


//Rapport Recharge MC Prépayée
let getActivitiesInd = (idDiv, idTable, cComment, partURI) => {
	


	var myElementInd = document.getElementById('ind');
	var myIdInd = myElementInd.getAttribute( 'id' );
	
	
	

	$("#" + idDiv).addClass("d-none");
	$("." + cComment).removeClass("d-none");
	$("." + cComment).empty();
	var userconnected = $("#userconnected").text().trim().split(" ")[0];

	var dynamicPart = partURI === "getapprouverops" ? userconnected : userconnected + "/RELOAD"
//getActivities('divapprreports','tableapprreports','commentreportsapprreports','getapprouverops')
	$.ajax({
		method: "POST",
		url: "/adminPortal/api/" + partURI + "/" + dynamicPart+ "/" + myIdInd,
		contentType: "application/json",
		dataType: "json", 
		async: true
	}
	).done(function(resp) {
		var sizeActivities = (JSON.parse(JSON.stringify(resp))).length;
		if (sizeActivities === 0) {
			$("." + cComment).empty().append("<p class ='alert alert-danger  p-0'  style ='padding:3px'>  pas d'opérations disponibles </p>");
		} else {
			var title01 = "report";
			$("#" + idDiv).removeClass("d-none");
			var tablereport = $('#' + idTable).DataTable({
				data: resp,
				destroy: true,
				paging: true,
				lengthMenu: [5, 10, 15, 20],
				searching: true,
				order: [[4, 'desc']],
				dom: 'Blfrtip',
				buttons: [
					{
						extend: 'pdfHtml5',
						text: '<i class="fa fa-file-pdf-o"></i> Export pdf',
						titleAttr: 'PDF',
						orientation: 'landscape',
						pageSize: 'LEGAL',
						className: 'btn btn-default btn-sm',
						title: title01 + " - RAWBANK",
						exportOptions: {
							columns: ':visible',
						},
						filename: title01,
						customize: function(doc) {
							doc.styles.tableHeader.fillColor = 'orange';
							doc.styles.tableHeader.fontsize = 10;

						}
					},
					{
						extend: 'csvHtml5',
						text: '<i class="fa fa-file-text-o"></i> Export csv',
						className: 'btn btn-default btn-sm',
						title: title01 + " - RAWBANK",
						exportOptions: {
							columns: ':visible',
						},
						filename: title01,

					},
					{
						extend: 'excelHtml5', text: '<i class="fa fa-file-excel-o"></i> Export excel',
						className: 'btn btn-default btn-sm',
						title: title01 + " - RAWBANK",
						exportOptions: {
							columns: ':visible',
						},
						filename: title01,
					}
				],
				"columns": [
					{
						className: 'dt-control',
						orderable: false,
						data: null,
						defaultContent: '',
					},
					{
						"data": "retrievalReference",
						"width": "15%",
						"name": "retrievalReference"

					},
					{
						"data": "cardNumber",
						"width": "15%",
						"name": "cardNumber"

					},
					{
						"data": "transamount",
						"width": "15%",
						"name": "transamount",

						render: function(data, type, row) {
							return parseFloat(data)/100 + " " + row.currency;
						}

					},
					{
						"data": "dateheuretrans",
						"width": "15%",
						"name": "dateheuretrans"

					}
					,
					{
						"data": "dateheuretransApproval",
						"width": "15%",
						"name": "dateheuretransApproval"
					}
					,
					{
						"data": "initiator",
						"width": "15%",
						"name": "initiator"
					}
					,
					{
						"data": "approuver",
						"width": "15%",
						"name": "approuver"
					},
					{
						"data": "approuverStatus",
						"width": "15%",
						"name": "approuverStatus"
					},
					 // add for filter
					  { "data": "customerName","name": "customerName", "visible": true } ,
		 			  { "data": "authCode","name": "authCode", "visible": false } 
				],
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
			// 
			// Add event listener for opening and closing details
			$('#' + idTable + ' tbody').on('click', 'td.dt-control', function() {
				 //
				
				var tr = $(this).closest('tr');
				var row = tablereport.row(tr);

				if (row.child.isShown()) {
					// This row is already open - close it
					row.child.hide();
					tr.removeClass('shown');
				} else {
					// Open this row 
					if (row.data()){
					row.child(format(row.data())).show();
					tr.addClass('shown');
					}
				
				}
			});

		}


	}).fail(function(jqXHR) {

		if (jqXHR.status == 412 || jqXHR.status == 0) {
			window.location.href = "/adminPortal/login?errorCode=" + jqXHR.status;
			return;
		}
		$("." + cComment).empty().append("<p class ='alert alert-danger  p-0'  style ='padding:3px'> erreur pour recevoir les données</p>");
	}).always();
}


//Rapport Recharge MC Prépayée BLK
let getActivitiesBlk = (idDiv, idTable, cComment, partURI) => {
	


	var myElementBlk = document.getElementById('blk');
	var myIdBlk = myElementBlk.getAttribute( 'id' );
	
	
	

	$("#" + idDiv).addClass("d-none");
	$("." + cComment).removeClass("d-none");
	$("." + cComment).empty();
	var userconnected = $("#userconnected").text().trim().split(" ")[0];
	

	var dynamicPart = partURI === "getapprouverops" ? userconnected : userconnected + "/RELOAD"
//getActivities('divapprreports','tableapprreports','commentreportsapprreports','getapprouverops')
	$.ajax({
		method: "POST",
		url: "/adminPortal/api/" + partURI + "/" + dynamicPart+ "/" + myIdBlk,
		contentType: "application/json",
		dataType: "json", 
		async: true
	}
	).done(function(resp) {
		var sizeActivities = (JSON.parse(JSON.stringify(resp))).length;
		if (sizeActivities === 0) {
			$("." + cComment).empty().append("<p class ='alert alert-danger  p-0'  style ='padding:3px'>  pas d'opérations disponibles </p>");
		} else {
			var title01 = "report";
			$("#" + idDiv).removeClass("d-none");
			var tablereport = $('#' + idTable).DataTable({
				data: resp,
				destroy: true,
				paging: true,
				lengthMenu: [5, 10, 15, 20],
				searching: true,
				order: [[4, 'desc']],
				dom: 'Blfrtip',
				buttons: [
					{
						extend: 'pdfHtml5',
						text: '<i class="fa fa-file-pdf-o"></i> Export pdf',
						titleAttr: 'PDF',
						orientation: 'landscape',
						pageSize: 'LEGAL',
						className: 'btn btn-default btn-sm',
						title: title01 + " - RAWBANK",
						exportOptions: {
							columns: ':visible',
						},
						filename: title01,
						customize: function(doc) {
							doc.styles.tableHeader.fillColor = 'orange';
							doc.styles.tableHeader.fontsize = 10;

						}
					},
					{
						extend: 'csvHtml5',
						text: '<i class="fa fa-file-text-o"></i> Export csv',
						className: 'btn btn-default btn-sm',
						title: title01 + " - RAWBANK",
						exportOptions: {
							columns: ':visible',
						},
						filename: title01,

					},
					{
						extend: 'excelHtml5', text: '<i class="fa fa-file-excel-o"></i> Export excel',
						className: 'btn btn-default btn-sm',
						title: title01 + " - RAWBANK",
						exportOptions: {
							columns: ':visible',
						},
						filename: title01,
					}
				],
				"columns": [
					{
						className: 'dt-control',
						orderable: false,
						data: null,
						defaultContent: '',
					},
					{
						"data": "retrievalReference",
						"width": "15%",
						"name": "retrievalReference"

					},
					{
						"data": "cardNumber",
						"width": "15%",
						"name": "cardNumber"

					},
					{
						"data": "transamount",
						"width": "15%",
						"name": "transamount",

						render: function(data, type, row) {
							return parseFloat(data)/100 + " " + row.currency;
						}

					},
					{
						"data": "dateheuretrans",
						"width": "15%",
						"name": "dateheuretrans"

					}
					,
					{
						"data": "dateheuretransApproval",
						"width": "15%",
						"name": "dateheuretransApproval"
					}
					,
					{
						"data": "initiator",
						"width": "15%",
						"name": "initiator"
					}
					,
					{
						"data": "approuver",
						"width": "15%",
						"name": "approuver"
					},
					{
						"data": "approuverStatus",
						"width": "15%",
						"name": "approuverStatus"
					},
					 // add for filter
					  { "data": "customerName","name": "customerName", "visible": true } ,
		 			  { "data": "authCode","name": "authCode", "visible": false } 
				],
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
			// 
			// Add event listener for opening and closing details
			$('#' + idTable + ' tbody').on('click', 'td.dt-control', function() {
				 //
				
				var tr = $(this).closest('tr');
				var row = tablereport.row(tr);

				if (row.child.isShown()) {
					// This row is already open - close it
					row.child.hide();
					tr.removeClass('shown');
				} else {
					// Open this row 
					if (row.data()){
					row.child(format(row.data())).show();
					tr.addClass('shown');
					}
				
				}
			});

		}


	}).fail(function(jqXHR) {

		if (jqXHR.status == 412 || jqXHR.status == 0) {
			window.location.href = "/adminPortal/login?errorCode=" + jqXHR.status;
			return;
		}
		$("." + cComment).empty().append("<p class ='alert alert-danger  p-0'  style ='padding:3px'> erreur pour recevoir les données</p>");
	}).always();
}



//Admin reports MC carte crédit
let getActivitiesAdmPsp = (idDiv, idTable, cComment, partURI) => {
	


	var myElement = document.getElementById('psp');
	var myId = myElement.getAttribute( 'id' );
	
	
	

	$("#" + idDiv).addClass("d-none");
	$("." + cComment).removeClass("d-none");
	$("." + cComment).empty();
	var userconnected = $("#userconnected").text().trim().split(" ")[0];

	var dynamicPart = partURI === "getapprouverops" ? userconnected : userconnected + "/RELOAD"
//getActivities('divapprreports','tableapprreports','commentreportsapprreports','getapprouverops')
	$.ajax({
		method: "POST",
		url: "/adminPortal/api/" + partURI + "/" + dynamicPart + "/" + myId,
		contentType: "application/json",
		dataType: "json", 
		async: true
	}
	).done(function(resp) {
		var sizeActivities = (JSON.parse(JSON.stringify(resp))).length;
		if (sizeActivities === 0) {
			$("." + cComment).empty().append("<p class ='alert alert-danger  p-0'  style ='padding:3px'>  pas d'opérations disponibles </p>");
		} else {
			var title01 = "report";
			$("#" + idDiv).removeClass("d-none");
			var tablereport = $('#' + idTable).DataTable({
				data: resp,
				destroy: true,
				paging: true,
				lengthMenu: [4, 10, 15, 20],
				searching: true,
				order: [[4, 'desc']],
				dom: 'Blfrtip',
				buttons: [
					{
						extend: 'pdfHtml5',
						text: '<i class="fa fa-file-pdf-o"></i> Export pdf',
						titleAttr: 'PDF',
						orientation: 'landscape',
						pageSize: 'LEGAL',
						className: 'btn btn-default btn-sm',
						title: title01 + " - RAWBANK",
						exportOptions: {
							columns: ':visible',
						},
						filename: title01,
						customize: function(doc) {
							doc.styles.tableHeader.fillColor = 'orange';
							doc.styles.tableHeader.fontsize = 10;

						}
					},
					{
						extend: 'csvHtml5',
						text: '<i class="fa fa-file-text-o"></i> Export csv',
						className: 'btn btn-default btn-sm',
						title: title01 + " - RAWBANK",
						exportOptions: {
							columns: ':visible',
						},
						filename: title01,

					},
					{
						extend: 'excelHtml5', text: '<i class="fa fa-file-excel-o"></i> Export excel',
						className: 'btn btn-default btn-sm',
						title: title01 + " - RAWBANK",
						exportOptions: {
							columns: ':visible',
						},
						filename: title01,
					}
				],
				"columns": [
					{
						className: 'dt-control',
						orderable: false,
						data: null,
						defaultContent: '',
					},
					{
						"data": "retrievalReference",
						"width": "15%",
						"name": "retrievalReference"

					},
					{
						"data": "cardNumber",
						"width": "15%",
						"name": "cardNumber"

					},
					{
						"data": "transamount",
						"width": "15%",
						"name": "transamount",

						render: function(data, type, row) {
							return parseFloat(data)/100 + " " + row.currency;
						}

					},
					{
						"data": "dateheuretrans",
						"width": "15%",
						"name": "dateheuretrans"

					}
					,
					{
						"data": "dateheuretransApproval",
						"width": "15%",
						"name": "dateheuretransApproval"
					}
					,
					{
						"data": "initiator",
						"width": "15%",
						"name": "initiator"
					}
					,
					{
						"data": "approuver",
						"width": "15%",
						"name": "approuver"
					},
					{
						"data": "approuverStatus",
						"width": "15%",
						"name": "approuverStatus"
					},
					 // add for filter
					  { "data": "customerName","name": "customerName", "visible": true } ,
		 			  { "data": "authCode","name": "authCode", "visible": false } 
				],
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
			// 
			// Add event listener for opening and closing details
			$('#' + idTable + ' tbody').on('click', 'td.dt-control', function() {
				 //
				
				var tr = $(this).closest('tr');
				var row = tablereport.row(tr);

				if (row.child.isShown()) {
					// This row is already open - close it
					row.child.hide();
					tr.removeClass('shown');
				} else {
					// Open this row 
					if (row.data()){
					row.child(format(row.data())).show();
					tr.addClass('shown');
					}
				
				}
			});

		}


	}).fail(function(jqXHR) {

		if (jqXHR.status == 412 || jqXHR.status == 0) {
			window.location.href = "/adminPortal/login?errorCode=" + jqXHR.status;
			return;
		}
		$("." + cComment).empty().append("<p class ='alert alert-danger  p-0'  style ='padding:3px'> erreur pour recevoir les données</p>");
	}).always();
}



//Admin reports
let getActivitiesAdmIndPp = (idDiv, idTable, cComment, partURI) => {
	


	var myElement = document.getElementById('ind');
	var myIndPp = myElement.getAttribute( 'id' );
	
	
	

	$("#" + idDiv).addClass("d-none");
	$("." + cComment).removeClass("d-none");
	$("." + cComment).empty();
	var userconnected = $("#userconnected").text().trim().split(" ")[0];

	var dynamicPart = partURI === "getapprouverops" ? userconnected : userconnected + "/RELOAD"
//getActivities('divapprreports','tableapprreports','commentreportsapprreports','getapprouverops')
	$.ajax({
		method: "POST",
		url: "/adminPortal/api/" + partURI + "/" + dynamicPart + "/" + myIndPp,
		contentType: "application/json",
		dataType: "json", 
		async: true
	}
	).done(function(resp) {
		var sizeActivities = (JSON.parse(JSON.stringify(resp))).length;
		if (sizeActivities === 0) {
			$("." + cComment).empty().append("<p class ='alert alert-danger  p-0'  style ='padding:3px'>  pas d'opérations disponibles </p>");
		} else {
			var title01 = "report";
			$("#" + idDiv).removeClass("d-none");
			var tablereport = $('#' + idTable).DataTable({
				data: resp,
				destroy: true,
				paging: true,
				lengthMenu: [4, 10, 15, 20],
				searching: true,
				order: [[4, 'desc']],
				dom: 'Blfrtip',
				buttons: [
					{
						extend: 'pdfHtml5',
						text: '<i class="fa fa-file-pdf-o"></i> Export pdf',
						titleAttr: 'PDF',
						orientation: 'landscape',
						pageSize: 'LEGAL',
						className: 'btn btn-default btn-sm',
						title: title01 + " - RAWBANK",
						exportOptions: {
							columns: ':visible',
						},
						filename: title01,
						customize: function(doc) {
							doc.styles.tableHeader.fillColor = 'orange';
							doc.styles.tableHeader.fontsize = 10;

						}
					},
					{
						extend: 'csvHtml5',
						text: '<i class="fa fa-file-text-o"></i> Export csv',
						className: 'btn btn-default btn-sm',
						title: title01 + " - RAWBANK",
						exportOptions: {
							columns: ':visible',
						},
						filename: title01,

					},
					{
						extend: 'excelHtml5', text: '<i class="fa fa-file-excel-o"></i> Export excel',
						className: 'btn btn-default btn-sm',
						title: title01 + " - RAWBANK",
						exportOptions: {
							columns: ':visible',
						},
						filename: title01,
					}
				],
				"columns": [
					{
						className: 'dt-control',
						orderable: false,
						data: null,
						defaultContent: '',
					},
					{
						"data": "retrievalReference",
						"width": "15%",
						"name": "retrievalReference"

					},
					{
						"data": "cardNumber",
						"width": "15%",
						"name": "cardNumber"

					},
					{
						"data": "transamount",
						"width": "15%",
						"name": "transamount",

						render: function(data, type, row) {
							return parseFloat(data)/100 + " " + row.currency;
						}

					},
					{
						"data": "dateheuretrans",
						"width": "15%",
						"name": "dateheuretrans"

					}
					,
					{
						"data": "dateheuretransApproval",
						"width": "15%",
						"name": "dateheuretransApproval"
					}
					,
					{
						"data": "initiator",
						"width": "15%",
						"name": "initiator"
					}
					,
					{
						"data": "approuver",
						"width": "15%",
						"name": "approuver"
					},
					{
						"data": "approuverStatus",
						"width": "15%",
						"name": "approuverStatus"
					},
					 // add for filter
					  { "data": "customerName","name": "customerName", "visible": true } ,
		 			  { "data": "authCode","name": "authCode", "visible": false } 
				],
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
			// 
			// Add event listener for opening and closing details
			$('#' + idTable + ' tbody').on('click', 'td.dt-control', function() {
				 //
				
				var tr = $(this).closest('tr');
				var row = tablereport.row(tr);

				if (row.child.isShown()) {
					// This row is already open - close it
					row.child.hide();
					tr.removeClass('shown');
				} else {
					// Open this row 
					if (row.data()){
					row.child(format(row.data())).show();
					tr.addClass('shown');
					}
				
				}
			});

		}


	}).fail(function(jqXHR) {

		if (jqXHR.status == 412 || jqXHR.status == 0) {
			window.location.href = "/adminPortal/login?errorCode=" + jqXHR.status;
			return;
		}
		$("." + cComment).empty().append("<p class ='alert alert-danger  p-0'  style ='padding:3px'> erreur pour recevoir les données</p>");
	}).always();
}






function format(d) {
	
	// `d` is the original data object for the row
	//'<span class="label label-success">Success </span>'
	var respo = d.responseCode === "00" ? '<span class="text-success">Success </span>' : d.responseCode === null && d.approuverStatus === "REJECTED" ?
		'<span class="text-danger font-weight-bold">Rejected </span>' : d.responseCode === null ? '<span class="text-warning font-weight-bold">Not yet approved </span>' : '<span class="text-danger">Failure </span>';
	return (
		'<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
		'<tr>' +
		'<td>Nom du client:</td>' +
		'<td>' +
		d.customerName +
		'</td>' +
		'</tr>' +
		'<tr>' +
		'<td>Compte débit client:</td>' +
		'<td>' +
		d.aaSrcAccount +
		'</td>' +
		'</tr>' +
		'<tr>' +
		'<td>status:</td>' +
		'<td>' +
		d.responseCode +
		'</td>' +
		'</tr>' +
		'<tr>' +
		'<td>Api Reponse :</td>' +
		'<td>' +
		d.responseMessage +
		'</td>' +
		'</tr>' +
		'<tr>' +
		'<td>Commentaire Initiateur:</td>' +
		'<td>' +
		d.comments +
		'</td>' +
		'</tr>' +
		'<tr>' +
		'<td>Approb Status:</td>' +
		'<td>' +
		d.approuverStatus +
		'</td>' +
		'</tr>' +
		'<tr>' +
		'<td>Commentaire approb:</td>' +
		'<td>' +
		d.commentsApprov +
		'</td>' +
		'</tr>' +
		'<tr>' +
		'<td>Numéro Autorisation:</td>' +
		'<td>' +
		d.authCode +
		'</td>' +
		'</tr>' +
		'<tr>' +
		'<td>Nom du Fichier:</td>' +
		'<td>' + "<a href='/adminPortal/api/downloadFile/pdf/"+d.fileName +"'  style ='color: none; font-weight:bolder'><i class=\"fa fa-book\"></i> "+ d.fileName + " </a>" + '</td>' +
		'</tr>' +
		'</table>'
	);
}


let getChangeStatusReports01 = () => {
	$(".commentChangeStatusReport01").removeClass("d-none");
	$(".commentChangeStatusReport01").empty();
	$("#divChangeStatusReports").addClass("d-none");
	$.ajax({
		method: "POST",
		url: "/adminPortal/api/getChangeStatusReports",
		contentType: "application/json",
		dataType: "json"
	}
	).done(function(response) {
		var sizeChangeReportStatus = (JSON.parse(JSON.stringify(response))).length;
		if (sizeChangeReportStatus === 0) {
			$(".commentChangeStatusReport01").empty().append("<p class ='alert alert-danger  p-0'  style ='padding:3px'> Pas de changement disponibles </p>");
		} else {
			$("#divChangeStatusReports").removeClass("d-none");
			var title01 = "Change status reports"
			$('#tableChangeStatusReports').DataTable({
				data: response,
				destroy: true,
				paging: true,
				lengthMenu: [5, 10, 15, 20],
				dom: 'Blfrtip',
				buttons: [
					{
						extend: 'pdfHtml5',
						text: '<i class="fa fa-file-pdf-o"></i> Export pdf',
						titleAttr: 'PDF',
						orientation: 'landscape',
						pageSize: 'LEGAL',
						className: 'btn btn-default btn-sm',
						title: title01 + " - RAWBANK",
						exportOptions: {
							columns: ':visible',
						},
						filename: title01,
						customize: function(doc) {
							doc.styles.tableHeader.fillColor = 'orange';
							doc.styles.tableHeader.fontsize = 10;

						}
					},
					{
						extend: 'csvHtml5',
						text: '<i class="fa fa-file-text-o"></i> Export csv',
						className: 'btn btn-default btn-sm',
						title: title01 + " - RAWBANK",
						exportOptions: {
							columns: ':visible',
						},
						filename: title01,

					},
					{
						extend: 'excelHtml5', text: '<i class="fa fa-file-excel-o"></i> Export excel',
						className: 'btn btn-default btn-sm',
						title: title01 + " - RAWBANK",
						exportOptions: {
							columns: ':visible',
						},
						filename: title01,
					}
				],
				searching: true,

				"columns": [

					{
						"data": "cardNumber",
						"width": "15%",
						"name": "cardNumber"

					},


					{
						"data": "cardoldStatus",
						"width": "10%",
						"name": "cardoldStatus",
						render: function(data, type, row) {
							return data === "Active" ? '<span class="label label-success">' + data + ' </span>' : data === 'Temporary blocked' ? '<span class="label label-danger">' + data + '</span>' : '<span class="label label-warning">' + data + '</span>';
						}
					},
					{
						"data": "cardnewStatus",
						"width": "15%",
						"name": "cardnewStatus",
						render: function(data, type, row) {
							return data === "Active" ? '<span class="label label-success">' + data + ' </span>' : '<span class="label label-danger">' + data + '</span>';
						}
					},
					{
						"data": "cardCurrenttStatus",
						"width": "10%",
						"name": "cardCurrenttStatus",
						render: function(data, type, row) {
							return data === "Active" ? '<span class="label label-success">' + data + ' </span>' : '<span class="label label-danger">' + data + '</span>';
						}
					},
					{
						"data": "cardStatusChangeDate",
						"width": "15%",
						"name": "cardStatusChangeDate"
					},


					{
						"data": "userName",
						"width": "10%",
						"name": "userName"
					},
					/*{
						"data": "validator",
						"width": "10%",
						"name": "validator"
					}
					,*/
					{
						"data": "fileName",
						"width": "25%",
							fnCreatedCell : function(nTd, sData,oData,iRow, iCol) {
										$(nTd).html(
											"<a href='/adminPortal/api/downloadFile/pdf/"+oData.fileName+"'style ='color: none; font-weight:bolder; font-size:11px'><i class=\"fa fa-book\"></i> "
													+ oData.fileName
													+ "&nbsp; <i class=\"icon-telecharger\"></i></a>");}
					}

				]

				,


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


		}


	}).fail(function(err, textstatus, error) {
		$(".commentChangeStatusReport01").append("<p class ='alert alert-danger  p-0'  style ='padding:3px'> Erreur  chargement du rapport pour cause pas de données</p>");
	}).always(function() {
	});
}



let  validate = (el, erroMessage, idButton) =>{
  var maxfilesize = 1024 * 1024*2.5,  // 2.5 Mb
      filesize = el.files[0].size;
  $("#"+idButton).prop('disabled', true);

  if ( filesize > maxfilesize )
  {
	  var message = "Fichier grand: " + (filesize/1024/1024).toFixed(2) + " Mb . Maximum : " +(maxfilesize/1024/1024) +" Mb";
	  $("."+ erroMessage).empty().append("<p class ='alert alert-danger  p-0' > "+message+" </p>");
	   $("#"+idButton).prop ('disabled', true);
    return false;
  }
  else
  {
	  $("."+erroMessage).empty();
	  $("#"+idButton).prop ('disabled', false);
    return true;
  }   
}





