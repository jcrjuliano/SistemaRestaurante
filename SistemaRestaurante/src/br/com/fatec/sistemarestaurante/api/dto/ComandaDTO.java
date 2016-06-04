package br.com.fatec.sistemarestaurante.api.dto;

import java.util.Date;

public class ComandaDTO {
	private Long id;
	private Double valorTotal;
	private Date dataAbertura;
	private Date dataFechamento;
	
	public ComandaDTO(){
		
	}
	

	public ComandaDTO(Long id, Double valorTotal, Date dataAbertura,
			Date dataFechamento) {
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

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	@Override
	public String toString(){
		return "Comanda[" + this.id + " - " + this.valorTotal + " - " + this.dataAbertura + " - " + this.dataFechamento + "]";
	}

}
