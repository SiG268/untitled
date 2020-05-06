package LeserSchreiber.StarvationSolvednew;

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
                StarvationSolvedNew.mut_readCount.acquire();
                StarvationSolvedNew.readCount++;
                if(StarvationSolvedNew.readCount==1) StarvationSolvedNew.mut_writeStorage.acquire();
                StarvationSolvedNew.mut_readCount.release();

                //Lese
                int n = StarvationSolvedNew.sharedStorage;
                System.out.println("Leser"+id+" liest: "+ n);
                sleep((int)(Math.random()*1000));

                //Löse Sperre auf Storage
                StarvationSolvedNew.mut_readCount.acquire();
                StarvationSolvedNew.readCount--;
                if(StarvationSolvedNew.readCount==0) StarvationSolvedNew.mut_writeStorage.release();
                StarvationSolvedNew.mut_readCount.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
