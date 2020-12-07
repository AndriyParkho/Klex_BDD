package dao;

import java.sql.Connection;
import java.sql.SQLException;

import connections.ConnectionOracle;

public abstract class DAO<T> {
    protected Connection connection = ConnectionOracle.getInstance();
    
    /**
     * Permet de créer une entrée dans la base de données
     * par rapport à un objet
     * @param obj
     */
    public abstract void create(T obj) throws SQLException;

    // /**
    //  * Permet de trouver un objet
    //  * @param obj
    //  */
    // public abstract void find(T obj) throws SQLException;

    // /**
    //  * Permet la suppression d'une entrée de la base
    //  * @param obj
    //  */
    // public abstract void delete(T obj) throws SQLException;

    public Connection getConnection() {
        return connection;
    }
}
