import com.revature.annotations.Table;
import com.revature.exceptions.MissingAnnotationException;
import com.revature.repositories.Repository;
import com.revature.models.Merchandise;
import com.revature.models.TestModel;
import com.revature.services.MerchService;

import java.sql.SQLException;

public class testing {
	
	public static void main(String[] args) {
		
		Repository<Object> repo = new Repository<>();
		MerchService ms = new MerchService(repo);
		Merchandise m = new Merchandise();
		
		ms.initializeTable(m);
		
		System.out.println("Table name:\t" + m.getClass().getAnnotation(Table.class).tableName() + "\n" );
		
		m.setName("Television");
		m.setPrice(499.99);
		m.setDepartment("Electronics");
		m.setAvailable(true);
		ms.addItem(m);
		
		m.setName("Couch");
		m.setPrice(799.99);
		m.setDepartment("Furniture");
		m.setAvailable(false);
		ms.addItem(m);
		
		m.setName("Bookshelf");
		m.setPrice(170.00);
		m.setDepartment("Furniture");
		m.setAvailable(true);
		ms.addItem(m);
		
		m.setName("Jeans");
		m.setPrice(50.00);
		m.setDepartment("Clothing");
		m.setAvailable(true);
		ms.addItem(m);
		
		m.setName("Car Battery");
		m.setPrice(139.99);
		m.setDepartment("Automotive");
		m.setAvailable(true);
		ms.addItem(m);
		
//		System.out.println( ms.getItem(3, m) +"\n");
		System.out.println( ms.getAll(m) +"\n");
//
//		ms.deleteItem(1, m);
//		System.out.println( ms.getAll(m) +"\n");
//
//		ms.update(2, m);
//		System.out.println( ms.getAll(m) +"\n");
		
		
		
		TestModel tm = new TestModel();
		
		try {
			repo.initializeTable(tm);
		} catch (SQLException | MissingAnnotationException e) {
			e.printStackTrace();
		}
		
		tm.setName("name1");
		tm.setSomeNum(1.1);
		tm.setOtherNum(11);
		repo.addItem(tm);
		
		TestModel t2 = new TestModel();
		t2.setName("name2");
		t2.setSomeNum(2.2);
		t2.setOtherNum(22);
		repo.addItem(t2);
		
		try {
			System.out.println( repo.getAll(tm) );
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
