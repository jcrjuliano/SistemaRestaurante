package br.com.fatec.sistemarestaurante.api.entity;

import java.util.List;

import com.google.common.collect.Lists;

public class ItemProd {
	public ItemProd() {
	}
	public static final String TABLE = "SCR_ITEM_PROD";
	public static final String COL_ID = "ID";
	public static final String COL_ID_PRODUTO = "ID_PRODUTO";
	public static final String COL_ID_PEDIDO = "ID_PEDIDO";
	public static final String COL_PRECO = "PRECO";
	
	private Long id;
	private Produto produto;
	private Pedido pedido;
	private Double preco;

	
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
	public ItemProd(Long id, Produto produto, Pedido pedido, Double preco) {
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


	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	

	public static List<String> getColunas() {
		return Lists.newArrayList(COL_ID, COL_ID_PRODUTO, COL_ID_PEDIDO, COL_PRECO);
	}

	public static String[] getColunasArray() {
		return new String[] { COL_ID, COL_ID_PRODUTO, COL_ID_PEDIDO, COL_PRECO};
	}
	
}
