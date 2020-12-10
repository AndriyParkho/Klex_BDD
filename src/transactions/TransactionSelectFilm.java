package transactions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
import model.Client;

public final class TransactionSelectFilm {
    public static void execute(String categorie, Client client, String email) {
    	Connection connection = ConnectionOracle.getInstance();
        try {
            try (ResultSet rs = Selections.findFilm(connection, categorie, client.getMarque(), client.getModele(), email)) {
                if (rs != null) {
                    System.out.println(JDBCUtilities.dumpResultSet(rs));
                } else {
                    System.out.println("Aucune entr\u00E9e ne correspond ра votre recherche");
                }
            }
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
