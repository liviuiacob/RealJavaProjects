package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Order;

/**
 * This class is responsible for the queries that interrogate the table Order in the database.
 */

public class OrderDAO{
	protected static final Logger LOGGER = Logger.getLogger(ClientsDAO.class.getName());
	private static final String insertStatementString ="INSERT INTO new_schema.order (nume_clients, nume_products, cantitate)" + "VALUES (?, ?, ?)";
	private static final String initStatementString="DELETE from new_schema.order where id_order>0";
	private static final String reportStatementString="SELECT * from new_schema.order";
	
	public static void init() {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement initStatement = null;
		try {
			initStatement = dbConnection.prepareStatement(initStatementString, Statement.RETURN_GENERATED_KEYS);
			initStatement.executeUpdate();
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO: init " + e.getMessage());
		} finally {
			ConnectionFactory.close(initStatement);
			ConnectionFactory.close(dbConnection);
		}
		
	}
	public static void insert(Order o) {	
			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement insertStatement = null;
			try {
				
				insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
				insertStatement.setString(1, o.getNume_clients());
				insertStatement.setString(2, o.getNume_products());
				insertStatement.setInt(3, o.getCantitate());
				insertStatement.executeUpdate();
			}catch(SQLException e) {
				LOGGER.log(Level.WARNING, "OrderDAO: insert " + e.getMessage());
			} finally {
				ConnectionFactory.close(insertStatement);
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
			LOGGER.log(Level.WARNING, "OrderDAO: report " + e.getMessage());
		}// finally {
			//ConnectionFactory.close(reportStatement);
			//ConnectionFactory.close(dbConnection);
		//}
		return null;
	}
	

}
