package br.com.fatec.sistemarestaurante.api.dto;

public class IngredienteDTO {


	private Long id;
	private String descricao;

	public IngredienteDTO() {

	}

	public IngredienteDTO(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Grupo[" + this.id + " - " + this.descricao + "]";
	}
}