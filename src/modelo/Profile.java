/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author joao.pedro.pereira
 */
public class Profile {
    private String name, password, status;
    
    private ArrayList<Post> posts; 
    private ArrayList<Comment> comments;
    
    private ArrayList<Message> messages;
    
    private ArrayList<Profile> friends;
    private ArrayList<Profile> friendRequests;

    public Profile(String name, String password, String status) {
        this.name = name;
        this.password = password;
        this.status = status;
        this.friends = new ArrayList<>();
        this.friendRequests = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.posts= new ArrayList<>();
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Profile> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Profile> friends) {
        this.friends = friends;
    }

    public ArrayList<Profile> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(ArrayList<Profile> friendRequests) {
        this.friendRequests = friendRequests;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
    
}
