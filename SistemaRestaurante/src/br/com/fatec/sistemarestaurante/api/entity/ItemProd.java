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
	private Long idProduto;
	private Long idPedido;
	private Double preco;
	public ItemProd(Long id, Long idProduto, Long idPedido, Double preco) {
		this.id = id;
		this.idProduto = idProduto;
		this.idPedido = idPedido;
		this.preco = preco;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
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
