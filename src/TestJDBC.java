package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Connection
 */
public class TestJDBC {
    static final String url = "jdbc:oracle:thin:@ensioracle1.imag.fr:" + "1521:ensioracle1";
    static final String user = "codd";
    static final String passwd = "*********";

    public static void main(String[] args) {
        try {
            // Enregistrement d’un pilote JDBC
            System.out.println("Loading Oracle thin driver...");
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("loaded.");

            // Établissement d’une connexion et Requête Simple
            System.out.println("Connecting to the database...");
            Connection conn = DriverManager.getConnection(url, user, passwd);
            System.out.println("connected.");

            // Demarrage de la transaction (implicite dans notre cas)
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            // Execution des requetes
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM emp;");

            // dump result
            String result = "";
            while (resultSet.next()) {
                result += resultSet.getString(1) + ',';
            }
            System.out.println(result);

            resultSet.close();
            // Terminaison de la transaction
            conn.commit();

            // close connections
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("failed !");
            e.printStackTrace();
        }
    }
}