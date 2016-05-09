package br.com.fatec.sistemarestaurante.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.google.common.collect.Lists;

import br.com.fatec.sistemarestaurante.api.dao.ListaPedidosDAO;
import br.com.fatec.sistemarestaurante.api.entity.ListaPedidos;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import static br.com.spektro.minispring.core.dbmapper.ConfigDBMapper.getDefaultConnectionType;

public class ListaPedidosDAOImpl implements ListaPedidosDAO {

	@Override
	public Long save(ListaPedidos listaPedidosSalvar) {
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			
			String colunas = DAOUtils.getColunas(ConfigDBMapper.getDefaultConnectionType(), ListaPedidos.getColunas());
			
			String values = DAOUtils.completarClausulaValues(getDefaultConnectionType(), 2, "SEQ_SCR_LISTA_PEDIDOS");
			
			String sql = "INSERT INTO " + ListaPedidos.TABLE + colunas + " VALUES " + values;
			
			insert = DAOUtils.criarStatment(sql, conn, getDefaultConnectionType(), ListaPedidos.getColunasArray());
			
			insert.setDouble(1, listaPedidosSalvar.getIdPedido());
			insert.setString(2, listaPedidosSalvar.getStatus());
			
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
	public ListaPedidos findById(Long id) {
		Connection conn = null;
		PreparedStatement find = null;
		ListaPedidos listaPedidos = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "SELECT * FROM " + listaPedidos.TABLE + " WHERE "
					+ listaPedidos.COL_ID + " = ?";
			find = conn.prepareStatement(sql);
			find.setLong(1, id);
			ResultSet rs = find.executeQuery();
			if (rs.next()) {
				listaPedidos = this.buildListaPedidos(rs);
			}
			return listaPedidos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(find);
		}
	}

	@Override
	public List<ListaPedidos> findAll() {
		Connection conn = null;
		PreparedStatement findAll = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			findAll = conn.prepareStatement("SELECT * FROM " + ListaPedidos.TABLE);
			ResultSet rs = findAll.executeQuery();
			return this.buildlistaPedidoss(rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(findAll);
		}
	}

	@Override
	public void update(ListaPedidos listaPedidosAtualizar) {
		Connection conn = null;
		PreparedStatement update = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + ListaPedidos.TABLE + " SET "
					+ ListaPedidos.COL_ID_PEDIDO + " = ?, " + ListaPedidos.COL_STATUS + " = ?, " + " WHERE " + ListaPedidos.COL_ID + " = ?");
			update.setLong(1, listaPedidosAtualizar.getIdPedido());
			update.setString(2, listaPedidosAtualizar.getStatus());
			update.setLong(3, listaPedidosAtualizar.getId());
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
			String sql = "DELETE FROM " + ListaPedidos.TABLE + " WHERE ID = ?";
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
	
	private ListaPedidos buildListaPedidos(ResultSet rs) throws SQLException {
		ListaPedidos listaPedidos = new ListaPedidos();
		listaPedidos.setId(rs.getLong(listaPedidos.COL_ID));
		listaPedidos.setIdPedido(rs.getLong(listaPedidos.COL_ID_PEDIDO));
		listaPedidos.setStatus(rs.getString(listaPedidos.COL_STATUS));
		return listaPedidos;
	}
	
	private List<ListaPedidos> buildlistaPedidoss(ResultSet rs) throws SQLException {
		List<ListaPedidos> listaPedidos = Lists.newArrayList();
		while (rs.next()) {
			listaPedidos.add(this.buildListaPedidos(rs));
		}
		return listaPedidos;
	}


}
