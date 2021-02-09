package dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Products;

/**
 * This class is responsible for the queries that interrogate the table Products in the database.
 */

public class ProductsDAO{
	protected static final Logger LOGGER = Logger.getLogger(ClientsDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO products (nume, cantitate, pret)" + " VALUES (?, ?, ?)";
	private static final String deleteStatementString = "DELETE FROM products where nume = ?";
	private static final String updateStatementString = "UPDATE products SET cantitate = (select p.cantitate from (select * from products) as p where nume= ?) - ?";
	private static final String initStatementString="DELETE from products where id_products>0";
	private static final String selectStatementString="SELECT pret from products where nume=?";
	private static final String reportStatementString="SELECT * from products";
	
	public static void init() {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement initStatement = null;
		try {
			initStatement = dbConnection.prepareStatement(initStatementString, Statement.RETURN_GENERATED_KEYS);
			initStatement.executeUpdate();
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, "ProductsDAO: init " + e.getMessage());
		} finally {
			ConnectionFactory.close(initStatement);
			ConnectionFactory.close(dbConnection);
		}
		
	}
	
	public static void insert(Products p) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, p.getNume());
			insertStatement.setInt(2, p.getCantitate());
			insertStatement.setDouble(3, p.getPret());
			insertStatement.executeUpdate();
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, "ProductsDAO: insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		
	}

	public static void delete(String nume) {
		// TODO Auto-generated method stub
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
			deleteStatement.setString(1, nume);
			deleteStatement.executeUpdate();
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, "ProductsDAO: delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

	public static void update(String nume_products, int cantitate) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
			updateStatement.setString(1, nume_products);
			updateStatement.setInt(2, cantitate);
			updateStatement.executeUpdate();	
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, "ProductsDAO: update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		
	}
	public static double getPret(String nume) {
		// TODO Auto-generated method stub
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		double i=0;
		try {
			selectStatement = dbConnection.prepareStatement(selectStatementString, Statement.RETURN_GENERATED_KEYS);
			selectStatement.setString(1, nume);
			ResultSet rs = selectStatement.executeQuery();
			
			if (rs.next()) {
				i = rs.getInt(1);
			}
			
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, "ProductsDAO: getPret " + e.getMessage());
		} finally {
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
		return i;
	}
	
	public static ResultSet report() {
		// TODO Auto-generated method stub
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement reportStatement = null;
		ResultSet rs = null;
		try {
			reportStatement = dbConnection.prepareStatement(reportStatementString, Statement.RETURN_GENERATED_KEYS);
			rs = reportStatement.executeQuery();
			return rs;
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, "ProductsDAO: report " + e.getMessage());
		}// finally {
			//ConnectionFactory.close(reportStatement);
			//ConnectionFactory.close(dbConnection);
		//}
		return null;
	}


}
