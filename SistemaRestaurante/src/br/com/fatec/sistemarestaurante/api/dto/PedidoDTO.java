package br.com.fatec.sistemarestaurante.api.dto;

import java.util.Date;

import br.com.fatec.sistemarestaurante.api.entity.Comanda;
import br.com.fatec.sistemarestaurante.api.entity.Garcom;

public class PedidoDTO {
	
	private Long id;
	private String status;
	private Date dataAbertura;
	private Double valorTotal;
	private Comanda comanda;
	private Garcom garcom;
	
	public PedidoDTO(){
	
	}
	
	public PedidoDTO(Long id ,String status,Date dataAbertura, Double valorTotal, Comanda comanda, Garcom garcom) {
		this.id = id;
		this.status = status;
		this.dataAbertura = dataAbertura;
		this.valorTotal = valorTotal;
		this.comanda = comanda;
		this.garcom = garcom;	
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

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Comanda getComanda() {
		return comanda;
	}

	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}

	public Garcom getGarcom() {
		return garcom;
	}

	public void setGarcom(Garcom garcom) {
		this.garcom = garcom;
	}

	@Override
	public String toString() {
		return "PedidoDTO [" + this.id + " - " + this.status + " - " + this.dataAbertura + " - " + this.valorTotal + " - " + this.comanda + " - " + this.garcom +"]";
	}
	
}
