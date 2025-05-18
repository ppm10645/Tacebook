package controller;

import model.Profile;
import persistence.ProfileDB;
import persistence.PersistenceException;
import persistence.TacebookDB;
import view.GUItInitMenuView;
import view.InitMenuView;
import view.TextInitMenuView;

/**
 *
 * @author joao.pedro.pereira
 */
public class InitMenuController {

    private InitMenuView initMenuView;
    private boolean textMode;

    public InitMenuController(boolean textMode) {
        this.textMode = textMode;
        if (textMode) {
            initMenuView = new TextInitMenuView(this);
        } else {
            initMenuView = new GUItInitMenuView(this);
        }
    }

    /**
     * Inicia a aplicacion
     */
    @SuppressWarnings("empty-statement")
    private void init() {
        //Manten a aplicacion ata que devolva false
        while(!initMenuView.showLoginMenu());
    }

    /**
     * Inicia session con un usuario existente
     * @param name nome de usuario
     * @param password contrasinal do usuario
     */
    public void login(String name, String password) {
        Profile profile = null;
        try {
            profile = ProfileDB.findBuNameAdnPassword(name, password, 0);

            if (profile == null) {
                initMenuView.showLoginErrorMessage();
            } else {
                   ProfileController controller = new ProfileController(profile, textMode);
                   controller.openSession(profile);
            }
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }
    }

    public void register() {
        initMenuView.showRegisterMenu();
    }

    /**
     * Crea un novo perfil
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
            ProfileController newprofilecontroller = new ProfileController(newprofile, textMode);
            newprofilecontroller.openSession(newprofile);
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }
    }

    public static void main(String[] args) {
        try {
            InitMenuController controller;

            if(args.length > 0) {
                if("text".equals(args[0])) {
                    controller = new InitMenuController(true);
                } else {
                    controller = new InitMenuController(false);
                }
            } else {
                controller = new InitMenuController(false);
            }
            
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
                initMenuView.showConnectionErrorMessage();
            case PersistenceException.CANNOT_READ ->
                initMenuView.showReadErrorMessage();
            case PersistenceException.CANNOT_WRITE ->
                initMenuView.showWriteErrorMessage();
            default -> {
            }
        }
    }

}
