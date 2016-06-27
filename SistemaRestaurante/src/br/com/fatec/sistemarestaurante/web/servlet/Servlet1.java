package br.com.fatec.sistemarestaurante.web.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet1 extends HttpServlet {

	/** */
	private static final long serialVersionUID = 8193598628287813449L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().println(req.getParameter("nome"));
		resp.getWriter().println("Servlet GET 1");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().println("Servlet POST 1");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("Servlet 1 - init");
	}

	@Override
	public void destroy() {
		System.out.println("Servlet 1 - destroy");
	}
}
