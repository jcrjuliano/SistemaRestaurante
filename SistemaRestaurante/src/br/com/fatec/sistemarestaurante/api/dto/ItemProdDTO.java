package br.com.fatec.sistemarestaurante.api.dto;

import br.com.fatec.sistemarestaurante.api.entity.Pedido;
import br.com.fatec.sistemarestaurante.api.entity.Produto;

public class ItemProdDTO {

	private Long id;
	private Produto produto;
	private Pedido pedido;
	private Double preco;

	public ItemProdDTO() {

	}

	public ItemProdDTO(Long id, Produto produto, Pedido pedido, Double preco) {
		super();
		this.id = id;
		this.produto = produto;
		this.pedido = pedido;
		this.preco = preco;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "ItemIngrediente[" + this.id + " - " + this.produto + " - " + this.pedido + " - " + this.preco + "]";
	}
}