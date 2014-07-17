package org.example;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
 
public class MinimalServer {
 
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
            response.getWriter().println("<?xml version=\"1.0\" encoding=\"utf-8\"?>"+ 
            		"<Persons><Person number=\"00000915\" name=\"John Doe\"/>" +
                    "<Person number=\"00000989\" name=\"Freddy Star\"/>" +
                    "<Person number=\"00000922\" name=\"Hero Mero\"/></Persons>");
        }
    }
}