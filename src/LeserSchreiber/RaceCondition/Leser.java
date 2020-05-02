package LeserSchreiber.RaceCondition;

public class Leser extends Thread {

    public Leser() {
    }
    private void lesen() throws InterruptedException {
        this.sleep(30);
        Datei d = RaceCondition.DS.getDatei("/root/users/user1/desktop/datei1");
        System.out.println("Leser liest: "+d.read());
    }
    @Override
    public void run() {
        while(true){
            try {
                lesen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
