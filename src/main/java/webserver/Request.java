package webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Request {
	
	private String method;
	private String path;
	private Map<String, String> headers = new HashMap<>();  //Request Headers: HTTP Message -> Content-Length: 345
	private Map<String, String> parameters = new HashMap<>(); //?param=value&param2=value2
	
	
	public Request(InputStream in) throws IOException {
		//We need to parse through the HTTP (Request) Message
		//We will use a buffered Reader to read the HTTP Message line by line
		parse(new BufferedReader(new InputStreamReader(in)));
	}
	
	private void parse(BufferedReader reader) throws IOException {
		
		String line = reader.readLine();
		System.out.println(line);
		
		parseRequestLine(line);
		
		//Should be starting the Headers from the HTTP Message
		extractHeaders(reader);
		
		// Once the above while loop is done, we have gone through all the headers
		// and we are on the empty line of the HTTP Message
		
		//If our HTTP Request is a GET Request it won't have a body. Other HTTP Methods will.
		//Check if the method is POST or PUT (for now)
		extractBody(reader);
		
	}
	
	private void extractHeaders(BufferedReader reader) throws IOException {
		String line;
		System.out.println("====Headers====");
		line = reader.readLine();
		while(line != null && line.length() != 0) {
			System.out.println(line);
			if(line.contains(":")) {
				parseHeader(line);
			}
			
			line = reader.readLine();
		}
	}
	
	private void extractBody(BufferedReader reader) throws IOException {
		String line;
		if ("POST".equals(method) || "PUT".equals(method)) {
			//Extract the body
			StringBuilder bodyBuilder = new StringBuilder();
			while((line = reader.readLine()) != null) {
				bodyBuilder.append(line);
			}
			
			String body = bodyBuilder.toString();
			System.out.println("Body: " + body);
			
			//Most likely, the only Body String Format that would have an '=' is if it was in our
			//currently desired x-www-form-urlencoded format. Formats like HTML, XML, or JSON don't really
			//use '='
			if(body.contains("=")) {
				parseParameter(body);
			}
			//In even better practice, we should read what the Content-Type header's value is, which
			//would tell us how to parse the information in the Request Body.
			
			//Quick test of our parameters
			for(String key : parameters.keySet()) {
				System.out.println("Parameter: " + key + "; Value: " + parameters.get(key));
			}
		}
	}
	
	private void parseParameter(String params) {
		for(String param : params.split("&")) {
			String[] keyValuePair = param.split("=");
			parameters.put(keyValuePair[0], keyValuePair[1]);
		}
	}
	
	private void parseHeader(String line) {
		String[] tokens = line.split(": ");
		headers.put(tokens[0], tokens[1]);
	}
	
	private void parseRequestLine(String line) {
		if(line == null) return;
		String[] requestLine = line.split(" ");
		System.out.println(Arrays.toString(requestLine));
		this.method = requestLine[0];
		this.path = requestLine[1];
		//Could also store the version if desired.
		
		//We will have to come back to here to manage the parameters as well.
		if(path.contains("?")) {
			// you need to put \? to literally mean ? in regex matching. Bc ? has meaning in regex
			// you actually need to put in \\? in a Java String if you want to pass in \?, due to escape sequences
			String[] splitUri = path.split("\\?");
			path = splitUri[0];
			parseParameter(splitUri[1]);
			
			//Quick test of our parameters
			for(String key : parameters.keySet()) {
				System.out.println("Parameter: " + key + "; Value: " + parameters.get(key));
			}
		}
		
	}
	
	public String getMethod() {
		return method;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getParameter(String key) {
		return this.parameters.get(key);
	}
}
