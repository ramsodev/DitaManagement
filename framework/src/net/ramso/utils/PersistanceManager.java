package net.ramso.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
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

	private static EntityManagerFactory entityManagerFactory;

	private static final Logger logger = LoggerFactory
			.getLogger(PersistanceManager.class);

	private static Properties properties = new Properties();

	public static void close() {
		PersistanceManager.entityManagerFactory.close();
	}

	public static void close(EntityManager entity) {
		entity.close();
	}

	public static <D> D executeNamedQueryResult(String namedQuery,
			List<Object> params) throws Exception {
		return PersistanceManager.executeNamedQueryResult(namedQuery, params,
				PersistanceManager.getEntityManager());
	}

	public static <D> D executeNamedQueryResult(String namedQuery,
			List<Object> params, EntityManager em) throws Exception {
		final Query query = em.createNamedQuery(namedQuery);
		return PersistanceManager.executeQueryResult(query, params);
	}

	public static <D> D executeNamedQueryResult(String namedQuery,
			Map<String, Object> params) throws Exception {
		return PersistanceManager.executeNamedQueryResult(namedQuery, params,
				PersistanceManager.getEntityManager());
	}

	public static <D> D executeNamedQueryResult(String namedQuery,
			Map<String, Object> params, EntityManager em) throws Exception {
		final Query query = em.createNamedQuery(namedQuery);
		return PersistanceManager.executeQueryResult(query, params);
	}

	public static <D> Collection<D> executeNamedQueryResults(String namedQuery,
			List<Object> params) throws Exception {
		return PersistanceManager.executeNamedQueryResults(namedQuery, params,
				PersistanceManager.getEntityManager());
	}

	public static <D> Collection<D> executeNamedQueryResults(String namedQuery,
			List<Object> params, EntityManager em) throws Exception {
		final Query query = em.createNamedQuery(namedQuery);
		return PersistanceManager.executeQueryResults(query, params);
	}

	public static <D> Collection<D> executeNamedQueryResults(String namedQuery,
			Map<String, Object> params) throws Exception {
		return PersistanceManager.executeNamedQueryResults(namedQuery, params,
				PersistanceManager.getEntityManager());
	}

	public static <D> Collection<D> executeNamedQueryResults(String namedQuery,
			Map<String, Object> params, EntityManager em) throws Exception {
		final Query query = em.createNamedQuery(namedQuery);
		return PersistanceManager.executeQueryResults(query, params);
	}

	/**
	 * get object from process query with List param values, in jpa params are
	 * ?1, ?9 etc example select c from entity c where c.columname1 = ?1 and
	 * c.columname2 = ?2
	 *
	 * */
	@SuppressWarnings("unchecked")
	public static <D> D executeQueryResult(Query query, List<Object> params)
			throws Exception {
		final StringBuffer queryTrace = new StringBuffer(query.toString());
		int index = 1;
		if (params != null) {

			for (final Object object : params) {
				queryTrace.append("Param ").append(index).append(": ")
						.append(object);
				query.setParameter(index++, object);
			}
		}
		PersistanceManager.logger.info("Query to execute: "
				+ queryTrace.toString());
		final Object result = query.getSingleResult();
		return (D) result;
	}

	/**
	 * get object from process query with Map param values, in jpa params are
	 * ?1, ?9 etc example select c from entity c where c.columname1 = ?1 and
	 * c.columname2 = ?2
	 *
	 * */
	@SuppressWarnings("unchecked")
	public static <D> D executeQueryResult(Query query,
			Map<String, Object> params) throws Exception {
		final StringBuffer queryTrace = new StringBuffer(query.toString());
		if (params != null) {

			for (final String key : params.keySet()) {
				queryTrace.append("Param ").append(key).append(": ")
						.append(params.get(key));
				query.setParameter(key, params.get(key));
			}
		}
		PersistanceManager.logger.info("Query to execute: "
				+ queryTrace.toString());
		final Object result = query.getSingleResult();
		return (D) result;
	}

	/**
	 * get object from process query with arry param values, in jpa params are
	 * ?1, ?9 etc example select c from entity c where c.columname1 = ?1 and
	 * c.columname2 = ?2
	 *
	 * */
	@SuppressWarnings("unchecked")
	public static <D> D executeQueryResult(Query query, Object... params)
			throws Exception {
		final StringBuffer queryTrace = new StringBuffer(query.toString());
		int index = 1;
		if (params != null) {

			for (final Object object : params) {
				queryTrace.append("Param ").append(index).append(": ")
						.append(object);
				query.setParameter(index++, object);
			}
		}
		PersistanceManager.logger.info("Query to execute: "
				+ queryTrace.toString());
		final Object result = query.getSingleResult();
		return (D) result;
	}

	/**
	 * get collection objects from process query with List param values, in jpa
	 * params are ?1, ?9 etc example select c from entity c where c.columname1 =
	 * ?1 and c.columname2 = ?2
	 *
	 * */
	@SuppressWarnings("unchecked")
	public static <D> Collection<D> executeQueryResults(Query query,
			List<Object> params) throws Exception {
		final StringBuffer queryTrace = new StringBuffer(query.toString());
		int index = 1;
		if (params != null) {
			for (final Object object : params) {
				queryTrace.append("Param ").append(index).append(": ")
						.append(object);
				query.setParameter(index++, object);

			}
		}
		PersistanceManager.logger.info("Query to execute: "
				+ queryTrace.toString());
		final Object result = query.getResultList();
		return (Collection<D>) result;

	}

	/**
	 * get collection objects from process query with Map param values, in jpa
	 * params are ?1, ?9 etc example select c from entity c where c.columname1 =
	 * ?1 and c.columname2 = ?2
	 *
	 * */
	@SuppressWarnings("unchecked")
	public static <D> Collection<D> executeQueryResults(Query query,
			Map<String, Object> params) throws Exception {
		final StringBuffer queryTrace = new StringBuffer(query.toString());
		if (params != null) {

			for (final String key : params.keySet()) {
				queryTrace.append("Param ").append(key).append(": ")
						.append(params.get(key));
				query.setParameter(key, params.get(key));
			}
		}
		PersistanceManager.logger.info("Query to execute: "
				+ queryTrace.toString());
		final Object result = query.getResultList();
		return (Collection<D>) result;
	}

	/**
	 * get collection objects from process query with arry param values, in jpa
	 * params are ?1, ?9 etc example select c from entity c where c.columname1 =
	 * ?1 and c.columname2 = ?2
	 *
	 * */
	@SuppressWarnings("unchecked")
	public static <D> Collection<D> executeQueryResults(Query query,
			Object... params) throws Exception {
		final StringBuffer queryTrace = new StringBuffer(query.toString());
		int index = 1;
		if (params != null) {

			for (final Object object : params) {
				queryTrace.append("Param ").append(index).append(": ")
						.append(object);
				query.setParameter(index++, object);
			}
		}
		PersistanceManager.logger.info("Query to execute: "
				+ queryTrace.toString());
		final Object result = query.getResultList();
		return (Collection<D>) result;
	}

	public static EntityManager getEntityManager() {

		return PersistanceManager.entityManagerFactory.createEntityManager();
	}

	public static void init(String path, String unit)
			throws FileNotFoundException, IOException {

		try {
			PersistanceManager.properties.load(new FileReader(path));
			PersistanceManager.entityManagerFactory = Persistence
					.createEntityManagerFactory(unit,
							PersistanceManager.properties);
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

}
