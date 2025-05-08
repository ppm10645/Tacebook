/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author joao.pedro.pereira
 */
public class Comment {
    private int id;
    private Date date;
    private String text;
    private Post post;
    private Profile sourceProfile;

    public Comment(Post post, String text, Profile sourceProfile) {
        this.post = post;
        this.text = text;
        this.sourceProfile = sourceProfile;
        this.date = new Date();
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Profile getSourceProfile() {
        return sourceProfile;
    }

    public void setSourceProfile(Profile sourceProfile) {
        this.sourceProfile = sourceProfile;
    }
    
    
    
}
