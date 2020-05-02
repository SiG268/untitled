package LeserSchreiber.RaceCondition;

public class Speicher {
    StringBuffer text =  new StringBuffer();

    public void write(String s){
        text.append(s);
    }

    public String read(){
        return text.toString();
    }
}
