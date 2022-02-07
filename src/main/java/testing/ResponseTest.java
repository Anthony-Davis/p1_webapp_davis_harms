package testing;

import webserver.Response;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ResponseTest {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		OutputStream outputStream = new FileOutputStream("src/main/resources/out.txt");
		
		Response res = new Response(outputStream);
		
		res.setStatus(404, "Not Found");
		res.getWriter().println("contents of body");
		
		res.send();
	}
	
}
