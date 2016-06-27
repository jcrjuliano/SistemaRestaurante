package br.com.fatec.sistemarestaurante.core.converter;

import java.util.List;

import com.google.common.collect.Lists;

import br.com.fatec.sistemarestaurante.api.dto.ItemProdDTO;
import br.com.fatec.sistemarestaurante.api.entity.ItemProd;
import br.com.spektro.minispring.dto.DTOConverter;

public class ItemProdDTOConverter implements DTOConverter<ItemProd, ItemProdDTO> {

	@Override
	public ItemProdDTO toDTO(ItemProd entidade) {
		ItemProdDTO dto = null;
		if (entidade != null){
			dto = new ItemProdDTO();
			dto.setId(entidade.getId());
			dto.setProduto(entidade.getProduto());
			dto.setPedido(entidade.getPedido());
			dto.setPreco(entidade.getPreco());
		}		
		return dto;
		
	}

	@Override
	public List<ItemProdDTO> toDTO(List<ItemProd> entidades) {
		List<ItemProdDTO> dtos = Lists.newArrayList();
		for(ItemProd entidade : entidades){
			dtos.add(this.toDTO(entidade));
		}
		return dtos;
	}

	@Override
	public ItemProd toEntity(ItemProdDTO dto) {
		ItemProd itemProd = new ItemProd();
		itemProd.setId(dto.getId());
		itemProd.setProduto(dto.getProduto());
		itemProd.setPedido(dto.getPedido());
		itemProd.setPreco(dto.getPreco());
		return itemProd;
	}

	@Override
	public List<ItemProd> toEntity(List<ItemProdDTO> dtos) {
		List<ItemProd> entidades = Lists.newArrayList();
		for(ItemProdDTO dto: dtos){
			entidades.add(this.toEntity(dto));
		}
		return entidades;
	}
	
}
