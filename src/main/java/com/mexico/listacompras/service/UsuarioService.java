package com.mexico.listacompras.service;

import com.mexico.listacompras.entities.Usuario;

public interface UsuarioService {

	public Usuario create(Usuario entity);
	public boolean existByDispositivoId(String dispositivoId);
	public Usuario findByDispositivoId(String dispositivoId);
}
