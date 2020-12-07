package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.FluxVideo;

public class DAOFluxVideo {
    
    public void create(FluxVideo fluxVideo) throws SQLException {
        final String insertFluxQuery = "INSERT INTO FluxTexte VALUES (idFlux_seq.currval, ?, idFichier_seq.currval, ?, ?, ?, ?)";
    
        try (PreparedStatement statementFlux = this.connection.prepareStatement(insertFluxQuery)) {
            statementFlux.setInt(1, fluxVideo.getDebit());
            statementFlux.setString(2, fluxVideo.getNomCodec());
            statementFlux.setString(3, fluxVideo.getTypeCodec());
            statementFlux.setInt(4, fluxVideo.getLargeurImage());
            statementFlux.setInt(5, fluxVideo.getHauteurImage());
            
            statementFlux.executeUpdate();
        }

    }

    public void find(long idFlux) throws SQLException {
        final String query = String.format("SELECT * FROM Flux WHERE idFlux = %ld", idFlux);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

    public void delete(long idFlux) throws SQLException {
        final String query = String.format("DELETE FROM Flux WHERE idFlux = %ld", idFlux);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

}
