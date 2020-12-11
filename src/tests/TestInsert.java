package tests;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;

import connections.ConnectionOracle;
import connections.JDBCUtilities;

public class TestInsert {
	public static void main(String args[]) throws FileNotFoundException {
        Connection connection = ConnectionOracle.getInstance();
        try {
        	ScriptRunner sr = new ScriptRunner(connection);
        	JDBCUtilities.loadFile(sr, "ressources/inserts.sql");
        	
        	
            
            connection.commit();
        } catch (SQLException e) {
            System.err.println("sql error !");
            JDBCUtilities.printSQLException(e);

            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch (SQLException excep) {
                    JDBCUtilities.printSQLException(excep);
                }
            }
        } finally {
            ConnectionOracle.closeInstance();
        }
    }
}
