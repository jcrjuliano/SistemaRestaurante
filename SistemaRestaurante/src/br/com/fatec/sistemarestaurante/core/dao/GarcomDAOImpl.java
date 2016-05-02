package br.com.fatec.sistemarestaurante.core.dao;

import static br.com.spektro.minispring.core.dbmapper.ConfigDBMapper.getDefaultConnectionType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.google.common.collect.Lists;
import br.com.fatec.sistemarestaurante.api.dao.GarcomDAO;
import br.com.fatec.sistemarestaurante.api.entity.Garcom;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class GarcomDAOImpl implements GarcomDAO {

	@Override
	public Long save(Garcom garcomSalvar) {
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();

			String colunas = DAOUtils.getColunas(getDefaultConnectionType(),
				Garcom.getColunas());

			String values = DAOUtils.completarClausulaValues(getDefaultConnectionType(),
					Garcom.getColunas().size() - 1, "SEQ_SCR_GARCOM");

			String sql = "INSERT INTO " + Garcom.TABLE + colunas + " VALUES " + values;

			insert = DAOUtils.criarStatment(sql, conn, getDefaultConnectionType(),
					Garcom.getColunasArray());
			
			insert.setString(1, garcomSalvar.getNome());
			insert.setString(2, garcomSalvar.getRegistro());
			insert.setString(3, garcomSalvar.getSexo());
			insert.setString(4, garcomSalvar.getIdade());
			insert.execute();

			ResultSet generatedKeys = insert.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getLong(1);
			}

			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(insert);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public Garcom findById(Long id) {
		Connection conn = null;
		PreparedStatement find = null;
		Garcom user = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			String sql = "SELECT * FROM " + Garcom.TABLE + " WHERE " + Garcom.COL_ID
					+ " = ?;";
			find = conn.prepareStatement(sql);
			find.setLong(1, id);
			ResultSet rs = find.executeQuery();
			if (rs.next()) {
				user = this.buildGarcom(rs);
			}
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(find);
		}
	}
	
	private List<Garcom> buildGarcons(ResultSet rs) throws SQLException {
		List<Garcom> garcons = Lists.newArrayList();
		while (rs.next()) {
			garcons.add(this.buildGarcom(rs));
		}
		return garcons;
	}

	private Garcom buildGarcom(ResultSet rs) throws SQLException {
		Garcom garcom = new Garcom();
		garcom.setId(rs.getLong(Garcom.COL_ID));
		garcom.setNome(rs.getString(Garcom.COL_NOME));
		garcom.setRegistro(rs.getString(Garcom.COL_REGISTRO));
		garcom.setSexo(rs.getString(Garcom.COL_SEXO));
		garcom.setIdade(rs.getString(Garcom.COL_IDADE));
		return garcom;
	}

	@Override
	public void update(Garcom garcomAtualizar) {
		Connection conn = null;
		PreparedStatement update = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + Garcom.TABLE + " SET "
					+ Garcom.COL_NOME + " = ?, " + Garcom.COL_REGISTRO + " = ?, "
					+ Garcom.COL_SEXO + " = ?, " + Garcom.COL_IDADE + " = ? " +" WHERE " + Garcom.COL_ID + " = ?");
			update.setString(1, garcomAtualizar.getNome());
			update.setString(2, garcomAtualizar.getRegistro());
			update.setString(3, garcomAtualizar.getSexo());
			update.setString(3, garcomAtualizar.getIdade());
			update.setLong(4, garcomAtualizar.getId());
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
			String sql = "DELETE FROM " + Garcom.TABLE + " WHERE ID = ?;";
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

	@Override
	public List<Garcom> findAll() {
		Connection conn = null;
		PreparedStatement findAll = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			findAll = conn.prepareStatement("SELECT * FROM " + Garcom.TABLE);
			ResultSet rs = findAll.executeQuery();
			return this.buildGarcons(rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(findAll);
		}
	}

}
