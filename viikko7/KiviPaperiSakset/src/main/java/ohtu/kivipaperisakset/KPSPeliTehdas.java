package ohtu.kivipaperisakset;

/**
 *
 * @author lmantyla
 */
public class KPSPeliTehdas {
    public static KPSPeli ihmisPelaaja() {
        return new KPSPelaajaVsPelaaja();
    }

    public static KPSPeli perusTekoaly() {
        return new KPSTekoaly(new TekoalyPerus());
    }

    public static KPSPeli parempiTekoaly() {
        return new KPSTekoaly(new TekoalyParannettu(20));
    }
}
