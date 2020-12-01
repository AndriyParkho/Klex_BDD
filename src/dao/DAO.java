package src.dao;

import java.sql.Connection;

import src.connections.ConnectionOracle;

public abstract class DAO<T> {
    public Connection connection = ConnectionOracle.getInstance();
    
    /**
     * Permet de créer une entrée dans la base de données
     * par rapport à un objet
     * @param obj
     */
    public abstract T create(T obj);
    
    /**
     * Permet de mettre à jour les données d'une entrée dans la base 
     * @param obj
     */
    public abstract T update(T obj);
    
    /**
     * Permet de récupérer un objet via son ID
     * @param id
     * @return
     */
    public abstract T find(long id);

    /**
     * Permet la suppression d'une entrée de la base
     * @param obj
     */
    public abstract void delete(T obj);
}
