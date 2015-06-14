(function($) {
	$(function() {
		$('#submitLogin').click(function() {
			var loginname = $('#loginname').val();
			var password = $('#password').val();
			
			$.ajax({
				url : 'service/user/login.action',
				method : 'post',
				data : {
					loginname : loginname,
					password : password,
					verifyCode : ''
				},
				dateType : 'json',
				success : function() {
					window.top.location.href = 'index.action';
				}
			});
		});
	});
})(window.jQuery);