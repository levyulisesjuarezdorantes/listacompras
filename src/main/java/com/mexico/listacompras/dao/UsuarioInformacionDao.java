package com.mexico.listacompras.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mexico.listacompras.entities.Usuario;
import com.mexico.listacompras.entities.UsuarioInformacion;

@Repository
public class UsuarioInformacionDao {

	@Autowired
private SessionFactory sessionFactory;

	public UsuarioInformacion save(UsuarioInformacion entity) {
	    sessionFactory.getCurrentSession().save(entity);
	 	return entity;
	}


}
