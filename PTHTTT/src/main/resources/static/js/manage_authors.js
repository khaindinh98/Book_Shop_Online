$(document).ready(function(){
	$("#searchTxt").keyup(function() {
		let keySearch = $("#searchTxt").val();
		let tbData = $("#tbData").children('tbody');
		tbData.children().remove();
		$.ajax({
			url : "/admin/search/authors",
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
			url : "/admin/search/authors",
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
			let colName = $("<td class='align-middle'>"+data.name+"</td>");
			let colBirthday = $("<td class='align-middle'>"+data.birthDay+"</td>");
			let colDesctiption= $("<td class='align-middle'>"+data.description+"</td>");
			let colAdditional= $("<td class='align-middle'><a href='/admin/authors/editauthor/"+data.id+"' class='mr-sm-2 text-primary'><i class='fa fa-pencil'></i></a> <a href='/admin/author/delete/"+data.id+"' class='text-danger'><i class='fa fa-trash'></i></a></td>");
			newRow.append(colId, colName, colBirthday, colDesctiption, colAdditional);
			tbData.append(newRow);			
		}
	}
});
