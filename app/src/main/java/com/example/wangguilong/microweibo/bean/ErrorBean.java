package com.example.wangguilong.microweibo.bean;

/**
 * Created by WangGuiLong on 2018/3/23.
 */

public class ErrorBean {

	/**
	 * error : User requests out of rate limit!
	 * error_code : 10023
	 * request : /2/statuses/home_timeline.json
	 */

	private String error;
	private int error_code;
	private String request;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}
}
