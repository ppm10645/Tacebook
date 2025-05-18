/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.InitMenuController;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import persistence.ProfileDB;
import persistence.TacebookDB;

/**
 *
 * @author joao.pedro.pereira
 */
public class GUItInitMenuView implements InitMenuView {

    private InitMenuController initMenuController;

    public GUItInitMenuView(InitMenuController initMenuController) {
        this.initMenuController = initMenuController;
    }

    @Override
    public boolean showLoginMenu() {

        String[] options = {"Iniciar sesión", "Rexistrarse", "Sair"};

        JTextField username = new JTextField(20);
        JTextField password = new JTextField(20);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(new JLabel("Nome de usuario:"), gbc);

        gbc.gridx = 2;
        panel.add(username, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(new JLabel("Contrasinal:"), gbc);

        gbc.gridx = 2;
        panel.add(password, gbc);

        int choice = JOptionPane.showOptionDialog(
                null,
                panel,
                "Entrar en tacebook",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        switch (choice) {
            case 0 -> {
                initMenuController.login(username.getText(), password.getText());
                return false;
            }
            case 1 -> {
                showRegisterMenu();
                return false;
            }
            case 2 -> {
                return true;
            }
        }

        return true;
    }

    /**
     * Mensaxe de error en caso de que o usuario ou o contrasinal sexan
     * incorrectos
     */
    @Override
    public void showLoginErrorMessage() {
        System.out.println("Error, o usuario ou o contrasinal son incorrectos");
    }

    /**
     * Menu para rexistrar un usuario
     */
    @Override
    public void showRegisterMenu() {

        String[] options = {"Aceptar", "Cancelar"};

        JTextField username = new JTextField(20);
        JTextField password = new JTextField(20);
        JTextField repassword = new JTextField(20);
        JTextField status = new JTextField(20);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(new JLabel("Nome de usuario:"), gbc);

        gbc.gridx = 2;
        panel.add(username, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(new JLabel("Contrasinal:"), gbc);

        gbc.gridx = 2;
        panel.add(password, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(new JLabel("Repite contrasinal:"), gbc);

        gbc.gridx = 2;
        panel.add(repassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(new JLabel("Estado:"), gbc);

        gbc.gridx = 2;
        panel.add(status, gbc);

        int choice = JOptionPane.showOptionDialog(
                null,
                panel,
                "Entrar en tacebook",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        switch (choice) {
            case 0 -> {
                if (password.getText() == null ? repassword.getText() != null : !password.getText().equals(repassword.getText())) {
                    JOptionPane.showMessageDialog(null, "O contasinal non coincide");
                } else if (password.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O contasinal non pode esta vacío");
                } else {
                    initMenuController.createProfile(username.getText(), password.getText(), status.getText());
                    initMenuController.login(username.getText(), password.getText());
                }
            }
            case 1 ->
                showLoginMenu();
        }

    }

    /**
     * Mostra unha mensaxe de erro coa conexion do almacen de datos
     */
    @Override
    public void showConnectionErrorMessage() {
        System.out.println("Erro na conexión co almacén de datos!");
    }

    /**
     * Mostra unha mensaxe de erro na lectura de datos
     */
    @Override
    public void showReadErrorMessage() {
        System.out.println("Erro na lectura de datos!");
    }

    /**
     * Mostra unha mensaxe de erro na escritura de datos
     */
    @Override
    public void showWriteErrorMessage() {
        System.out.println("Erro na escritura dos datos!");
    }

    @Override
    public String showNewNameMenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
