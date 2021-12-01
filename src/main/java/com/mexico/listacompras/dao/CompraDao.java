package com.mexico.listacompras.dao;


import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;


import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mexico.listacompras.entities.Compra;
import com.mexico.listacompras.entities.Usuario;

@Repository
public class CompraDao {

	@Autowired
private SessionFactory sessionFactory;

	public Compra save(Compra entity) {
	    sessionFactory.getCurrentSession().save(entity);
	 	return entity;
	}
	public Compra findByUsuarioDispoisitivoAndCompraId(String dispositivoId,Long compraId) {
		 String select = "select c FROM Compra c INNER JOIN Usuario u ON c.usuario.usuarioId = u.usuarioId WHERE c.compraId =:compraId AND u.dispositivoId =:dispositivoId";
		 Compra compra = (Compra) this.sessionFactory.getCurrentSession().createQuery(select).
				 setParameter("dispositivoId", dispositivoId).
				 setParameter("compraId", compraId).
				 setMaxResults(1).getSingleResult();
	     return compra;
	}
	public List<Compra> findByUsuarioDispositivoId(String dispositivoId) {
		 String select = "select c FROM Compra c INNER JOIN Usuario u ON c.usuario.usuarioId = u.usuarioId WHERE u.dispositivoId =:dispositivoId";
		 List<Compra> compras = (List<Compra>)this.sessionFactory.getCurrentSession().createQuery(select).
				 setParameter("dispositivoId", dispositivoId).getResultList();
	     return compras;
	}
	public Compra update(Compra compra) {
	 Transaction tx =	sessionFactory.getCurrentSession().beginTransaction();
		sessionFactory.getCurrentSession().update(compra);
		 tx.commit();
		
		 return compra;
	}
	public void delete(Compra compra) {
		// TODO Auto-generated method stub
		 Transaction tx =	sessionFactory.getCurrentSession().beginTransaction();
			
		sessionFactory.getCurrentSession().delete(compra);
		tx.commit();
			
	}
	

}
