/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.ArrayList;
import modelo.Profile;

/**
 *
 * @author joao.pedro.pereira
 */
public class TacebookDB {
    
    private static ArrayList<Profile> profiles = new ArrayList<>();

    public static ArrayList<Profile> getProfiles() {
        return profiles;
    }

    public static void setProfiles(ArrayList<Profile> profiles) {
        TacebookDB.profiles = profiles;
    }
}
