package com.mexico.listacompras.dto;

import java.math.BigDecimal;

public class ProductoDto {
	private Long productoId;
	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}

	public Boolean getChecked() {
		return checked;
	}

	private String nombre;
	private BigDecimal precio;
	private Boolean checked;
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Boolean isChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
}
