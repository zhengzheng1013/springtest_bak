(function($) {
	$('#saveBlog').click(function() {
		$.ajax({
			url : '../service/blog/saveBlog.action',
			method : 'post',
			data : {
				id : blogId,
				title : $('#title').val(),
				content : $('#content').val()
			},
			dataType : 'json',
			success : function(result) {
				if(result.code == 0) {
					window.top.location.href = 'viewBlog.html?blogId=' + result.data;
				} else {
					alert(result.msg);
				}
			}
		});
	});
	
	$('#editBlog').click(function() {
		window.top.location.href = 'editBlog.html?blogId=' + blogId;
	});
})(window.jQuery);