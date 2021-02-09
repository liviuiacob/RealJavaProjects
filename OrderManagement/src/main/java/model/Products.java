package model;

/**
 * This class simulates the products, and has associated with it a sql table with the data
 */

public class Products {
	private int id_products;
	private String nume;
	private int cantitate;
	private double pret;
	
	public Products(int id_products, String nume, int cantitate, double pret) {
		this.id_products=id_products;
		this.nume=nume;
		this.cantitate=cantitate;
		this.pret=pret;
	}
	
	public Products(String nume, int cantitate, double pret) {
		this.nume=nume;
		this.cantitate=cantitate;
		this.pret=pret;
	}
	
	public int getId_products() {
		return id_products;
	}
	public void setId_products(int id_products) {
		this.id_products=id_products;
	}
	public int getCantitate() {
		return cantitate;
	}
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	public double getPret() {
		return pret;
	}
	public void setPret(double pret) {
		this.pret = pret;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}
	
	public String toString() {
		return "Product [id = " + id_products + ", name = " + nume + ", price = " + pret + ", quantity = " + cantitate + "]";
	}
}
