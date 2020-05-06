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
                StarvationSolved.mut_readCount.acquire();
                StarvationSolved.readCount++;
                if(StarvationSolved.readCount==1) StarvationSolved.mut_writeStorage.acquire();
                StarvationSolved.mut_readCount.release();

                //Lese
                StarvationSolved.mut_writeStorage.acquire();
                int n = StarvationSolved.sharedStorage;
                System.out.println("Leser"+id+" liest: "+ n);
                sleep((int)(Math.random()*1000));

                //Löse Sperre auf Storage
                StarvationSolved.mut_readCount.acquire();
                StarvationSolved.readCount--;
                if(StarvationSolved.readCount==0) StarvationSolved.mut_writeStorage.release();
                StarvationSolved.mut_readCount.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
