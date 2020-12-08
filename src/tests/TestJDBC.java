package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.jdbc.ScriptRunner;

import connections.JDBCUtilities;

/**
 * Connection
 */
public class TestJDBC {
    static final String url = "jdbc:oracle:thin:@oracle1.ensimag.fr:" + "1521:oracle1";
    static final String user = "vincentn";
    static final String passwd = "vincentn";

    public static void testSimpleQuery(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        statement.setQueryTimeout(30); // set timeout to 30 sec.
        statement.executeUpdate(JDBCUtilities.dropIfExist("person"));
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
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            System.out.println("connected.");

            // testSimpleQuery(connection);
            // testSimpleTransaction(connection);

            // Initialize the script runner
            ScriptRunner sr = new ScriptRunner(connection);
            // sr.setEscapeProcessing(false);

            // Drop existing tables
            try (Statement statement = connection.createStatement()) {
                for (String tableName : tables) {
                    statement.executeUpdate(JDBCUtilities.dropIfExist(tableName));
                    connection.commit();
                }
                /* statement.executeUpdate("DROP SEQUENCE idAlbum_seq");
                connection.commit(); */
            }

            // Running the script
            // JDBCUtilities.loadFile(sr, "ressources/transaction.sql");
            JDBCUtilities.loadFile(sr, "ressources/CreateTables.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertCategorieMusique.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertAlbum.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertAlbumAPourCategorie.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertArtiste.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertFilm.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertUtilisateur.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertFichier.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertPiste.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertClient.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertCodec.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertAPourInstrument.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertAPourRole.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertEstUnePiste.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertEstUnFilm.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertCategorieFilm.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertFilmAPourCategorie.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertImgExtraiteFilm.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertPisteAPourCategorie.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertSupporteCodec.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertFlux.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertFluxTexte.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertFluxAudio.sql");
            JDBCUtilities.loadFile(sr, "ressources/inserts/InsertFluxVideo.sql");

            JDBCUtilities.selectAll(connection, "CategorieMusique");
            JDBCUtilities.selectAll(connection, "Album");
            JDBCUtilities.selectAll(connection, "AlbumAPourCategorie");
            JDBCUtilities.selectAll(connection, "Artiste");
            JDBCUtilities.selectAll(connection, "Film");
            JDBCUtilities.selectAll(connection, "Utilisateur");
            JDBCUtilities.selectAll(connection, "Fichier");
            JDBCUtilities.selectAll(connection, "Piste");
            JDBCUtilities.selectAll(connection, "Client");
            JDBCUtilities.selectAll(connection, "Codec");
            JDBCUtilities.selectAll(connection, "APourInstrument");
            JDBCUtilities.selectAll(connection, "APourRole");
            JDBCUtilities.selectAll(connection, "EstUnePiste");
            JDBCUtilities.selectAll(connection, "EstUnFilm");
            JDBCUtilities.selectAll(connection, "CategorieFilm");
            JDBCUtilities.selectAll(connection, "FilmAPourCategorie");
            JDBCUtilities.selectAll(connection, "ImgExtraiteFilm");
            JDBCUtilities.selectAll(connection, "PisteAPourCategorie");
            JDBCUtilities.selectAll(connection, "SupporteCodec");
            JDBCUtilities.selectAll(connection, "Flux");
            JDBCUtilities.selectAll(connection, "FluxTexte");
            JDBCUtilities.selectAll(connection, "FluxAudio");
            JDBCUtilities.selectAll(connection, "FluxVideo");

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