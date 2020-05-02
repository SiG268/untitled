package LeserSchreiber.RaceCondition;

public class Datei {
    String dateiName;
    StringBuffer inhalt;

    public Datei(String dateiName) {
        this.dateiName = dateiName;
    }

    @Override
    public String toString() {
        return dateiName+ "\nInhalt = { "+inhalt+" }";
    }
}
