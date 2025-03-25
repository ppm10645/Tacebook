/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Profile;
import persistencia.ProfileDB;
import vista.ProfileView;

/**
 *
 * @author joao.pedro.pereira
 */
public class ProfileController {
    
    private ProfileView profileView;
    private Profile sessionProfile;
    
    public ProfileController(){
        this.profileView = new ProfileView(this);
    }
    
    /**
     * Obten o numero de publicacións a mostrar
     * @return 
     */
    public int getPostsShowed(){
        return profileView.getPostsShowed();
    }
    
    /**
     * Mostra o menu do perfil 
     */
    public void reloadProfile(){
        sessionProfile =  ProfileDB.findByName(sessionProfile.getName(), getPostsShowed()); //Busca el perfil con el mismo nombre
        profileView.showProfileMenu(sessionProfile); //Muestra el perfil seleccionado
    }
    
    /**
     * Abre unha sesión con un perfil
     * @param sessionProfile 
     */
    public void openSession(Profile sessionProfile){
        this.sessionProfile = sessionProfile;
        
        if(this.profileView == null) {
            this.profileView = new ProfileView(this);
        }
        profileView.showProfileMenu(sessionProfile);
    }
    
    public void updateProfileStatus(String newStatus){
        sessionProfile.setStatus(newStatus);
        ProfileDB.update(sessionProfile);
        reloadProfile();
    }
}
