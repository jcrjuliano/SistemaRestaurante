package br.com.fatec.sistemarestaurante.api.entity;

import java.util.List;

import com.google.common.collect.Lists;

public class Pedido {
	public static final String TABLE = "SCR_PEDIDO";
	public static final String COL_ID = "ID";
	public static final String COL_STATUS = "STATUS";
	public static final String COL_DATA_ABERTURA = "DATA_ABERTURA";
	public static final String COL_VALOR_TOTAL = "VALOR_TOTAL";
	public static final String COL_ID_COMANDA = "ID_COMANDA";
	public static final String COL_ID_GARCOM = "ID_GARCOM";
	
	
	private Long id;
	private String status;
	private String dataAbertura;
	private Double valorTotal;
	private Long idComanda;
	private Long idGarcom;
	
	public Pedido(Long id, String status, String dataAbertura,
			Double valorTotal, Long idComanda, Long idGarcom) {
		this.id = id;
		this.status = status;
		this.dataAbertura = dataAbertura;
		this.valorTotal = valorTotal;
		this.idComanda = idComanda;
		this.idGarcom = idGarcom;
	}
	
	public Pedido(){
		
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

	public String getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Long getIdComanda() {
		return idComanda;
	}

	public void setIdComanda(Long idComanda) {
		this.idComanda = idComanda;
	}

	public Long getIdGarcom() {
		return idGarcom;
	}

	public void setIdGarcom(Long idGarcom) {
		this.idGarcom = idGarcom;
	}
	
	public static List<String> getColunas() {
		return Lists.newArrayList(COL_ID, COL_STATUS, COL_DATA_ABERTURA, COL_VALOR_TOTAL, COL_ID_COMANDA, COL_ID_GARCOM);
	}

	public static String[] getColunasArray() {
		return new String[] { COL_ID, COL_STATUS, COL_DATA_ABERTURA, COL_VALOR_TOTAL, COL_ID_COMANDA, COL_ID_GARCOM };
	}
	
}
