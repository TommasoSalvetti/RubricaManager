package org.example;


import org.example.model.Utente;
import org.example.view.LoginFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        Utente utente = new Utente("admin", "1234");

        new LoginFrame(utente).setVisible(true);
    }
}