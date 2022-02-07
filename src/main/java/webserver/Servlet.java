package webserver;

public interface Servlet {
	
	void service(Request request, Response response);
	
}
