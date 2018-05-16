var loadAirports = function() {
	$('.airport-dropdown').append($('<option>', { 
        value: "",
        text : ""
    }));
	$.ajax({
		type : "GET",
		url : "/airports",
		success : function(airports) {
			$.each(airports, function (i, airport) {
			    $('.airport-dropdown').append($('<option>', { 
			        value: airport.iataCode,
			        text : airport.name + " ("  + airport.iataCode + ") "
			    }));
			});
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

$("#search-flights-btn").on("click", function() {
	$("#table-body-id").empty();
	$("#error-id").empty();
	let formData = $("#flights-params-id").serializeArray();
	let params = [];
	for (let i = 0; i<formData.length; i++) {
		if (formData[i].value !== undefined && formData[i].value !== null && formData[i].value !== "") {
			params.push(formData[i])
		}
	}
	
	$.ajax({
		type: "GET",
		url: "/flights?" + $.param(params),
		success: function(results){
			let row = '<tr>' + 
						'<td class="origin"/>'+
						'<td class="destination"/>'+
						'<td class="departure-date"/>'+
						'<td class="departure-stops"/>'+
						'<td class="return-date"/>'+
						'<td class="return-stops"/>'+
						'<td class="price"/></tr>';
			let baseUrl = window.location.origin;
			for (let i = 0; i < results.length; i++) {
				let rowObject = $(row);
				rowObject.find(".origin").text(results[i].origin);
				rowObject.find(".destination").text(results[i].destination);
				rowObject.find(".departure-date").text(results[i].departureDate);
				rowObject.find(".departure-stops").text(results[i].departureStops);
				rowObject.find(".return-date").text(results[i].returnDate);
				rowObject.find(".return-stops").text(results[i].returnStops);
				rowObject.find(".price").text(results[i].price + " " + results[i].currency);
				
				
				$("#table-body-id").append(rowObject);
			}
			
		},
		error: function(err) {
			console.log(err.responseText);
			$("#error-id").text(err.responseText);
		},
		dataType: "json",
		contentType : "application/json"
	});
});