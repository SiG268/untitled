package LeserSchreiberNew.Dead;



public class Leser extends Thread {

    int id;
    public Leser(int i) {
        this.id=i;
    }

    @Override
    public void run() {
        while(true){
            try {
                
                //Dead.mut_arbeiten.acquire();
                Dead.ss1.acquire();
                int n = Dead.sharedStorage1;
                System.out.println("Leser"+id+" liest: "+ n);
                Dead.ss2.acquire();
                Dead.sharedStorage2 = n;
                Dead.ss2.release();
                Dead.ss1.release();
                sleep((int)(Math.random()*1000));

                //Dead.mut_arbeiten.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
