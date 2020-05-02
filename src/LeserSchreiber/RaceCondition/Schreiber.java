package LeserSchreiber.RaceCondition;

public class Schreiber extends Thread{
    int count = 0;
    int adress = 0;

    public Schreiber() {

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
        System.out.println(count);
    }
}
