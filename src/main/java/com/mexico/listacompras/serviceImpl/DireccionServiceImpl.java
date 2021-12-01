package com.mexico.listacompras.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mexico.listacompras.dao.DireccionDao;
import com.mexico.listacompras.entities.Direccion;
import com.mexico.listacompras.service.DireccionService;


@Service
public class DireccionServiceImpl implements DireccionService {

	@Autowired
	DireccionDao direccionRepository;
	
	

	@Override
	public Direccion insert(Direccion direccion) {
		return direccionRepository.save(direccion);
	}

}
