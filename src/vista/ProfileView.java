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
import persistencia.ProfileDB;

/**
 *
 * @author joao.pedro.pereira
 */
public class ProfileView {

    private int postsShowed = 1;
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
        if (ownProfile) {
            //Mostra o nome do usuario
            System.out.println("tacebook - Perfil do usuario: " + profile.getName());
            //Mostra o estato do usuario
            System.out.println("Estado actual: " + profile.getStatus());
            //Mostra as publicacions do usuario e os comentarios da publicacion
            System.out.println("A túa biografía (" + postsShowed + " últimas publicacións):");
            if (!profile.getPosts().isEmpty()) {
                for (int i = 0; i < postsShowed; i++) {
                    System.out.println(i + ". " + profile.getPosts().get(i).getDate().toString() + " ti escriches (" + profile.getPosts().get(i).getProfileLikes().size() + " me gusta):");
                    System.out.println(profile.getPosts().get(i).getText());

                    for (Comment c : profile.getPosts().get(i).getComments()) {
                        System.out.println("- " + c.getText() + " - " + c.getSourceProfile().getName() + " - " + c.getDate().toString());
                    }
                }
            }
            //Mostra as amizades
            System.out.println("Lista de amigos: ");
            for (int i = 0; i < profile.getFriends().size(); i++) {
                System.out.println(i + ". " + profile.getFriends().get(i).getName() + " - " + profile.getFriends().get(i).getStatus());
            }
            //Mostra as solicitudes de amizade
            if (!profile.getFriendRequests().isEmpty()) {

                System.out.println("Tes solicitude de amizade dos seguintes perfís:");
                for (int i = 0; i < profile.getFriendRequests().size(); i++) {
                    System.out.println(i + ". " + profile.getFriendRequests().get(i).getName() + " quere establecer amizade contigo.");
                }
            }
            //Mostra as mensaxes 
            System.out.println("Mensaxes privadas:");
            //Contador de mensaxes sen leer
            int unreadedmessages = 0;
            for (int i = 0; i < profile.getMessages().size(); i++) {

                if (profile.getMessages().get(i).isRead() == false) {
                    unreadedmessages++;
                }
            }
            if (unreadedmessages > 0) {
                System.out.println("Tes " + unreadedmessages + " sen ler!!");
            }
            for (int i = 0; i < profile.getMessages().size(); i++) {
                if (profile.getMessages().get(i).isRead()) {
                    System.out.println(i + ". De " + profile.getMessages().get(i).getSourceProfile().getName() + "(" + profile.getMessages().get(i).getDate() + ") " + profile.getMessages().get(i).getText().substring(0, 10));
                } else {
                    System.out.println("*" + i + ". De " + profile.getMessages().get(i).getSourceProfile().getName() + "(" + profile.getMessages().get(i).getDate() + ") " + profile.getMessages().get(i).getText().substring(0, 10));
                }
            }
        }
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
        boolean showprofile = true;

        while (showprofile) {

            showProfileInfo(showprofile, profile);

            System.out.println("");
            System.out.println("Escolle unha opción:");
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
                    writeNewPost(scanner, profile);
                    break;
                case 2:
                    commentPost(scanner, profile);
                    break;
                case 3:
                    addLike(scanner, profile);
                    break;
                case 4:
                    showBiography(true, scanner, profile);
                    break;
                case 5:
                    sendFriendshipRequest(true, scanner, profile);
                    break;
                case 6:
                    proccessFriendshipRequest(true, scanner, profile, true);
                    break;
                case 7:
                    proccessFriendshipRequest(true, scanner, profile, false);
                    break;
                case 8:
                    sendPrivateMessage(true, scanner, profile);
                    break;
                case 9:
                    readPrivateMessage(true, scanner, profile);
                    break;
                case 10:
                    deletePrivateMessage(true, scanner, profile);
                    break;
                case 11:
                    showOldPosts(scanner, profile);
                    break;
                case 12:
                    changeStatus(true, scanner, profile);
                    break;
                case 13:
                    System.out.println("Saíndo do perfil");
                    showprofile = false;
                    break;
                default:
                    System.out.println("Opción non válida");
                    break;
            }
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

