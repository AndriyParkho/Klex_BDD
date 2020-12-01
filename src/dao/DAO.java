package src.dao;

import java.sql.Connection;
import java.sql.SQLException;

import src.connections.ConnectionOracle;

public abstract class DAO<T> {
    public Connection connection = ConnectionOracle.getInstance();
    
    /**
     * Permet de créer une entrée dans la base de données
     * par rapport à un objet
     * @param obj
     */
    public abstract T create(T obj) throws SQLException;

    /**
     * Permet de mettre à jour les données d'une entrée dans la base 
     * @param obj
     */
    public abstract T update(T obj);

    /**
     * Permet la suppression d'une entrée de la base
     * @param obj
     */
    public abstract void delete(T obj);
}
