package br.com.fatec.sistemarestaurante.api.entity;

public class Produto {
	//SCR = SISTEMA CONTROLE RESTAURANTE
	public static final String TABLE = "SCR_PRODUTO";
	public static final String COL_ID = "ID";
	public static final String COL_DESCRICAO = "DESCRICAO";
	public static final String COL_STATUS = "STATUS";
	public static final String COL_PRECO = "PRECO";
	
	private Long id;
	private String descricao;
	private String status;
	private Double preco;
	
	public Produto(Long id, String descricao, String status, Double preco){
		this.id = id;
		this.descricao = descricao;
		this.status = status;
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
}
