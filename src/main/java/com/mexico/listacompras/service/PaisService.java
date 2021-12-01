package com.mexico.listacompras.service;

import java.util.List;
import com.mexico.listacompras.entities.Pais;

public interface PaisService {

	public List<Pais> findAll();
	public Pais findById(int paisId);
	public Pais getOneById(int paisId);
	public boolean exists(int paisId);
}
