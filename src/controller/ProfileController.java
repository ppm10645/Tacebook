/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Comment;
import model.Message;
import model.Post;
import model.Profile;
import persistence.CommentDB;
import persistence.MessageDB;
import persistence.PersistenceException;
import persistence.PostDB;
import persistence.ProfileDB;
import view.ProfileView;
import view.TextProfileView;

/**
 *
 * @author joao.pedro.pereira
 */
public class ProfileController {

    private ProfileView profileView;
    private Profile sessionProfile;
    private Profile shownProfile;

    public ProfileController() {
        this.profileView = new TextProfileView(this);
        this.shownProfile = sessionProfile;
    }

    public Profile getShownProfile() {
        return shownProfile;
    }

    public void setShownProfile(Profile shownProfile) {
        this.shownProfile = shownProfile;
        reloadProfile();
    }

    /**
     * Obten o numero de publicacións a mostrar
     *
     * @return
     */
    public int getPostsShowed() {
        return profileView.getPostsShowed();
    }

    /**
     * Mostra o menu do perfil
     */
    public void reloadProfile() {
        // Precisamente neste método "reloadProfile" cambiaremos o código para que en lugar de almacenar o perfil no atributo
        // "sessionProfile" o garde no atributo "shownProfile", e sexa ese atributo o que se lle pase ao obxecto da vista
    }

    /**
     * Abre unha sesión con un perfil
     *
     * @param sessionProfile
     */
    public void openSession(Profile sessionProfile) {
        this.sessionProfile = sessionProfile;

        if (this.profileView == null) {
            this.profileView = new TextProfileView(this);
        }
        profileView.showProfileMenu(sessionProfile);
    }

    /**
     * Actualiza o estatus do perfil
     *
     * @param newStatus
     */
    public void updateProfileStatus(String newStatus) {
        try {
            sessionProfile.setStatus(newStatus);
            ProfileDB.update(sessionProfile);
            reloadProfile();
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }
    }

    /**
     * Crea un novo post
     *
     * @param text
     * @param destProfile
     */
    public void newPost(String text, Profile destProfile) {
        try {
            Post mypost = new Post(sessionProfile, text, destProfile);
            
            PostDB.save(mypost);
            reloadProfile();
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }
    }

    /**
     * Crea un novo comentario
     *
     * @param post
     * @param commentText
     */
    public void newComment(Post post, String commentText) {
        try {
            Comment mycomment = new Comment(post, commentText, sessionProfile);
            
            CommentDB.save(mycomment);
            reloadProfile();
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }
    }

    /**
     * Añade un like a un post
     *
     * @param post
     */
    public void newLike(Post post) {
        // Comproba se o usuario non he o autor da publicación e non dera xa un like
        if (!post.getAuthor().getName().equals(sessionProfile.getName()) && !post.getProfileLikes().contains(post.getProfile())) {
            post.getProfileLikes().add(sessionProfile);
        }
        reloadProfile();
    }

    /**
     * Añade unha solicitude de amizade a un usuario
     *
     * @param profileName nome do usuario ao que se lle quere mandar a
     * solicitude de amizade
     */
    public void newFriendshipRequest(String profileName) {
        try {
            ProfileDB.saveFrienshipRequest(ProfileDB.findByName(profileName, 1), sessionProfile);
            reloadProfile();
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }
    }

    /**
     * Acepta a solicitude de amizade
     *
     * @param sourceProfile
     */
    public void acceptFriendshipRequest(Profile sourceProfile) {
        try {
            // Elimina a solicitude de amizade e añade o perfil a lista de amigos
            ProfileDB.removeFrienshipRequest(sessionProfile, sourceProfile);
            sourceProfile.getFriends().add(sessionProfile);
            sessionProfile.getFriends().add(sourceProfile);
            
            reloadProfile();
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }
    }

    /**
     * Non acepta a solicitude de amizade
     *
     * @param sourceProfile
     */
    public void rejectFriendshipRequest(Profile sourceProfile) {
        try {
            //Elimina a solicitude de amizade
            ProfileDB.removeFrienshipRequest(sessionProfile, sourceProfile);
            
            reloadProfile();
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }
    }

    /**
     * Crea unha mensaxe
     *
     * @param destProfile
     * @param text
     */
    public void newMessage(Profile destProfile, String text) {
        try {
            //Crea un obxeto Message
            Message myMessage = new Message(destProfile, text, sessionProfile);
            // Garda o obxeto
            MessageDB.save(myMessage);
            
            reloadProfile();
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }
    }

    /**
     * Elimina unha mensaxe
     *
     * @param message
     */
    public void deleteMessage(Message message) {
        try {
            MessageDB.remove(message);
            
            reloadProfile();
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }
    }

    /**
     * Marca unha mensaxe como leida
     *
     * @param message
     */
    public void markMessageAsRead(Message message) {
        try {
            message.setRead(true);
            MessageDB.update(message);
            
            reloadProfile();
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }
    }

    /**
     * Marca unha mensaxe como leida e envia unha mensaxe de resposta
     *
     * @param message
     * @param text
     */
    public void replyMessage(Message message, String text) {
        try {
            message.setRead(true);
            MessageDB.update(message);
            
            newMessage(sessionProfile, text);
            reloadProfile();
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }
    }
    
    /**
     * Procesa unha excepción de persistencia e en función do código da excepción chamará a un dos tres métodos de error da clase view
     * @param ex 
     */
    private void proccessPersistenceException(PersistenceException ex) {
        switch (ex.getCode()) {
            case PersistenceException.CONECTION_ERROR -> profileView.showConnectionErrorMessage();
            case PersistenceException.CANNOT_READ -> profileView.showReadErrorMessage();
            case PersistenceException.CANNOT_WRITE -> profileView.showWriteErrorMessage();
            default -> {
            }
        }
    }

}
