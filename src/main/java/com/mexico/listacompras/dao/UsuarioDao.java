package com.mexico.listacompras.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mexico.listacompras.entities.Usuario;

@Repository
public class UsuarioDao {

	@Autowired
private SessionFactory sessionFactory;

	public Usuario save(Usuario entity) {
	    sessionFactory.getCurrentSession().save(entity);
	 	return entity;
	}



	public List<Usuario> existByDispositivoId(String dispositivoId) {
	    CriteriaBuilder builder = 	 sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery =  builder.createQuery(Usuario.class);
	    Root<Usuario> root = criteriaQuery.from(Usuario.class);
		criteriaQuery.select(root).where(builder.equal(root.get("dispositivoId"),dispositivoId));
		return sessionFactory.getCurrentSession().createQuery(criteriaQuery).setMaxResults(1).getResultList();
			
	}
	public Usuario findByDispositivoId(String dispositivoId) {
		CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		criteriaQuery.select(root).where(builder.equal(root.get("dispositivoId"),dispositivoId));
		return sessionFactory.getCurrentSession().createQuery(criteriaQuery).getResultList().stream().findFirst().orElse(null);
		
		
	}
}
