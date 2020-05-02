package LeserSchreiber.RaceCondition;

public class Schreiber extends Thread{
    private int id;

    public Schreiber(int i) {
        this.id = i;
    }

    @Override
    public void run() {
        while(true){
            try {
                schreiben();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RaceCondition.count++;
        }
    }

    private void schreiben() throws InterruptedException {
        this.sleep(100);
        Datei d = RaceCondition.DS.getDatei("/root/users/user1/desktop/datei1");
        String s = "Hallo "+RaceCondition.count;
        System.out.println("Schreiber"+this.id+" schreibt: "+s);
        d.write(s);
    }
}
