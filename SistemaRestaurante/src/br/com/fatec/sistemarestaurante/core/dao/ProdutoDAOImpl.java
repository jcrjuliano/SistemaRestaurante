package br.com.fatec.sistemarestaurante.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.google.common.collect.Lists;

import br.com.fatec.sistemarestaurante.api.dao.ProdutoDAO;
import br.com.fatec.sistemarestaurante.api.entity.Produto;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import static br.com.spektro.minispring.core.dbmapper.ConfigDBMapper.getDefaultConnectionType;

public class ProdutoDAOImpl implements ProdutoDAO {

	@Override
	public Long save(Produto produtoSalvar) {
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			
			String colunas = DAOUtils.getColunas(ConfigDBMapper.getDefaultConnectionType(), Produto.getColunas());
			
			String values = DAOUtils.completarClausulaValues(getDefaultConnectionType(), 3, "SEQ_SCR_PRODUTO");
			
			String sql = "INSERT INTO " + Produto.TABLE + colunas + " VALUES " + values;
			
			insert = DAOUtils.criarStatment(sql, conn, getDefaultConnectionType(), Produto.getColunasArray());
			insert.setString(1, produtoSalvar.getDescricao());
			insert.setString(2, produtoSalvar.getStatus());
			insert.setDouble(3, produtoSalvar.getPreco());
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
	public Produto findById(Long id) {
		Connection conn = null;
		PreparedStatement find = null;
		Produto produto = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "SELECT * FROM " + Produto.TABLE + " WHERE " +
					Produto.COL_ID + " = ?";
			find = conn.prepareStatement(sql);
			find.setLong(1, id);
			ResultSet rs = find.executeQuery();
			if (rs.next()) {
				produto = this.buildProduto(rs);
			}
			return produto;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(find);
		}
	}
	
	@Override
	public List<Produto> findByStats(String status){
		Connection conn = null;
		PreparedStatement find = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			find = conn.prepareStatement("SELECT * FROM " + Produto.TABLE + " WHERE " + Produto.COL_STATUS + " = ?");
			find.setString(1, status);
			ResultSet rs = find.executeQuery();
			return this.buildProdutos(rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(find);
		}
	}

	@Override
	public List<Produto> findAll() {
		Connection conn = null;
		PreparedStatement findAll = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			findAll = conn.prepareStatement("SELECT * FROM " + Produto.TABLE);
			ResultSet rs = findAll.executeQuery();
			return this.buildProdutos(rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(findAll);
		}
	}

	@Override
	public void update(Produto produtoAtualizar) {
		Connection conn = null;
		PreparedStatement update = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + Produto.TABLE + " SET "
					+ Produto.COL_DESCRICAO + " = ?, " +  Produto.COL_STATUS + " = ?, " + Produto.COL_PRECO + " = ? " + " WHERE " + Produto.COL_ID + " = ?");
					
			update.setString(1, produtoAtualizar.getDescricao());
			update.setString(2, produtoAtualizar.getStatus());
			update.setDouble(3, produtoAtualizar.getPreco());
			update.setLong(4, produtoAtualizar.getId());
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
			String sql = "DELETE FROM " + Produto.TABLE + " WHERE ID = ?";
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
	
	private Produto buildProduto(ResultSet rs) throws SQLException {
		Produto produto = new Produto();
		produto.setId(rs.getLong(Produto.COL_ID));
		produto.setDescricao(rs.getString(Produto.COL_DESCRICAO));
		produto.setStatus(rs.getString(Produto.COL_STATUS));
		produto.setPreco(rs.getDouble(Produto.COL_PRECO));
		return produto;
	}
	
	private List<Produto> buildProdutos(ResultSet rs) throws SQLException {
		List<Produto> produtos = Lists.newArrayList();
		while (rs.next()) {
			produtos.add(this.buildProduto(rs));
		}
		return produtos;
	}


}
