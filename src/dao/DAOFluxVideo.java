package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.FluxVideo;

public class DAOFluxVideo extends DAO<FluxVideo> {

    @Override
    public void create(FluxVideo fluxVideo) throws SQLException {
        final String insertFluxQuery = "INSERT INTO FluxVideo VALUES (idFlux_seq.currval, ?, idFichier_seq.currval, ?, ?, ?, ?)";

        try (PreparedStatement statementFlux = this.connection.prepareStatement(insertFluxQuery)) {
            statementFlux.setInt(1, fluxVideo.getDebit());
            statementFlux.setString(2, fluxVideo.getNomCodec());
            statementFlux.setString(3, fluxVideo.getTypeCodec());
            statementFlux.setInt(4, fluxVideo.getLargeur());
            statementFlux.setInt(5, fluxVideo.getHauteur());
            statementFlux.executeUpdate();
        }
    }

    @Override
    public ResultSet find(FluxVideo fluxVideo) throws SQLException {
        return this.find(fluxVideo.getId());
    }

    public ResultSet find(long idFlux) throws SQLException {
        final String query = String.format("SELECT * FROM FluxVideo WHERE idFlux = %ld", idFlux);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public void delete(long idFlux) throws SQLException {
        final String query = String.format("DELETE FROM FluxVideo WHERE idFlux = %ld", idFlux);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
