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
public class Message {
    private int id;
    private String text;
    private Date date;
    private boolean read;
    private Profile destProfile;
    private Profile sourceProfile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Profile getDestProfile() {
        return destProfile;
    }

    public void setDestProfile(Profile destProfile) {
        this.destProfile = destProfile;
    }

    public Profile getSourceProfile() {
        return sourceProfile;
    }

    public void setSourceProfile(Profile sourceProfile) {
        this.sourceProfile = sourceProfile;
    }

    public Message(Profile destProfile, String text) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.read = read;
        this.destProfile = destProfile;
        this.sourceProfile = sourceProfile;
    }
    
    
    
}
