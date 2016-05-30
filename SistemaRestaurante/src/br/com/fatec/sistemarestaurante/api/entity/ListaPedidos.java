package br.com.fatec.sistemarestaurante.api.entity;

import java.util.List;

import com.google.common.collect.Lists;

public class ListaPedidos {
	public static final String TABLE = "SCR_LISTA_PEDIDOS";
	public static final String COL_ID = "ID";
	public static final String COL_ID_PEDIDO = "ID_PEDIDO";
	public static final String COL_STATUS = "STATUS";
	
	private Long id;
	private Pedido Pedido;
	private String status;
	
	
	public Pedido getPedido() {
		return Pedido;
	}
	public void setPedido(Pedido pedido) {
		Pedido = pedido;
	}
	public ListaPedidos(Long id,
			br.com.fatec.sistemarestaurante.api.entity.Pedido pedido,
			String status) {
		this.id = id;
		Pedido = pedido;
		this.status = status;
	}
	public ListaPedidos(){
		
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
	
	public static List<String> getColunas() {
		return Lists.newArrayList(COL_ID, COL_ID_PEDIDO, COL_STATUS);
	}

	public static String[] getColunasArray() {
		return new String[] { COL_ID, COL_ID_PEDIDO, COL_STATUS };
	}
	
	
	
}
