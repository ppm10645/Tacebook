/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.util.ArrayList;
import model.Profile;

/**
 *
 * @author joao.pedro.pereira
 */
public class ProfileDB {

    private ArrayList<Profile> profiles = new ArrayList<>();

    /**
     * Recupera un perfil buscando polo nome e devolvendo o obxeto
     *
     * @param name
     * @param numberOfPosts
     * @return
     * @throws persistence.PersistenceException
     */
    public static Profile findByName(String name, int numberOfPosts) throws PersistenceException {

            for (Profile profile : TacebookDB.getProfiles()) {
                if (profile.getName().equals(name)) {
                    return profile;
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
     * @throws persistence.PersistenceException
     */
    public static Profile findBuNameAdnPassword(String name, String password, int numberOfPosts) throws PersistenceException {
            for (Profile profile : TacebookDB.getProfiles()) {
                if (profile.getName().equals(name) && profile.getPassword().equals(password)) {
                    return profile;
                }
            }

            return null;
    }

    public static void save(Profile profile) throws PersistenceException {
        TacebookDB.getProfiles().add(profile);
    }

    public static void update(Profile profile) throws PersistenceException {
        
    }

    /**
     * Añade una nueva solicitud de amistad
     *
     * @param destProfile usuario destino de la solicitud
     * @param sourceProfile usuario de la solicitud
     * @throws persistence.PersistenceException
     */
    public static void saveFrienshipRequest(Profile destProfile, Profile sourceProfile) throws PersistenceException {
        destProfile.getFriendRequests().add(sourceProfile);
    }

    /**
     * Borra unha solicitude de amizade entre dous perfis
     *
     * @param destProfile usuario que quere borrar a solicitude
     * @param sourceProfile usuario que se quere borrar a solicitude
     * @throws persistence.PersistenceException
     */
    public static void removeFrienshipRequest(Profile destProfile, Profile sourceProfile) throws PersistenceException {
        destProfile.getFriendRequests().remove(sourceProfile);
    }

    /**
     * Almacena unha amizade entre dous perfis e elimina a solicitude (Tengo que
     * eliminar la solicitud?)
     *
     * @param profile1
     * @param profile2
     * @throws persistence.PersistenceException
     */
    public static void saveFriendship(Profile profile1, Profile profile2) throws PersistenceException {
        profile1.getFriends().add(profile2);
        profile2.getFriends().add(profile1);
    }
}
