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

    /**
     * Recupera un perfil buscando polo nome e devolvendo o obxeto
     *
     * @param name
     * @param numberOfPosts
     * @return
     */
    public static Profile findByName(String name, int numberOfPosts) {

        for (Profile profile : TacebookDB.getProfiles()) {
            if (profile.getName().equals(name)) {
                Profile result = new Profile(profile.getName(), profile.getPassword(), profile.getStatus());
                return result;
            }
        }
        return null;
    }

    /**
     * Recupera un perfil buscando polo nome e o contrasinal devolvendo o obxeto
     *
     * @param name
     * @param password
     * @param numberOfPosts
     * @return
     */
    public static Profile findBuNameAdnPassword(String name, String password, int numberOfPosts) {

        for (Profile profile : TacebookDB.getProfiles()) {
            if (profile.getName().equals(name) && profile.getPassword().equals(password)) {
                Profile result = new Profile(profile.getName(), profile.getPassword(), profile.getStatus());
                return result;
            }
        }

        return null;
    }

    public static void save(Profile profile) {
        TacebookDB.getProfiles().add(profile);
    }

    public static void update(Profile profile) {

    }
    
    /**
     * Almacena unha nova solicitude de amizade
     * @param destProfile
     * @param sourceProfile 
     */
    public static void saveFrienshipRequest(Profile destProfile, Profile sourceProfile) {
        
    }
    
    /**
     * Borra unha solicitude de amizade entre dous perfis
     * @param destProfile
     * @param sourceProfile 
     */
    public static void removeFrienshipRequest(Profile destProfile, Profile sourceProfile) {
        
    }
    
    /**
     * Almacena unha amizade entre dous perfis
     * @param profile1
     * @param profile2 
     */
    public static void saveFriendship(Profile profile1, Profile profile2) {
        
    }
}
