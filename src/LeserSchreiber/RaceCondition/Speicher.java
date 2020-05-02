package LeserSchreiber.RaceCondition;

import java.util.ArrayList;
import java.util.List;

public class Speicher {
    List<Datei> text = new ArrayList<Datei>();

    public void write(Datei s,int adress){
        text.add(adress,s);
    }

    public Datei read(int adress){
        return text.get(adress);
    }
}
