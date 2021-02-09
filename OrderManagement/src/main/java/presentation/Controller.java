package presentation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import bll.ClientsBLL;
import bll.OrderBLL;
import bll.ProductsBLL;
import model.Clients;
import model.Order;
import model.Products;

public class Controller {
	public Controller() {
		ClientsBLL.init();
		ProductsBLL.init();
		OrderBLL.init();
	}
	public void read(String path) {
		BufferedReader r;
		try {
			//System.out.println("1");
			r = new BufferedReader(new FileReader(path));
			String linie=r.readLine();
			while(linie!=null) {
				//System.out.println("1");
				if (linie.contains("Insert")) {
					if(linie.contains("client")) {
						//System.out.println("1");
						String nume, adresa;
						nume=linie.split(": ")[1].split(", ")[0];
						adresa=linie.split(": ")[1].split(", ")[1];
						Clients c=new Clients(nume, adresa);
						ClientsBLL.insert(c);
					}
					if(linie.contains("product")) {
						String nume;
						int cantitate;
						double pret;
						nume=linie.split(": ")[1].split(", ")[0];
						cantitate=Integer.parseInt(linie.split(": ")[1].split(", ")[1]);
						pret=	  Double.parseDouble(linie.split(": ")[1].split(", ")[2]);;
						//System.out.println(nume+ " "+ cantitate + " " + pret+"\n");
						Products p =new Products(nume, cantitate, pret);
						ProductsBLL.insert(p);
					}
				}
				if (linie.contains("Delete")) {
					if(linie.contains("client")) {
						String nume=linie.split(": ")[1];
						ClientsBLL.delete(nume);
					}
					if(linie.contains("product")) {
						String nume=linie.split(": ")[1];
						ProductsBLL.delete(nume);
					}
				}
				if (linie.contains("Order")) {
					String numec, numep;
					int cantitate;
					numec=linie.split(": ")[1].split(", ")[0];
					numep=linie.split(": ")[1].split(", ")[1];
					cantitate=Integer.parseInt(linie.split(": ")[1].split(", ")[2]);
					//System.out.println(cantitate);
					Order o =new Order(numec, numep, cantitate);
					OrderBLL.insert(o);
				}
				if (linie.contains("Report")) {
					if(linie.contains("client")) {
						ClientsBLL.report();
					}
					if(linie.contains("product")) {
						ProductsBLL.report();
					}
					if(linie.contains("order")) {
						OrderBLL.report();
					}
				}
				linie=r.readLine();
			}
			r.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
