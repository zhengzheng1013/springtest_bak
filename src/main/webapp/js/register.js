(function($) {
	$(function() {
		$('#submitRegister').click(function() {
			var username = $('#username').val();
			var email = $('#email').val();
			var password = $('#password').val();
			var repPassword = $('#username').val();
			
			$.ajax({
				url : 'service/user/register.action',
				method : 'post',
				data : {
					username : username,
					email : email,
					password : password
				},
				dateType : 'json',
				success : function() {
					window.top.location.href = 'login.action';
				}
			});
		});
	});
})(window.jQuery);