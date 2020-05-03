package LeserSchreiberNew.Dead;

public class Schreiber extends Thread{

    Daten daten;
    Datei datei;
    int index;

    public Schreiber(Datei datei, Daten daten, int index) {
        this.datei=datei;
        this.daten=daten;
        this.index=index;
    }

    @Override
    public void run() {
        while (true) {
            try {
                RaceCondition.mut_data.acquire();
                String data = daten.get();
                if (data != null) {
                    sleep(1000);
                    String s = data + index;
                    System.out.println("Writer: " + s);
                    datei.write(s);
                    index++;

            }
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}

