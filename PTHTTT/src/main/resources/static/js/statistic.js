function setData(result, type){
	$("#nameEarnings").text("EARNINGS (" + type + ")")
	$("#earnings").text(result.earnings);
	$("#totalOfItemBought").text(result.totalOfItemBought);
	$("#totalOfItemSold").text(result.totalOfItemSold);
	$("#totalOfProductBought").text(result.totalOfProductBought);
	$("#totalOfProductSold").text(result.totalOfProductSold);
	let tbData = $("#tbData").children('tbody');
	tbData.children().remove();
	let count = 1;
	for(data of result.statisProductRests){	
		let newRow= $("<tr></tr>");
		let colNum= $("<td>"+count+"</td>");
		let colName= $("<td>"+data.nameBook+"</td>");
		let colNumOfItemBought= $("<td>"+data.numOfItemBought+"</td>");
		let colNumOfItemSold= $("<td>"+data.numOfItemSold+"</td>");
		let colPriceBuy= $("<td>"+data.priceBuy+"</td>");
		let colPriceSell= $("<td>"+data.priceSell+"</td>");
		let colNumOfItemRest= $("<td>"+data.numOfItemRest+"</td>");
		let colEarningsByBook= $("<td>"+data.earningsByBook+"</td>");
		newRow.append(colNum, colName, colNumOfItemBought, colNumOfItemSold, colPriceBuy, colPriceSell, colNumOfItemRest, colEarningsByBook);
		tbData.append(newRow);
		count+=1;
	}
}

function getData(type){
	
	$.ajax({
		url : "/admin/statistic/"+type.replace(/\s+/g, ''),
		type : 'GET',
		success : function(result){
			setData(result, type);
		}
	});
}

$("document").ready(function() {
	
	getData("this date");
	$(".imgProduct").hover(function() {
		$(this).css("transform", "scale(1.05");
		var a = $(".nameBook");
		$(this).parent().find(a).css("color", "red");

	}, function() {
		$(".imgProduct").css("transform", "scale(1.0)");
		var a = $(".nameBook");
		$(this).parent().find(a).css("color", "black");
	});

	
	
});