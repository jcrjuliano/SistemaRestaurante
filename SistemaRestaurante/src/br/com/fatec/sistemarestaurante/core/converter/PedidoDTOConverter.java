package br.com.fatec.sistemarestaurante.core.converter;

import java.util.List;

import com.google.common.collect.Lists;

import br.com.fatec.sistemarestaurante.api.dto.PedidoDTO;
import br.com.fatec.sistemarestaurante.api.entity.Pedido;
import br.com.spektro.minispring.dto.DTOConverter;

public class PedidoDTOConverter implements DTOConverter<Pedido, PedidoDTO> {

	@Override
	public PedidoDTO toDTO(Pedido entidade) {
		PedidoDTO dto = null;
		if (entidade != null){
			dto = new PedidoDTO();
			dto.setId(entidade.getId());
			dto.setStatus(entidade.getStatus());
			dto.setDataAbertura(entidade.getDataAbertura());
			dto.setValorTotal(entidade.getValorTotal());
			dto.setComanda(entidade.getComanda());
			dto.setGarcom(entidade.getGarcom());
		}		
		return dto;
		
	}

	@Override
	public List<PedidoDTO> toDTO(List<Pedido> entidades) {
		List<PedidoDTO> dtos = Lists.newArrayList();
		for(Pedido entidade : entidades){
			dtos.add(this.toDTO(entidade));
		}
		return dtos;
	}

	@Override
	public Pedido toEntity(PedidoDTO dto) {
		Pedido pedido = new Pedido();
		pedido.setId(dto.getId());
		pedido.setStatus(dto.getStatus());
		pedido.setDataAbertura(dto.getDataAbertura());
		pedido.setValorTotal(dto.getValorTotal());
		pedido.setComanda(dto.getComanda());
		pedido.setGarcom(dto.getGarcom());
		return pedido;
	}

	@Override
	public List<Pedido> toEntity(List<PedidoDTO> dtos) {
		List<Pedido> entidades = Lists.newArrayList();
		for(PedidoDTO dto: dtos){
			entidades.add(this.toEntity(dto));
		}
		return entidades;
	}
	
}
