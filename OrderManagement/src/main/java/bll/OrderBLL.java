package bll;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import dao.OrderDAO;
import dao.ProductsDAO;
import model.Order;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * In this class are made the calls for the query methods for the Order table.
 */

public class OrderBLL {
	static int i=0;
	static int ik=0;

    /**
     * This method generates a pdf file with the bill for the order just inserted in the table
     */
	private static void generateBill(Order o)
	{

		try {
			double total=o.getCantitate();
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("Order" + (ik++) + ".pdf"));
			
			total= total * (ProductsDAO.getPret(o.getNume_products()));
			//System.out.println(o.getCantitate() + " "+ o.getNume_products()+ " "+ total);
			document.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk chunk1 = new Chunk("Client: "+ o.getNume_clients()+ " a cumparat "+ o.getCantitate()+ " " +o.getNume_products(), font);
			Paragraph p1=new Paragraph(chunk1);
			
			Chunk chunk2=new Chunk("cu pretul de: "+ ProductsDAO.getPret(o.getNume_products()) + "$/bucata. Total: " + total, font);
			Paragraph p2=new Paragraph(chunk2);
			
			document.add(p1);
			document.add(p2);
			document.close();
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
    /**
     * This method calls the insert method in OrderDAO and the method from the above for inserting an order into the database
     */
	public static void insert(Order o) {
		OrderDAO.insert(o);
		ProductsDAO.update(o.getNume_products(), o.getCantitate());
		generateBill(o);
	}
    /**
     * Deletes everything from the existing tables so that the information is consistent
     */
	public static void init() {
		OrderDAO.init();
	}

    /**
     * Generates a pdf file report, printing all the orders inserted in the table
     */
	public static void report() {
		
		ResultSet rs= OrderDAO.report();
		final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("Report Order"+(i++)+".pdf"));
			document.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			while (rs.next())
			{
				Chunk chunk1 = new Chunk(rs.getInt(1)+". "+ rs.getString(2) + " a cumparat " + rs.getInt(4) +" "+ rs.getString(3), font);
				Paragraph p1=new Paragraph(chunk1);
				document.add(p1);
			}
			document.close();
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, "OrderBLL: report " + e.getMessage());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}
	
}
