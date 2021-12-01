package com.mexico.listacompras.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mexico.listacompras.dao.UsuarioDao;
import com.mexico.listacompras.entities.Usuario;
import com.mexico.listacompras.service.UsuarioService;


@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioDao usuarioRepository;

	

	@Override
	public Usuario create(Usuario entity) {
		return usuarioRepository.save(entity);
	}



	@Override
	public boolean existByDispositivoId(String dispositivoId) {
		return !this.usuarioRepository.existByDispositivoId(dispositivoId).isEmpty();

	}



	@Override
	public Usuario findByDispositivoId(String dispositivoId) {
		return this.usuarioRepository.findByDispositivoId(dispositivoId);
	}
	
	
}
