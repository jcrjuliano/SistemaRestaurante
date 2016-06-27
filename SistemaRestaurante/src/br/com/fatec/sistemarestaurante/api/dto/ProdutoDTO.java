package br.com.fatec.sistemarestaurante.api.dto;

public class ProdutoDTO {
	
	private Long id;
	private String descricao;
	private String status;
	private Double preco;
	
	public ProdutoDTO(){
	
	}
	
	public ProdutoDTO(Long id, String descricao, String status, double preco) {
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
	
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
		
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	@Override
	public String toString() {
		return "ProdutoDTO [" + id + " - " + descricao + " - " + status + " - " + preco + "]";
	}
	
}
