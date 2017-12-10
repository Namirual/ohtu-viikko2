/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.komento;

import javax.swing.JTextField;
import ohtu.Sovelluslogiikka;

/**
 *
 * @author lmantyla
 */
public class Miinus implements Komento {

    Sovelluslogiikka sovellus;
    JTextField syotekentta;
    JTextField tuloskentta;
    int tulosEnnen;

    public Miinus(Sovelluslogiikka sovellus, JTextField syotekentta, JTextField tuloskentta) {
        this.sovellus = sovellus;
        this.syotekentta = syotekentta;
        this.tuloskentta = tuloskentta;
        this.tulosEnnen = 0;
    }

    @Override
    public void suorita() {
        tulosEnnen = sovellus.tulos();
        try {
            sovellus.miinus(Integer.parseInt(syotekentta.getText()));
        } catch (java.lang.NumberFormatException exception) {
            System.out.println("Not a number!");
    }
    }

    @Override
    public void peru() {
        sovellus.asetaTulos(tulosEnnen);
    }
}
