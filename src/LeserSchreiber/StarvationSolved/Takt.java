package LeserSchreiber.StarvationSolved;

public class Takt extends Thread{
    @Override
    public void run() {
        try {
            while(true) {
                StarvationSolved.mut_write.release();
                sleep(100);
                StarvationSolved.mut_write.acquire();
                StarvationSolved.mut_read.release();
                sleep(100);
                StarvationSolved.mut_read.acquire();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
