package com.mexico.listacompras.service;

import com.mexico.listacompras.entities.Producto;

public interface ProductoService {

	public Producto update(Producto producto);
	public Producto findByIdAndCompraId(Long productoId,Long compraId);
	public boolean deleteProducto(Long productoId,Long compraId);
	
}
