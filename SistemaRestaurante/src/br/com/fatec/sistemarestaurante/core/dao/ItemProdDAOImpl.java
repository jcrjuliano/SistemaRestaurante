package br.com.fatec.sistemarestaurante.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.google.common.collect.Lists;

import br.com.fatec.sistemarestaurante.api.dao.ComandaDAO;
import br.com.fatec.sistemarestaurante.api.dao.GarcomDAO;
import br.com.fatec.sistemarestaurante.api.dao.ItemProdDAO;
import br.com.fatec.sistemarestaurante.api.dao.PedidoDAO;
import br.com.fatec.sistemarestaurante.api.dao.ProdutoDAO;
import br.com.fatec.sistemarestaurante.api.entity.ItemProd;
import br.com.fatec.sistemarestaurante.api.entity.Pedido;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import br.com.spektro.minispring.core.implfinder.ImplFinder;
import static br.com.spektro.minispring.core.dbmapper.ConfigDBMapper.getDefaultConnectionType;

public class ItemProdDAOImpl implements ItemProdDAO {
	
	private ProdutoDAO daoProduto;
	private PedidoDAO daoPedido;

	public ItemProdDAOImpl(){
		this.daoProduto = ImplFinder.getImpl(ProdutoDAO.class);
		this.daoPedido = ImplFinder.getImpl(PedidoDAO.class);

	}
	

	@Override
	public Long save(ItemProd itemProdSalvar) {
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			
			String colunas = DAOUtils.getColunas(ConfigDBMapper.getDefaultConnectionType(), ItemProd.getColunas());
			
			String values = DAOUtils.completarClausulaValues(getDefaultConnectionType(), 3, "SEQ_SCR_ITEM_PROD");
			
			String sql = "INSERT INTO " + ItemProd.TABLE + colunas + " VALUES " + values;
			
			insert = DAOUtils.criarStatment(sql, conn, getDefaultConnectionType(), ItemProd.getColunasArray());
			
			insert.setLong(1, itemProdSalvar.getProduto().getId());
			insert.setLong(2, itemProdSalvar.getPedido().getId());
			insert.setDouble(3, itemProdSalvar.getPreco());
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
	public ItemProd findById(Long id) {
		Connection conn = null;
		PreparedStatement find = null;
		ItemProd itemProd = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "SELECT * FROM " + ItemProd.TABLE + " WHERE "
					+ ItemProd.COL_ID + " = ?";
			find = conn.prepareStatement(sql);
			find.setLong(1, id);
			ResultSet rs = find.executeQuery();
			if (rs.next()) {
				itemProd = this.buildItemProd(rs);
			}
			return itemProd;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(find);
		}
	}

	@Override
	public List<ItemProd> findAll() {
		Connection conn = null;
		PreparedStatement findAll = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			findAll = conn.prepareStatement("SELECT * FROM " + ItemProd.TABLE);
			ResultSet rs = findAll.executeQuery();
			return this.buildItemProds(rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(findAll);
		}
	}

	@Override
	public void update(ItemProd itemProdAtualizar) {
		Connection conn = null;
		PreparedStatement update = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + ItemProd.TABLE + " SET "
					+ ItemProd.COL_ID_PRODUTO + " = ?, " + ItemProd.COL_ID_PEDIDO + " = ?, " + ItemProd.COL_PRECO + " = ? " 
					+ " WHERE " + ItemProd.COL_ID + " = ?");
			
			
			update.setLong(1, itemProdAtualizar.getProduto().getId());
			update.setLong(2, itemProdAtualizar.getPedido().getId());
			update.setDouble(3, itemProdAtualizar.getPreco());
			update.setLong(4, itemProdAtualizar.getId());
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
			String sql = "DELETE FROM " + ItemProd.TABLE + " WHERE ID = ?";
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
	
	private ItemProd buildItemProd(ResultSet rs) throws SQLException {
		ItemProd itemProd = new ItemProd();
		itemProd.setPedido(this.daoPedido.findById(rs.getLong(ItemProd.COL_ID_PEDIDO)));
		itemProd.setProduto(this.daoProduto.findById(rs.getLong(ItemProd.COL_ID_PRODUTO)));
		itemProd.setPreco(rs.getDouble(ItemProd.COL_PRECO));
		return itemProd;
	}
	
	private List<ItemProd> buildItemProds(ResultSet rs) throws SQLException {
		List<ItemProd> itemProd = Lists.newArrayList();
		while (rs.next()) {
			itemProd.add(this.buildItemProd(rs));
		}
		return itemProd;
	}


}
