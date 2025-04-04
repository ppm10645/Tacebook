/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Profile;
import persistencia.ProfileDB;
import vista.InitMenuView;
import controlador.ProfileController;

/**
 *
 * @author joao.pedro.pereira
 */
public class InitMenuController {

    private InitMenuView initMenuView = new InitMenuView(this);

    private void init() {
        while (!initMenuView.showLoginMenu());
    }

    public void login(String name, String password) {
        ProfileController profile = ProfileDB.findBuNameAdnPassword(name, password, 10);

        if (profile == null) {
            initMenuView.showLoginErrorMessage();
        } else {
            profilecontroller.openSession(profile);
        }
    }

    public void register() {
        initMenuView.showRegisterMenu();
    }

    public void createProfile(String name, String password, String status) {
        // Comprobamos que el nome no este usado
        while (ProfileDB.findByName(name, 0) != null) {
            name = initMenuView.showNewNameMenu();
        }
        // Creamos el objeto para el nuevo perfil y lo guardamos
        Profile newprofile = new Profile(name, password, status);
        ProfileDB.save(newprofile);
        // Creamos el controlador de la sesion con ese perfil
        ProfileController profilecontroller = new ProfileController();
        profilecontroller.openSession(newprofile);
    }

    public static void main(String[] args) {
        InitMenuController controller = new InitMenuController();
        controller.init();
    }

}
