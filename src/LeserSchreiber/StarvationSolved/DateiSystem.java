package LeserSchreiber.StarvationSolved;

import java.util.ArrayList;
import java.util.HashMap;

public class DateiSystem {
    private ArrayList<Datei> Dateien =  new ArrayList<>();
    HashMap<String, Datei> map = new HashMap<String, Datei>();

    public void create(String referenz, String dateiName){
        Datei datei = new Datei(dateiName);
        map.put(referenz+dateiName,datei);
    }
    public Datei getDatei(String referenz){
        return map.get(referenz);
    }

}
