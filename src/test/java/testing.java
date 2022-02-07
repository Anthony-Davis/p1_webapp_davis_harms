import com.revature.repositories.Repository;
import com.revature.util.SqlDataType;
import models.Merchandise;
import services.MerchService;

public class testing {
	
	public static void main(String[] args) {
		
		Repository<Object> repo = new Repository<>();
		MerchService ms = new MerchService(repo);
		Merchandise m = new Merchandise();
		
		ms.initializeTable(m);
		
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
		
		System.out.println( ms.getItem(3, m) +"\n");
		
		System.out.println( ms.getAll(m) +"\n");
		
		ms.deleteItem(1, m);
		System.out.println( ms.getAll(m) +"\n");
		
		ms.update(2, m);
		System.out.println( ms.getAll(m) +"\n");
		
	}
	
	
}
