/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import modelo.Profile;

/**
 *
 * @author joao.pedro.pereira
 */
public class ProfileDB {
    
    public static Profile findByName(String name, int numberOfPosts) {
        
        return null;
    }
    
    public static Profile findBuNameAdnPassword(String name, String password, int numberOfPosts) {
        
        return null;
        
    }
    
    public static void save(Profile profile) {
        TacebookDB.getProfiles().add(profile);
    }
    
    public static void update(Profile profile) {
        
    }
}
