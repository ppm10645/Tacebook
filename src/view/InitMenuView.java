/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.InitMenuController;
import java.util.NoSuchElementException;
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
        OUTER:
        while (true) {
            System.out.println("Escolle unha opción:");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Crear un novo perfil");
            System.out.println("3. Saír da aplicación");
            String usname, uspassword;
            int option = readNumber(scanner);
            switch (option) {
                case 1 -> {
                    System.out.println("Escriba o nome de usuario");
                    usname = scanner.nextLine();
                    System.out.println("Escriba o  contrasinal");
                    uspassword = scanner.nextLine();
                    initMenuController.login(usname, uspassword);
                    break OUTER;
                }
                case 2 -> {
                    initMenuController.register();
                    break OUTER;
                }
                case 3 -> {
                    System.out.println("Saindo da aplicacion");
                    return true;
                }
                default -> {
                    System.out.println("Escolla algunha das opcións disponibles");
                    break OUTER;
                }
            }
        }
        return false;
    }

    /**
     * Mensaxe de error en caso de que o usuario ou o contrasinal sexan
     * incorrectos
     */
    public void showLoginErrorMessage() {
        System.out.println("Error, o usuario ou o contrasinal son incorrectos");
    }

    /**
     * Menu para rexistrar un usuario
     */
    public void showRegisterMenu() {
        System.out.println("Introduzca o nome de usuario");
        String name = scanner.nextLine();
        String contrasinal;

        while (true) {
            System.out.println("Introduzca o contrasinal");
            contrasinal = scanner.nextLine();
            System.out.println("Introduzca de novo o contrasinal");
            String repcontrasinal = scanner.nextLine();
            if (repcontrasinal.equals(contrasinal)) {
                break;
            } else {
                System.out.println("O contrasinal non coincide");
            }
        }
        System.out.println("Introduzca o estado");
        String estado = scanner.nextLine();
        System.out.println("Rexistrouse correctamente o usuario");
        initMenuController.createProfile(name, contrasinal, estado);
    }

    public String showNewNameMenu() {
        System.out.println("O nome de usuario xa está en uso, introduzca un novo nome de usuario");
        return scanner.nextLine();
    }
    
    private int readNumber(Scanner scanner) {
        try{
            int number = scanner.nextInt();
            scanner.nextLine(); //Limpiar buffer
            return number;
        } catch (NoSuchElementException e) {
            System.out.println("Debe introduccir un numero");
            scanner.nextLine(); //Limpiar buffer en caso de error
            return readNumber(scanner);
        }
    }
}
