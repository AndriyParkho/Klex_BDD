package dao;

import java.sql.Connection;
import java.sql.SQLException;

import connections.ConnectionOracle;

public abstract class DAO<T> {
    public Connection connection = ConnectionOracle.getInstance();
    
    /**
     * Permet de cr�er une entr�e dans la base de donn�es
     * par rapport � un objet
     * @param obj
     */
    public abstract T create(T obj) throws SQLException;

    /**
     * Permet de mettre � jour les donn�es d'une entr�e dans la base 
     * @param obj
     */
    public abstract T update(T obj) throws SQLException;

    /**
     * Permet la suppression d'une entr�e de la base
     * @param obj
     */
    public abstract void delete(T obj) throws SQLException;
}
