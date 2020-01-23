package com.flagpicker.demo.response;

public class Response {

	private final String status;
	private final String flags;

	public Response(String status, String flags) {
		this.status = status;
		this.flags = flags;
	}

	public String getStatus() {
		return status;
	}

	public String getFlags() {
		return flags;
	}
}