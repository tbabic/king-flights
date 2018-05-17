var loadAirports = function() {
	$.ajax({
		type : "GET",
		url : "/airports",
		success : function(results) {
			$("#table-body-id").empty();
			
			let row = '<tr>' + 
						'<td class="airport-code"/>'+
						'<td class="airport-name"/></tr>';
			for (let i = 0; i < results.length; i++) {
				let rowObject = $(row);
				rowObject.find(".airport-code").text(results[i].iataCode);
				rowObject.find(".airport-name").text(results[i].name);

				$("#table-body-id").append(rowObject);
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

loadAirports();

var addAirport = function(isRetry) {
	let formData = $("#admin-airport-form").serializeArray();
	let jsonObject = {};
	for (let i = 0; i < formData.length; i++) {
		jsonObject[formData[i].name] = formData[i].value;
	}
	let json = JSON.stringify(jsonObject);
	$.ajax({
		type : "POST",
		url : "/airports",
		beforeSend: function(xhr, settings) { 
			xhr.setRequestHeader('Authorization','Bearer ' + localStorage.getItem("access-token") ); 
		},
		data : json,
		success : function() {
			$("#airport-error-id").empty();
			loadAirports();
		},
		error : function(err1, err2) {
			console.log(err1);
			console.log(err2);
			//refresh access token
			if (err1.status === 403 && !isRetry) {
				autoLogin(function() {
					addAirport(true);
				});
			} else {
				$("#airport-error-id").text(err1.responseText);;
			}
			
			
			
		},
		
		contentType : "application/json"
	});
}

$("#add-airport-btn").on("click", function(event) {
	event.preventDefault();
	addAirport(false);
});