package model;

public class Product {
	private int pId;
	private String pName;
	private String pQuantity;
	private String pRate;
	
	
	public Product() {
		super();
	}
	public Product(int pId,String pName, String pQuantity, String pRate) {
		super();
		this.pId=pId;
		this.pName = pName;
		this.pQuantity = pQuantity;
		this.pRate = pRate;
	}
	public Product(String pName, String pQuantity, String pRate) {
		super();
		this.pName = pName;
		this.pQuantity = pQuantity;
		this.pRate = pRate;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpQuantity() {
		return pQuantity;
	}
	public void setpQuantity(String pQuantity) {
		this.pQuantity = pQuantity;
	}
	public String getpRate() {
		return pRate;
	}
	public void setpRate(String pRate) {
		this.pRate = pRate;
	}
	
	
	
}
