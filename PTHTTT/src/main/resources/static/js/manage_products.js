$(document).ready(function(){
	$("#searchTxt").keyup(function() {
		let keySearch = $("#searchTxt").val();
		let tbData = $("#tbData").children('tbody');
		tbData.children().remove();
		$.ajax({
			url : "/admin/search/products",
			type : 'GET',
			data : {
				key : keySearch,
			},
			success : setData
		});
	});
	$("#clsBtn").click(function() {
		$("#searchTxt").val('');
		let tbData = $("#tbData").children('tbody');
		tbData.children().remove();
		$.ajax({
			url : "/admin/search/products",
			type : 'GET',
			data : {
				key : '',
			},
			success : setData
		});
	});
	function setData(results){
		let tbData = $("#tbData").children('tbody');
		for(data of results){
			let newRow= $("<tr></tr>");
			let colId = $("<td class='align-middle' scope='row'>"+ data.id +"</td>");
			let colImage = $("<td class='align-middle' style='width:10rem;'></td>");
			let contentColImage = $("<img class='w-100' src='"+ data.pathImages +"'>");
			colImage.append(contentColImage);	
			let colName = $("<td class='align-middle'>"+data.name+"</td>");
			let colAuthors = $("<td class='align-middle'></td>");
			for(key in data.bookAuthor)
				if(key==0)
					colAuthors.append($("<span>"+data.bookAuthor[key].name+"</span>"))
				else
					colAuthors.append($("<span>, "+data.bookAuthor[key].name+"</span>"))
			let colCategory = $("<td class='align-middle'>"+data.category.title+"</td>");
			let colPriceBuy = $("<td class='align-middle'>"+data.priceBuy+"</td>");
			let colPriceSell = $("<td class='align-middle'>"+data.priceSell+"</td>");
			let colQuantity= $("<td class='align-middle'>"+data.quantity+"</td>");
			let colAdditional= $("<td class='align-middle'><a href='/admin/products/editproduct/"+data.id+"' class='mr-sm-2 text-primary'><i class='fa fa-pencil'></i></a> <a href='/admin/product/delete/"+data.id+"' class='text-danger'><i class='fa fa-trash'></i></a></td>");
			newRow.append(colId, colImage, colName, colAuthors, colCategory, colPriceBuy, colPriceSell, colQuantity, colAdditional);
			tbData.append(newRow);
		}
	}
});
