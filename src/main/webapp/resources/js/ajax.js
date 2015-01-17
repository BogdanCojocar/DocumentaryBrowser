function doDocumentaryPost() {
	var name = $('#name').val();
	var category = $('#category').val();

	$.ajax({
		type : "post",
		url : $("#docPost").attr("action"),
		data : "name=" + name + "&category=" + category
	});
}

function doDocumentaryDelete() {
	var id = $('#id').val();

	$.ajax({
		type : "post",
		url : $("#docDelete").attr("action"),
		data : "id=" + id
	});
}