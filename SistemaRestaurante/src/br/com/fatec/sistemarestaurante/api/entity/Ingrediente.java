package br.com.fatec.sistemarestaurante.api.entity;

import java.util.List;

import com.google.common.collect.Lists;

public class Ingrediente {
	public static final String TABLE = "SCR_INGREDIENTE";
	public static final String COL_ID = "ID";
	public static final String COL_DESCRICAO = "DESCRICAO";
	
	private Long id;
	private String descricao;
	public Ingrediente(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public Ingrediente(){
	
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
	

	public static List<String> getColunas() {
		return Lists.newArrayList(COL_ID, COL_DESCRICAO);
	}

	public static String[] getColunasArray() {
		return new String[] { COL_ID, COL_DESCRICAO};
	}
	
	

}
