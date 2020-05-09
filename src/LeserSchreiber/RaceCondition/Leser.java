package LeserSchreiber.RaceCondition;

public class Leser extends Thread {

    int id;
    public Leser(int i) {
        this.id=i;
    }

    @Override
    public void run() {
        while(true){
            try {
                //Lese Speicher aus
                int n = RaceCondition.sharedStorage;
                System.out.println("Leser"+id+" liest: "+ n);
                //Zeit zum lesen
                sleep((int)(Math.random()*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
