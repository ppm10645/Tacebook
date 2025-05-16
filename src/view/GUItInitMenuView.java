/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.InitMenuController;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author joao.pedro.pereira
 */
public class GUItInitMenuView implements InitMenuView {

    private InitMenuController initMenuController;
    private Scanner scanner;

    public GUItInitMenuView(InitMenuController initMenuController) {
        this.initMenuController = initMenuController;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public boolean showLoginMenu() {

        String[] options = {"Iniciar sesión", "Rexistrarse", "Sair"};
        boolean exit = false;

        while (!exit) {
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
                case 0 ->
                    initMenuController.login(username.getText(), password.getText());
                case 1 ->
                    showRegisterMenu();
                case 2 ->
                    exit = true;
            }
        }

        return false;
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

        
        boolean exit = false;

        while (!exit) {
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
                    if(password.getText().equals(repassword.getText())) {
                        initMenuController.createProfile(username.getText(), password.getText(), status.getText());
                        initMenuController.login(username.getText(), password.getText());
                        exit = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "O contasinal non coincide");
                    }
                }
                case 1 ->
                    exit = true;
            }

        }
        
//        System.out.println("Introduzca o nome de usuario");
//        String name = scanner.nextLine();
//        String contrasinal;
//
//        while (true) {
//            System.out.println("Introduzca o contrasinal");
//            contrasinal = scanner.nextLine();
//            System.out.println("Introduzca de novo o contrasinal");
//            String repcontrasinal = scanner.nextLine();
//            if (repcontrasinal.equals(contrasinal)) {
//                break;
//            } else {
//                System.out.println("O contrasinal non coincide");
//            }
//        }
//        System.out.println("Introduzca o estado");
//        String estado = scanner.nextLine();
//        System.out.println("Rexistrouse correctamente o usuario");
//        initMenuController.createProfile(name, contrasinal, estado);
    }

    @Override
    public String showNewNameMenu() {
        System.out.println("O nome de usuario xa está en uso, introduzca un novo nome de usuario");
        return scanner.nextLine();
    }

    private int readNumber(Scanner scanner) {
        try {
            int number = scanner.nextInt();
            scanner.nextLine(); //Limpiar buffer
            return number;
        } catch (NoSuchElementException e) {
            System.out.println("Debe introduccir un numero");
            scanner.nextLine(); //Limpiar buffer en caso de error
            return readNumber(scanner);
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

}
