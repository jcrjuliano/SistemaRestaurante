package br.com.fatec.sistemarestaurante.core.service;

import java.util.List;

import br.com.fatec.sistemarestaurante.api.dao.ProdutoDAO;
import br.com.fatec.sistemarestaurante.api.dto.ProdutoDTO;
import br.com.fatec.sistemarestaurante.api.entity.Produto;
import br.com.fatec.sistemarestaurante.api.service.ProdutoService;
import br.com.fatec.sistemarestaurante.core.converter.ProdutoDTOConverter;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class ProdutoServiceImpl implements ProdutoService {
	private ProdutoDAO produtoDAO;
	private ProdutoDTOConverter produtoDTOConverter;
	
	public ProdutoServiceImpl(){
		produtoDAO = ImplFinder.getImpl(ProdutoDAO.class);
		produtoDTOConverter = ImplFinder.getFinalImpl(ProdutoDTOConverter.class);
	}
	@Override
	public ProdutoDTO salvar(ProdutoDTO produtoDTO) {
		Produto produtoEntidade = this.produtoDTOConverter.toEntity(produtoDTO);
		Long id = produtoDAO.save(produtoEntidade);
		produtoDTO.setId(id);
		return produtoDTO;
	}

	@Override
	public void atualizar(ProdutoDTO produto) {
		Produto produtoEntidade = this.produtoDTOConverter.toEntity(produto);
		this.produtoDAO.update(produtoEntidade);

	}

	@Override
	public List<ProdutoDTO> listar() {
		return this.produtoDTOConverter.toDTO(produtoDAO.findAll());
	}


	@Override
	public void deletar(Long ingredienteId) {
		this.produtoDAO.delete(ingredienteId);
	}
	
	@Override
	public ProdutoDTO buscarPorId(Long produtoId) {
		return produtoDTOConverter.toDTO(this.produtoDAO.findById(produtoId));
	}
	@Override
	public List<ProdutoDTO> listarPorStatus(String status) {
		return produtoDTOConverter.toDTO(this.produtoDAO.findByStats(status));
	}
	
	
	

}
