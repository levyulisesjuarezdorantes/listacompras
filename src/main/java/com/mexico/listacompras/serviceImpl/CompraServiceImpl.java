package com.mexico.listacompras.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mexico.listacompras.dao.CompraDao;
import com.mexico.listacompras.dao.ProductoDao;
import com.mexico.listacompras.dto.CompraDto;
import com.mexico.listacompras.dto.CompraHeaderDto;
import com.mexico.listacompras.dto.ComprasUsuarioDto;
import com.mexico.listacompras.dto.ProductoDto;
import com.mexico.listacompras.entities.Compra;
import com.mexico.listacompras.entities.Producto;
import com.mexico.listacompras.service.CompraService;


@Service
public class CompraServiceImpl implements CompraService {

	@Autowired
	CompraDao compraDao;


	@Autowired
	ProductoDao productoDao;

	
	@Override
	public Compra create(Compra entity) {
		return compraDao.save(entity);
	}

	@Override
	public Compra findByUsuarioDispositivoIdAndCompraId(String dispositivoId, Long compraId) {
		return compraDao.findByUsuarioDispoisitivoAndCompraId(dispositivoId, compraId);
	}


	@Override
	public Compra addProducto(Compra compra, Producto producto) {
		// TODO Auto-generated method stub
		productoDao.save(producto);
		compraDao.update(compra);
		
		return compra;
	}

	@Override
	public ComprasUsuarioDto getCompras(String dispositivoId) {
		ComprasUsuarioDto comprasUsuarioDto = new ComprasUsuarioDto();
		comprasUsuarioDto.setDispositivoId(dispositivoId);
		
		List<CompraHeaderDto> comprasDto = new ArrayList<CompraHeaderDto>();
		List<Compra> compras = compraDao.findByUsuarioDispositivoId(dispositivoId);
		if(compras!= null && !compras.isEmpty()) {
			
			for(Compra compra: compras) {
				CompraHeaderDto compraDto = new CompraHeaderDto();
				compraDto.setNombre(compra.getNombre());
				List<Producto> productos = productoDao.findByUsuarioDispoisitivoAndCompraId(compra.getCompraId());
				 BigDecimal totalCompra = null;
				 Long productosCount = 0L;
				
				 if(productos!=null && !productos.isEmpty()) {					
				   totalCompra = new BigDecimal(productos.stream().filter(x->x.getPrecio()!=null).mapToDouble(x->x.getPrecio().doubleValue()).sum());
				   productosCount= (long) productos.size();
				}else {
					totalCompra = new BigDecimal(0.0);					
				}	
				compraDto.setTotalCompra(totalCompra);
				compraDto.setProductosCount(productosCount);				
				compraDto.setCompraId(compra.getCompraId());
				comprasDto.add(compraDto);
				
			}
			
		}
		comprasUsuarioDto.setCompras(comprasDto);
		
		return comprasUsuarioDto;
		
	}
	
	@Override
	public CompraDto getProductos(String dispositivoId, Long compraId) {
	    Compra compra = 	this.compraDao.findByUsuarioDispoisitivoAndCompraId(dispositivoId, compraId);
	    List<Producto> productos =  this.productoDao.findByUsuarioDispoisitivoAndCompraId(compra.getCompraId());
		
	    CompraDto compraDto = new CompraDto();
	    compraDto.setCompraId(compra.getCompraId());
	    compraDto.setNombre(compra.getNombre());
	    
	    BigDecimal totalCompra = new BigDecimal(0);
	    ArrayList<ProductoDto> productosDto = new ArrayList<>();
	    for(Producto p : productos) {
			ProductoDto productoDto = new ProductoDto();
			productoDto.setProductoId(p.getProductoId());
			productoDto.setNombre(p.getNombre());
			productoDto.setPrecio(p.getPrecio());
			productoDto.setChecked(p.isChecked());
			
			productosDto.add(productoDto);		
			if(p.getPrecio()!=null)
			totalCompra = totalCompra.add(p.getPrecio());
		}
	    compraDto.setProductos(productosDto);
	    compraDto.setTotalCompra(totalCompra);
		return compraDto;
	}

	@Override
	public boolean delete(String dispositivoId, Long compraId) {
		// TODO Auto-generated method stub
		Compra compra  =  compraDao.findByUsuarioDispoisitivoAndCompraId(dispositivoId, compraId);
	    List<Producto> productos =  this.productoDao.findByUsuarioDispoisitivoAndCompraId(compra.getCompraId());
		for(Producto producto : productos) {
			productoDao.delete(producto);
		}
		compraDao.delete(compra);
		return true;
	}
	@Override
	public boolean deleteProductos(String dispositivoId, Long compraId) {
		// TODO Auto-generated method stub
		Compra compra  =  compraDao.findByUsuarioDispoisitivoAndCompraId(dispositivoId, compraId);
	    List<Producto> productos =  this.productoDao.findByUsuarioDispoisitivoAndCompraId(compra.getCompraId());
		for(Producto producto : productos) {
			productoDao.deleteProducto(producto);
		}
		return true;
	}

	

	


	
	
}
