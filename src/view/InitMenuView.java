/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author joao.pedro.pereira
 */
public interface InitMenuView {

    public boolean showLoginMenu();

    /**
     * Mensaxe de error en caso de que o usuario ou o contrasinal sexan
     * incorrectos
     */
    public void showLoginErrorMessage();

    /**
     * Menu para rexistrar un usuario
     */
    public void showRegisterMenu();

    /**
     * 
     * @return 
     */
    public String showNewNameMenu();
    
    /**
     * Mostra unha mensaxe de erro coa conexion do almacen de datos
     */
    public void showConnectionErrorMessage();
    
    /**
     * Mostra unha mensaxe de erro na lectura de datos
     */
    public void showReadErrorMessage();
    
    /**
     * Mostra unha mensaxe de erro na escritura de datos
     */
    public void showWriteErrorMessage();
}
