package com.mexico.listacompras.dto;

import java.math.BigDecimal;
import java.util.List;

public class CompraDto{
	private Long compraId;
	public Long getCompraId() {
		return compraId;
	}
	public void setCompraId(Long compraId) {
		this.compraId = compraId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<ProductoDto> getProductos() {
		return productos;
	}
	public void setProductos(List<ProductoDto> productos) {
		this.productos = productos;
	}
	private String nombre;
	private List<ProductoDto> productos;
	
	private BigDecimal totalCompra;
	public BigDecimal getTotalCompra() {
		return totalCompra;
	}
	public void setTotalCompra(BigDecimal totalCompra) {
		this.totalCompra = totalCompra;
	}
}