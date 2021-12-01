package com.mexico.listacompras.dto;

import java.math.BigDecimal;
import java.util.List;

public class CompraHeaderDto{
	private Long compraId;
	private Long productosCount;
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
	private String nombre;
	
	
	private BigDecimal totalCompra;
	public BigDecimal getTotalCompra() {
		return totalCompra;
	}
	public void setTotalCompra(BigDecimal totalCompra) {
		this.totalCompra = totalCompra;
	}
	public Long getProductosCount() {
		return productosCount;
	}
	public void setProductosCount(Long productosCount) {
		this.productosCount = productosCount;
	}
	
	
}