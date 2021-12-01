package com.mexico.listacompras.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mexico.listacompras.entities.Pais;

@Repository
public class PaisDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Pais> findAll() {
		CriteriaQuery<Pais> query = sessionFactory.getCurrentSession().getCriteriaBuilder().createQuery(Pais.class);
		Root<Pais> root =  query.from(Pais.class);
		query.select(root);
		return sessionFactory.getCurrentSession().createQuery(query).list();		
		 
	}

	public Pais findById(int paisId) {
		CriteriaBuilder builder  = sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Pais> query = builder.createQuery(Pais.class);
		Root<Pais> root = query.from(Pais.class);
		query.select(root).where(builder.equal(root.get("paisId"),paisId));
		
		return sessionFactory.getCurrentSession().createQuery(query).getSingleResult();		
		
	}
	public boolean existsById(int paisId) {
			Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT count(*) from pais as p where p.paisId=:paisIdentificador");
			query.setParameter("paisIdentificador",paisId);
			BigInteger result  =  (BigInteger) query.getSingleResult();
			return result.intValue()>0;
			
	}

	public Pais getById(int paisId) {
		CriteriaBuilder builder  = sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Pais> query = builder.createQuery(Pais.class);
		Root<Pais> root = query.from(Pais.class);
		query.select(root).where(builder.equal(root.get("paisId"),paisId));
		return sessionFactory.getCurrentSession().createQuery(query).getSingleResult();	
		
	}
}
