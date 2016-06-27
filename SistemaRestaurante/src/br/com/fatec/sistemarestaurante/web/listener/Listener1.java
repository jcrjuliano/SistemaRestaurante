package br.com.fatec.sistemarestaurante.web.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Listener1 implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Listener 1 - destroy");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Listener 1 - Init");
	}

}
