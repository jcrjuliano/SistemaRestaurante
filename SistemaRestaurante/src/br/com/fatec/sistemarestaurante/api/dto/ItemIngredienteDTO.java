package br.com.fatec.sistemarestaurante.api.dto;

import br.com.fatec.sistemarestaurante.api.entity.Ingrediente;
import br.com.fatec.sistemarestaurante.api.entity.Produto;

public class ItemIngredienteDTO {

	private Produto produto;
	private Ingrediente ingrediente;
	private Integer quantidade;
	
	public ItemIngredienteDTO() {

	}

	public ItemIngredienteDTO(Produto produto, Ingrediente ingrediente, Integer quantidade) {
		super();
		this.produto = produto;
		this.ingrediente = ingrediente;
		this.quantidade = quantidade;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "ItemIngrediente[" + this.produto + " - " + this.ingrediente + " - " + this.quantidade + "]";
	}
}