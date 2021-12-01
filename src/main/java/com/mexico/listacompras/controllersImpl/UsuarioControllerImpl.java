package com.mexico.listacompras.controllersImpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mexico.listacompras.controllers.UsuarioController;
import com.mexico.listacompras.dto.CompraDto;
import com.mexico.listacompras.dto.CompraUsuarioDto;
import com.mexico.listacompras.dto.ComprasUsuarioDto;
import com.mexico.listacompras.dto.MensajeOkResponseDto;
import com.mexico.listacompras.dto.ProductoDto;
import com.mexico.listacompras.dto.UsuarioRegistroDto;
import com.mexico.listacompras.entities.Compra;
import com.mexico.listacompras.entities.Direccion;
import com.mexico.listacompras.entities.Pais;
import com.mexico.listacompras.entities.Producto;
import com.mexico.listacompras.entities.Usuario;
import com.mexico.listacompras.entities.UsuarioInformacion;
import com.mexico.listacompras.service.CompraService;
import com.mexico.listacompras.service.DireccionService;
import com.mexico.listacompras.service.PaisService;
import com.mexico.listacompras.service.ProductoService;
import com.mexico.listacompras.service.UsuarioInformacionService;
import com.mexico.listacompras.service.UsuarioService;

@RequestMapping("/usuarios")
@RestController
public class UsuarioControllerImpl implements UsuarioController {

	@Autowired
	DireccionService direccionService;
	
	@Autowired
	UsuarioInformacionService usuarioInformacionService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	PaisService paisService;
	
	@Autowired
	CompraService compraService;
	
	@Autowired
	ProductoService productoService;
	
