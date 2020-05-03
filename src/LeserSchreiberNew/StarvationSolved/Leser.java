package LeserSchreiberNew.StarvationSolved;


public class Leser extends Thread {

    int id;
    public Leser(int i) {
        this.id=i;
    }

    @Override
    public void run() {
        while(true){
            try {
                RaceCondition.mut_willLesen.acquire();
                RaceCondition.mut_arbeiten_Leser.acquire();
                //Gemeinsamen Speicher beanspruchen
                RaceCondition.mut_readCount.acquire();
                RaceCondition.readCount++;
                if(RaceCondition.readCount==1){
                    RaceCondition.mut_arbeiten_Schreiber.acquire();}
                RaceCondition.mut_readCount.release();
                RaceCondition.mut_arbeiten_Schreiber.release();
                RaceCondition.mut_willLesen.release();
                //Gemeinsamen Speicher lesen
                sleep((int)(Math.random()*1000));
                int n = RaceCondition.sharedStorage;
                System.out.println("Leser"+id+" liest: "+ n);

                //Gemeinsamen Speicher wieder freigeben
                RaceCondition.mut_readCount.acquire();
                RaceCondition.readCount--;
                if(RaceCondition.readCount==0){
                    RaceCondition.mut_arbeiten_Schreiber.release();}
                RaceCondition.mut_readCount.release();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
