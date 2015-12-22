package dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import data.Product;
import util.HibernateUtil;

public class GenericDaoImpl<T> implements GenericDao<T>{
	private static Logger log = Logger.getLogger(GenericDaoImpl.class);
	
	private Class<T> type;
	
	public GenericDaoImpl(Class<T> type) {
		this.type = type;
	}

	@Override
	public Long create(T obj) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Long id = null;
		try {
			session.beginTransaction();
			id = (Long) session.save(obj);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			log.error("Transaction failed");
			session.getTransaction().rollback();
		} finally {
			if (session != null)
				session.close();
		}
		return id;
	}

	@Override
	public T read(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		T obj = null;
		try {
			obj = (T) session.get(type, id);
		} catch (HibernateException e) {
			log.error("Transaction failed");
		} finally {
			session.close();
		}
		return obj;
	}

	@Override
	public void update(T obj) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			log.error("Transaction failed");
			session.getTransaction().rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}

	@Override
	public boolean delete(T obj) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(obj);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			log.error("Transaction failed");
			session.getTransaction().rollback();
			return false;
		} finally {
			if (session != null)
				session.close();			
		}
		return true;
	}

	@Override
	public List<T> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Query query = session.createQuery("from "+type.getSimpleName());
			return query.list();
		} finally {
			session.close();
		}
	}

}
