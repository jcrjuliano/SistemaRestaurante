package br.com.fatec.sistemarestaurante.web.servlet;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet2 extends HttpServlet {

	int count = 0;

	/** */
	private static final long serialVersionUID = 8193598628287813449L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().println("Servlet GET 2 - " + this.count++);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().println("Servlet POST 2");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("Servlet 2 - init");
	}

	@Override
	public void destroy() {
		System.out.println("Servlet 2 - destroy");
	}
}
