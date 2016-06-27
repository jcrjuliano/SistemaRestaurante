package br.com.fatec.sistemarestaurante.web.listener;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Listener2 implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Listener 2 - destroy");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Listener 2 - Init");
	}

}
