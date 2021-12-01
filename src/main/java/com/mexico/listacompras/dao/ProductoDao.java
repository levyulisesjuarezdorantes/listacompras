package com.mexico.listacompras.dao;


import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mexico.listacompras.entities.Producto;

@Repository
public class ProductoDao {

	@Autowired
private SessionFactory sessionFactory;
	public Producto findByIdAndCompra(Long productoId,Long compraId) {
		CriteriaBuilder builder  = sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Producto> query = builder.createQuery(Producto.class);
		Root<Producto> root = query.from(Producto.class);		
		Predicate predicateId = builder.equal(root.get("productoId"), productoId);
		Predicate predicateCompraId = builder.equal(root.join("compra").get("compraId"), compraId);		
		query.select(root).where(predicateId,predicateCompraId);	
		return sessionFactory.getCurrentSession().createQuery(query).getSingleResult();	

	}
	public Producto save(Producto entity) {
	    sessionFactory.getCurrentSession().save(entity);
	 	return entity;
	}
	public Producto update(Producto entity) {
		 Transaction tx =	sessionFactory.getCurrentSession().beginTransaction();
			sessionFactory.getCurrentSession().update(entity);
			 tx.commit();
	   
	 	return entity;
	}
	public List<Producto> findByUsuarioDispoisitivoAndCompraId(Long compraId) {
		 String select = "select p FROM Producto p  WHERE p.compra.compraId =:compraId";
		 List<Producto> productos = (List<Producto>) this.sessionFactory.getCurrentSession().createQuery(select).
				 setParameter("compraId", compraId).getResultList();

		 return productos;
	}
	public void delete(Producto producto) {
		sessionFactory.getCurrentSession().delete(producto);		
	}
	public void deleteProducto(Producto producto) {
		 Transaction tx =	sessionFactory.getCurrentSession().beginTransaction();
			sessionFactory.getCurrentSession().delete(producto);
			 tx.commit();
	   
	}

}
