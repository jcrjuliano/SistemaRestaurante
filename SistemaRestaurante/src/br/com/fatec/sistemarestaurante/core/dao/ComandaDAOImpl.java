package br.com.fatec.sistemarestaurante.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.google.common.collect.Lists;

import br.com.fatec.sistemarestaurante.api.dao.ComandaDAO;
import br.com.fatec.sistemarestaurante.api.entity.Comanda;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import static br.com.spektro.minispring.core.dbmapper.ConfigDBMapper.getDefaultConnectionType;
public class ComandaDAOImpl implements ComandaDAO {

	@Override
	public Long save(Comanda comandaSalvar) {
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			
			String colunas = DAOUtils.getColunas(ConfigDBMapper.getDefaultConnectionType(), Comanda.getColunas());
			
			String values = DAOUtils.completarClausulaValues(getDefaultConnectionType(), 2, "SEQ_SCR_COMANDA");
			
			String sql = "INSERT INTO " + Comanda.TABLE + colunas + " VALUES " + values;
			
			insert = DAOUtils.criarStatment(sql, conn, getDefaultConnectionType(), Comanda.getColunasArray());
			
			insert.setDouble(1, comandaSalvar.getValorTotal());
			insert.setString(2, comandaSalvar.getDataAbertura());
			insert.setString(3,  comandaSalvar.getDataFechamento());
			
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
	public Comanda findById(Long id) {
		Connection conn = null;
		PreparedStatement find = null;
		Comanda comanda = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "SELECT * FROM " + Comanda.TABLE + " WHERE "
					+ Comanda.COL_ID + " = ?";
			find = conn.prepareStatement(sql);
			find.setLong(1, id);
			ResultSet rs = find.executeQuery();
			if (rs.next()) {
				comanda = this.buildComanda(rs);
			}
			return comanda;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(find);
		}
	}

	@Override
	public List<Comanda> findAll() {
		Connection conn = null;
		PreparedStatement findAll = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			findAll = conn.prepareStatement("SELECT * FROM " + Comanda.TABLE);
			ResultSet rs = findAll.executeQuery();
			return this.buildComandas(rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(findAll);
		}
	}

	@Override
	public void update(Comanda comandaAtualizar) {
		Connection conn = null;
		PreparedStatement update = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + Comanda.TABLE + " SET "
					+ Comanda.COL_VALOR_TOTAL + " = ?, " + Comanda.COL_DATA_ABERTURA + " = ?, " + Comanda.COL_DATA_FECHAMENTO + " = ? " 
					+ " WHERE " + Comanda.COL_ID + " = ?");
			update.setDouble(1, comandaAtualizar.getValorTotal());
			update.setString(2, comandaAtualizar.getDataAbertura());
			update.setString(3, comandaAtualizar.getDataFechamento());
			update.setLong(4, comandaAtualizar.getId());
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
			String sql = "DELETE FROM " + Comanda.TABLE + " WHERE ID = ?";
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
	
	private Comanda buildComanda(ResultSet rs) throws SQLException {
		Comanda comanda = new Comanda();
		comanda.setId(rs.getLong(Comanda.COL_ID));
		comanda.setValorTotal(rs.getDouble(Comanda.COL_VALOR_TOTAL));
		comanda.setDataAbertura(rs.getString(Comanda.COL_DATA_ABERTURA));
		comanda.setDataFechamento(rs.getString(Comanda.COL_DATA_ABERTURA));
		return comanda;
	}
	
	private List<Comanda> buildComandas(ResultSet rs) throws SQLException {
		List<Comanda> comandas = Lists.newArrayList();
		while (rs.next()) {
			comandas.add(this.buildComanda(rs));
		}
		return comandas;
	}


}
