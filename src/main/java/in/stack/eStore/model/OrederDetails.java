package in.stack.eStore.model;

import java.util.List;

public class OrederDetails {
	
	private List<Product> products;
	private double discount;
		
	public OrederDetails() {
		
	}
	
	public OrederDetails(List<Product> products, double discount) {
		super();
		this.products = products;
		this.discount = discount;
	}


	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}


}
