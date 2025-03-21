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
    private ProfileController profilecontroller = new ProfileController();

    private void init() {
        while(!initMenuView.showLoginMenu());
    }

    public void login(String name, String password) {
            Profile profile = ProfileDB.findBuNameAdnPassword(name, password, 10);
           
           if(profile == null){
               initMenuView.showLoginErrorMessage();
           } else {
               profilecontroller.openSession(profile);
           }
    }
    
    public void register(){
        initMenuView.showRegisterMenu();
    }
    
    public void createProfile(String name, String password, String status){
        
        while(ProfileDB.findByName(name, 10) != null){
            name = initMenuView.showNewNameMenu();
        }
            Profile newprofile = new Profile(name, password,status);
            ProfileDB.save(newprofile);
            ProfileController profileController = new ProfileController();
            profilecontroller.openSession(newprofile);
    }
    
    public static void main(String[] args) {
            InitMenuController controller = new InitMenuController();
            controller.init();
    }

}
