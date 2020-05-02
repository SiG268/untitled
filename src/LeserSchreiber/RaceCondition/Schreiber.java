package LeserSchreiber.RaceCondition;

public class Schreiber extends Thread{
    InodeTable d;
    int count = 0;
    int adress = 0;

    public Schreiber(InodeTable d) {
        this.d=d;
    }

    @Override
    public void run() {
        while(true){
            try {
                schreiben();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }

    private void schreiben() throws InterruptedException {
        this.sleep(100);
        d.write( new Datei("Inhalt"+count),adress);
        System.out.println(count);
    }
}