	@Override
	@PostMapping
	public ResponseEntity<String> post(@RequestBody UsuarioRegistroDto usuarioRegistroDto) {
		Timestamp now = new Timestamp(System.currentTimeMillis());		
		
		if(usuarioService.existByDispositivoId(usuarioRegistroDto.getDispositivoId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Dispositivo existente");
		}
		
		
		Usuario usuario = new Usuario();
		usuario.setActivo(true);
		usuario.setDispositivoId(usuarioRegistroDto.getDispositivoId());
		usuario.setFechaCreacion(now);
		usuario.setFechaUltimaActualizacion(now);
		usuario.setNombreUsuario(usuarioRegistroDto.getNombreUsuario());
		usuario.setPassword(usuarioRegistroDto.getPassword());
	
		
		UsuarioInformacion usuarioInformacion = new UsuarioInformacion();
		
		Direccion direccion = new Direccion();
		direccion.setActivo(true);
		direccion.setFechaCreacion(now);
		
		boolean exists = paisService.exists(usuarioRegistroDto.getPaisId());
		if(exists) {
			Pais pais = paisService.getOneById(usuarioRegistroDto.getPaisId());			
			direccion.setPais(pais);
		}
		
	    Direccion savedDireccion = 	direccionService.insert(direccion);
		usuarioInformacion.setDireccion(savedDireccion);
		
		
		usuarioInformacion.setActivo(true);		
		usuarioInformacion.setFechaCreacion(now);
		usuarioInformacion.setFechaUltimaActualizacion(now);
		usuarioInformacion.setEdad(usuarioRegistroDto.getEdad());
		usuarioInformacion.setGenero(usuarioRegistroDto.getGenero());
		usuarioInformacion.setNombre(usuarioRegistroDto.getNombre());
	  
		UsuarioInformacion usuarioInformacionSaved = usuarioInformacionService.create(usuarioInformacion); 
		
	  	usuario.setUsuarioInformacion(usuarioInformacionSaved);
	  	Usuario saved = usuarioService.create(usuario);
	  	
	  	return new ResponseEntity<String>(saved.getUsuarioId().toString(), HttpStatus.CREATED);
	
	}
	@PostMapping("/{dispositivoId}/compras")
	public ResponseEntity<CompraDto> createListaUsuario(@PathVariable String dispositivoId,@RequestBody CompraUsuarioDto compraUsuarioDto) {
		Timestamp now = new Timestamp(System.currentTimeMillis());		
		
		if(!usuarioService.existByDispositivoId(dispositivoId)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		Usuario usuario = usuarioService.findByDispositivoId(dispositivoId);
		
		
		Compra compra = new Compra();
		compra.setActivo(true);

    	compra.setFechaCreacion(now);
		compra.setFechaUltimaActualizacion(now);
		compra.setNombre(compraUsuarioDto.getNombreLista());
		compra.setUsuario(usuario);
		
		
		Compra result = compraService.create(compra);
		CompraDto compraDto = new CompraDto();
		compraDto.setCompraId(result.getCompraId());
		compraDto.setNombre(result.getNombre());
		compraDto.setTotalCompra(new BigDecimal(0.0));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(compraDto);
	}	
	@GetMapping("/{dispositivoId}/compras/{compraId}/productos")
	public ResponseEntity<CompraDto> GetProductos(@PathVariable Long compraId,@PathVariable String dispositivoId){
		return  ResponseEntity.ok().body(compraService.getProductos(dispositivoId, compraId));	
	}
	@PostMapping("/{dispositivoId}/compras/{compraId}/productos")
	public ResponseEntity<ProductoDto> PostProductoInCompra(@RequestBody ProductoDto productoDto,@PathVariable Long compraId,@PathVariable String dispositivoId){
		Timestamp now = new Timestamp(System.currentTimeMillis());		
		Producto producto = new Producto();
		producto.setActivo(true);

    	producto.setFechaCreacion(now);
		producto.setFechaUltimaActualizacion(now);
		producto.setNombre(productoDto.getNombre());
	
		

		
		Compra compra = compraService.findByUsuarioDispositivoIdAndCompraId(dispositivoId, compraId);
		
		if(compra!=null) {
			producto.setCompra(compra);
			compra.setFechaUltimaActualizacion(now);
			compraService.addProducto(compra, producto);		
			productoDto.setProductoId(producto.getProductoId());			
			return ResponseEntity.ok(productoDto);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	@PutMapping("/{dispositivoId}/compras/{compraId}/productos/{productoId}")
	public ResponseEntity<MensajeOkResponseDto> PostProducto(@RequestBody ProductoDto productoDto,@PathVariable Long productoId,@PathVariable Long compraId,@PathVariable String dispositivoId){
		
		Producto producto = productoService.findByIdAndCompraId(productoId,compraId);
		if(producto==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensajeOkResponseDto("Producto not found"));
		}		
		else {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			producto.setFechaUltimaActualizacion(now);
			producto.setChecked(productoDto.isChecked());
			producto.setNombre(productoDto.getNombre());
			producto.setPrecio(productoDto.getPrecio());
			productoService.update(producto);
			return ResponseEntity.ok(new MensajeOkResponseDto("Actualizado exitosamente"));
		}		
	}	
	@GetMapping("/{dispositivoId}/compras")
	@Override
	public ResponseEntity<ComprasUsuarioDto> getComprasDispositivoId(@PathVariable String dispositivoId) {
		Usuario usuario = this.usuarioService.findByDispositivoId(dispositivoId);
		if(usuario==null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	   ComprasUsuarioDto compras = 	this.compraService.getCompras(dispositivoId);
	   compras.setNombreUsuario(usuario.getNombreUsuario());
	   return ResponseEntity.ok(compras);
	}
	@GetMapping("/{dispositivoId}")
	public ResponseEntity GetByDispositivoId(@PathVariable String dispositivoId) {
		Usuario usuario = this.usuarioService.findByDispositivoId(dispositivoId);
		if(usuario==null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	    return ResponseEntity.ok().build();
	}
	@DeleteMapping("/{dispositivoId}/compras/{compraId}")
	public ResponseEntity<MensajeOkResponseDto> DeleteCompra(@PathVariable Long compraId,@PathVariable String dispositivoId){
		if(compraService.delete(dispositivoId, compraId)) {
			return  ResponseEntity.ok().body(new MensajeOkResponseDto("Borrado"));
		}
		else   {
			return    ResponseEntity.status(HttpStatus.CONFLICT).body(new MensajeOkResponseDto("Couldnt delete"));	
		}
			
	}
	@DeleteMapping("/{dispositivoId}/compras/{compraId}/productos/{productoId}")
	public ResponseEntity<MensajeOkResponseDto> DeleteProducto(@PathVariable Long compraId,@PathVariable String dispositivoId,@PathVariable Long productoId){
		Compra compra  =  compraService.findByUsuarioDispositivoIdAndCompraId(dispositivoId, compraId);
		if(compra!=null) {
			productoService.deleteProducto(productoId, compra.getCompraId());
			return  ResponseEntity.ok().body(new MensajeOkResponseDto("Borrado"));
		}else {
			return    ResponseEntity.status(HttpStatus.CONFLICT).body(new MensajeOkResponseDto("Couldn delete, compra not found"));
		}		
	
	}
	@DeleteMapping("/{dispositivoId}/compras/{compraId}/productos")
	public ResponseEntity<MensajeOkResponseDto> DeleteProductos(@PathVariable Long compraId,@PathVariable String dispositivoId){
			if(compraService.deleteProductos(dispositivoId, compraId)) {
				return  ResponseEntity.ok().body(new MensajeOkResponseDto("Borrado"));		
			}else {
				return  ResponseEntity.status(HttpStatus.CONFLICT).body(new MensajeOkResponseDto("no se pudo borrar"));	
			}
					
	}
}
