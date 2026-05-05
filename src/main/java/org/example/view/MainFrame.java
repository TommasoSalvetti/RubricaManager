package org.example.view;

import org.example.controller.RubricaManager;
import org.example.model.Persona;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame {

    private RubricaManager manager;
    private JTable table;
    private DefaultTableModel model;

    public MainFrame(RubricaManager manager) {
        this.manager = manager;
        manager.carica();

        setTitle("Rubrica");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        model = new DefaultTableModel(new String[]{"Nome", "Cognome", "Telefono"}, 0);
        table = new JTable(model);

        table.setAutoCreateRowSorter(true);

        aggiornaTabella();

        JToolBar toolbar = new JToolBar();

        JButton nuovo = new JButton("Nuovo");
        JButton modifica = new JButton("Modifica");
        JButton elimina = new JButton("Elimina");

        toolbar.add(nuovo);
        toolbar.add(modifica);
        toolbar.add(elimina);

        add(toolbar, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        nuovo.addActionListener(e -> apriEditor(null));

        modifica.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Seleziona una persona");
                return;
            }
            apriEditor(manager.getPersone().get(row));
        });

        elimina.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Seleziona una persona");
                return;
            }

            Persona p = manager.getPersone().get(row);

            int conf = JOptionPane.showConfirmDialog(
                    this,
                    "Eliminare " + p.getNome() + " " + p.getCognome() + "?",
                    "Conferma",
                    JOptionPane.YES_NO_OPTION
            );

            if (conf == JOptionPane.YES_OPTION) {
                manager.rimuovi(row);
                aggiornaTabella();
            }
        });

        setLocationRelativeTo(null);
    }

    private void aggiornaTabella() {
        model.setRowCount(0);
        for (Persona p : manager.getPersone()) {
            model.addRow(new Object[]{
                    p.getNome(),
                    p.getCognome(),
                    p.getTelefono()
            });
        }
    }

    private void apriEditor(Persona persona) {
        EditorPersonaDialog dialog =
                new EditorPersonaDialog(this, manager, persona);

        dialog.setVisible(true);
        aggiornaTabella();
    }
}
