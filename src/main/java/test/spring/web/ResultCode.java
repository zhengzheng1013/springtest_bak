package test.spring.web;

public enum ResultCode {

	SUCCESS(0, "success."),
	
	SERVER_ERROR(-1, "server error!");

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
