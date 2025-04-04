/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import modelo.Message;

/**
 *
 * @author joao.pedro.pereira
 */
public class MessageDB {
    
    /**
     * Almacena unha nova mensaxe
     * @param message 
     */
    public static void save(Message message) {
        message.getDestProfile().getMessages().add(0,message);
    }
    
    /**
     * Actualiza a información dunha mensaxe
     * @param message 
     */
    public static void update(Message message) {
        
    }
    
    /**
     * Borra unha mensaxe
     * @param message 
     */
    public static void remove(Message message) {
        message.getDestProfile().getMessages().remove(message);
    }
}
