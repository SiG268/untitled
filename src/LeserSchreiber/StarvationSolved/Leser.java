package LeserSchreiber.StarvationSolved;

public class Leser extends Thread {

    int id;
    public Leser(int i) {
        this.id=i;
    }

    @Override
    public void run() {
        while(true){
            try {
                //Setze Sperre auf Storage
                System.out.println("Leser"+id+" will lesen");
                StarvationSolved.mut_readTakt.acquire();
                StarvationSolved.mut_wantread.acquire();
                StarvationSolved.mut_read.acquire();
                StarvationSolved.mut_readCount.acquire();
                StarvationSolved.readCount++;
                if(StarvationSolved.readCount==1) StarvationSolved.mut_write.acquire();
                StarvationSolved.mut_readCount.release();

                //Lese
                StarvationSolved.mut_read.release();
                StarvationSolved.mut_wantread.release();
                StarvationSolved.mut_readTakt.release();
                int n = StarvationSolved.sharedStorage;
                System.out.println("Leser"+id+" liest: "+ n);
                sleep((int)(Math.random()*1000));
                System.out.println("Leser"+id+" hat gelesen");
                //Löse Sperre auf Storage
                StarvationSolved.mut_readCount.acquire();
                StarvationSolved.readCount--;
                if(StarvationSolved.readCount==0) StarvationSolved.mut_write.release();
                StarvationSolved.mut_readCount.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
