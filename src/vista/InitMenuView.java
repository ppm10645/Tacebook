/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.InitMenuController;
import java.util.Scanner;

/**
 *
 * @author joao.pedro.pereira
 */
public class InitMenuView {

    private InitMenuController initMenuController;
    private Scanner scanner;

    public InitMenuView(InitMenuController initMenuController) {
        this.initMenuController = initMenuController;
        this.scanner = new Scanner(System.in);
    }

    public boolean showLoginMenu() {
        while (true) {
            System.out.println("Escolle unha opción:");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Crear un novo perfil");
            System.out.println("3. Saír da aplicación");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
//                initMenuController.login();
                    System.out.println("Login");

                case "2":
//                    initMenuController.register();
                    System.out.println("Register");

                case "3":
                    System.out.println("Saindo da aplicacion");
                    return false;
            }
        }

    }
    
    /**
     * Mensaxe de error en caso de que o usuario ou o contrasinal sexan incorrectos
     */
    public void showLoginErrorMessage(){
        System.out.println("Error, o usuario ou o contrasinal son incorrectos");
    }
    
    /**
     * Menu para rexistrar un usuario
     */
    public void showRegisterMenu(){
        System.out.println("Introduzca o nome de usuario");
        String name = scanner.nextLine();
        
        while(true){
        System.out.println("Introduzca o contrasinal");
        String contrasinal = scanner.nextLine();
        System.out.println("Introduzca de novo o contrasinal");
        String repcontrasinal = scanner.nextLine();
        if(repcontrasinal != contrasinal){
            System.out.println("O contrasinal non coincide");
        } else {
            System.out.println("Rexistrouse correctamente o usuario");
            break;
        }
        
        System.out.println("Introduzca o estado");
        String estado = scanner.nextLine();
        }
//        initMenuController.createProfile;
    }
    
    public String showNewNameMenu(){
        System.out.println("O nome de usuario xa está en uso, introduzca un novo nome de usuario");
        String name = scanner.nextLine();
        
        return name;
    }
}
