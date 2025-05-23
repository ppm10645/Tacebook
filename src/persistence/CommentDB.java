/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import model.Comment;

/**
 *
 * @author joao.pedro.pereira
 */
public class CommentDB {
    
    /**
     * Almacena un novo comentario
     * @param comment 
     * @throws persistence.PersistenceException 
     */
    public static void save(Comment comment) throws PersistenceException {
            comment.getPost().getComments().add(0, comment);
    }
}
