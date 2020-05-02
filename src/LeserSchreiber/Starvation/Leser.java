package LeserSchreiber.Starvation;

public class Leser extends Thread {

    public Leser() {
    }
    private void lesen() throws InterruptedException {
        //this.sleep(1);
        Starvation.mut_arbeiten.acquire();
        Starvation.read_count++;
        if(Starvation.read_count == 1){
            Starvation.sem_Schreiber.acquire();
        }
        Starvation.mut_arbeiten.release();
        Datei d = Starvation.DS.getDatei("/root/users/user1/desktop/datei1");
        System.out.println("Leser liest: "+d.read());
        //this.sleep(1000);
        Starvation.mut_arbeiten.acquire();
        Starvation.read_count--;
        if(Starvation.read_count == 0){
            Starvation.sem_Schreiber.release();
        }
        Starvation.mut_arbeiten.release();
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
