package model;

/**
 * This class simulates the clients, and has associated with it a sql table with the data
 */

public class Clients {
	private int id_clients;
	private String nume;
	private String adresa;
	
	public Clients(int id_clients, String nume, String adresa) {
		this.id_clients=id_clients;
		this.nume=nume;
		this.adresa=adresa;
	}
	public Clients(String nume, String adresa) {
		this.nume=nume;
		this.adresa=adresa;
	}
	
	public int getId_clients() {
		return id_clients;
	}
	public void setId_clients(int id_clients) {
		this.id_clients=id_clients;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String toString() {
		return "Client [id = " + id_clients + ", name = " + nume + ", address = " + adresa + "]";
	}
}
