package transactions;

import java.sql.Connection;
import java.sql.SQLException;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
import dao.DAOFactory;
import model.Utilisateur;

public final class TransactionUtilisateur {
    public static void execute(Utilisateur utilisateur) {
        Connection connection = ConnectionOracle.getInstance();
        try {
            DAOFactory.getUtilisateurDAO().createOrUpdate(utilisateur);
            
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
