var postSimpleForm = function(formSelector, onSuccess, onError) {
	let formData = $(formSelector).serializeArray();
	let jsonObject = {};
	for (let i = 0; i < formData.length; i++) {
		jsonObject[formData[i].name] = formData[i].value;
	}
	let json = JSON.stringify(jsonObject);
	let serverUrl = $(formSelector).attr('action');
	if (onError === undefined) {
		onError = function() {
			$("#result-id").text("Error has occured");
		};
	}
	$.ajax({
		type : "POST",
		url : serverUrl,
		data : json,
		success : onSuccess,
		error : onError,
		dataType : "json",
		contentType : "application/json"
	});
}

var loadSimpleForm = function(formSelector, extraFormFill) {
	let serverUrl = $(formSelector).attr('action');
	serverUrl += "/" + getId();
	
	$.ajax({
		type : "GET",
		url : serverUrl,
		success : function(data) {
			for (var property in data) {
				let propSelector = "[name='"+property+"']";
				let value = data[property];
				if ($(formSelector).find(propSelector).attr("type") === 'date') {
					value = value.split("T")[0];
				}
				$(formSelector).find(propSelector).val(value);
			}
			if(extraFormFill) {
				extraFormFill(data)
			}
			
		},
		error : function(err1, err2, err3) {
			console.log(err1);
			console.log(err2);
			console.log(err3);
		},
		dataType : "json",
		contentType : "application/json"
	});	
}

