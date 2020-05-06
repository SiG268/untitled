package LeserSchreiber.StarvationSolved;

public class Takt extends Thread{
    @Override
    public void run() {
        try {

            while(true) {
                System.out.println("Lesephase");
                StarvationSolved.mut_readTakt.release();
                sleep(100);
                StarvationSolved.mut_readTakt.acquire();
                System.out.println("Schreibphase");
                StarvationSolved.mut_writeTakt.release();
                sleep(100);
                StarvationSolved.mut_writeTakt.acquire();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
