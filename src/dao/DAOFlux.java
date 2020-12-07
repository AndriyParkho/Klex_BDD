package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Flux;

public class DAOFlux extends DAO<Flux> {
    
    @Override
    public void create(Flux flux) throws SQLException {
        final String insertFluxQuery = "INSERT INTO Flux VALUES (idFlux_seq.nextval, ?, idFichier_seq.currval, ?, ?)";
    
        try (PreparedStatement statementFlux = this.connection.prepareStatement(insertFluxQuery)) {
            statementFlux.setInt(1, flux.getDebit());
            statementFlux.setString(2, flux.getNomCodec());
            statementFlux.setString(3, flux.getTypeCodec());
            
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
