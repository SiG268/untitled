package LeserSchreiber.RaceCondition;

import java.util.ArrayList;

public class Datei {
    String dateiName;
    ArrayList<String> inhalt = new ArrayList<>();

    public Datei(String dateiName) {
        this.dateiName = dateiName;
    }


    public String read(){
        String text ="";
        if(inhalt.size()>0) {
             text = inhalt.get(inhalt.size() - 1);    //Lese letzten String
        }
        return text;
    }

    public void write(String s){
       inhalt.add(s);
    }
}
