$(document).ready(function(){
	$("#searchTxt").keyup(function() {
		let keySearch = $("#searchTxt").val();
		let tbData = $("#tbData").children('tbody');
		tbData.children().remove();
		$.ajax({
			url : "/admin/search/categories",
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
			url : "/admin/search/categories",
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
			let contentColImage = $("<img class='w-100' src='"+ data.img +"'>");
			colImage.append(contentColImage);	
			let colTitle = $("<td class='align-middle'>"+data.title+"</td>");
			let colDesctiption= $("<td class='align-middle'>"+data.description+"</td>");
			let colAdditional= $("<td class='align-middle'><a href='/admin/category/editcategory/"+data.id+"' class='mr-sm-2 text-primary'><i class='fa fa-pencil'></i></a> <a href='/admin/category/delete/"+data.id+"' class='text-danger'><i class='fa fa-trash'></i></a></td>");
			newRow.append(colId, colImage, colTitle, colDesctiption, colAdditional);
			tbData.append(newRow);
		}
	}
});
