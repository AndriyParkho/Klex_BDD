package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Flux;

public class DAOFlux extends DAO<Flux> {

    @Override
    public void create(Flux flux) throws SQLException {

        String queryId = "SELECT idFlux_seq.nextval from DUAL";
        int nextID_from_seq = 0;
        try (ResultSet rs = this.connection.prepareStatement(queryId).executeQuery()) {
            if (rs.next()) {
                nextID_from_seq = rs.getInt(1);
                flux.setId(nextID_from_seq);
            }
        }

        final String insertFluxQuery = "INSERT INTO Flux VALUES (idFlux_seq.currval, ?, idFichier_seq.currval, ?, ?)";
        try (PreparedStatement statementFlux = this.connection.prepareStatement(insertFluxQuery)) {
            statementFlux.setFloat(1, flux.getDebit());
            statementFlux.setString(2, flux.getNomCodec());
            statementFlux.setString(3, flux.getTypeCodec());
            statementFlux.executeUpdate();
        }
    }

    @Override
    public ResultSet find(Flux flux) throws SQLException {
        return this.find(flux.getId());
    }

    public ResultSet find(long idFlux) throws SQLException {
        final String query = String.format("SELECT * FROM Flux WHERE idFlux = %ld", idFlux);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public void delete(long idFlux) throws SQLException {
        final String query = String.format("DELETE FROM Flux WHERE idFlux = %ld", idFlux);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

}
