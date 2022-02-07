package webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {
	
	private final int port;
	private ServerSocket serverSocket;
	private ExecutorService threadPool = Executors.newFixedThreadPool(10);
	public Map<String, Servlet> container = new HashMap<>();
	
	public WebServer(int port) {
		this.port = port;
		Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
	}
	
	public void addServlet(String path, Servlet servlet) {
		this.container.put(path, servlet);
	}
	
	public void start() {
		
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Listening on port " + port);
			while(serverSocket.isBound()) {
				Socket client = serverSocket.accept();
				threadPool.execute(() -> handle(client));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	private void handle(Socket client) {
		
		try {
			final Request request = new Request(client.getInputStream());
			final Response response = new Response(client.getOutputStream());
			Servlet servlet = this.container.get(request.getPath());
			if(servlet == null) {
				servlet = this.container.get("/");
			}
			servlet.service(request, response);
			response.send();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void shutdown() {
		System.out.println("Shutting Down");
		try {
			this.threadPool.shutdown();
			this.serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}