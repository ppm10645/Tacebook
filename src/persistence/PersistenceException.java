/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

/**
 *
 * @author perei
 */
public class PersistenceException extends Exception {

    public static final int CONECTION_ERROR = 0;
    public static final int CANNOT_READ = 1;
    public static final int CANNOT_WRITE = 2;

    private int code;

    //Constructor
    public PersistenceException(int code, String message) {
        super(message);
        this.code = code;
    }

    //Getter para o código do erro
    public int getCode() {
        return code;
    }

}
