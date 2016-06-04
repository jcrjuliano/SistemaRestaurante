package br.com.fatec.sistemarestaurante.api.entity;

import java.util.List;

import com.google.common.collect.Lists;

public class ItemIngrediente {
	
	public static final String TABLE = "SCR_ITEM_INGREDIENTE";
	public static final String COL_PROD_ID = "PROD_ID";
	public static final String COL_INGRED_ID = "INGRED_ID";
	public static final String COL_QUANTIDADE = "QUANTIDADE";
	
	private Produto produto;
	private Ingrediente ingrediente;
	private Integer quantidade;
	
	public ItemIngrediente(Produto produto, Ingrediente ingrediente,
			Integer quantidade) {
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

	public ItemIngrediente(){
		
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public static List<String> getColunas() {
		return Lists.newArrayList(COL_PROD_ID, COL_INGRED_ID, COL_QUANTIDADE);
	}

	public static String[] getColunasArray() {
		return new String[] { COL_PROD_ID, COL_INGRED_ID, COL_QUANTIDADE};
	}

}
