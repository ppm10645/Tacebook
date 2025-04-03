/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Comment;
import modelo.Post;
import modelo.Profile;
import persistencia.CommentDB;
import persistencia.PostDB;
import persistencia.ProfileDB;
import vista.ProfileView;

/**
 *
 * @author joao.pedro.pereira
 */
public class ProfileController {
    
    private ProfileView profileView;
    private Profile sessionProfile;
    private Profile shownProfile;
    
    public ProfileController(){
        this.profileView = new ProfileView(this);
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
     * @return 
     */
    public int getPostsShowed(){
        return profileView.getPostsShowed();
    }
    
    /**
     * Mostra o menu do perfil 
     */
    public void reloadProfile(){
        // Precisamente neste método "reloadProfile" cambiaremos o código para que en lugar de almacenar o perfil no atributo
        // "sessionProfile" o garde no atributo "shownProfile", e sexa ese atributo o que se lle pase ao obxecto da vista
    }
    
    /**
     * Abre unha sesión con un perfil
     * @param sessionProfile 
     */
    public void openSession(Profile sessionProfile){
        this.sessionProfile = sessionProfile;
        
        if(this.profileView == null) {
            this.profileView = new ProfileView(this);
        }
        profileView.showProfileMenu(sessionProfile);
    }
    
    /**
     * Actualiza o estatus do perfil
     * @param newStatus 
     */
    public void updateProfileStatus(String newStatus){
        sessionProfile.setStatus(newStatus);
        ProfileDB.update(sessionProfile);
        reloadProfile();
    }
    
    /**
     * Crea un novo post
     * @param text
     * @param destProfile 
     */
    public void newPost(String text, Profile destProfile){
        Post mypost = new Post(null,text,destProfile);
        
        PostDB.save(mypost);
        reloadProfile();
    }
    
    /**
     * Crea un novo comentario
     * @param post
     * @param commentText 
     */
    public void newComment(Post post, String commentText){
        Comment mycomment = new Comment(post, commentText);
        
        CommentDB.save(mycomment);
        reloadProfile();
    }
    
    /**
     * Añade un like a un post
     * @param post 
     */
    public void newLike(Post post){
        if(!post.getAuthor().getName().equals(sessionProfile.getName()) && !post.getProfileLikes().contains(post.getProfile())){
            post.getProfileLikes().add(sessionProfile);
        } 
        reloadProfile();
    }
    
    public void newFriendshipRequest(String profileName){
        if(!sessionProfile.getFriendRequests().contains(ProfileDB.findByName(profileName, 0))){
            ProfileDB.saveFrienshipRequest(sessionProfile, ProfileDB.findByName(profileName, 0));
        } 
        reloadProfile();
    }
    
    public void acceptFriendshipRequest(Profile sourceProfile){
        
    }
    
    
}
