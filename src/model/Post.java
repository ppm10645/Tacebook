/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author joao.pedro.pereira
 */
public class Post {

    private int id;
    private Date date;
    private String text;
    private Profile profile;
    private Profile author;
    private ArrayList<Profile> profileLikes;
    private ArrayList<Comment> comments;

    public Post(Profile author, String text, Profile profile) {
        this.date = new Date();  // Fecha automática al crear el post
        this.text = text;
        this.author = author;    // El autor de la publicación (quién escribe)
        this.profile = profile;  // El dueño del muro donde se publica
        this.profileLikes = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Profile getAuthor() {
        return author;
    }

    public void setAuthor(Profile author) {
        this.author = author;
    }

    public ArrayList<Profile> getProfileLikes() {
        return profileLikes;
    }

    public void setProfileLikes(ArrayList<Profile> profileLikes) {
        this.profileLikes = profileLikes;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

}
