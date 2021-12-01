package com.mexico.listacompras.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mexico.listacompras.dao.ProductoDao;
import com.mexico.listacompras.entities.Producto;
import com.mexico.listacompras.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	ProductoDao productoDao;


	@Override
	public Producto update(Producto producto) {
		
		return productoDao.update(producto);
	}


	@Override
	public Producto findByIdAndCompraId(Long productoId, Long compraId) {
		// TODO Auto-generated method stub
		return productoDao.findByIdAndCompra(productoId,compraId);
	}


	@Override
	public boolean deleteProducto(Long productoId, Long compraId) {
		// TODO Auto-generated method stub
		Producto producto = findByIdAndCompraId(productoId, compraId);
		productoDao.deleteProducto(producto);
		return true;
	}
}
