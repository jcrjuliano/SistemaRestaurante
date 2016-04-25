package br.com.fatec.sistemarestaurante.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class DAOUtils {

	public static String completarClausulaValues(Class driverClass, int qtdParametros,
			String sequence) {
		String classname = driverClass.getName();
		StringBuilder clausula = new StringBuilder();
		switch (classname) {
			case "org.hsqldb.jdbcDriver":
			case "org.postgresql.Driver":
				clausula.append("nextval('" + sequence + "'),");
				break;
			case "oracle.jdbc.driver.OracleDriver":
				clausula.append(sequence + ".nextVal,");
				break;
			case "com.mysql.jdbc.Driver":
				// do nothing
				break;
		}

		clausula.append(StringUtils.repeat("?", ", ", qtdParametros));

		return "(" + clausula.toString() + ")";
	}

	public static String getColunas(Class dbClass, List<String> nomeColunas) {
		String classname = dbClass.getName();
		String colunas = null;
		switch (classname) {
			case "org.hsqldb.jdbcDriver":
			case "org.postgresql.Driver":
			case "oracle.jdbc.driver.OracleDriver":
				colunas = StringUtils.join(nomeColunas, ", ");
				break;
			case "com.mysql.jdbc.Driver":
				nomeColunas = nomeColunas.subList(1, nomeColunas.size());
				colunas = StringUtils.join(nomeColunas, ", ");
				break;
		}
		return "(" + colunas + ")";
	}

	public static PreparedStatement criarStatment(String sql, Connection conn,
			Class dbClass, String[] colunas) throws SQLException {
		String classname = dbClass.getName();
		switch (classname) {
			case "org.postgresql.Driver":
			case "com.mysql.jdbc.Driver":
				return conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			case "org.hsqldb.jdbcDriver":
			case "oracle.jdbc.driver.OracleDriver":
				return conn.prepareStatement(sql, colunas);
		}
		return null;
	}

	public static String preparePlaceHolders(int length) {
		return StringUtils.join(Collections.nCopies(length, "?"), ",");
	}

	public static void setValues(PreparedStatement preparedStatement, List<Long> valore)
			throws SQLException {
		for (int i = 0; i < valore.size(); i++) {
			preparedStatement.setObject(i + 1, valore.get(i));
		}
	}

}
