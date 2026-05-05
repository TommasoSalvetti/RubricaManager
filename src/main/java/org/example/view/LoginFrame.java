package org.example.view;

import org.example.controller.RubricaManager;
import org.example.model.Utente;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    public LoginFrame(Utente utente) {
        setTitle("Login");
        setSize(350, 180);
        setLayout(new GridLayout(4,2));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextField user = new JTextField();
        JPasswordField pass = new JPasswordField();

        JButton login = new JButton("LOGIN");

        add(new JLabel("Username")); add(user);
        add(new JLabel("Password")); add(pass);
        add(new JLabel(""));
        add(new JLabel("Tip: admin - 1234"));
        add(new JLabel(""));
        add(login);

        login.addActionListener(e -> {
            if (utente.login(user.getText(), new String(pass.getPassword()))) {

                RubricaManager manager = new RubricaManager();

                dispose();
                new MainFrame(manager).setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, "Login errato");
            }
        });

        setLocationRelativeTo(null);
    }
}
