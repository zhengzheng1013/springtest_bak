(function($) {
	$(function() {
		$('#submitRecharge').click(function() {
			var ammount = $('#rechargeAmmount').val();
			
			$.ajax({
				url : 'service/account/recharge.action',
				method : 'post',
				data : {
					ammount : ammount
				},
				dateType : 'json',
				success : function() {
					window.top.location.reload();
				}
			});
		});
		
		$('#submitTransfer').click(function() {
			var transferUid = $('#transferUid').val();
			var ammount = $('#transferAmmount').val();
			
			$.ajax({
				url : 'service/account/transfer.action',
				method : 'post',
				data : {
					uid : transferUid,
					ammount : ammount
				},
				dateType : 'json',
				success : function() {
					window.top.location.reload();
				}
			});
		});
	});
})(window.jQuery);