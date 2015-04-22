package test.spring.exception;

import test.spring.web.ResultCode;

public class BusinessException extends Exception {

	private static final long serialVersionUID = -892107245914897170L;

	/** 异常码 */
	private int code = ResultCode.SERVER_ERROR.getCode();

	/** 异常提示信息 */
	private String msg = ResultCode.SERVER_ERROR.getMsg();

	/** 异常，可选 */
	private Throwable throwable;

	public BusinessException(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public BusinessException(int code, String msg, Throwable throwable) {
		this.code = code;
		this.msg = msg;
		this.throwable = throwable;
	}

	public BusinessException(ResultCode ResultCode) {
		this(ResultCode.getCode(), ResultCode.getMsg());
	}

	public BusinessException(ResultCode ResultCode, Throwable throwable) {
		this(ResultCode.getCode(), ResultCode.getMsg(), throwable);
	}

	public BusinessException(ResultCode ResultCode, String extMsg) {
		this(ResultCode.getCode(), ResultCode.getMsg() + "(" + extMsg + ")");
	}

	/**
	 * @return 异常码
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param 异常码
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return 异常提示信息
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param 异常提示信息
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return 异常，可选
	 */
	public Throwable getThrowable() {
		return throwable;
	}

	/**
	 * @param 异常，可选
	 */
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("BussinessException occurred, ResultCode : " + code + ", errorMsg : " + msg);
		return sb.toString();
	}
}
