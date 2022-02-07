package driver;

import webserver.DefaultServlet;
import webserver.WebServer;

public class WebDriver {
	
	public static void main(String[] args) {
		
		WebServer server = new WebServer(8080);
		server.addServlet("/", new DefaultServlet());
		server.addServlet("/hello", (request, response) -> {
			String name = request.getParameter("name");
			if(name == null) {
				name = "World";
			}
			response.getWriter().println("Hello, " + name);
		});
		
		server.start();
	}
}
