
package controller;

import model.Profile;
import persistence.ProfileDB;
import view.InitMenuView;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.PersistenceException;

/**
 *
 * @author joao.pedro.pereira
 */
public class InitMenuController {

    private InitMenuView initMenuView = new InitMenuView(this);
    private ProfileController profilecontroller = new ProfileController();

    private void init() {
        while (!initMenuView.showLoginMenu());
    }

    public void login(String name, String password) {
        try {
            Profile profile = ProfileDB.findBuNameAdnPassword(name, password, 0);
            
            if (profile == null) {
                initMenuView.showLoginErrorMessage();
            } else {
                profilecontroller.openSession(profile);
            }
        } catch (PersistenceException ex) {
            Logger.getLogger(InitMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void register() {
        initMenuView.showRegisterMenu();
    }

    /**
     * 
     * @param name
     * @param password
     * @param status 
     */
    public void createProfile(String name, String password, String status) {
        try {
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
        } catch (PersistenceException ex) {
            Logger.getLogger(InitMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        try {
            InitMenuController controller = new InitMenuController();
            
            //Usuario temporais para facer as probas
            Profile user1 = new Profile("a", "1", "activo");
            Profile user2 = new Profile("b", "1", "soy un usuario de prueba");
            
            ProfileDB.save(user1);
            ProfileDB.save(user2);
            
            controller.init();
        } catch (PersistenceException ex) {
            Logger.getLogger(InitMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
