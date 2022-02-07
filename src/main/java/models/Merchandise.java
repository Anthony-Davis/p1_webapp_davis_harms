package models;

import com.revature.annotations.Column;
import com.revature.annotations.Table;
import com.revature.util.SqlDataType;


@Table(tableName = "merchandise")
public class Merchandise {
	
	@Column(name = "id", type = SqlDataType.INTEGER)
	private int id;
	@Column(name = "name", type = SqlDataType.VARCHAR)
	private String name;
	@Column(name = "price", type = SqlDataType.DOUBLE)
	private double price;
	@Column(name = "department", type = SqlDataType.VARCHAR)
	private String department;
	
	public Merchandise(){}
	
	public int getId() {
		return id;
	}
	
	public void setId(int itemId) {
		this.id = itemId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String itemName) {
		this.name = itemName;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Override
	public String toString() {
		return "Entity{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price +
				", department=" + department +
				'}';
	}
}
