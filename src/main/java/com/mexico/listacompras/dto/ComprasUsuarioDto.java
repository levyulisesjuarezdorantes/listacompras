package com.mexico.listacompras.dto;

import java.util.List;

public class ComprasUsuarioDto {

	private String nombreUsuario;
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getDispositivoId() {
		return dispositivoId;
	}
	public void setDispositivoId(String dispositivoId) {
		this.dispositivoId = dispositivoId;
	}
	public List<CompraHeaderDto> getCompras() {
		return compras;
	}
	public void setCompras(List<CompraHeaderDto> compras) {
		this.compras = compras;
	}
	private String dispositivoId;
	private List<CompraHeaderDto> compras;
	
}
