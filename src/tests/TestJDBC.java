package src.tests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.jdbc.ScriptRunner;

/**
 * Connection
 */
public class TestJDBC {
    static final String url = "jdbc:oracle:thin:@oracle1.ensimag.fr:" + "1521:oracle1";
    static final String user = "vincentn";
    static final String passwd = "vincentn";

    public static String dropIfExist(String tableName) {
        return "BEGIN EXECUTE IMMEDIATE 'DROP TABLE " + tableName
                + " CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN IF SQLCODE != -942 THEN RAISE; END IF; END;";
    }

    public static void selectAll(Connection connection, String tableName) throws SQLException {
        String query = "select * from " + tableName;
        // automatically close Statement and ResultSet objects, regardless of whether an
        // SQLException has been thrown.
        try (Statement statement = connection.createStatement()) {
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            try (ResultSet rs = statement.executeQuery(query)) {
                // dump and print result
                System.out.println(dumpResultSet(rs));
            }
        }
    }

    public static void testSimpleQuery(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        statement.setQueryTimeout(30); // set timeout to 30 sec.
        statement.executeUpdate(dropIfExist("person"));
        statement.executeUpdate("create table person(id integer, name varchar(20))");
        statement.executeUpdate("insert into person values(1, 'leo')");
        statement.executeUpdate("insert into person values(2, 'yui')");
        ResultSet rs = statement.executeQuery("select * from person");
        String result = "\n";
        while (rs.next()) {
            result += rs.getString("name") + " " + rs.getString("id") + '\n';
        }
        System.out.println(result);
        rs.close();

        // close statement
        statement.close();
    }

    public static void testSimpleTransaction(Connection connection) throws SQLException {
        // Demarrage de la transaction (implicite dans notre cas)
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

        // Execution des requetes
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM person");

        // dump result
        String result = "";
        while (rs.next()) {
            result += rs.getString("name") + " " + rs.getString("id") + '\n';
        }
        System.out.println(result);
        rs.close();

        // Terminaison de la transaction
        connection.commit();

        // close statement
        statement.close();
    }

    public static void loadFile(ScriptRunner sr, String fileName) {
        try {
            sr.runScript(new BufferedReader(new FileReader(fileName)));
        } catch (FileNotFoundException e) {
            System.err.println("file not found !");
            e.printStackTrace();
        }
    }

    public static String dumpResultSet(ResultSet rs) throws SQLException {
        String result = "\n";
        /*
         * while (rs.next()) { for (String columnName : columns) { // les méthodes
         * getString et getObject sont des méthodes génériques qui peuvent être //
         * employées quel que soit le type SQL de la valeur recherchée. result +=
         * rs.getString(columnName) + " "; } result += '\n'; }
         */
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1)
                    result += ",  \n";
                String columnValue = rs.getString(i);
                result += columnValue + " " + rsmd.getColumnName(i);
            }
            result += "\n";
        }
        return result;
    }

    public static void main(String[] args) {
        Connection connection = null;

        List<String> tables = List.of("Flux", "FluxTexte", "FluxAudio", "FluxVideo", "Dept", "Emp",
                "PisteAPourCategorie", "Album", "AlbumAPourCategorie", "Codec", "Client", "SupporteCodec",
                "CategorieMusique", "Artiste", "APourRole", "Piste", "APourInstrument", "Utilisateur", "Fichier",
                "Film", "FilmAPourCategorie", "EstUnFilm", "EstUnePiste", "ImgExtraiteFilm", "CategorieFilm", "APourCategorie");
        try {
            // Enregistrement d’un pilote JDBC
            System.out.println("Loading Oracle thin driver...");
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("loaded.");

            // Établissement d’une connexion et Requête Simple
            System.out.println("Connecting to the database...");
            connection = DriverManager.getConnection(url, user, passwd);
            System.out.println("connected.");

            // testSimpleQuery(connection);
            // testSimpleTransaction(connection);

            // Initialize the script runner
            ScriptRunner sr = new ScriptRunner(connection);
            sr.setEscapeProcessing(false);

            // Drop existing tables
            try (Statement statement = connection.createStatement()) {
                for (String tableName : tables) {
                    statement.executeUpdate(dropIfExist(tableName));
                }
            }

            // Running the script
            loadFile(sr, "ressources/CreateTables.sql");
            loadFile(sr, "ressources/InsertCategorieMusique.sql");
            loadFile(sr, "ressources/InsertAlbum.sql");
            loadFile(sr, "ressources/InsertAlbumAPourCategorie.sql");
            loadFile(sr, "ressources/InsertArtiste.sql");
            loadFile(sr, "ressources/InsertFilm.sql");
            loadFile(sr, "ressources/InsertUtilisateur.sql");
            loadFile(sr, "ressources/InsertFichier.sql");
            loadFile(sr, "ressources/InsertPiste.sql");
            loadFile(sr, "ressources/InsertClient.sql");
            loadFile(sr, "ressources/InsertCodec.sql");
            loadFile(sr, "ressources/InsertAPourInstrument.sql");
            loadFile(sr, "ressources/InsertAPourRole.sql");
            loadFile(sr, "ressources/InsertEstUnePiste.sql");
            loadFile(sr, "ressources/InsertEstUnFilm.sql");
            loadFile(sr, "ressources/InsertCategorieFilm.sql");
            loadFile(sr, "ressources/InsertFilmAPourCategorie.sql");
            loadFile(sr, "ressources/InsertImgExtraiteFilm.sql");
            loadFile(sr, "ressources/InsertPisteAPourCategorie.sql");
            loadFile(sr, "ressources/InsertSupporteCodec.sql");
            selectAll(connection, "CategorieMusique");
            selectAll(connection, "Album");
            selectAll(connection, "AlbumAPourCategorie");
            selectAll(connection, "Artiste");
            selectAll(connection, "Film");
            selectAll(connection, "Utilisateur");
            selectAll(connection, "Fichier");
            selectAll(connection, "Piste");
            selectAll(connection, "Client");
            selectAll(connection, "Codec");
            selectAll(connection, "APourInstrument");
            selectAll(connection, "APourRole");
            selectAll(connection, "EstUnePiste");
            selectAll(connection, "EstUnFilm");
            selectAll(connection, "CategorieFilm");
            selectAll(connection, "FilmAPourCategorie");
            selectAll(connection, "ImgExtraiteFilm");
            selectAll(connection, "PisteAPourCategorie");
            selectAll(connection, "SupporteCodec");

        } catch (SQLException e) {
            System.err.println("sql error !");
            JDBCUtilities.printSQLException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                JDBCUtilities.printSQLException(e);
            }
        }
    }
}