        int number;
        do {
            System.out.print(text + " [0-" + (maxNumber - 1) + "]: ");
            number = scanner.nextInt();
            scanner.nextLine();
        } while (number < 0 || number >= maxNumber);
        return number;
    }

    /**
     * Pide o texto para crear unha nova publicación
     *
     * @param scanner
     * @param profile
     */
    private void writeNewPost(Scanner scanner, Profile profile) {
        System.out.println("Texto de la publicación :");
        String text = scanner.nextLine();

        profileController.newPost(text, profile);
    }

    /**
     * Crea un comentario
     *
     * @param scanner
     * @param profile
     */
    private void commentPost(Scanner scanner, Profile profile) {
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
     *
     * @param scanner
     * @param profile
     */
    private void addLike(Scanner scanner, Profile profile) {
        //Pide selecionar unha publicación
        String selectiontext = "Selecciona unha publicacion";
        int postNumber = selectElement(selectiontext, postsShowed, scanner);
        Post p = profile.getPosts().get(postNumber);

        //Añade o like a publicación
        profileController.newLike(p);
    }

    /**
     * Mostra o perfil dun usuario diferente ao que está mostrando
     *
     * @param ownProfile
     * @param scanner
     * @param profile usuario a mostrar
     */
    private void showBiography(boolean ownProfile, Scanner scanner, Profile profile) {
        if (ownProfile) {
            System.out.println("Selecciona un amigo para mostrar o seu perfil:");
            for (int i = 0; i < profile.getFriends().size(); i++) {
                System.out.println(i + ". " + profile.getFriends().get(i).getName());
            }
            int friendSelected = selectElement("Elixe un numero", postsShowed, scanner);
            Profile friendProfile = profile.getFriends().get(friendSelected);
            profileController.setShownProfile(friendProfile); //Mostra o perfil doutro usuario
        }
    }

    /**
     * Pideo o nome dun perfil e envialle unha solicitude de amizade
     *
     * @param ownProfile
     * @param scanner
     * @param profile
     */
    private void sendFriendshipRequest(boolean ownProfile, Scanner scanner, Profile profile) {
        if (ownProfile) {
            System.out.println("Escriba o nome do perfil ao que quere mandar solicitude de amizade");
            String nameRequest = scanner.nextLine();
            if (nameRequest.isEmpty()) {
                showProfileNotFoundMessage();
            } else if (nameRequest.equals(profile.getName())) {
                System.out.println("Non podes facerte solicitude de amizade a ti mesmo");
            } else {
                profileController.newFriendshipRequest(nameRequest);
            }
        } else {
            System.out.println("Mandando solicitude de amizade a " + profileController.getShownProfile().getName());
            profileController.newFriendshipRequest(profileController.getShownProfile().getName()); //Manda a solicitude ao usuario que está visualizando
        }
    }

    /**
     * Pideo o número dunha solicitude de amizade e chama ao controllador para
     * aceptala ou rexeitala, en función do que se indique no parámetro "accept"
     *
     * @param ownProfile
     * @param scanner
     * @param profile
     * @param accept decide se se acepta ou rexeita a solicitude de amizade
     */
    private void proccessFriendshipRequest(boolean ownProfile, Scanner scanner, Profile profile, boolean accept) {
        if (ownProfile) {
            System.out.println("Selecione unha solicitude de amizade para aceptala ou rexeitala");
            //Mosta todas as solicitudes de amizade do perfil
            for (int i = 0; i < profile.getFriendRequests().size(); i++) {
                System.out.println(i + ". " + profile.getFriendRequests().get(i).getName());
            }
            int selectedFriendShipRequest = selectElement("Elixe o numero da publicación", postsShowed, scanner); //Numero da solicitude que se seleccionou
            Profile profileSelected = profile.getFriendRequests().get(selectedFriendShipRequest);

            //Selecciona se acepta ou rexeita a solicitude de amizade
            System.out.println("1. Aceptar");
            System.out.println("2. Rexeitar");
            System.out.println("3. Cancelar");
            String num = scanner.nextLine();

            if (num.equals("1")) {
                profileController.acceptFriendshipRequest(profileSelected);
            } else if (num.equals("2")) {
                profileController.rejectFriendshipRequest(profileSelected);
            }
        }
    }

    /**
     * Se estamos vendo o propio perfil, pide ao usuario seleccionar un amigo e
     * o texto da mensaxe e chama ao controlador para enviar unha mensaxe. Se
     * estamos vendo o perfil dunha amizade, pideo o texto para enviarlle unha
     * mensaxe a ese perfil.
     *
     * @param ownProfile
     * @param scanner
     * @param profile
     */
    private void sendPrivateMessage(boolean ownProfile, Scanner scanner, Profile profile) {
        if (ownProfile) {
            //Selecciona el perfil para enviar el mensaje
            System.out.println("Selecciona un amigo para mandar una mensaje: ");
            for (int i = 0; i < profile.getFriends().size(); i++) {
                System.out.println(i + ". " + profile.getFriends().get(i).getName());
            }
            int friendSelected = selectElement("Elixe un numero", postsShowed, scanner);
            Profile friendProfile = profile.getFriends().get(friendSelected);

            //Escribir y enviar el mensaje
            System.out.println("Escriba la mensaje que desea enviar");
            String text = scanner.nextLine();
            profileController.newMessage(friendProfile, text);
        } else {
            //Escribir y enviar el mensaje
            System.out.println("Escriba la mensaje que desea enviar a este usuario");
            String text = scanner.nextLine();
            profileController.newMessage(profileController.getShownProfile(), text); //Envia mensaje al usuario mostrado
        }
    }

    /**
     * Pide ao usuario que se seleccione unha mensaxe e a mostra completa, dando
     * as opcións de respondela, eliminala ou simplemente volver á biografia
     * marcando a mensaxe como lida, chamando ao controlador para executar as
     * distintas accións.
     *
     * @param ownProfile
     * @param scanner
     * @param profile
     */
    private void readPrivateMessage(boolean ownProfile, Scanner scanner, Profile profile) {
        System.out.println("Que mensaxe queres ler");
        int messageIndex = scanner.nextInt();
        for (int i = 0; i < profile.getMessages().size(); i++) {
            System.out.println(i + ". " + profile.getMessages().get(i).getText());
        }
    }

    /**
     * Pide ao usuario que seleccion unha mensaxe e chama ao controlador para
     * borrala
     *
     * @param ownProfile
     * @param scanner
     * @param profile
     */
    private void deletePrivateMessage(boolean ownProfile, Scanner scanner, Profile profile) {
        if (ownProfile) {

        }
    }

    /**
     * Pide o número de publicacións que se queren visualizar e chamar ao
     * controlador para recargar o perfil
     *
     * @param scanner
     * @param profile
     */
    private void showOldPosts(Scanner scanner, Profile profile) {
        //Pide e recolle o numero de publicacións que se queren visualizar
        System.out.println("Cantas publicacións queres visualizar");
        int numposts = scanner.nextInt();
        scanner.nextLine();
        //Establece ese numero ao perfil
        postsShowed = numposts;
        //Recarga o perfil
        profileController.reloadProfile();
    }

    /**
     * Informa que un perfil non se atopou (Úsase cando se quere enviar unha
     * solicitude de amizade).
     */
    public void showProfileNotFoundMessage() {
        System.out.println("Perfil non atopado");
    }

    /**
     * Informa de que non se pode facer like sobre unha publicación propia
     */
    public void showCannotLikeOwnPostMessage() {
        System.out.println("Non podes facer like sobre as tuas propias publicacions");
    }

    /**
     * Informa de que non se pode facer like sobre unha publicación sobre a que
     * xa se fixo like
     */
    public void showAlreadyLikedPostMessage() {
        System.out.println("Non podes volver facer like sobre a mesma publicación");
    }

    /**
     * Informa de que xa tes amizade con ese perfil
     *
     * @param profileName
     */
    public void showIsAlreadyFriendMessage(String profileName) {
        System.out.println("Xa tes unha solicitude de amizade con este perfil");
    }

    /**
     * Informa de que ese perfil xa ten unha solicitude de amizade contigo
     *
     * @param profileName
     */
    public void showExistsFrienshipRequestMessage(String profileName) {
        System.out.println("Este perfil xa ten unha solicitude de amizade contigo");
    }

    /**
     * Informa de que xa tes unha solicitude de amizade con ese perfil
     *
     * @param profileName
     */
    public void showDuplicateFrienshipRequestMessage(String profileName) {
        System.out.println("Xa tes unha solicitude de amizade con este perfil");
    }
}
