package com.mexico.listacompras.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mexico.listacompras.dao.PaisDao;
import com.mexico.listacompras.entities.Pais;
import com.mexico.listacompras.service.PaisService;

@Service
public class PaisServiceImpl implements PaisService {

	@Autowired
	PaisDao paisRepository;
	
	@Override
	public List<Pais> findAll() {
		return paisRepository.findAll();
	}

	@Override
	public Pais findById(int paisId) {
		 return paisRepository.findById(paisId);
	}
	public boolean exists(int paisId) {
		return paisRepository.existsById(paisId);
	}

	@Override
	public Pais getOneById(int paisId) {
		return paisRepository.getById(paisId);
	}

}
