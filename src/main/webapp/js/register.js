(function($) {
	$(function() {
		$('#submitRegister').click(function() {
			var username = $('#username').val();
			var email = $('#email').val();
			var password = $('#password').val();
			var repPassword = $('#username').val();
			
			$.ajax({
				url : 'register.action',
				method : 'post',
				data : {
					username : username,
					email : email,
					password : password
				},
				dateType : 'json',
				success : function() {
					console.log("success");
				}
			});
		});
	});
})(window.jQuery);