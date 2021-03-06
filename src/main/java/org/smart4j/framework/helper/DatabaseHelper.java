package org.smart4j.framework.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.util.CollectionUtil;

public class DatabaseHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);

	private static final ThreadLocal<Connection> CONNECTION_HOLDER;
	private static final QueryRunner QUERY_RUNNER;
	private static final BasicDataSource DATA_SOURCE;

	static {

		CONNECTION_HOLDER = new ThreadLocal<Connection>();
		QUERY_RUNNER = new QueryRunner();

		DATA_SOURCE = new BasicDataSource();
		DATA_SOURCE.setDriverClassName(ConfigHelper.getJdbcDriver());
		DATA_SOURCE.setUrl(ConfigHelper.getJdbcUrl());
		DATA_SOURCE.setUsername(ConfigHelper.getJdbcUsername());
		DATA_SOURCE.setPassword(ConfigHelper.getJdbcPassword());

		try {
			Class.forName(ConfigHelper.getJdbcDriver());
		} catch (ClassNotFoundException e) {
			LOGGER.error("can not load jdbc driver", e);
		}
	}

	public static Connection getConnection() {
		Connection conn = CONNECTION_HOLDER.get();
		if (conn == null) {
			try {
				conn = DATA_SOURCE.getConnection();
			} catch (SQLException e) {
				LOGGER.error("get connection failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.set(conn);
			}
		}
		return conn;
	}

	/**
	 * 查詢實體列表
	 * 
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
		List<T> entityList = null;
		try {
			Connection conn = getConnection();
			entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);
		} catch (SQLException e) {
			LOGGER.error("query entity list failure", e);
			throw new RuntimeException(e);
		}
		return entityList;
	}

	/**
	 * 查詢實體
	 * 
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
		T entity = null;
		try {
			Connection conn = getConnection();
			entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(entityClass), params);
		} catch (SQLException e) {
			LOGGER.error("query entity failure", e);
			throw new RuntimeException(e);
		}
		return entity;
	}

	/**
	 * 執行查詢語句
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Map<String, Object>> executeQuery(String sql, Object... params) {
		List<Map<String, Object>> result = null;
		try {
			Connection conn = getConnection();
			result = QUERY_RUNNER.query(conn, sql, new MapListHandler(), params);
		} catch (SQLException e) {
			LOGGER.error("execute query failure", e);
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 執行更新語句( 包含 update, insert, delete )
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int executeUpdate(String sql, Object... params) {
		int rows = 0;
		try {
			Connection conn = getConnection();
			rows = QUERY_RUNNER.update(conn, sql, params);
		} catch (SQLException e) {
			LOGGER.error("execute update failure", e);
			throw new RuntimeException(e);
		}
		return rows;
	}

	/**
	 * BO與TABLE名稱轉換
	 * 
	 * @param entityClass
	 * @return
	 */
	private static String getTableName(Class<?> entityClass) {
		return entityClass.getSimpleName();
	}

	/**
	 * 插入實體
	 * 
	 * @param entityClass
	 * @param fieldMap
	 * @return
	 */
	public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {
		if (CollectionUtil.isEmpty(fieldMap)) {
			LOGGER.error("can not insert entity: fieldMap is empty");
			return false;
		}

		String sql = "INSERT INTO " + getTableName(entityClass);
		StringBuilder columns = new StringBuilder("(");
		StringBuilder values = new StringBuilder("(");
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append(", ");
			values.append("?, ");
		}
		columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
		values.replace(values.lastIndexOf(", "), values.length(), ")");
		sql += columns + " VALUES " + values;

		Object[] params = fieldMap.values().toArray();

		return executeUpdate(sql, params) == 1;
	}

	/**
	 * 更新實體
	 * 
	 * @param entityClass
	 * @param id
	 * @param fieldMap
	 * @return
	 */
	public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap) {
		if (CollectionUtil.isEmpty(fieldMap)) {
			LOGGER.error("can not update entity: fieldMap is empty");
			return false;
		}

		String sql = "UPDATE " + getTableName(entityClass) + " SET ";
		StringBuilder columns = new StringBuilder();
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append("=?, ");
		}
		sql += columns.substring(0, columns.lastIndexOf(", ")) + " WHERE id=? ";

		List<Object> paramList = new ArrayList<Object>();
		paramList.addAll(fieldMap.values());
		paramList.add(id);
		Object[] params = paramList.toArray();

		return executeUpdate(sql, params) == 1;
	}

	/**
	 * 刪除實體
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public static <T> boolean deleteEntity(Class<T> entityClass, long id) {

		String sql = "DELETE FROM " + getTableName(entityClass) + " WHERE id=? ";
		return executeUpdate(sql, id) == 1;
	}

	public static void executeSqlFile(String filePath) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		try {
			String sql;
			while ((sql = reader.readLine()) != null) {
				DatabaseHelper.executeUpdate(sql);
			}
		} catch (IOException e) {
			LOGGER.error("execute sql file failure", e);
			throw new RuntimeException(e);
		}
	}
	
	public static void beginTransaction() {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				LOGGER.error("begin transaction failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.set(conn);
			}
		}
	}
	
	public static void commitTransaction() {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				LOGGER.error("commit transaction failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}
	}
	
	public static void rollbackTransaction() {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e) {
				LOGGER.error("rollback transaction failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}
	}
	
	public static DataSource getDataSource() {
		return DATA_SOURCE;
	}
	
}
