package bll;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import dao.ProductsDAO;
import model.Products;

/**
 * In this class are made the calls for the query methods for the Products table.
 */

public class ProductsBLL {
	
	static int i=0;
	
	public static void insert(Products p) {
		ProductsDAO.insert(p);
	}
	public static void delete(String nume) {
		ProductsDAO.delete(nume);
	}
	public static void init() {
		ProductsDAO.init();
	}

	/**
	 * Generates a pdf file report, printing all the Products inserted in the table
	 */
	public static void report() {
		ResultSet rs= ProductsDAO.report();
		final Logger LOGGER = Logger.getLogger(ProductsDAO.class.getName());
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("Report Product"+(i++)+".pdf"));
			document.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			while (rs.next())
			{
				Chunk chunk1 = new Chunk(rs.getInt(1)+". "+ rs.getString(2) + ", cantitate:" + rs.getInt(3)+" pret: "+rs.getInt(4), font);
				Paragraph p1=new Paragraph(chunk1);
				document.add(p1);
			}
			document.close();
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, "ProductsBLL: report " + e.getMessage());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
