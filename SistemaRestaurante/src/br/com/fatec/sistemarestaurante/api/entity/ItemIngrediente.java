package br.com.fatec.sistemarestaurante.api.entity;

public class ItemIngrediente {
	
	public static final String TABLE = "SCR_ITEM_INGREDIENTE";
	public static final String COL_PROD_ID = "PROD_ID";
	public static final String COL_INGRED_ID = "INGRED_ID";
	public static final String COL_QUANTIDADE = "QUANTIDADE";
	
	private Long prodId;
	private Long ingredId;
	private Integer quantidade;
	public ItemIngrediente(Long prodId, Long ingredId, Integer quantidade) {
		this.prodId = prodId;
		this.ingredId = ingredId;
		this.quantidade = quantidade;
	}
	public Long getProdId() {
		return prodId;
	}
	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}
	public Long getIngredId() {
		return ingredId;
	}
	public void setIngredId(Long ingredId) {
		this.ingredId = ingredId;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	

}
