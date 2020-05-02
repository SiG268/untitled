package LeserSchreiber.RaceCondition;

public class Schreiber extends Thread{
    int count = 0;

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
        this.sleep(10);
        Datei d = RaceCondition.DS.getDatei("/root/users/user1/desktop/datei1");
        String s = "Hallo "+count;
        System.out.println("Schreiber schreibt: "+s);
        d.write(s);
    }
}
