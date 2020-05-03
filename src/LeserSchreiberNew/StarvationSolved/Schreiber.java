package LeserSchreiberNew.StarvationSolved;

public class Schreiber extends Thread{
    int id;
    public Schreiber(int i) {
        this.id =i;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //Leser Sperren solange es noch Schreiber gibt
                RaceCondition.mut_writeCount.acquire();
                RaceCondition.writeCount++;
                if(RaceCondition.writeCount==1){RaceCondition.mut_arbeiten_Leser.acquire();}
                RaceCondition.mut_writeCount.release();

                //Berechnung druchführen (1 Schreiber nach dem anderen)
                RaceCondition.mut_arbeiten_Schreiber.acquire();
                int n = RaceCondition.sharedStorage;
                //Berechnung durchführen
                sleep((int)(Math.random()*1000));
                n++;
                System.out.println("Schreiber"+id+" schreibt:" +n);
                RaceCondition.sharedStorage=n;
                RaceCondition.mut_arbeiten_Schreiber.release();

                //Leser wieder freigeben
                RaceCondition.mut_writeCount.acquire();
                RaceCondition.writeCount--;
                if(RaceCondition.writeCount==0){RaceCondition.mut_arbeiten_Leser.release();}
                RaceCondition.mut_writeCount.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

