package LeserSchreiber.RaceCondition;

public class Datei {
    int id;
    String Inhalt;
    public Datei(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Datei{" +
                "Inhalt='" + Inhalt + '\'' +
                '}';
    }
}
