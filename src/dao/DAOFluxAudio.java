package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.FluxAudio;

public class DAOFluxAudio extends DAO<FluxAudio>{
    
    @Override
    public void create(FluxAudio fluxAudio) throws SQLException {
        final String insertFluxQuery = "INSERT INTO FluxAudio VALUES (idFlux_seq.currval, ?, idFichier_seq.currval, ?, ?, ?, ?)";
    
        try (PreparedStatement statementFlux = this.connection.prepareStatement(insertFluxQuery)) {
            statementFlux.setInt(1, fluxAudio.getDebit());
            statementFlux.setString(2, fluxAudio.getNomCodec());
            statementFlux.setString(3, fluxAudio.getTypeCodec());
            statementFlux.setInt(4, fluxAudio.getEchantillonage());
            statementFlux.setString(5, fluxAudio.getLangue());
            
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
