/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import model.Message;

/**
 *
 * @author joao.pedro.pereira
 */
public class MessageDB {
    
    /**
     * Almacena unha nova mensaxe
     * @param message 
     * @throws persistence.PersistenceException 
     */
    public static void save(Message message) throws PersistenceException{
        message.getDestProfile().getMessages().add(0,message);
    }
    
    /**
     * Actualiza a información dunha mensaxe
     * @param message 
     * @throws persistence.PersistenceException 
     */
    public static void update(Message message) throws PersistenceException{
        
    }
    
    /**
     * Borra unha mensaxe
     * @param message 
     * @throws persistence.PersistenceException 
     */
    public static void remove(Message message) throws PersistenceException{
        message.getDestProfile().getMessages().remove(message);
    }
}
