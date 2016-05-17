package br.com.fatec.sistemarestaurante.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.google.common.collect.Lists;

import br.com.fatec.sistemarestaurante.api.dao.IngredienteDAO;
import br.com.fatec.sistemarestaurante.api.entity.Comanda;
import br.com.fatec.sistemarestaurante.api.entity.Ingrediente;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import static br.com.spektro.minispring.core.dbmapper.ConfigDBMapper.getDefaultConnectionType;

public class IngredienteDAOImpl implements IngredienteDAO {

	@Override
	public Long save(Ingrediente ingredienteSalvar) {
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			
			String colunas = DAOUtils.getColunas(ConfigDBMapper.getDefaultConnectionType(), Ingrediente.getColunas());
			
			String values = DAOUtils.completarClausulaValues(getDefaultConnectionType(), 1, "SEQ_SCR_INGREDIENTE");
			
			String sql = "INSERT INTO " + Ingrediente.TABLE + colunas + " VALUES " + values;
			
			insert = DAOUtils.criarStatment(sql, conn, getDefaultConnectionType(), Ingrediente.getColunasArray());
			
			insert.setString(1, ingredienteSalvar.getDescricao());
			
			insert.execute();
			
			ResultSet generatedKeys = insert.getGeneratedKeys();
			if (generatedKeys.next()){
				return generatedKeys.getLong(1);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(insert);
			DbUtils.closeQuietly(conn);
		}
		return null;
	}

	@Override
	public Ingrediente findById(Long id) {
		Connection conn = null;
		PreparedStatement find = null;
		Ingrediente ingrediente = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "SELECT * FROM " + Ingrediente.TABLE + " WHERE "
					+ Ingrediente.COL_ID + " = ?";
			find = conn.prepareStatement(sql);
			find.setLong(1, id);
			ResultSet rs = find.executeQuery();
			if (rs.next()) {
				ingrediente = this.buildIngrediente(rs);
			}
			return ingrediente;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(find);
		}
	}

	@Override
	public List<Ingrediente> findAll() {
		Connection conn = null;
		PreparedStatement findAll = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			findAll = conn.prepareStatement("SELECT * FROM " + Ingrediente.TABLE);
			ResultSet rs = findAll.executeQuery();
			return this.buildIngredientes(rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(findAll);
		}
	}

	@Override
	public void update(Ingrediente ingredienteAtualizar) {
		Connection conn = null;
		PreparedStatement update = null;

		try {
			conn = ConfigDBMapper.getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + Ingrediente.TABLE + " SET "
					+ Ingrediente.COL_DESCRICAO + " = ? " + " WHERE " + Ingrediente.COL_ID + " = ?");
			update.setString(1, ingredienteAtualizar.getDescricao());
			update.setLong(2, ingredienteAtualizar.getId());
			update.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(update);
		}
		
	}

	@Override
	public void delete(Long id) {
		Connection conn = null;
		PreparedStatement delete = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "DELETE FROM " + Ingrediente.TABLE + " WHERE ID = ?";
			delete = conn.prepareStatement(sql);
			delete.setLong(1, id);
			delete.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(delete);
			DbUtils.closeQuietly(conn);
		}
		
	}
	
	private Ingrediente buildIngrediente(ResultSet rs) throws SQLException {
		Ingrediente ingrediente = new Ingrediente();
		ingrediente.setId(rs.getLong(Ingrediente.COL_ID));
		ingrediente.setDescricao(rs.getString(Ingrediente.COL_DESCRICAO));
		return ingrediente;
	}
	
	private List<Ingrediente> buildIngredientes(ResultSet rs) throws SQLException {
		List<Ingrediente> ingredientes = Lists.newArrayList();
		while (rs.next()) {
			ingredientes.add(this.buildIngrediente(rs));
		}
		return ingredientes;
	}


}
