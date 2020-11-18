package src.tests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.jdbc.ScriptRunner;

public class TestJDBCLocal {
    static final String url = "jdbc:sqlite:sqlitetest.db"; // ou "jdbc:sqlite::memory:"

    public static void main(java.lang.String[] args) {
        Connection connection = null;
        try {
            // Établissement d’une connexion et Requête Simple
            System.out.println("Connecting to the database...");
            connection = DriverManager.getConnection(url);
            System.out.println("connected.");

            Statement statement = connection.createStatement();

            // Test de query
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            statement.executeUpdate("drop table if exists person");
            statement.executeUpdate("create table person (id integer, name string)");
            statement.executeUpdate("insert into person values(1, 'leo')");
            statement.executeUpdate("insert into person values(2, 'yui')");
            ResultSet rs = statement.executeQuery("select * from person");
            String result = "\n";
            while (rs.next()) {
                result += rs.getString("name") + " " + rs.getString("id") + '\n';
            }
            System.out.println(result);

            // Initialize the script runner
            ScriptRunner sr = new ScriptRunner(connection);
            sr.setEscapeProcessing(false);
            // Creating a reader object
            Reader reader = new BufferedReader(new FileReader("ressources/sampleScript.sql"));
            // Running the script
            sr.runScript(reader);
        } catch (SQLException e) {
            System.err.println("sql error !");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.err.println("file not found !");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}
