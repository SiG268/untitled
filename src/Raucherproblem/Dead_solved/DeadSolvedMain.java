package Raucherproblem.Dead_solved;

import java.util.concurrent.Semaphore;

public class DeadSolvedMain {
    //Sperre für den Dealer (Items werden nur auf den Tisch gelegt wenn die vorherigen verbraucht wurden)
    public static Semaphore dealerSperre = new Semaphore(1);
    //Item Sperre um gleichzeitigen Zugriff zu verhindern
    public static Semaphore itemsOnTable = new Semaphore(0);

    public static void main(String[] args) {

        //Tisch, Dealer und Raucher erstellen
        Table table = new Table();
        Raucher r1 =  new Raucher(table, "Streichholz",1);
        Raucher r2 =  new Raucher(table, "Tabak",2);
        Raucher r3 =  new Raucher(table, "Papier",3);
        Dealer d =  new Dealer(table);

        //Raucher und Dealer Thread starten
        d.start();
        r1.start();
        r2.start();
        r3.start();



    }


}

