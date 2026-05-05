package org.example.view;

import org.example.controller.RubricaManager;
import org.example.model.Persona;

import javax.swing.*;
import java.awt.*;

public class EditorPersonaDialog extends JDialog {

    public EditorPersonaDialog(JFrame parent, RubricaManager manager, Persona persona) {

        super(parent, true);

        setTitle("Editor Persona");
        setSize(300, 300);
        setLayout(new GridLayout(6, 2));

        JTextField nome = new JTextField();
        JTextField cognome = new JTextField();
        JTextField indirizzo = new JTextField();
        JTextField telefono = new JTextField();
        JTextField eta = new JTextField();

        if (persona != null) {
            nome.setText(persona.getNome());
            cognome.setText(persona.getCognome());
            indirizzo.setText(persona.getIndirizzo());
            telefono.setText(persona.getTelefono());
            eta.setText(String.valueOf(persona.getEta()));
        }

        add(new JLabel("Nome")); add(nome);
        add(new JLabel("Cognome")); add(cognome);
        add(new JLabel("Indirizzo")); add(indirizzo);
        add(new JLabel("Telefono")); add(telefono);
        add(new JLabel("Età")); add(eta);

        JButton salva = new JButton("Salva");
        JButton annulla = new JButton("Annulla");

        salva.addActionListener(e -> {
            try {
                int etaVal = Integer.parseInt(eta.getText());

                if (persona == null) {
                    manager.aggiungi(new Persona(
                            nome.getText(),
                            cognome.getText(),
                            indirizzo.getText(),
                            telefono.getText(),
                            etaVal
                    ));
                } else {
                    persona.setNome(nome.getText());
                    persona.setCognome(cognome.getText());
                    persona.setIndirizzo(indirizzo.getText());
                    persona.setTelefono(telefono.getText());
                    persona.setEta(etaVal);
                    manager.salva();
                }

                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Età non valida");
            }
        });

        annulla.addActionListener(e -> dispose());

        add(salva);
        add(annulla);

        setLocationRelativeTo(null);
    }
}
