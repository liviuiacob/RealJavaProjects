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

import dao.ClientsDAO;
import model.Clients;

/**
 * In this class are made the calls for the query methods for the Clients table.
 */

public class ClientsBLL {
	static int i=0;
	
	public static void insert(Clients c) {
		ClientsDAO.insert(c);
	}
	public static void delete(String nume) {
		ClientsDAO.delete(nume);
	}
	public static void init() {
		ClientsDAO.init();
	}


	/**
	 * Generates a pdf file report, printing all the Clients inserted in the table
	 */
	public static void report() {
		ResultSet rs= ClientsDAO.report();
		final Logger LOGGER = Logger.getLogger(ClientsDAO.class.getName());
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("Report Client"+(i++)+".pdf"));
			document.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			while (rs.next())
			{
				Chunk chunk1 = new Chunk(rs.getInt(1)+". "+ rs.getString(2) + ", " + rs.getString(3), font);
				Paragraph p1=new Paragraph(chunk1);
				document.add(p1);
			}
			document.close();
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, "ClientsBLL: report " + e.getMessage());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
