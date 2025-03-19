/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Profile;
import persistencia.ProfileDB;
import vista.InitMenuView;


/**
 *
 * @author joao.pedro.pereira
 */
public class InitMenuController {

    private InitMenuView initMenuView = new InitMenuView(this);

    private void init() {
        while(!initMenuView.showLoginMenu());
    }

    public void login(String name, String password) {
           Profile profile = ProfileDB.findBuNameAdnPassword(name, password, 0); //Busca si existe un perfil con ese nombre y contraseña
           
           if(profile == null){
               initMenuView.showLoginErrorMessage();
           } else {
               //crea perfil da clase ProfileController, pendiente de crear
           }
    }
    
    public void register(){
        initMenuView.showRegisterMenu();
    }
    
    public void createProfile(String name, String password, String status){
        Profile profile = ProfileDB.findByName(name, 0); //
        
        while(profile != null){
            initMenuView.showNewNameMenu();
        }
            Profile newprofile = new Profile(name, password,status);
            
        
    }
    
    public static void main(String[] args) {
            InitMenuController controller = new InitMenuController();
            controller.init();
    }

}
