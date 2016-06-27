package br.com.fatec.sistemarestaurante.web.listener;

import java.util.Calendar;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.fatec.sistemarestaurante.api.dao.ComandaDAO;
import br.com.fatec.sistemarestaurante.api.dao.GarcomDAO;
import br.com.fatec.sistemarestaurante.api.dao.IngredienteDAO;
import br.com.fatec.sistemarestaurante.api.dao.ItemIngredienteDAO;
import br.com.fatec.sistemarestaurante.api.dao.ItemProdDAO;
import br.com.fatec.sistemarestaurante.api.dao.ListaPedidosDAO;
import br.com.fatec.sistemarestaurante.api.dao.PedidoDAO;
import br.com.fatec.sistemarestaurante.api.dao.ProdutoDAO;
import br.com.fatec.sistemarestaurante.api.entity.Comanda;
import br.com.fatec.sistemarestaurante.api.entity.Garcom;
import br.com.fatec.sistemarestaurante.api.entity.Ingrediente;
import br.com.fatec.sistemarestaurante.api.entity.ItemIngrediente;
import br.com.fatec.sistemarestaurante.api.entity.ItemProd;
import br.com.fatec.sistemarestaurante.api.entity.ListaPedidos;
import br.com.fatec.sistemarestaurante.api.entity.Pedido;
import br.com.fatec.sistemarestaurante.api.entity.Produto;
import br.com.spektro.minispring.core.implfinder.ImplFinder;

public class ScenarioListener implements ServletContextListener{
	
	private Comanda comanda1, comanda2, comanda3;
	private Garcom garcom1, garcom2, garcom3, garcom4;
	private Ingrediente ingred1, ingred2, ingred3, ingred4;
	private ItemIngrediente it1, it2, it3, it4;
	private ItemProd itemProd1, itemProd2, itemProd3, itemProd4;
	private ListaPedidos lp1;
	private Pedido ped1, ped2, ped3;
	private Produto prod1, prod2, prod3;
	
	protected ComandaDAO comandaDAO;
	protected GarcomDAO garcomDAO;
	protected IngredienteDAO ingredienteDAO;
	protected ItemIngredienteDAO itemIngredienteDAO;
	protected ItemProdDAO itemProdDAO;
	protected ListaPedidosDAO listaPedidosDAO;
	protected PedidoDAO pedidoDAO;
	protected ProdutoDAO produtoDAO;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Listener Scenario - destroy");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		this.comandaDAO = ImplFinder.getImpl(ComandaDAO.class);
		this.garcomDAO = ImplFinder.getImpl(GarcomDAO.class);
		this.ingredienteDAO = ImplFinder.getImpl(IngredienteDAO.class);
		this.itemIngredienteDAO = ImplFinder.getImpl(ItemIngredienteDAO.class);
		this.itemProdDAO = ImplFinder.getImpl(ItemProdDAO.class);
		this.listaPedidosDAO = ImplFinder.getImpl(ListaPedidosDAO.class);
		this.pedidoDAO = ImplFinder.getImpl(PedidoDAO.class);
		this.produtoDAO = ImplFinder.getImpl(ProdutoDAO.class);		
		
