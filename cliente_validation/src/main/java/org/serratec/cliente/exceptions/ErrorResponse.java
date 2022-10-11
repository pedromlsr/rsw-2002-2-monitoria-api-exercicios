package org.serratec.cliente.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
	private Integer status;
	private String titulo;
	private LocalDateTime dataHora;
	private List<String> errors;
	
	public ErrorResponse(Integer status, String titulo, LocalDateTime dataHora, List<String> errors) {
		this.status = status;
		this.titulo = titulo;
		this.dataHora = dataHora;
		this.errors = errors;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public List<String> getErrors() {
		return errors;
	}
	
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
