package LeserSchreiber.DeadSolved;


public class Leser extends Thread {

    int id;
    public Leser(int i) {
        this.id=i;
    }

    @Override
    public void run() {
        while(true){
            try {
                DeadSolved.mut_queue.acquire();
                //Setze Sperre auf Storage
                DeadSolved.mut_readCount.acquire();
                DeadSolved.readCount++;
                if(DeadSolved.readCount==1) DeadSolved.mut_writeStorage.acquire();
                DeadSolved.mut_queue.release();
                DeadSolved.mut_readCount.release();

                //Lese
                int n = DeadSolved.sharedStorage;
                System.out.println("Leser"+id+" liest: "+ n);
                sleep((int)(Math.random()*1000));
                //Löse Sperre auf Storage
                DeadSolved.mut_readCount.acquire();
                DeadSolved.readCount--;
                if(DeadSolved.readCount==0) DeadSolved.mut_writeStorage.release();
                DeadSolved.mut_readCount.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
