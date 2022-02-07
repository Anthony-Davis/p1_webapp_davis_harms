package servlets;

import exceptions.ResourceNotFoundException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class MainServlet extends HttpServlet {
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestHelper.getProcess(request, response);
		
		/*
		response.getWriter().append("Welcome to the Main Servlet!");
		System.out.println("Welcome to the Main Servlet!");
		
		//ServletConfig sconf = this.getServletConfig();
		//String configstr = sconf.getInitParameter("Secret");
		//response.getWriter().append("\n" + configstr);
		//response.getWriter().append("\n" + this.getInitParameter("Secret"));
		
		ServletContext sconxt = this.getServletContext();
		String conxtstr = sconxt.getInitParameter("AppName");
		response.getWriter().append("\n" + conxtstr);
		
		HttpSession session = request.getSession();
		//System.out.println(session.getId());
		
		//session.setMaxInactiveInterval(900);
		//session.setAttribute("currentUser", "{ 'username':'Ryan','password':'pass' }");
		
		System.out.println(session.getAttribute("currentUser"));
		
		System.out.println(request.getRequestURL());
		System.out.println(request.getContextPath());
		System.out.println(request.getRequestURI());
		
		
		String uri = request.getRequestURI();
		
		switch(uri) {
			
			case "/*":
				response.getWriter().append("\n* Worked!");
				break;
			case "/MainServlet":
				response.getWriter().append("\nMainServlet Worked!");
				break;
			case "/webapp/MainServlet":
				response.getWriter().append("\nUse of webapp/MainServlet worked");
				break;
			case "/webapp":
				response.getWriter().append("\nUse of webapp worked");
				break;
			case "/MainServlet/googleredirect":
				response.sendRedirect("https://www.google.com");
				break;
			default:
				response.getWriter().append("console.log(\\'IN DEFAULT STATEMENT!\\')");
				response.sendError(418);
				break;
			
		}*/
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestHelper.postProcess(request, response);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestHelper.putProcess(request, response);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			RequestHelper.deleteProcess(request, response);
		} catch (ResourceNotFoundException e) {
			response.sendError(422, "Cannot delete Merchandise ID that does not exist");
		}
	}

	/*
	
	public MainServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.getWriter().append("Welcome to My Servlet!");
		System.out.println("Welcome to My Servlet!");
		
		ServletConfig sconf = this.getServletConfig();
		String configstr = sconf.getInitParameter("Secret");
		response.getWriter().append("\n" + configstr);
		response.getWriter().append("\n" + this.getInitParameter("Secret"));
		
		ServletContext sconxt = this.getServletContext();
		String conxtstr = sconxt.getInitParameter("AppName");
		response.getWriter().append("\n" + conxtstr);
		
		HttpSession session = request.getSession();
		System.out.println(session.getId());
		
		session.setMaxInactiveInterval(900);
		
		session.setAttribute("currentUser", "{ 'username':'Ryan','password':'pass' }");
		
		System.out.println(session.getAttribute("currentUser"));
		
		System.out.println(request.getRequestURL());
		System.out.println(request.getContextPath());
		System.out.println(request.getRequestURI());
		
		
		String uri = request.getRequestURI();
		
		switch(uri) {
			
			case "/ServletEx/test": {
				response.getWriter().append("\nTest Worked!");
				break;
			} case "/ServletEx/googleredirect": {
				response.sendRedirect("https://www.google.com");
				break;
			} case "/ServletEx/secondredirect": {
				response.sendRedirect("/ServletEx/SecondServlet");
				break;
			} case "/ServletEx/secondforward": {
				//Forwards needs a RequestDispatcher
				RequestDispatcher rd = request.getRequestDispatcher("/SecondServlet");
				rd.forward(request, response);
			}
			default: {
				response.sendError(418);
				break;
			}
			
		}
	}
	 */
}
