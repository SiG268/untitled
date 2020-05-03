package LeserSchreiberNew.Starvation;


public class Leser extends Thread {

    int id;
    public Leser(int i) {
        this.id=i;
    }

    @Override
    public void run() {
        while(true){
            try {
                //Gemeinsamen Speicher beanspruchen
                RaceCondition.mut_readCount.acquire();
                RaceCondition.readCount++;
                if(RaceCondition.readCount==1){
                    RaceCondition.mut_arbeiten.acquire();}
                RaceCondition.mut_readCount.release();

                //Gemeinsamen Speicher lesen
                sleep((int)(Math.random()*1000));
                int n = RaceCondition.sharedStorage;
                System.out.println("Leser"+id+" liest: "+ n);

                //Gemeinsamen Speicher wieder freigeben
                RaceCondition.mut_readCount.acquire();
                RaceCondition.readCount--;
                if(RaceCondition.readCount==0){
                    RaceCondition.mut_arbeiten.release();}
                RaceCondition.mut_readCount.release();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
