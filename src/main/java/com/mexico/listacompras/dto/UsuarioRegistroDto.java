package com.mexico.listacompras.dto;

public class UsuarioRegistroDto {

	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public short getEdad() {
		return edad;
	}
	public void setEdad(short edad) {
		this.edad = edad;
	}
	public int getPaisId() {
		return paisId;
	}
	public void setPaisId(int paisId) {
		this.paisId = paisId;
	}
	public short getGenero() {
		return genero;
	}
	public void setGenero(short genero) {
		this.genero = genero;
	}
	
	
	public String getDispositivoId() {
		return dispositivoId;
	}
	public void setDispositivoId(String dispositivoId) {
		this.dispositivoId = dispositivoId;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	private String nombreUsuario;	
	private String password;
	private short edad;
	private int paisId;
	private short genero;
	private String nombre;
	private String dispositivoId;

}
