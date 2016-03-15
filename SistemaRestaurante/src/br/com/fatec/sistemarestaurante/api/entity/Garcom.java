package br.com.fatec.sistemarestaurante.api.entity;

public class Garcom {
	public static final String TABLE = "SCR_GARCOM";
	public static final String COL_ID = "ID";
	public static final String COL_NOME = "NOME";
	public static final String COL_REGISTRO = "REGISTRO";
	public static final String COL_SEXO = "SEXO";
	public static final String COL_IDADE = "IDADE";
	
	private Long id;
	private String nome;
	private String registro;
	private String sexo;
	private String idade;
	
	public Garcom(Long id, String nome, String registro, String sexo,
			String idade) {
		this.id = id;
		this.nome = nome;
		this.registro = registro;
		this.sexo = sexo;
		this.idade = idade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}
	
	
}
