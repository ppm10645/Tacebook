/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ProfileController;
import java.util.Scanner;
import modelo.Profile;

/**
 *
 * @author joao.pedro.pereira
 */
public class ProfileView {

    private int postsShowed = 10;
    private ProfileController profileController;
    private Scanner scanner;

    public ProfileView(ProfileController profileController) {
        this.scanner = new Scanner(System.in);
        this.profileController = profileController;
    }

    public int getPostsShowed() {
        return postsShowed;
    }

    /**
     * Muestra la información del perfil
     *
     * @param ownProfile
     * @param profile
     */
    private void showProfileInfo(boolean ownProfile, Profile profile) {
        System.out.println("tacebook - Perfil do usuario: " + profile.getName());
        System.out.println("Estado actual: " + profile.getStatus());
    }

    /**
     * Cambio de estado de perfil
     *
     * @param ownProfile
     * @param scanner
     * @param profile
     */
    private void changeStatus(boolean ownProfile, Scanner scanner, Profile profile) {
        if (ownProfile) {
            System.out.println("Introduzca o novo estado");
            String newState = scanner.nextLine();
            profileController.updateProfileStatus(newState);
        } else {
            System.out.println("Esta opticón non está disponible");
            showProfileMenu(profile);
        }
    }

    /**
     * Menu del perfil para mostrar la información del perfil o cambiar el
     * estado
     *
     * @param profile
     */
    public void showProfileMenu(Profile profile) {
        showProfileInfo(true, profile);

        //while (true) {
        System.out.println("");
        System.out.println("1. Cambiar estado do perfil");
        System.out.println("2. Pechar sesión");
        System.out.println("Elixe unha opción: ");

        String input = scanner.nextLine(); // Leer entrada como String  
        int option;
        try {
            option = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            option = -1; // Manejo de error  
        }

        switch (option) {
            case 1:
                changeStatus(true, scanner, profile);
                break;
            case 2:
                System.out.println("Saíndo do perfil");
                return;
            default:
                System.out.println("Opción non válida");
        }
        //}
    }

}
