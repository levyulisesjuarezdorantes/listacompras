package com.mexico.listacompras.dto;

public class MensajeOkResponseDto {

	private String mensaje;

	
	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public MensajeOkResponseDto(String mensaje) {
		
		this.mensaje = mensaje;
	}
	
}
