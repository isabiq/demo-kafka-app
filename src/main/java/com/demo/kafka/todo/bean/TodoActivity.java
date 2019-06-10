package com.demo.kafka.todo.bean;

import java.util.Date;

public class TodoActivity {
	private String path;
	private String httpMethod;
	private String httpStatus;
	private Date date;

	public TodoActivity() {
		super();
	}

	public TodoActivity(String path, String httpMethod, String httpStatus, Date date) {
		super();
		this.path = path;
		this.httpMethod = httpMethod;
		this.httpStatus = httpStatus;
		this.date = date;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "TodoActivity [path=" + path + ", httpMethod=" + httpMethod + ", httpStatus=" + httpStatus + ", date="
				+ date + "]";
	}

	public String toCSV() {
		return path + ";" + httpMethod + ";" + httpStatus + ";" + date;
	}

}
