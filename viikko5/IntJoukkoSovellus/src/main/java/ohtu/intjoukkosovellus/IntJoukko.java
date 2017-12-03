package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5; // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        luoIntJoukko(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        luoIntJoukko(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
        luoIntJoukko(kapasiteetti, kasvatuskoko);
    }

    public void luoIntJoukko(int kapasiteetti, int kasvatuskoko) {
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }

        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (!lukuOnJoukossa(luku)) {
            lukujono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm == lukujono.length) {
                kasvataJononKokoa();
            }
            return true;
        }
        return false;
    }

    public boolean lukuOnJoukossa(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        return false;
    }

    public int haeLuvunPaikka(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return i;
            }
        }
        return -1;
    }

    public void kasvataJononKokoa() {
        int[] vanhaTaulukko = new int[lukujono.length];
        vanhaTaulukko = lukujono;
        kopioiTaulukko(lukujono, vanhaTaulukko);
        lukujono = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(vanhaTaulukko, lukujono);
    }

    public boolean poista(int luku) {
        int kohta = haeLuvunPaikka(luku);
        if (kohta != -1) {
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                lukujono[j] = lukujono[j + 1];
            }
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += lukujono[i] + ", ";
        }
        if (alkioidenLkm > 0) {
            tuotos += lukujono[alkioidenLkm - 1];
        }
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }
}
