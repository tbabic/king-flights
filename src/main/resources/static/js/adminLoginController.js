var loginSuccess = function(response) {
	$("#login-error-id").empty();
	$("#admin-login-id").hide();
	$("#admin-airports-id").show();
	localStorage.setItem("access-token", response.access_token);
	localStorage.setItem("refresh-token", response.refresh_token);
}

var autoLogin = function(callback) {
	let refreshToken = localStorage.getItem("refresh-token");
	if (refreshToken === undefined || refreshToken === null) {
		return;
	}
	$.ajax({
		type : "POST",
		url : "/oauth/token?grant_type=refresh_token&refresh_token="+refreshToken,
		beforeSend: function (xhr) {
		    xhr.setRequestHeader ("Authorization", "Basic " + btoa("my-trusted-client" + ":" + "secret"));
		},
		success : function(response) {
			loginSuccess(response);
			if (callback) {
				callback();
			}
		},
		error : function(err1, err2) {
			console.log(err1);
			console.log(err2);
			$("#admin-login-id").show();
			$("#admin-airports-id").hide();
			localStorage.removeItem("access-token");
			localStorage.removeItem("refresh-token");
		}
	});
}

autoLogin();

$("#login-btn").on("click", function(event) {
	event.preventDefault();
	let query = $("#login-form").serialize();
	$.ajax({
		type : "POST",
		url : "/oauth/token?" + query,
		beforeSend: function (xhr) {
		    xhr.setRequestHeader ("Authorization", "Basic " + btoa("my-trusted-client" + ":" + "secret"));
		},
		success : function(response) {
			loginSuccess(response);
		},
		error : function() {
			$("#login-error-id").text("Invalid credentials");
		}
	});
	
});

	