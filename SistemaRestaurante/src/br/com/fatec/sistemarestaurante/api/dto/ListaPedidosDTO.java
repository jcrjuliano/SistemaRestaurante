package br.com.fatec.sistemarestaurante.api.dto;

import br.com.fatec.sistemarestaurante.api.entity.Pedido;
import br.com.fatec.sistemarestaurante.api.entity.Produto;

public class ListaPedidosDTO {
	
	private Long id;
	private Pedido pedido;
	private String status;

	public ListaPedidosDTO() {

	}

	public ListaPedidosDTO(Long id, Pedido pedido, String status) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ItemIngrediente[" + this.id + " - " + this.pedido + " - " + this.status + "]";
	}

}
