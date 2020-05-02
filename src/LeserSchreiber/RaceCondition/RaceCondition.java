package LeserSchreiber.RaceCondition;

public class RaceCondition {

    public static void main(String[] args) {
        Speicher datei = new Speicher();
        Speicher zwischenSpeicher = new Speicher();

        Leser l = new Leser(datei);
        Schreiber s = new Schreiber(datei);
    }
}
