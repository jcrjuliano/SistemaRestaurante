package br.com.fatec.sistemarestaurante.core.converter;

import java.util.List;

import com.google.common.collect.Lists;

import br.com.fatec.sistemarestaurante.api.dto.ProdutoDTO;
import br.com.fatec.sistemarestaurante.api.entity.Produto;
import br.com.spektro.minispring.dto.DTOConverter;

public class ProdutoDTOConverter implements DTOConverter<Produto, ProdutoDTO> {

	@Override
	public ProdutoDTO toDTO(Produto entidade) {
		ProdutoDTO dto = null;
		if (entidade != null){
			dto = new ProdutoDTO();
			dto.setId(entidade.getId());
			dto.setDescricao(entidade.getDescricao());
			dto.setStatus(entidade.getStatus());
			dto.setPreco(entidade.getPreco());
		}		
		return dto;
		
	}

	@Override
	public List<ProdutoDTO> toDTO(List<Produto> entidades) {
		List<ProdutoDTO> dtos = Lists.newArrayList();
		for(Produto entidade : entidades){
			dtos.add(this.toDTO(entidade));
		}
		return dtos;
	}

	@Override
	public Produto toEntity(ProdutoDTO dto) {
		Produto produto = new Produto();
		produto.setId(dto.getId());
		produto.setDescricao(dto.getDescricao());
		produto.setStatus(dto.getStatus());
		produto.setPreco(dto.getPreco());
		return produto;
	}

	@Override
	public List<Produto> toEntity(List<ProdutoDTO> dtos) {
		List<Produto> entidades = Lists.newArrayList();
		for(ProdutoDTO dto: dtos){
			entidades.add(this.toEntity(dto));
		}
		return entidades;
	}
	
}