		insertGarcom();
		insertIngrediente();
		insertProduto();
		insertItemIngred();
		insertComanda();
		insertPedido();
		insertListaPedidos();
		insertItemProd();
	}
	
	private void insertGarcom(){
		garcom1 = new Garcom();
		garcom1.setIdade("18");
		garcom1.setNome("Clay Morrow");
		garcom1.setRegistro("0001");
		garcom1.setSexo("Masculino");
		
		
		garcom2 = new Garcom();
		garcom2.setIdade("20");
		garcom2.setNome("Arya Stark");
		garcom2.setRegistro("0002");
		garcom2.setSexo("Feminino");
		
		garcom3 = new Garcom();
		garcom3.setIdade("19");
		garcom3.setNome("Jon Snow");
		garcom3.setRegistro("0003");
		garcom3.setSexo("Masculino");
		
		garcom4 = new Garcom();
		garcom4.setIdade("18");
		garcom4.setNome("Gemma Teller");
		garcom4.setRegistro("0004");
		garcom4.setSexo("Feminino");
		
		garcom1.setId(this.garcomDAO.save(garcom1));
		garcom2.setId(this.garcomDAO.save(garcom2));
		garcom3.setId(this.garcomDAO.save(garcom3));
		garcom4.setId(this.garcomDAO.save(garcom4));
		
	}
	
	private void insertIngrediente(){
		ingred1 = new Ingrediente();
		ingred2 = new Ingrediente();
		ingred3 = new Ingrediente();
		ingred4 = new Ingrediente();
		
		ingred1.setDescricao("Pão");

		ingred2.setDescricao("Salsicha");

		ingred3.setDescricao("Hamburguer");
		
		ingred4.setDescricao("Presunto");
		
		ingred1.setId(this.ingredienteDAO.save(ingred1));
		ingred2.setId(this.ingredienteDAO.save(ingred2));
		ingred3.setId(this.ingredienteDAO.save(ingred3));
		ingred4.setId(this.ingredienteDAO.save(ingred4));
		
	}
	
	private void insertProduto(){
		prod1 = new Produto();
		prod2 = new Produto();
		prod3 = new Produto();
		
		prod1.setDescricao("Coca-Cola");
		prod1.setPreco(new Double(5.00));
		prod1.setStatus("Disponivel");
		
		prod2.setDescricao("Fanta");
		prod2.setPreco(new Double(5.00));
		prod2.setStatus("Indisponivel");
		
		prod3.setDescricao("X-Tudo");
		prod3.setPreco(new Double(15.00));
		prod3.setStatus("Disponivel");
		
		prod1.setId(this.produtoDAO.save(prod1));
		prod2.setId(this.produtoDAO.save(prod2));
		prod3.setId(this.produtoDAO.save(prod3));
		
	}
	
	private void insertItemIngred(){
		it1 = new ItemIngrediente();
		it2 = new ItemIngrediente();
		it3 = new ItemIngrediente();
		it4 = new ItemIngrediente();
		
		it1.setIngrediente(ingred1);
		it1.setProduto(prod2);
		it1.setQuantidade(100);
		
		it2.setIngrediente(ingred2);
		it2.setProduto(prod2);
		it2.setQuantidade(56);
		
		it3.setIngrediente(ingred3);
		it3.setProduto(prod2);
		it3.setQuantidade(44);

		it4.setIngrediente(ingred4);
		it4.setProduto(prod2);
		it4.setQuantidade(54);
		
		
		this.itemIngredienteDAO.save(it1);
		this.itemIngredienteDAO.save(it2);
		this.itemIngredienteDAO.save(it3);
		this.itemIngredienteDAO.save(it4);
		
		
	}
	
	private void insertComanda(){
		comanda1 = new Comanda();
		comanda2 = new Comanda();
		comanda3 = new Comanda();
		
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2016);
		c1.set(Calendar.MONTH, Calendar.MAY);
		c1.set(Calendar.DAY_OF_MONTH, 16);
		c1.set(Calendar.HOUR_OF_DAY, 18);
		c1.set(Calendar.MINUTE, 8);
		c1.set(Calendar.SECOND, 56);
		
		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2016);
		c2.set(Calendar.MONTH, Calendar.MAY);
		c2.set(Calendar.DAY_OF_MONTH, 18);
		c2.set(Calendar.HOUR_OF_DAY, 16);
		c2.set(Calendar.MINUTE, 40);
		c2.set(Calendar.SECOND, 0);
		
		Calendar c3 = Calendar.getInstance();
		c3.set(Calendar.YEAR, 2016);
		c3.set(Calendar.MONTH, Calendar.MAY);
		c3.set(Calendar.DAY_OF_MONTH, 21);
		c3.set(Calendar.HOUR_OF_DAY, 20);
		c3.set(Calendar.MINUTE, 12);
		c3.set(Calendar.SECOND, 0);
		
		
		
		
		comanda1.setDataAbertura(c1.getTime());
		c1.set(Calendar.HOUR_OF_DAY, 20);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		comanda1.setDataFechamento(c1.getTime());
		comanda1.setValorTotal(new Double(0.0));
		comanda1.setId(this.comandaDAO.save(comanda1));
		
		comanda2.setDataAbertura(c2.getTime());
		c2.set(Calendar.HOUR_OF_DAY, 19);
		c2.set(Calendar.MINUTE, 4);
		c2.set(Calendar.SECOND, 0);
		comanda2.setDataFechamento(c2.getTime());
		comanda2.setValorTotal(new Double(0.0));
		comanda2.setId(comandaDAO.save(comanda2));
		
		comanda3.setDataAbertura(c3.getTime());
		c3.set(Calendar.HOUR_OF_DAY, 23);
		c3.set(Calendar.MINUTE, 42);
		c3.set(Calendar.SECOND, 0);
		comanda3.setDataFechamento(c3.getTime());
		comanda3.setValorTotal(new Double(0.0));
		
		comanda3.setId(this.comandaDAO.save(comanda3));
		
		
	
	}
	
	private void insertPedido(){
		ped1 = new Pedido();
		ped2 = new Pedido();
		ped3 = new Pedido();
		
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2016);
		c1.set(Calendar.MONTH, Calendar.MAY);
		c1.set(Calendar.DAY_OF_MONTH, 16);
		c1.set(Calendar.HOUR_OF_DAY, 18);
		c1.set(Calendar.MINUTE, 8);
		c1.set(Calendar.SECOND, 56);
		
		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2016);
		c2.set(Calendar.MONTH, Calendar.MAY);
		c2.set(Calendar.DAY_OF_MONTH, 18);
		c2.set(Calendar.HOUR_OF_DAY, 16);
		c2.set(Calendar.MINUTE, 40);
		c2.set(Calendar.SECOND, 0);
		
		Calendar c3 = Calendar.getInstance();
		c3.set(Calendar.YEAR, 2016);
		c3.set(Calendar.MONTH, Calendar.MAY);
		c3.set(Calendar.DAY_OF_MONTH, 21);
		c3.set(Calendar.HOUR_OF_DAY, 20);
		c3.set(Calendar.MINUTE, 12);
		c3.set(Calendar.SECOND, 0);
		
		ped1.setComanda(comanda1);
		ped1.setDataAbertura(c1.getTime());
		ped1.setGarcom(garcom1);
		ped1.setStatus("Pendente");
		ped1.setValorTotal(new Double(25.00));
		ped1.setId(this.pedidoDAO.save(ped1));
		
		ped2.setComanda(comanda2);
		ped2.setDataAbertura(c2.getTime());
		ped2.setGarcom(garcom2);
		ped2.setStatus("Pendente");
		ped2.setValorTotal(new Double(15.00));
		ped2.setId(this.pedidoDAO.save(ped2));
		
		ped3.setComanda(comanda3);
		ped3.setDataAbertura(c3.getTime());
		ped3.setGarcom(garcom3);
		ped3.setStatus("Pendente");
		ped3.setValorTotal(new Double(55.00));
		ped3.setId(this.pedidoDAO.save(ped3));
		
		
	}

	private void insertListaPedidos(){
		lp1 = new ListaPedidos();
		
		lp1.setPedido(ped1);
		lp1.setStatus("Pendente");
		lp1.setId(this.listaPedidosDAO.save(lp1));
		
	}
	
	private void insertItemProd(){
		itemProd1 = new ItemProd();
		itemProd2 = new ItemProd();
		itemProd3 = new ItemProd();
		itemProd4 = new ItemProd();
		
		itemProd1.setPedido(ped1);
		itemProd1.setPreco(prod1.getPreco());
		itemProd1.setProduto(prod1);
		
		itemProd2.setPedido(ped1);
		itemProd2.setPreco(prod2.getPreco());
		itemProd2.setProduto(prod2);
		
		itemProd3.setPedido(ped2);
		itemProd3.setPreco(prod3.getPreco());
		itemProd3.setProduto(prod3);
		
		itemProd4.setPedido(ped3);
		itemProd4.setPreco(prod1.getPreco());
		itemProd4.setProduto(prod1);
		
		
		itemProd1.setId(this.itemProdDAO.save(itemProd1));
		itemProd2.setId(this.itemProdDAO.save(itemProd2));
		itemProd3.setId(this.itemProdDAO.save(itemProd3));
		itemProd4.setId(this.itemProdDAO.save(itemProd4));
	}
}
