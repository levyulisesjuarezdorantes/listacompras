package com.mexico.listacompras.service;

import java.util.List;

import com.mexico.listacompras.dto.CompraDto;
import com.mexico.listacompras.dto.ComprasUsuarioDto;
import com.mexico.listacompras.dto.ProductoDto;
import com.mexico.listacompras.entities.Compra;
import com.mexico.listacompras.entities.Producto;

public interface CompraService {

	public Compra create(Compra entity);
	public Compra findByUsuarioDispositivoIdAndCompraId(String dispositivoId,Long compraId);
	public Compra addProducto(Compra compra,Producto producto);
	public ComprasUsuarioDto getCompras(String dispositivoId);
	public CompraDto getProductos(String dispositivoId,Long compraId);
	public boolean delete(String dispositivoId, Long compraId);
	public boolean deleteProductos(String dispositivoId, Long compraId);
}
