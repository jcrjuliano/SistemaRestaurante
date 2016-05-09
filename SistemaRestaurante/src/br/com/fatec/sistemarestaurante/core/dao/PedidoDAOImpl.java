package br.com.fatec.sistemarestaurante.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.google.common.collect.Lists;

import br.com.fatec.sistemarestaurante.api.dao.PedidoDAO;
import br.com.fatec.sistemarestaurante.api.entity.Pedido;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import static br.com.spektro.minispring.core.dbmapper.ConfigDBMapper.getDefaultConnectionType;

public class PedidoDAOImpl implements PedidoDAO {

	@Override
	public Long save(Pedido pedidoSalvar) {
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			
			String colunas = DAOUtils.getColunas(ConfigDBMapper.getDefaultConnectionType(), Pedido.getColunas());
			
			String values = DAOUtils.completarClausulaValues(getDefaultConnectionType(), 2, "SEQ_SCR_PEDIDO");
			
			String sql = "INSERT INTO " + Pedido.TABLE + colunas + " VALUES " + values;
			
			insert = DAOUtils.criarStatment(sql, conn, getDefaultConnectionType(), Pedido.getColunasArray());
			
			insert.setString(1, pedidoSalvar.getStatus());
			insert.setString(2, pedidoSalvar.getDataAbertura());
			insert.setDouble(3, pedidoSalvar.getValorTotal());
			insert.setLong(4, pedidoSalvar.getIdComanda());
			insert.setLong(5, pedidoSalvar.getIdGarcom());
			
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
	public Pedido findById(Long id) {
		Connection conn = null;
		PreparedStatement find = null;
		Pedido pedido = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "SELECT * FROM " + Pedido.TABLE + " WHERE "
					+ Pedido.COL_STATUS+ " = ?" + Pedido.COL_DATA_ABERTURA + " = ?" + Pedido.COL_VALOR_TOTAL + " = ?" + Pedido.COL_ID_COMANDA + " = ?"
					+ Pedido.COL_ID_GARCOM + " = ?";
			find = conn.prepareStatement(sql);
			find.setLong(1, id);
			ResultSet rs = find.executeQuery();
			if (rs.next()) {
				pedido = this.buildPedido(rs);
			}
			return pedido;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(find);
		}
	}

	@Override
	public List<Pedido> findAll() {
		Connection conn = null;
		PreparedStatement findAll = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			findAll = conn.prepareStatement("SELECT * FROM " + Pedido.TABLE);
			ResultSet rs = findAll.executeQuery();
			return this.buildPedidos(rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(findAll);
		}
	}

	@Override
	public void update(Pedido pedidoAtualizar) {
		Connection conn = null;
		PreparedStatement update = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + Pedido.TABLE + " SET "
					+ Pedido.COL_STATUS + " = ?, " +  Pedido.COL_DATA_ABERTURA + " = ?, " + Pedido.COL_VALOR_TOTAL + " = ?, " + "WHERE " + Pedido.COL_ID_COMANDA + " = ?" + Pedido.COL_ID_GARCOM+ " = ?");
			update.setString(1, pedidoAtualizar.getStatus());
			update.setString(2, pedidoAtualizar.getDataAbertura());
			update.setDouble(3, pedidoAtualizar.getValorTotal());
			update.setLong(4, pedidoAtualizar.getIdComanda());
			update.setLong(5, pedidoAtualizar.getIdGarcom());
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
			String sql = "DELETE FROM " + Pedido.TABLE + " WHERE ID = ?";
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
	
	private Pedido buildPedido(ResultSet rs) throws SQLException {
		Pedido pedido = new Pedido();
		pedido.setIdComanda(rs.getLong(Pedido.COL_ID_COMANDA));
		pedido.setIdGarcom(rs.getLong(Pedido.COL_ID_GARCOM));
		pedido.setStatus(rs.getString(Pedido.COL_STATUS));
		pedido.setDataAbertura(rs.getString(Pedido.COL_DATA_ABERTURA));
		pedido.setValorTotal(rs.getDouble(Pedido.COL_VALOR_TOTAL));
		return pedido;
	}
	
	private List<Pedido> buildPedidos(ResultSet rs) throws SQLException {
		List<Pedido> pedidos = Lists.newArrayList();
		while (rs.next()) {
			pedidos.add(this.buildPedido(rs));
		}
		return pedidos;
	}


}
