package model;

/**
 * This class simulates the orders, and has associated with it a sql table with the data
 */

public class Order {
	private int id_order;
	private String nume_clients;
	private String nume_products;
	private int cantitate;
	
	public Order(String nume_clients, String nume_products, int cantitate) {
		this.nume_clients=nume_clients;
		this.nume_products=nume_products;
		this.cantitate=cantitate;
	}
	public int getId_order() {
		return id_order;
	}
	public void setId_order(int id_order) {
		this.id_order = id_order;
	}
	public String getNume_clients() {
		return nume_clients;
	}
	public void setNume_clients(String nume_clients) {
		this.nume_clients = nume_clients;
	}
	public String getNume_products() {
		return nume_products;
	}
	public void setNume_products(String nume_products) {
		this.nume_products = nume_products;
	}
	public int getCantitate() {
		return cantitate;
	}
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	public String toString() {
		return "Order [id= " +id_order+  ", name = " + nume_clients + ", product = " + nume_products + ", quantity = " + cantitate + "]";
	}
	
	
}
