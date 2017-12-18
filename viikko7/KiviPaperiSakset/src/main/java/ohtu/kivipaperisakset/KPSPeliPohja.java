package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KPSPeliPohja implements KPSPeli {

    protected static final Scanner scanner = new Scanner(System.in);
    protected String ekanSiirto;
    protected String tokanSiirto;

    public void pelaa() {
        Tuomari tuomari = new Tuomari();

        System.out.println("Ensimmäisen pelaajan siirto: ");
        ekanSiirto = scanner.nextLine();
        tokanSiirto = haeToisenSiirto();

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            System.out.println("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = scanner.nextLine();
            tokanSiirto = haeToisenSiirto();
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }

    protected abstract String haeToisenSiirto();

    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}
