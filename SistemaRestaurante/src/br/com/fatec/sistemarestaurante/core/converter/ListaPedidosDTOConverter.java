package br.com.fatec.sistemarestaurante.core.converter;

import java.util.List;

import com.google.common.collect.Lists;

import br.com.fatec.sistemarestaurante.api.dto.ListaPedidosDTO;
import br.com.fatec.sistemarestaurante.api.entity.ListaPedidos;
import br.com.spektro.minispring.dto.DTOConverter;

public class ListaPedidosDTOConverter implements DTOConverter<ListaPedidos, ListaPedidosDTO> {

	@Override
	public ListaPedidosDTO toDTO(ListaPedidos entidade) {
		ListaPedidosDTO dto = null;
		if (entidade != null){
			dto = new ListaPedidosDTO();
			dto.setId(entidade.getId());
			dto.setPedido(entidade.getPedido());
			dto.setStatus(entidade.getStatus());
		}		
		return dto;
		
	}

	@Override
	public List<ListaPedidosDTO> toDTO(List<ListaPedidos> entidades) {
		List<ListaPedidosDTO> dtos = Lists.newArrayList();
		for(ListaPedidos entidade : entidades){
			dtos.add(this.toDTO(entidade));
		}
		return dtos;
	}

	@Override
	public ListaPedidos toEntity(ListaPedidosDTO dto) {
		ListaPedidos listaPedidos = new ListaPedidos();
		listaPedidos.setId(dto.getId());
		listaPedidos.setPedido(dto.getPedido());
		listaPedidos.setStatus(dto.getStatus());
		return listaPedidos;
	}

	@Override
	public List<ListaPedidos> toEntity(List<ListaPedidosDTO> dtos) {
		List<ListaPedidos> entidades = Lists.newArrayList();
		for(ListaPedidosDTO dto: dtos){
			entidades.add(this.toEntity(dto));
		}
		return entidades;
	}
	
}
