package org.example;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
 
public class MinimalServer {
 
	private static final String PERSONS_RESPONSE;
	static {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?><Persons>");
		for (int i = 1; i <= 1000; i++) {
			sb
			.append("<Person number=\"")
			.append(i)
			.append("\" name=\"")
			.append("Person_")
			.append(i)
			.append("\"/>");
		}
		sb.append("</Persons>");
		PERSONS_RESPONSE = sb.toString();
	}
	
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(HelloServlet.class, "/persons");//Set the servlet to run.
        server.setHandler(handler);    
        server.start();
        server.join();
    }
    
    @SuppressWarnings("serial")
    public static class HelloServlet extends HttpServlet {
 
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PERSONS_RESPONSE);
        }
    }
}