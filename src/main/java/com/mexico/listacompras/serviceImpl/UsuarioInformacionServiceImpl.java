package com.mexico.listacompras.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mexico.listacompras.dao.UsuarioInformacionDao;
import com.mexico.listacompras.entities.UsuarioInformacion;
import com.mexico.listacompras.service.UsuarioInformacionService;

@Service
public class UsuarioInformacionServiceImpl implements UsuarioInformacionService {

	@Autowired
	UsuarioInformacionDao usuarioInformacionRepository;

	@Override
	public UsuarioInformacion create(UsuarioInformacion entity) {
	   UsuarioInformacion saved =  this.usuarioInformacionRepository.save(entity);
	   return saved;		
	}
	
	
}
