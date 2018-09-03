package com.posm.util;

public class AJAXResult {
	private boolean success;//返回结果
	private Object data;//返回数据

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
