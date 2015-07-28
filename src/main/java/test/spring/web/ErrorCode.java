package test.spring.web;

public enum ErrorCode {

	SUCCESS(0, "success."),
	
	SERVER_ERROR(-1, "服务器内部异常!"),
	
	PARAMETER_ERROR(-2, "参数错误!"),
	
	USER_NOT_EXISTS(-2001, "博客不存在"),
	
	PASSWORD_NOT_CORRECT(-2002, "用户名与密码不匹配！"),
	
	BLOG_NOT_EXISTS(-2003, "博客不存在"),
	
	USER_NOT_BLOG_OWNER(-2004, "无权限编辑此博客");

	private int code;

	private String msg;

	ErrorCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
