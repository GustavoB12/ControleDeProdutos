package br.com.compass.ControleDeProdutos.controller.config;

public class ExceptionResponse {
	private String message;
	private Integer status_code;

	public ExceptionResponse(String message, Integer status_code) {
		this.message = message;
		this.status_code = status_code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus_code() {
		return status_code;
	}

	public void setStatus_code(Integer status_code) {
		this.status_code = status_code;
	}

}
