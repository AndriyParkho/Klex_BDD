package tests;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
import transactions.Selections;

public final class TestTransactionSelectionPiste {
    public static void main(String[] args) {
        Connection connection = ConnectionOracle.getInstance();
        try {
            try (ResultSet rs = Selections.findPiste(connection, "rock", "Sony", "M4", "@gmail.com")) {
                if (rs != null) {
                    System.out.println(JDBCUtilities.dumpResultSet(rs));
                } else {
                    System.out.println("Aucune entrée ne correspond à votre recherche");
                }
            }
            connection.commit();
        } catch (SQLException e) {
            System.err.println("sql error !");
            JDBCUtilities.printSQLException(e);

            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back\n");
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
