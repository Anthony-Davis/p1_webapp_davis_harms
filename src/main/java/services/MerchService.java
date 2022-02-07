package services;

import com.revature.exceptions.MissingAnnotationException;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.repositories.Repository;
import com.revature.services.RepoService;
import models.Merchandise;

import java.sql.SQLException;

public class MerchService implements RepoService {
	
	private Repository<Object> repo;
	
	public MerchService(Repository<Object> repo){
		this.repo = repo;
	}
	
	@Override
	public void initializeTable(Object o) {
		try {
			repo.initializeTable(o);
		} catch (SQLException | MissingAnnotationException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Merchandise addItem(Object o) {
		//return repo.addItem(o);
		return (Merchandise) repo.addItem(o);
	}
	
	@Override
	public Merchandise getItem(int id, Object o) {
		return (Merchandise) repo.getItem(id, o);
	}
	
	@Override
	public Merchandise getAll(Object o) {
		try {
			return (Merchandise) repo.getAll(o);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void deleteItem(int id, Object o) {
		repo.deleteItem(id, o);
	}
	
	@Override
	public void update(int id, Object o) {
		try {
			repo.update(id, o);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
