package com.mexico.listacompras.controllers;
import org.springframework.http.ResponseEntity;

import com.mexico.listacompras.dto.ComprasUsuarioDto;
import com.mexico.listacompras.dto.UsuarioRegistroDto;
public interface UsuarioController {


	 ResponseEntity<String> post(UsuarioRegistroDto usuarioRegistroDto);
	 ResponseEntity<ComprasUsuarioDto> getComprasDispositivoId(String dispositivoId);
}
