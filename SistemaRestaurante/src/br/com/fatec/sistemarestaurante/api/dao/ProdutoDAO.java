package br.com.fatec.sistemarestaurante.api.dao;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.entity.Produto;

public interface ProdutoDAO {

	Long save(Produto produtoSalvar);

	Produto findById(Long id);

	void update(Produto produtoAtualizar);

	List<Produto> findAll();

	void delete(Long id);

	public List<Produto> findByStats(String status);
}
