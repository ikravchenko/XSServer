package org.example;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
 
public class MinimalServer {
 
	private static final String PERSONS_RESPONSE;
	private static final String DOORS_RESPONSE;
	private static final String PERMISSIONS_RESPONSE;
	
	static {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?><Persons>");
		for (int i = 1; i <= 100; i++) {
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
	
	static {
		StringBuilder doorsSB = new StringBuilder();
		doorsSB.append("<?xml version=\"1.0\" encoding=\"utf-8\"?><Doors>");
		for (int i = 1; i <= 100; i++) {
			doorsSB
			.append("<Door number=\"")
			.append(i)
			.append("\" name=\"")
			.append("Door_")
			.append(i)
			.append("\"/>");
		}
		doorsSB.append("</Doors>");
		DOORS_RESPONSE = doorsSB.toString();
	}
	
	static {
		StringBuilder permissionsSB = new StringBuilder();
		permissionsSB.append("<?xml version=\"1.0\" encoding=\"utf-8\"?><Permissions>");
		for (int i = 1; i <= 100; i+=9) {
			for (int j = 1; j <= 100; j+=3) {
				permissionsSB
				.append("<Permission doorNumber=\"")
				.append(i)
				.append("\" personNumber=\"")
				.append(j)
				.append("\"/>");
			}
		}
		permissionsSB.append("</Permissions>");
		PERMISSIONS_RESPONSE = permissionsSB.toString();
	}
	
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(PersonsServlet.class, "/persons");
        handler.addServletWithMapping(DoorsServlet.class, "/doors");
        handler.addServletWithMapping(PermissionsServlet.class, "/permissions");
        server.setHandler(handler);    
        server.start();
        server.join();
    }
    
    @SuppressWarnings("serial")
    public static class PersonsServlet extends HttpServlet {
 
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PERSONS_RESPONSE);
        }
    }
    
    @SuppressWarnings("serial")
    public static class DoorsServlet extends HttpServlet {
 
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(DOORS_RESPONSE);
        }
    }
    
    @SuppressWarnings("serial")
    public static class PermissionsServlet extends HttpServlet {
 
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(PERMISSIONS_RESPONSE);
        }
    }
}