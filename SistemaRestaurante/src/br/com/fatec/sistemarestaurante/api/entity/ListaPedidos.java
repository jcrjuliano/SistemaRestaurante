package br.com.fatec.sistemarestaurante.api.entity;

public class ListaPedidos {
	public static final String TABLE = "SCR_LISTA_PEDIDOS";
	public static final String COL_ID = "ID";
	public static final String COL_ID_PEDIDO = "ID_PEDIDO";
	public static final String COL_STATUS = "STATUS";
	
	private Long id;
	private Long idPedido;
	private String status;
	public ListaPedidos(Long id, Long idPedido, String status) {
		this.id = id;
		this.idPedido = idPedido;
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
	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
