package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JTextField;
import ohtu.komento.Komento;

public class Tapahtumankuuntelija implements ActionListener {

    private final JTextField syotekentta;
    private final JTextField tuloskentta;
    private final JButton nollaa;
    private final JButton undo;
    private final Map<JButton, Komento> komennot;
    private final Sovelluslogiikka sovellus;
    private Komento edellinen;

    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.syotekentta = syotekentta;
        this.tuloskentta = tuloskentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();

        komennot = new HashMap<>();
        komennot.put(plus, new ohtu.komento.Plus(sovellus, syotekentta, tuloskentta));
        komennot.put(miinus, new ohtu.komento.Miinus(sovellus, syotekentta, tuloskentta));
        komennot.put(nollaa, new ohtu.komento.Nollaus(sovellus, syotekentta, tuloskentta));

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        Komento komento = komennot.get(ae.getSource());

        if (komento != null) {
            komento.suorita();
            edellinen = komento;
        } else if (edellinen != null) {
            edellinen.peru();
            edellinen = null;
        }

        int laskunTulos = sovellus.tulos();

        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);

        if (laskunTulos == 0) {
            nollaa.setEnabled(false);
        } else {
            nollaa.setEnabled(true);
        }

        if (edellinen != null) {
            undo.setEnabled(true);
        } else {
            undo.setEnabled(false);
        }
    }
}
