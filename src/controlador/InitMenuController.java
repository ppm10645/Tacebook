/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Profile;
import vista.InitMenuView;

/**
 *
 * @author joao.pedro.pereira
 */
public class InitMenuController {

    private InitMenuView initMenuView;

    private void init() {

        if (initMenuView.showLoginMenu() == false) {
            initMenuView.showLoginMenu();
        }
    }

    public void login(String name, String password) {

    }
    
    public void register(){
        initMenuView.showRegisterMenu();
    }
    
    public void createProfile(String name, String password, String status){
        
        Profile newprofile = new Profile(name, password,status);
    
    public static void main(String[] args) {
            
    }

}
