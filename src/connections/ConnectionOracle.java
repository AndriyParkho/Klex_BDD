package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionOracle {
    private static final String url = "jdbc:oracle:thin:@oracle1.ensimag.fr:" + "1521:oracle1";
    private static final String user = "vincentn";
    private static final String passwd = "vincentn";

    private static Connection connection = null;

    // only gets called once, when the class itself is initialized, no matter how
    // many objects of that type you create.
    static {
        // Enregistrement d’un pilote JDBC
        System.out.println("Loading Oracle thin driver...");
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.err.println("sql error !");
            JDBCUtilities.printSQLException(e);
        }
        System.out.println("loaded.");
    }

    public static Connection getInstance() {
        if (connection == null) {
            // Établissement d’une connexion et Requête Simple
            System.out.println("Connecting to the database...");
            try {
                connection = DriverManager.getConnection(url, user, passwd);
                // connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            } catch (SQLException e) {
                System.err.println("sql error !");
                JDBCUtilities.printSQLException(e);
            }
            System.out.println("connected.");
        }
        return connection;
    }
}
