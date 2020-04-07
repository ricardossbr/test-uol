package br.com.uol.cadastrojogador.model;

public class ResponseHttp {
	private String status;
	private Number statusCode;
	
	
	public ResponseHttp(String status, Number statusCode){
		this.status = status;
		this.statusCode = statusCode;
	}

	public String getStatus() {
		return status;
	}

	public Number getStatusCode() {
		return statusCode;
	}
}
