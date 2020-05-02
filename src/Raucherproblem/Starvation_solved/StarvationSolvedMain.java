package Raucherproblem.Starvation_solved;

import java.util.concurrent.Semaphore;

public class StarvationSolvedMain {

    public static Semaphore s = new Semaphore(1);
    public static Semaphore itemsOnTable = new Semaphore(0);

    public static void main(String[] args) {

        //Tisch
        Table table = new Table();
        Raucher r1 =  new Raucher(table, "Streichholz",1);
        Raucher r2 =  new Raucher(table, "Tabak",2);
        Raucher r3 =  new Raucher(table, "Pepps",3);
        Dealer d =  new Dealer(table);


        d.start();
        r1.start();
        r2.start();
        r3.start();



    }


}

