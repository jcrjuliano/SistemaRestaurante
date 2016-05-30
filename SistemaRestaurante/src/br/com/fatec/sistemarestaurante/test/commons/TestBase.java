package br.com.fatec.sistemarestaurante.test.commons;

import org.junit.After;
import org.junit.BeforeClass;

import br.com.fatec.sistemarestaurante.api.entity.Comanda;
import br.com.fatec.sistemarestaurante.api.entity.Garcom;
import br.com.fatec.sistemarestaurante.api.entity.Ingrediente;
import br.com.fatec.sistemarestaurante.api.entity.ItemIngrediente;
import br.com.fatec.sistemarestaurante.api.entity.ItemProd;
import br.com.fatec.sistemarestaurante.api.entity.ListaPedidos;
import br.com.fatec.sistemarestaurante.api.entity.Pedido;
import br.com.fatec.sistemarestaurante.api.entity.Produto;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import br.com.spektro.minispring.core.implfinder.ContextSpecifier;
import br.com.spektro.minispring.core.liquibaseRunner.LiquibaseRunnerService;
import br.com.spektro.minispring.core.query.QueryExecutorService;

public class TestBase {
	
	@BeforeClass
	public static void setUp(){
		//Seta o contexto, ou seja, classe base da aplicação
		ContextSpecifier.setContext("br.com.fatec.sistemarestaurante");
		
		//Você diz qual será o banco utilizado.
		ConfigDBMapper.setDefaultConnectionName("test");
		
		//Descobre onde ta o arquivo changelog.master e executa usando api do liquibase.
		LiquibaseRunnerService.run();
	}
	

	@After
	public void setDown() {
		QueryExecutorService.executeQuery("DELETE FROM " + ListaPedidos.TABLE);
		QueryExecutorService.executeQuery("DELETE FROM " + ItemProd.TABLE);
		QueryExecutorService.executeQuery("DELETE FROM " + Pedido.TABLE);
		QueryExecutorService.executeQuery("DELETE FROM " + ItemIngrediente.TABLE);
		
		QueryExecutorService.executeQuery("DELETE FROM " + Comanda.TABLE);
		QueryExecutorService.executeQuery("DELETE FROM " + Garcom.TABLE);
		QueryExecutorService.executeQuery("DELETE FROM " + Ingrediente.TABLE);
		
		
		
		
		QueryExecutorService.executeQuery("DELETE FROM " + Produto.TABLE);
		
		QueryExecutorService.executeQuery("ALTER SEQUENCE SEQ_SCR_INGREDIENTE RESTART WITH 1");
		QueryExecutorService.executeQuery("ALTER SEQUENCE SEQ_SCR_PRODUTO RESTART WITH 1");
		QueryExecutorService.executeQuery("ALTER SEQUENCE SEQ_SCR_ITEM_INGREDIENTE RESTART WITH 1");
		QueryExecutorService.executeQuery("ALTER SEQUENCE SEQ_SCR_ITEM_PROD RESTART WITH 1");
		QueryExecutorService.executeQuery("ALTER SEQUENCE SEQ_SCR_PEDIDO RESTART WITH 1");
		QueryExecutorService.executeQuery("ALTER SEQUENCE SEQ_SCR_LISTA_PEDIDOS RESTART WITH 1");
		QueryExecutorService.executeQuery("ALTER SEQUENCE SEQ_SCR_COMANDA RESTART WITH 1");
		QueryExecutorService.executeQuery("ALTER SEQUENCE SEQ_SCR_GARCOM RESTART WITH 1");
		
	}
}
