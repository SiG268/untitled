package LeserSchreiberNew.Dead;


public class Leser extends Thread {
    Datei datei;
    Daten daten;

    public Leser(Datei datei, Daten daten) {
        this.datei = datei;
        this.daten = daten;
    }

    @Override
    public void run() {
        while(true){
            try {
                String s = datei.read();
                sleep(1000);
                System.out.println("Leser liest: "+s);
                daten.set(s);
                RaceCondition.mut_data.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
