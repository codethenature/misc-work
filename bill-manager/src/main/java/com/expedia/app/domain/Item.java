package com.expedia.app.domain;

public class Item {
	
	double price;
	String name; // key parameter
	ContainerType containerType;
	boolean taxFree;
	
	public Item(double price, String name, ContainerType containerType, boolean taxFree) {
		super();
		this.price = price;
		this.name = name;
		this.containerType = containerType;
		this.taxFree = taxFree;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ContainerType getContainerType() {
		return containerType;
	}

	public void setContainerType(ContainerType containerType) {
		this.containerType = containerType;
	}

	public boolean isTaxFree() {
		return taxFree;
	}

	public void setTaxFree(boolean taxFree) {
		this.taxFree = taxFree;
	}
	
	public double getSalesTax() {
		return taxFree ? 0 : price * Constants.SALES_TAX_PERCENT;
	}
}
