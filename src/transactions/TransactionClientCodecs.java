package transactions;

import java.sql.Connection;
import java.sql.SQLException;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
import dao.DAOFactory;
import model.Codec;
import model.SupporteCodec;
import model.aggregates.ClientCodecs;

public final class TransactionClientCodecs {
    public static void execute(ClientCodecs clientCodecs) {
        Connection connection = ConnectionOracle.getInstance();
        try {
            DAOFactory.getClientDAO().createOrUpdate(clientCodecs.getClient());

            for (Codec codec : clientCodecs.getCodecs()) {
                DAOFactory.getCodecDAO().createOrUpdate(codec);
                DAOFactory.getSupporteCodec().createOrUpdate(new SupporteCodec(clientCodecs.getClient().getMarque(),
                        clientCodecs.getClient().getModele(), codec.getNom(), codec.getType()));
            }

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
