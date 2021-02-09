package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


import connection.ConnectionFactory;
import model.Clients;

/**
 * This class is responsible for the queries that interrogate the table Clients in the database.
 */

public class ClientsDAO{
	protected static final Logger LOGGER = Logger.getLogger(ClientsDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO clients (nume, adresa)" + " VALUES (?, ?)";
	private static final String deleteStatementString = "DELETE FROM clients where nume = ?";
	private static final String initStatementString="DELETE from clients where id_clients>0";
	private static final String reportStatementString="SELECT * from clients";
	
	public static void init() {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement initStatement = null;
		try {
			initStatement = dbConnection.prepareStatement(initStatementString, Statement.RETURN_GENERATED_KEYS);
			initStatement.executeUpdate();
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, "ClientsDAO: init " + e.getMessage());
		} finally {
			ConnectionFactory.close(initStatement);
			ConnectionFactory.close(dbConnection);
		}
		
	}
	
	
	public static void insert(Clients c) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, c.getNume());
			insertStatement.setString(2, c.getAdresa());
			insertStatement.executeUpdate();
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, "ClientsDAO: insert " + e.getMessage());
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
			LOGGER.log(Level.WARNING, "ClientsDAO: delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
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
			LOGGER.log(Level.WARNING, "ClientsDAO: report " + e.getMessage());
		}// finally {
			//ConnectionFactory.close(reportStatement);
			//ConnectionFactory.close(dbConnection);
		//}
		return null;
	}


}
