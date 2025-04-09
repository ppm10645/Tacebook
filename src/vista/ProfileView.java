/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ProfileController;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import modelo.Comment;
import modelo.Post;
import modelo.Profile;

/**
 *
 * @author joao.pedro.pereira
 */
public class ProfileView {

    private int postsShowed = 10;
    private ProfileController profileController;
    private Scanner scanner;
    private SimpleDateFormat formatter;

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
        //Mostra o nome do usuario
        System.out.println("tacebook - Perfil do usuario: " + profile.getName());
        //Mostra o estato do usuario
        System.out.println("Estado actual: " + profile.getStatus());
        //Mostra as publicacions do usuario e os comentarios da publicacion
        for (int i=0; i<profile.getPosts().size(); i++) {
            System.out.println(i +". " + profile.getPosts().get(i).getText());
            
           for (Comment c:profile.getPosts().get(i).getComments()) {
               System.out.println("- " + c.getText() + " - " + c.getSourceProfile().getName() + " - " + c.getDate().toString());
           }
        }
        //Mostra as solicitudes de amizade
        System.out.println("Solicitudes de amizade: " + profile.getFriendRequests());
        //Mostra os amigos
        System.out.println("Amizades: " + profile.getFriends());
        //Mostra as mensaxes
        System.out.println("Mensaxes: " + profile.getMessages());
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

        System.out.println("");
        System.out.println("1. Escribir unha nova publicación");
        System.out.println("2. Comentar unha publicación");
        System.out.println("3. Facer me gusta sobre unha publicacion");
        System.out.println("4. Ver a biografía dun amigo");
        System.out.println("5. Enviar unha solicitude de amizade");
        System.out.println("6. Aceptar unha solicitude de amizade");
        System.out.println("7. Rexeitar unha solicitude de amizade");
        System.out.println("8. Enviar unha mensaxe privada a un amigo");
        System.out.println("9. Ler unha mensaxe privada");
        System.out.println("10. Eliminar unha mensaxe privada");
        System.out.println("11. Ver publicacions anteriores");
        System.out.println("12. Cambiar o estado");
        System.out.println("13. Pechar sesion");

        String input = scanner.nextLine(); // Leer entrada como String  
        int option;
        try {
            option = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            option = -1; // Manejo de error  
        }

        switch (option) {
            case 1:

            case 2:

            case 3:

            case 4:

            case 5:

            case 6:

            case 7:

            case 8:

            case 9:

            case 10:

            case 11:

            case 12:
                changeStatus(true, scanner, profile);
                break;
            case 13:
                System.out.println("Saíndo do perfil");
                return;
            default:
                System.out.println("Opción non válida");
        }
    }

    /**
     * Selecciona un elemento dunha lista pedindo un numero ao usuario
     *
     * @param text
     * @param maxNumber
     * @param scanner
     * @return
     */
    private int selectElement(String text, int maxNumber, Scanner scanner) {

        int imput = -1;

        while (imput < 0 && imput > maxNumber - 1) {
            System.out.println(text);
            imput = scanner.nextInt();
            scanner.nextLine(); //Limpar o buffer
        }

        return imput;
    }
    
    /**
     * Pide o texto para crear unha nova publicación
     * @param scanner
     * @param profile 
     */
    private void writeNewPost(Scanner scanner, Profile profile){
        System.out.println("Texto de la publicación :");
        String text = scanner.nextLine();
        
        profileController.newPost(text, profile);
    }
    
    /**
     * Crea un comentario
     * @param scanner
     * @param profile 
     */
    private void commentPost(Scanner scanner, Profile profile){
        //Pide selecionar unha publicación
        String selectiontext = "Selecciona unha publicacion";
        int postNumber = selectElement(selectiontext, postsShowed, scanner);
        Post p = profile.getPosts().get(postNumber);
        
        
        //Crea o comentario
        System.out.println("Que quere comentar: ");
        String text = scanner.nextLine();
        profileController.newComment(p, text);
    }
    
    /**
     * Añade un like
     * @param scanner
     * @param profile 
     */
    private void addLike(Scanner scanner, Profile profile){
        //Pide selecionar unha publicación
        String selectiontext = "Selecciona unha publicacion";
        int postNumber = selectElement(selectiontext, postsShowed, scanner);
        Post p = profile.getPosts().get(postNumber);
        
        //Añade o like a publicación
        profileController.newLike(p);
    }
    
    /**
     * Mostra o perfil dun usuario diferente ao que está mostrando
     * @param ownProfile
     * @param scanner
     * @param profile 
     */
    private void showBiography(boolean ownProfile, Scanner scanner, Profile profile){
        if(ownProfile){
            System.out.println("Selecciona un amigo para mostrar o seu perfil");
            
        }
    }

}
