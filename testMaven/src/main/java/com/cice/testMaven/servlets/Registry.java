package com.cice.testMaven.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cice.testMaven.datasource.Datasource;


/**
 * Servlet implementation class Registry
 */
public class Registry extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String SUCCESS = "main.jsp";
    private static String Error = "index.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registry() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/registry.jsp");
		
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("NOMBRE");
		String pass = request.getParameter("PASS");
		System.out.println("Nombre ->" +nombre);
		System.out.println("Pass ->" +pass);
		String result = null;
		System.out.println("Guardando...");
		try {
			Datasource.addDataToDB(nombre, pass);
			result = SUCCESS;
			System.out.println("Guardando!!");
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (NoSuchAlgorithmException e) {
			result = Error;
			System.out.println("Error");
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(result);
		dispatcher.forward(request, response);
	

	
	}

}
