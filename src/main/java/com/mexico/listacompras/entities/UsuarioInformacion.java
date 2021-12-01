package com.mexico.listacompras.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name="usuarioInformacion")
@Entity
public class UsuarioInformacion implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "bigint",nullable = false)
	private Long usuarioInformacionId;
	
	@Column(nullable = false)	
	private short genero;
	
	@Column(nullable = false)	
	private Timestamp fechaCreacion;
	
	@Column(nullable = false)	
	private Timestamp fechaUltimaActualizacion;

	@Column(nullable = false)	
	private Boolean activo;
	private int edad;
		
	@Column(nullable = true)	
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "direccionId")	
	private Direccion direccion;
	
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public Long getUsuarioInformacionId() {
		return usuarioInformacionId;
	}
	public void setUsuarioInformacionId(Long usuarioInformacionId) {
		this.usuarioInformacionId = usuarioInformacionId;
	}
	public short getGenero() {
		return genero;
	}
	public void setGenero(short genero) {
		this.genero = genero;
	}
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Timestamp getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}
	public void setFechaUltimaActualizacion(Timestamp fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	
}
