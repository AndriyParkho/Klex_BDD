package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionOracle {
    private static final String url = "jdbc:oracle:thin:@oracle1.ensimag.fr:" + "1521:oracle1";
    private static final String user = "argentoa";
    private static final String passwd = "argentoa";

    private static Connection connection = null;

    // only gets called once, when the class itself is initialized, no matter how
    // many objects of that type you create.
    static {
        // Enregistrement d’un pilote JDBC
        System.out.println("Loading Oracle thin driver...");
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (final SQLException e) {
            System.err.println("sql error !");
            JDBCUtilities.printSQLException(e);
        }
        System.out.println("loaded.");
    }

    public static Connection getInstance() {
        // Établissement d’une connexion et Requête Simple
        try {
            if (connection == null || connection.isClosed()) {
                System.out.println("Connecting to the database...");
                connection = DriverManager.getConnection(url, user, passwd);
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); // vs
                                                                                         // Connection.TRANSACTION_READ_COMMITTED
                connection.setAutoCommit(false);
                System.out.println("connected.");
            } else {
                // System.out.println("Récupération de l'instance de la connection...");
            }
        } catch (final SQLException e) {
            System.err.println("sql error !");
            JDBCUtilities.printSQLException(e);
        }
        return connection;
    }

    public static void closeInstance() {
        try {
            if (connection != null && connection.isValid(0)) {
                connection.close();
                System.out.println("Closed.");
            }
        } catch (SQLException e) {
            System.err.println("sql error !");
            JDBCUtilities.printSQLException(e);
        }
    }
}
