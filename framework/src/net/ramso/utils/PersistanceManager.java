package net.ramso.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersistanceManager {

	private static final Logger logger = LoggerFactory.getLogger(PersistanceManager.class);

	private static EntityManagerFactory entityManagerFactory;

	private static Properties properties = new Properties();

	public static void init(String path, String unit) throws FileNotFoundException, IOException {

		try {
			properties.load(new FileReader(path));
			entityManagerFactory = Persistence.createEntityManagerFactory(unit, properties);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static EntityManager getEntityManager() {

		return entityManagerFactory.createEntityManager();
	}

	public static void close(EntityManager entity) {
		entity.close();
	}

	public static void close() {
		entityManagerFactory.close();
	}

	public static <D> D executeNamedQueryResult(String namedQuery, List<Object> params) throws Exception {
		return executeNamedQueryResult(namedQuery, params, getEntityManager());
	}

	public static <D> D executeNamedQueryResult(String namedQuery, List<Object> params, EntityManager em) throws Exception {
		Query query = em.createNamedQuery(namedQuery);
		return executeQueryResult(query, params);
	}

	public static <D> Collection<D> executeNamedQueryResults(String namedQuery, List<Object> params) throws Exception {
		return executeNamedQueryResults(namedQuery, params, getEntityManager());
	}

	public static <D> Collection<D> executeNamedQueryResults(String namedQuery, List<Object> params, EntityManager em) throws Exception {
		Query query = em.createNamedQuery(namedQuery);
		return executeQueryResults(query, params);
	}

	public static <D> D executeNamedQueryResult(String namedQuery, Map<String, Object> params) throws Exception {
		return executeNamedQueryResult(namedQuery, params, getEntityManager());
	}

	public static <D> D executeNamedQueryResult(String namedQuery, Map<String, Object> params, EntityManager em) throws Exception {
		Query query = em.createNamedQuery(namedQuery);
		return executeQueryResult(query, params);
	}

	public static <D> Collection<D> executeNamedQueryResults(String namedQuery, Map<String, Object> params) throws Exception {
		return executeNamedQueryResults(namedQuery, params, getEntityManager());
	}

	public static <D> Collection<D> executeNamedQueryResults(String namedQuery, Map<String, Object> params, EntityManager em) throws Exception {
		Query query = em.createNamedQuery(namedQuery);
		return executeQueryResults(query, params);
	}

	/**
	 * get collection objects from process query with arry param values, in jpa params are ?1, ?9
	 * etc example select c from entity c where c.columname1 = ?1 and c.columname2 = ?2
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public static <D> Collection<D> executeQueryResults(Query query, Object... params) throws Exception {
		StringBuffer queryTrace = new StringBuffer(query.toString());
		int index = 1;
		if (params != null) {

			for (Object object : params) {
				queryTrace.append("Param ").append(index).append(": ").append(object);
				query.setParameter(index++, object);
			}
		}
		logger.info("Query to execute: " + queryTrace.toString());
		Object result = query.getResultList();
		return (Collection<D>) result;
	}

	/**
	 * get object from process query with arry param values, in jpa params are ?1, ?9 etc example
	 * select c from entity c where c.columname1 = ?1 and c.columname2 = ?2
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public static <D> D executeQueryResult(Query query, Object... params) throws Exception {
		StringBuffer queryTrace = new StringBuffer(query.toString());
		int index = 1;
		if (params != null) {

			for (Object object : params) {
				queryTrace.append("Param ").append(index).append(": ").append(object);
				query.setParameter(index++, object);
			}
		}
		logger.info("Query to execute: " + queryTrace.toString());
		Object result = query.getSingleResult();
		return (D) result;
	}

	/**
	 * get collection objects from process query with Map param values, in jpa params are ?1, ?9 etc
	 * example select c from entity c where c.columname1 = ?1 and c.columname2 = ?2
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public static <D> Collection<D> executeQueryResults(Query query, Map<String, Object> params) throws Exception {
		StringBuffer queryTrace = new StringBuffer(query.toString());
		if (params != null) {

			for (String key : params.keySet()) {
				queryTrace.append("Param ").append(key).append(": ").append(params.get(key));
				query.setParameter(key, params.get(key));
			}
		}
		logger.info("Query to execute: " + queryTrace.toString());
		Object result = query.getResultList();
		return (Collection<D>) result;
	}

	/**
	 * get object from process query with Map param values, in jpa params are ?1, ?9 etc example
	 * select c from entity c where c.columname1 = ?1 and c.columname2 = ?2
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public static <D> D executeQueryResult(Query query, Map<String, Object> params) throws Exception {
		StringBuffer queryTrace = new StringBuffer(query.toString());
		if (params != null) {

			for (String key : params.keySet()) {
				queryTrace.append("Param ").append(key).append(": ").append(params.get(key));
				query.setParameter(key, params.get(key));
			}
		}
		logger.info("Query to execute: " + queryTrace.toString());
		Object result = query.getSingleResult();
		return (D) result;
	}

	/**
	 * get collection objects from process query with List param values, in jpa params are ?1, ?9
	 * etc example select c from entity c where c.columname1 = ?1 and c.columname2 = ?2
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public static <D> Collection<D> executeQueryResults(Query query, List<Object> params) throws Exception {
		StringBuffer queryTrace = new StringBuffer(query.toString());
		int index = 1;
		if (params != null) {
			for (Iterator<Object> iterator = params.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();
				queryTrace.append("Param ").append(index).append(": ").append(object);
				query.setParameter(index++, object);

			}
		}
		logger.info("Query to execute: " + queryTrace.toString());
		Object result = query.getResultList();
		return (Collection<D>) result;

	}

	/**
	 * get object from process query with List param values, in jpa params are ?1, ?9 etc example
	 * select c from entity c where c.columname1 = ?1 and c.columname2 = ?2
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public static <D> D executeQueryResult(Query query, List<Object> params) throws Exception {
		StringBuffer queryTrace = new StringBuffer(query.toString());
		int index = 1;
		if (params != null) {

			for (Iterator<Object> iterator = params.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();
				queryTrace.append("Param ").append(index).append(": ").append(object);
				query.setParameter(index++, object);
			}
		}
		logger.info("Query to execute: " + queryTrace.toString());
		Object result = query.getSingleResult();
		return (D) result;
	}

}
