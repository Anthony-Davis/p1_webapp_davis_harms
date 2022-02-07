package testing;

//import com.sun.org.apache.regexp.internal.RE;
import webserver.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RequestTest {
	
	
	public static void main(String[] args) throws IOException {
		
		//Craft a sample HTTP Message
		String getMessage =
				"GET /hello HTTP/1.1\n" +
						"Host: localhost:8080\n" +
						"Content-Length: 123\n" +
						"\n";
		
		String getMessage2 =
				"GET /hello?name=Adam&age=40 HTTP/1.1\n" +
						"Host: localhost:8080\n" +
						"Content-Length: 123\n" +
						"\n";
		
		
		String postMessage =
				"POST /hello HTTP/1.1\n" +
						"Host: localhost:8080\n" +
						"Content-Length: 345\n" +
						"\n" +
						"name=Ryan&age=50";
		
		
		InputStream inputStream = new ByteArrayInputStream(getMessage.getBytes());
		Request req1 = new Request(inputStream);
		
		System.out.println();
		inputStream = new ByteArrayInputStream(getMessage2.getBytes());
		Request req2 = new Request(inputStream);
		
		System.out.println();
		inputStream = new ByteArrayInputStream(postMessage.getBytes());
		Request req3 = new Request(inputStream);
		
	}
	
}
