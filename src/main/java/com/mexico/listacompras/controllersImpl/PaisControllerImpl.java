package com.mexico.listacompras.controllersImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mexico.listacompras.controllers.PaisController;
import com.mexico.listacompras.entities.Pais;
import com.mexico.listacompras.service.PaisService;

@RequestMapping("/paises")
@RestController
public class PaisControllerImpl implements PaisController {

	@Autowired
	PaisService paisService;
	
	@GetMapping
	@Override
	public List<Pais> getPaises() {
		return paisService.findAll();
	}

}
