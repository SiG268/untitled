package LeserSchreiber.RaceCondition;

public class Schreiber extends Thread{
    Speicher d;
    int count = 0;

    public Schreiber(Speicher d) {
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
        d.write("Write"+count,count);
        System.out.println(count);
    }
}
