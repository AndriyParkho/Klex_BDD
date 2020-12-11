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
        	//vide les tables
        	JDBCUtilities.loadFile(sr, "ressources/inserts/emptyAll.sql");
        	//rempli les tables
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertUtilisateur.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertFichier.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertAlbum.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertCategorieMusique.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertAlbumAPourCategorie.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertArtiste.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertClient.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertCodec.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertFlux.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertFluxAudio.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertPiste.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertPisteAPourCategorie.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertSupporteCodec.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertEstUnePiste.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertAPourInstrument.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertCategorieFilm.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertFilm.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertAPourRole.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertFilmAPourCategorie.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertImgExtraiteFilm.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertFluxVideo.sql");
        	JDBCUtilities.loadFile(sr, "ressources/inserts/InsertFluxTexte.sql");
            
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
