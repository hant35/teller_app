package vn.fpt.util.rest;

import java.io.Serializable;

public class RestResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final int STATUS_SUCCESS = 0;
	public static final int STATUS_ERROR = 1;
	private int status;
	private String errorMessage;
	private T content;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public T getContent() {
		return content;
	}
	public void setContent(T content) {
		this.content = content;
	}
	
	
}