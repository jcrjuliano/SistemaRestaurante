package br.com.fatec.sistemarestaurante.api.entity;

import java.util.List;

import com.google.common.collect.Lists;

public class Comanda {
	
	public static final String TABLE = "SCR_COMANDA";
	public static final String COL_ID = "ID";
	public static final String COL_VALOR_TOTAL = "VALOR_TOTAL";
	public static final String COL_DATA_ABERTURA = "DATA_ABERTURA";
	public static final String COL_DATA_FECHAMENTO = "DATA_FECHAMENTO";
	
	private Long id;
	private Double valorTotal;
	private String dataAbertura;
	private String dataFechamento;
	public Comanda(Long id, Double valorTotal, String dataAbertura,
			String dataFechamento) {
		this.id = id;
		this.valorTotal = valorTotal;
		this.dataAbertura = dataAbertura;
		this.dataFechamento = dataFechamento;
	}
	
	public Comanda(){
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public String getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(String dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	
	public static List<String> getColunas(){
		return Lists.newArrayList(COL_ID, COL_VALOR_TOTAL, COL_DATA_ABERTURA, COL_DATA_FECHAMENTO);
	}
	
	public static String[] getColunasArray(){
		return new String[] {COL_ID, COL_VALOR_TOTAL, COL_DATA_ABERTURA, COL_DATA_FECHAMENTO};
	}
	
}
