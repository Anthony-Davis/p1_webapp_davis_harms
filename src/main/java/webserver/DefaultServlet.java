package webserver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DefaultServlet implements Servlet {
	
	@Override
	public void service(Request request, Response response) {
		
		try {
			String path = (request.getPath() != null) ? request.getPath().substring(1) : "";
			
			if("".equals(path)) {
				path = "src/main/webapp/index.html";
			}
			
			//Take our path string and convert it into an Object that represents that file, based on that path.
			//Read the contents of that file.
			Path p = Paths.get(path);
			List<String> fileLines = Files.readAllLines(p);
			String MIMEType = Files.probeContentType(p);
			
			StringBuilder fileBody = new StringBuilder();
			for(String line : fileLines) {
				fileBody.append(line);
			}
			
			response.setHeader("Content-Type", MIMEType);
			response.getWriter().println(fileBody);
			
		} catch (IOException e) {
			response.setStatus(404, "Not Found");
			response.setHeader("Content-Type", "text/html");
			response.getWriter().println("<h1>File Not Found!</h1>");
		}
	}
}
