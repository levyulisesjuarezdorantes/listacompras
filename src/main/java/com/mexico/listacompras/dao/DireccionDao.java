package com.mexico.listacompras.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mexico.listacompras.entities.Direccion;

@Repository
public class DireccionDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Direccion save(Direccion direccion) {
		sessionFactory.getCurrentSession().save(direccion);
		return direccion;
	}
}
