package com.revature.servlets;

import com.revature.OrmDriver;
import com.revature.controllers.MerchController;
import com.revature.models.Merchandise;
import com.revature.repositories.Repository;
import com.revature.services.MerchService;
import com.revature.util.DbConnector;
import com.revature.exceptions.ResourceNotFoundException;
import org.postgresql.core.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class RequestHelper {
	
	public static void getProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Repository<Object> repo = new Repository<>();
		MerchService ms = new MerchService(repo);
		MerchController mc = new MerchController(ms);
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		String[] uriTokens = uri.split("/");
		System.out.println(Arrays.toString(uriTokens));
		System.out.println("num of uriTokens:\t" + uriTokens.length);
		
		switch (uriTokens.length) {
			case 0:
			case 1:
			case 2:
				response.sendError(404);
				break;
			case 3:
				if(("merchandise").equals(uriTokens[2])) mc.getAllMerch(request, response);
				else response.sendError(400, "Collection does not exist");
				break;
			case 4:
				request.setAttribute("id", uriTokens[3]);
				if(("merchandise").equals(uriTokens[2])) mc.getMerchById(request, response);
				break;
			default:
				response.sendError(400);
				break;
		}
		
	}
	
	public static void postProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Repository<Object> repo = new Repository<>();
		MerchService ms = new MerchService(repo);
		MerchController mc = new MerchController(ms);
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		String[] uriTokens = uri.split("/");
		System.out.println(Arrays.toString(uriTokens));
		
		switch (uriTokens.length) {
			case 0:
			case 1:
			case 2:
				response.sendError(404);
				break;
			case 3:
				if (("merchandise").equals(uriTokens[2])) mc.addMerch(request, response);
				else response.sendError(400, "Collection does not exist");
				break;
			default:
				response.sendError(400);
				break;
		}
		
	}
	
	public static void putProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Repository<Object> repo = new Repository<>();
		MerchService ms = new MerchService(repo);
		MerchController mc = new MerchController(ms);
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		String[] uriTokens = uri.split("/");
		System.out.println(Arrays.toString(uriTokens));
		
		switch (uriTokens.length) {
			case 0:
			case 1:
			case 2:
				response.sendError(404);
				break;
			case 4:
				int id = 0;
				String input = uriTokens[3];
				
				if(input.matches("[0-9]+")) {
					id = Integer.parseInt(input);
				} else {
					response.sendError(400, "ID is not a number");
					return;
				}
				
				request.setAttribute("id", id);
				if (("merchandise").equals(uriTokens[2])) mc.updateMerch(request, response);
				else response.sendError(400, "Collection does not exist");
				break;
			default:
				response.sendError(400);
				break;
		}
		
	}
	
	public static void deleteProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ResourceNotFoundException, ResourceNotFoundException {
		
		Repository<Object> repo = new Repository<>();
		MerchService ms = new MerchService(repo);
		MerchController mc = new MerchController(ms);
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		String[] uriTokens = uri.split("/");
		System.out.println(Arrays.toString(uriTokens));
		
		switch (uriTokens.length) {
			case 0:
			case 1:
			case 2:
				response.sendError(404);
				break;
			case 4:
				int id = 0;
				String input = uriTokens[3];
				
				if(input.matches("[0-9]+")) {
					id = Integer.parseInt(input);
				} else {
					response.sendError(400, "ID is not a number");
					return;
				}
				
				request.setAttribute("id", id);
				if (("merchandise").equals(uriTokens[2])) mc.deleteMerch(request, response);
				else response.sendError(400, "Collection does not exist");
				break;
			default:
				response.sendError(400);
				break;
			
		}
	}
	
	
}
