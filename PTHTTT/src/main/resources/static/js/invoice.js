let index;

function deleteInInvoice(detailInvoiceId){
	let tbData = $("#tbDetailInvoices").children('tbody').children();
	if(detailInvoiceId == index-1){
		let colId =  $($("#detailInvoiceId"+detailInvoiceId).children()[0]).find("input");
		
		let cookieDetailInvoices = JSON.parse(atob($.cookie("detailInvoices")));
		let pos = cookieDetailInvoices.indexOf(colId.val());
		cookieDetailInvoices.splice(pos, 1);
		$.cookie("detailInvoices", btoa(JSON.stringify(cookieDetailInvoices)));
		$("#detailInvoiceId"+detailInvoiceId).remove();
		index-=1;
	}
	else{
		for(let i=detailInvoiceId; i<tbData.length-1; i++){
			let colId = $($("#detailInvoiceId"+i).children()[0]).find("input");
			let nextColId = $($("#detailInvoiceId"+(i+1)).children()[0]).find("input");
			colId.val(nextColId.val());
			
			let cookieDetailInvoices = JSON.parse(atob($.cookie("detailInvoices")));
			let pos = cookieDetailInvoices.indexOf(colId.val());
			cookieDetailInvoices.splice(pos, 1);
			$.cookie("detailInvoices", btoa(JSON.stringify(cookieDetailInvoices)));
			
			let colImage = $($("#detailInvoiceId"+i).children()[1]);
			let nextColImage = $($("#detailInvoiceId"+(i+1)).children()[1]).children();
			colImage.empty();
			colImage.append(nextColImage);
			
			let colName = $($("#detailInvoiceId"+i).children()[2]).find("input");
			let nextColName = $($("#detailInvoiceId"+(i+1)).children()[2]).find("input");
			colName.val(nextColName.val());
			
			let colPriceBuy = $($("#detailInvoiceId"+i).children()[3]).find("input");
			let nextColPriceBuy = $($("#detailInvoiceId"+(i+1)).children()[3]).find("input");
			colPriceBuy.val(nextColPriceBuy.val());
			
			let colQuantity = $($("#detailInvoiceId"+i).children()[4]).find("input");
			let nextColQuantity = $($("#detailInvoiceId"+(i+1)).children()[4]).find("input")
			colQuantity.val(nextColQuantity.val());
	
		}	
		$("#detailInvoiceId"+(index-1)).remove();
		index-=1;
	}
	reset();
}

function addToInvoice(bookId){
	let book = $('#bookId'+bookId).hide();
	let bookName = $('#bookNameBookId'+bookId).text();
	let quantity = $('#quantityBookId'+bookId).val();
	let priceBuy = $('#priceBuyBookId'+bookId).val();
	let pathImage = $('#pathImageBookId'+bookId).attr('src');
	let tbDetailInvoices = $('#tbDetailInvoices').children('tbody');
	let newRow = $("<tr id='detailInvoiceId"+index+"'></tr>");
	let colId = $('<td></td>');
	let spanColId = $('<span>'+(index + 1)+'</span>');
	let contentColId = $("<input class='form-control' type='hidden' id='detailInvoiceForms"+index+".bookId' name='detailInvoiceForms["+index+"].bookId' value='"+bookId+"'/>");
	colId.append(spanColId,contentColId);
	let colImage = $("<td style='width: 5rem;'></td>");
	let contentColImage = $("<img class='w-100' id='imageDetailInvoiceForms"+ index +"' src='"+ pathImage +"'/>");
	colImage.append(contentColImage);
	let colBookName = $("<td></td>");
	let contentColBookName = $("<input class='form-control' type='text' id='detailInvoiceForms"+index+".bookName' name='detailInvoiceForms["+index+"].bookName'  value='"+bookName+"' readonly>");
	colBookName.append(contentColBookName);
	let colPriceBuy = $("<td></td>");
	let contentPriceBuy = $("<input class='form-control' type='number' id='detailInvoiceForms"+index+".priceBuy' name='detailInvoiceForms["+index+"].priceBuy' value='"+ priceBuy +"'readonly>");
	colPriceBuy.append(contentPriceBuy);
/* 										let colPriceSell = $("<td></td>");
		let contentPriceSell = $("<input type='number' value='0' readonly>");
		colPriceSell.append(contentPriceSell); */
	let colQuantity= $("<td></td>");
	let contentColQuantity = $("<input class='form-control inputQuantity' type='number' id='detailInvoiceForms"+index+".quantity' name='detailInvoiceForms["+index+"].quantity'  value='"+quantity+"' >");
	colQuantity.append(contentColQuantity);
	let colDelete = $('<td></td>')
	let contentColDelete = $("<button class='btn btn-danger' type='button' onclick='deleteInInvoice("+ index +")'><i class='fa fa-trash'></i></button>");
	colDelete.append(contentColDelete);
	newRow.append(colId, colImage, colBookName, colPriceBuy, colQuantity, colDelete);//, colPriceSell
	tbDetailInvoices.append(newRow);
	let cookieDetailInvoices = JSON.parse(atob($.cookie("detailInvoices")));
	cookieDetailInvoices.push(bookId);
	$.cookie("detailInvoices", btoa(JSON.stringify(cookieDetailInvoices)));
	index+=1;
}

function setData(results){
	let tbData = $("#tbData").children('tbody');
	let count = 1;
	for(data of results){
		let newRow= $("<tr id='bookId" + data.id+ "'>");
		let colId = $("<td>"+count+"</td>");
		let colImage = $("<td style='width: 5rem;'></td>");
		let contentColImage = $("<img class='w-100' src='"+data.pathImages+"' id='pathImageBookId"+data.id+"'>");
		colImage.append(contentColImage);	
		let colName = $("<td id='bookNameBookId"+data.id+"'>"+data.name+"</td>");
		let colPriceBuy = $("<td><input class='form-control' value='"+data.priceBuy+"' type='number' id='priceBuyBookId"+data.id+"'/></td>");
		let colQuantity= $("<td><input class='form-control inputQuantity' value='0' type='number' id='quantityBookId"+data.id+"'/></td>");
		let colAdditional= $("<td><button onclick='addToInvoice("+data.id+")' class='btn btn-primary'>Add</button></td>");			
		newRow.append(colId, colImage, colName, colPriceBuy, colQuantity, colAdditional);
		tbData.append(newRow);
		count+=1;
	}
}

function reset(){
	$("#searchTxt").val('');
	let tbData = $("#tbData").children('tbody');
	tbData.children().remove();
	$.ajax({
		url : "/admin/search/productsOfInvoice",
		type : 'GET',
		data : {
			key : '',
			detailInvoices:$.cookie("detailInvoices")
		},
		success : setData
	});
}

$(document).ready(function(){
	setQuantity($(".inputQuantity"));
	setPrice($(".inputPrice"));
	index = $('#tbDetailInvoices').children('tbody').children().length;
	
	$("#searchTxt").keyup(function() {
		let keySearch = $("#searchTxt").val();
		let tbData = $("#tbData").children('tbody');
		tbData.children().remove();
		$.ajax({
			url : "/admin/search/productsOfInvoice",
			type : 'GET',
			data : {
				key : keySearch,
				detailInvoices:$.cookie("detailInvoices")
			},
			success : setData
		});
	});
	$("#clsBtn").click(reset);
	
});