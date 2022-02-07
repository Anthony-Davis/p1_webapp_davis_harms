package webserver;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Response {
	
	private String responseLine = "HTTP/1.1 200 OK";
	private Map<String, String> headers;
	
	private OutputStream outputStream;
	private StringWriter stringWriter; //Will be a way to access what has been printed by the PrintWriter
	private PrintWriter printWriter; //Used to print information into our OutputStream.
	// namely used to print information from our generated body.
	
	
	public Response(OutputStream outputStream) {
		this.outputStream = outputStream;
		this.stringWriter = new StringWriter();
		this.printWriter = new PrintWriter(stringWriter);
		headers = new HashMap<>();
	}
	
	public void setStatus(int statusCode, String statusMessage) {
		this.responseLine = "HTTP/1.1 " + statusCode + " " + statusMessage;
	}
	
	public void setHeader(String key, String value) {
		this.headers.put(key, value);
	}
	
	public PrintWriter getWriter() {
		return this.printWriter;
	}
	
	//Craft a method for how to take all of the printed information, and headers, and put into our OutputStream.
	//In preparation for the Socket returning that information to the Client.
	public void send() {
		//Crafting the HTTP Message into OutputStream
		String body = this.stringWriter.toString();
		PrintWriter writer = new PrintWriter(outputStream, true);
		writer.println(this.responseLine);
		headers.put("Connection", "Close");
		headers.put("Content-Length", Integer.toString(body.length()));
		headers.forEach((key, value) -> writer.println(key + ": " + value));
		writer.println();
		writer.println(body);
	}
	
}
