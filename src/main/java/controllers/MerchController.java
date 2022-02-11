package controllers;

import com.google.gson.Gson;
import exceptions.ResourceNotFoundException;
import models.Merchandise;
import services.MerchService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MerchController {
	
	MerchService ms;
	Gson gson = new Gson();
	
	public MerchController(MerchService ms) {
		this.ms = ms;
	}
	
	public void getMerchById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String input = request.getAttribute("id").toString();
		int id = 0;
		if (input.matches("[0-9]+")) {
			id = Integer.parseInt(input);
		} else {
			response.sendError(400, "ID is not a number");
			return;
		}
		
		Merchandise m = new Merchandise();
		m = ms.getItem(id, m);
		
		response.getWriter().append((m != null) ? gson.toJson(m) : "{}");
		
	}
	
	public void getAllMerch(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		List<Object> merchList = new ArrayList<>();
		String price = request.getParameter("price");
		Merchandise m = new Merchandise();
		
		//if the price is null, then that Query Parameter was not provided, so we will do a normal getAllMerch
		if (price == null) {
			//merchList = ms.getAll(m);
			merchList.add(ms.getAll(m));
		} else {
			try {
				merchList.add(ms.getItem(1, m));
			} catch (NumberFormatException e) {
				response.sendError(400, "Cannot find merch item by id.");
			}
		}
		
		response.getWriter().append(gson.toJson(merchList));
		System.out.println(merchList);
	}
	
	public void addMerch(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//Extract data/information from the request
		BufferedReader reader = request.getReader();
		Merchandise m = gson.fromJson(reader, Merchandise.class);
		
		//Call some service(s) to process the data/information
		m = ms.addItem(m);
		
		//Generate a response from that processed data.
		if (m != null) {
			response.setStatus(201);
			response.getWriter().append(gson.toJson(m));
		} else {
			response.getWriter().append("{}");
		}
		
	}
	
	public void updateMerch(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Merchandise m = gson.fromJson(request.getReader(), Merchandise.class);
		m.setId((int) request.getAttribute("id"));
		
		ms.update(m.getId(), m);
		m = ms.getItem(m.getId(), m);
		
		response.getWriter().append((m != null) ? gson.toJson(m) : "{}");
	}
	
	public void deleteMerch(HttpServletRequest request, HttpServletResponse response) throws ResourceNotFoundException, IOException {
		
		Merchandise m = new Merchandise();
		int id = (int) request.getAttribute("id");
		
		ms.deleteItem(id, m);

		//response.getWriter().append((m != null) ? gson.toJson(m) : "{}");
		response.setStatus(204);
	}
	
	
	
}
