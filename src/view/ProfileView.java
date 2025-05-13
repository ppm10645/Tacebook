/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.Profile;

/**
 *
 * @author joao.pedro.pereira
 */
public interface ProfileView {

    public int getPostsShowed();

    /**
     * Menu del perfil para mostrar la información del perfil o cambiar el
     * estado
     *
     * @param profile
     */
    public void showProfileMenu(Profile profile);

    /**
     * Informa que un perfil non se atopou (Úsase cando se quere enviar unha
     * solicitude de amizade).
     */
    public void showProfileNotFoundMessage();

    /**
     * Informa de que non se pode facer like sobre unha publicación propia
     */
    public void showCannotLikeOwnPostMessage();

    /**
     * Informa de que non se pode facer like sobre unha publicación sobre a que
     * xa se fixo like
     */
    public void showAlreadyLikedPostMessage();

    /**
     * Informa de que xa tes amizade con ese perfil
     *
     * @param profileName
     */
    public void showIsAlreadyFriendMessage(String profileName);

    /**
     * Informa de que ese perfil xa ten unha solicitude de amizade contigo
     *
     * @param profileName
     */
    public void showExistsFrienshipRequestMessage(String profileName);

    /**
     * Informa de que xa tes unha solicitude de amizade con ese perfil
     *
     * @param profileName
     */
    public void showDuplicateFrienshipRequestMessage(String profileName);

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
