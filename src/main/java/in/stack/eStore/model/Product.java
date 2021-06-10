package in.stack.eStore.model;

public class Product {
	private int produtId;
	private String productName;
	private int quantity;
	private double price;
	private boolean isactive;
	
	
	public Product() {
		
	}

	public Product(int produtId, String productName, int quantity, double price, boolean isactive) {
		super();
		this.produtId = produtId;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.isactive = isactive;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public int getProdutId() {
		return produtId;
	}
	public void setProdutId(int produtId) {
		this.produtId = produtId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [produtId=" + produtId + ", productName=" + productName + ", quantity=" + quantity + ", price="
				+ price + ", isactive=" + isactive + "]";
	}

}
