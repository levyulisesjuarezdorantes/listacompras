package com.mexico.listacompras.entities;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name="pais")
@Entity
public class Pais  implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="paisId", columnDefinition = "int",nullable = false)
	Integer paisId;
	@Column(name = "nombre",nullable = false,length = 500)
	String nombre;
	@Column(name = "activo",nullable = false)
	Boolean activo;
	@Column(nullable = false)
	Timestamp fechaCreacion;
	public Integer getPaisId() {
		return paisId;
	}
	public void setPaisId(Integer paisId) {
		this.paisId = paisId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Boolean isActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
}
