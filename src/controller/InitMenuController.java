package controller;

import model.Profile;
import persistence.ProfileDB;
import persistence.PersistenceException;
import persistence.TacebookDB;
import view.GUItInitMenuView;
import view.TextInitMenuView;

/**
 *
 * @author joao.pedro.pereira
 */
public class InitMenuController {

    private TextInitMenuView textMenuView;
    private GUItInitMenuView guiMenuView;

    private ProfileController profilecontroller;
    private boolean textMode = true;

    public InitMenuController(boolean textMode) {
        this.textMode = textMode;
        this.profilecontroller = new ProfileController(textMode);
        if (textMode) {
            this.textMenuView = new TextInitMenuView(this);
        } else {
            this.guiMenuView = new GUItInitMenuView(this);
        }
    }

    private void init() {
        if (textMode) {
            while (!textMenuView.showLoginMenu());
        } else {
            guiMenuView.showLoginMenu();
        }
    }

    public void login(String name, String password) {
        try {
            Profile profile = ProfileDB.findBuNameAdnPassword(name, password, 0);

            if (profile == null) {
                textMenuView.showLoginErrorMessage();
            } else {
                profilecontroller.openSession(profile);
            }
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }
    }

    public void register() {
        textMenuView.showRegisterMenu();
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
                name = textMenuView.showNewNameMenu();
            }
            // Creamos el objeto para el nuevo perfil y lo guardamos
            Profile newprofile = new Profile(name, password, status);
            ProfileDB.save(newprofile);
            // Creamos el controlador de la sesion con ese perfil
            ProfileController newprofilecontroller = new ProfileController(this.textMode);
            newprofilecontroller.openSession(newprofile);
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }
    }

    public static void main(String[] args) {
        boolean textMode = args.length > 0 && args[0].equalsIgnoreCase("text");
        
        try {
            InitMenuController controller = new InitMenuController(textMode);

            //Usuario temporais para facer as probas
            Profile user1 = new Profile("a", "1", "activo");
            Profile user2 = new Profile("b", "1", "soy un usuario de prueba");

            ProfileDB.save(user1);
            ProfileDB.save(user2);

            controller.init();

            TacebookDB.close();
        } catch (PersistenceException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    /**
     * Procesa unha excepción de persistencia e en función do código da
     * excepción chamará a un dos tres métodos de error da clase view
     *
     * @param ex
     */
    private void proccessPersistenceException(PersistenceException ex) {
        switch (ex.getCode()) {
            case PersistenceException.CONECTION_ERROR ->
                textMenuView.showConnectionErrorMessage();
            case PersistenceException.CANNOT_READ ->
                textMenuView.showReadErrorMessage();
            case PersistenceException.CANNOT_WRITE ->
                textMenuView.showWriteErrorMessage();
            default -> {
            }
        }
    }

}
