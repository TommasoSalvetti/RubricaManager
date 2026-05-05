package org.example.controller;

import org.example.model.Persona;

import java.io.*;
import java.util.*;

public class RubricaManager {

    private Vector<Persona> persone = new Vector<>();
    private File cartella = new File("informazioni");

    public Vector<Persona> getPersone() {
        return persone;
    }

    public void aggiungi(Persona p) {
        persone.add(p);
        salva();
    }

    public void rimuovi(int index) {
        persone.remove(index);
        salva();
    }

    public void salva() {
        if (!cartella.exists()) {
            cartella.mkdir();
        }

        for (File f : cartella.listFiles()) {
            f.delete();
        }

        for (Persona p : persone) {
            try {
                String base = p.getNome() + "-" + p.getCognome();
                File file = new File(cartella, base + ".txt");

                int i = 1;
                while (file.exists()) {
                    file = new File(cartella, base + "_" + i + ".txt");
                    i++;
                }

                PrintStream ps = new PrintStream(file);
                ps.println(p.toFileString());
                ps.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void carica() {
        if (!cartella.exists()) return;

        for (File file : cartella.listFiles()) {
            try (Scanner sc = new Scanner(file)) {
                if (sc.hasNextLine()) {
                    String[] d = sc.nextLine().split(";");
                    persone.add(new Persona(
                            d[0], d[1], d[2], d[3],
                            Integer.parseInt(d[4])
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
