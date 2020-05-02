package LeserSchreiber.Dead;

public class Datei {
    String dateiName;
    int inhalt = 0;

    public Datei(String dateiName) {
        this.dateiName = dateiName;
    }


    public int read(){
        return this.inhalt;
    }

    public void write(int s){
       inhalt = s;
    }
}
