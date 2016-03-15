package br.com.fatec.sistemarestaurante.api.entity;

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
	
}
