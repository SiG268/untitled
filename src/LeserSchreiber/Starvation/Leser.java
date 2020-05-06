package LeserSchreiber.Starvation;

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
                Starvation.mut_readCount.acquire();
                Starvation.readCount++;
                if(Starvation.readCount==1) Starvation.mut_writeStorage.acquire();
                Starvation.mut_readCount.release();

                //Lese
                Starvation.mut_writeStorage.acquire();
                int n = Starvation.sharedStorage;
                System.out.println("Leser"+id+" liest: "+ n);
                sleep((int)(Math.random()*1000));

                //Löse Sperre auf Storage
                Starvation.mut_readCount.acquire();
                Starvation.readCount--;
                if(Starvation.readCount==0) Starvation.mut_writeStorage.release();
                Starvation.mut_readCount.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
