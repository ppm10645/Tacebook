/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import model.Post;
import model.Profile;

/**
 *
 * @author joao.pedro.pereira
 */
public class PostDB {   
    
    /**
     * Almacena unha nova publicación 
     * @param post 
     * @throws persistence.PersistenceException 
     */
    public static void save(Post post) throws PersistenceException{
        Profile p = post.getProfile();
        p.getPosts().add(0, post);
    }
    
    /**
     * Garda un Like sobre unha publicación
     * @param post
     * @param profile 
     * @throws persistence.PersistenceException 
     */
    public static void saveLike(Post post, Profile profile) throws PersistenceException{
        post.getProfileLikes().add(profile);
    }
    
}
