package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.FluxTexte;

public class DAOFluxTexte extends DAO<FluxTexte> {
    
    @Override
    public void create(FluxTexte fluxTexte) throws SQLException {
        final String insertFluxQuery = "INSERT INTO FluxTexte VALUES (idFlux_seq.currval, ?, idFichier_seq.currval, ?, ?, ?)";
    
        try (PreparedStatement statementFlux = this.connection.prepareStatement(insertFluxQuery)) {
            statementFlux.setInt(1, fluxTexte.getDebit());
            statementFlux.setString(2, fluxTexte.getNomCodec());
            statementFlux.setString(3, fluxTexte.getTypeCodec());
            statementFlux.setString(4, fluxTexte.getLangue());
            
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
