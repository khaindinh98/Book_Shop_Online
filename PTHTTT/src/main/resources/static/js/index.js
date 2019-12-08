function setData(result, type){
	$("#nameEarnings").text("EARNINGS (" + type + ")")
	$("#earnings").text(result.earnings);
	$("#totalOfItemBought").text(result.totalOfItemBought);
	$("#totalOfItemSold").text(result.totalOfItemSold);
	$("#totalOfProductBought").text(result.totalOfProductBought);
	$("#totalOfProductSold").text(result.totalOfProductSold);
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
	
	getData("this year");
	let ctx = document.getElementById("myChart");
	let labels = [ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]
	let data = [];
	for (i in labels){
		$.ajax({
			url : "/admin/statistic/bymonth",
			type : 'GET',
			data : {month:(parseInt(i)+1)},
			success : function(result){
				data.push(result.earnings);
			},
			async:false
		});
	}
	let myChart = new Chart(ctx, {
		type : 'line',
		data : {
			labels : labels,
			datasets : [ {
				data : data,
				lineTension : 0,
				backgroundColor : 'transparent',
				borderColor : '#007bff',
				borderWidth : 4,
				pointBackgroundColor : '#007bff'
			} ]
		},
		options : {
			scales : {
				yAxes : [ {
					ticks : {
						beginAtZero : false
					}
				} ]
			},
			legend : {
				display : false,
			}
		}
	});
	
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