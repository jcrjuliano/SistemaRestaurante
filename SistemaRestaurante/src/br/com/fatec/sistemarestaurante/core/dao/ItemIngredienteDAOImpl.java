package br.com.fatec.sistemarestaurante.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.google.common.collect.Lists;

import br.com.fatec.sistemarestaurante.api.dao.IngredienteDAO;
import br.com.fatec.sistemarestaurante.api.dao.ItemIngredienteDAO;
import br.com.fatec.sistemarestaurante.api.dao.ProdutoDAO;
import br.com.fatec.sistemarestaurante.api.entity.ItemIngrediente;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import br.com.spektro.minispring.core.implfinder.ImplFinder;
import static br.com.spektro.minispring.core.dbmapper.ConfigDBMapper.getDefaultConnectionType;

public class ItemIngredienteDAOImpl implements ItemIngredienteDAO {

	ProdutoDAO produtoDAO;
	IngredienteDAO ingredienteDAO;
	
	public ItemIngredienteDAOImpl() {
			this.produtoDAO = ImplFinder.getImpl(ProdutoDAO.class);
			this.ingredienteDAO = ImplFinder.getImpl(IngredienteDAO.class);
	}
	@Override
	public void save(ItemIngrediente itemIngredienteSalvar) {
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			
			String colunas = DAOUtils.getColunas(ConfigDBMapper.getDefaultConnectionType(), ItemIngrediente.getColunas());
						
			String sql = "INSERT INTO " + ItemIngrediente.TABLE + colunas + " VALUES (?, ?, ?)";
			
			insert = DAOUtils.criarStatment(sql, conn, getDefaultConnectionType(), ItemIngrediente.getColunasArray());
			
			insert.setLong(1, itemIngredienteSalvar.getProduto().getId());
			insert.setLong(2, itemIngredienteSalvar.getIngrediente().getId());
			insert.setInt(3, itemIngredienteSalvar.getQuantidade());
			
			insert.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(insert);
			DbUtils.closeQuietly(conn);
		}
		
		//Continuar reparo pois essa classe não possui chave unica, é feita por chave composta.
	}

	@Override
	public ItemIngrediente findByIds(Long idProduto, Long idIngrediente) {
		Connection conn = null;
		PreparedStatement find = null;
		ItemIngrediente ingrediente = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "SELECT * FROM " + ItemIngrediente.TABLE + " WHERE "
					+ ItemIngrediente.COL_PROD_ID + " = ? and " + ItemIngrediente.COL_INGRED_ID + " = ?";
			find = conn.prepareStatement(sql);
			find.setLong(1, idProduto);
			find.setLong(2, idIngrediente);
			ResultSet rs = find.executeQuery();
			if (rs.next()) {
				ingrediente = this.buildItemIngrediente(rs);
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
	public List<ItemIngrediente> findAll() {
		Connection conn = null;
		PreparedStatement findAll = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			findAll = conn.prepareStatement("SELECT * FROM " + ItemIngrediente.TABLE);
			ResultSet rs = findAll.executeQuery();
			return this.buildItemIngredientes(rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(findAll);
		}
	}

	@Override
	public void update(ItemIngrediente itemIngredienteAtualizar) {
		Connection conn = null;
		PreparedStatement update = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + ItemIngrediente.TABLE + " SET "
					+ ItemIngrediente.COL_QUANTIDADE + " = ? " + " WHERE " + ItemIngrediente.COL_PROD_ID + " = ? and " + ItemIngrediente.COL_INGRED_ID + " = ?");
			update.setInt(1, itemIngredienteAtualizar.getQuantidade());
			update.setLong(2, itemIngredienteAtualizar.getProduto().getId());
			update.setLong(3, itemIngredienteAtualizar.getIngrediente().getId());
			update.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(update);
		}
		
	}

	@Override
	public void delete(Long prodId, Long ingredId) {
		Connection conn = null;
		PreparedStatement delete = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "DELETE FROM " + ItemIngrediente.TABLE + " WHERE "+ ItemIngrediente.COL_PROD_ID +" = ? and " + ItemIngrediente.COL_INGRED_ID + " = ?";
			delete = conn.prepareStatement(sql);
			delete.setLong(1, prodId);
			delete.setLong(2, ingredId);
			delete.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(delete);
			DbUtils.closeQuietly(conn);
		}
		
	}
	
	private ItemIngrediente buildItemIngrediente(ResultSet rs) throws SQLException {
		ItemIngrediente itemIngrediente = new ItemIngrediente();
		itemIngrediente.setProduto(this.produtoDAO.findById(rs.getLong(ItemIngrediente.COL_PROD_ID)));
		itemIngrediente.setIngrediente(this.ingredienteDAO.findById(rs.getLong(ItemIngrediente.COL_INGRED_ID)));
		itemIngrediente.setQuantidade(rs.getInt(ItemIngrediente.COL_QUANTIDADE));
		return itemIngrediente;
	}
	
	private List<ItemIngrediente> buildItemIngredientes(ResultSet rs) throws SQLException {
		List<ItemIngrediente> ingredientes = Lists.newArrayList();
		while (rs.next()) {
			ingredientes.add(this.buildItemIngrediente(rs));
		}
		return ingredientes;
	}
	@Override
	public List<ItemIngrediente> findByProduto(Long id) {
		Connection conn = null;
		PreparedStatement findByProg = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			findByProg = conn.prepareStatement("SELECT * FROM " + ItemIngrediente.TABLE + " WHERE " + ItemIngrediente.COL_PROD_ID + " = ?");
			findByProg.setLong(1, id);
			ResultSet rs = findByProg.executeQuery();
			return this.buildItemIngredientes(rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(findByProg);
		}
	}


}
