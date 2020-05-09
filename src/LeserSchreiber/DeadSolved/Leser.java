package LeserSchreiber.DeadSolved;


import LeserSchreiber.Dead.Dead;

public class Leser extends Thread {

    int id;
    public Leser(int i) {
        this.id=i;
    }

    @Override
    public void run() {
        while(true){
            try {
                //Nehmen der warteschlange
                Dead.mut_queue.acquire();
                //Setze Sperre auf Storage
                Dead.mut_readCount.acquire();
                Dead.readCount++;
                if(Dead.readCount==1) Dead.mut_writeStorage.acquire();
                //Lasse Warteschlange los
                Dead.mut_queue.release();
                Dead.mut_readCount.release();

                //Lese
                int n = Dead.sharedStorage;
                System.out.println("Leser"+id+" liest: "+ n);
                sleep((int)(Math.random()*1000));
                //Löse Sperre auf Storage
                Dead.mut_readCount.acquire();
                Dead.readCount--;
                if(Dead.readCount==0) Dead.mut_writeStorage.release();
                Dead.mut_readCount.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
