package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KPSPeliPohja {

    private Tekoaly tekoaly;

    public KPSTekoaly(Tekoaly tekoaly) {
        this.tekoaly = tekoaly;
    }

    @Override
    protected String haeToisenSiirto() {
        String siirto = tekoaly.annaSiirto();
        tekoaly.asetaSiirto(ekanSiirto);

        System.out.println("Tietokone valitsi: " + siirto);
        return siirto;
    }
}
