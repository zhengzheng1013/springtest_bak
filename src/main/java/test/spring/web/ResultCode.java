package test.spring.web;

public enum ResultCode {

	SUCCESS(0, "success."),
	
	SERVER_ERROR(-1, "服务器内部异常!"),
	
	PARAMETER_ERROR(-2, "参数错误!"),
	
	USER_NOT_EXISTS(-2001, "用户不存在");

	private int code;

	private String msg;

	ResultCode(int code, String msg) {
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
