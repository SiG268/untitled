package Raucherproblem.Starvation_solved;

public class Raucher extends Thread {
    String item;
    String itemPuffer;
    Table table;
    String myItem;
    int threadID;
    //Interner counter um Items neu auszuteilen
    int counter;

    public boolean validateItems() {
        //Hole ein Item von dem Tisch
        item = table.getItem();
        if (item != null) {
            //Raucher hat ein Item
            System.out.println("Raucher"+threadID+" nimmt sich: "+ item);
            //Überprüfung ob das genommene Item der unendlichen Ressource entspricht
            if (item.equals(myItem)) {
                //Item entspricht der unendlichen Ressource
                //Ist noch ein Item im Itempuffer?
                if(itemPuffer!=null){
                    //Lege Item im Itempuffer zurück auf den Tisch
                    table.putItem(itemPuffer);
                    System.out.println("Raucher"+threadID+" legt ein Item zurück");
                    itemPuffer=null;

                }
                //Lege genommenes Item zurück auf den Tisch
                table.putItem(item); //Legt Item auf den Tisch
                System.out.println("Raucher"+threadID+" legt ein Item zurück");
                item=null;
                return false;
            } else {
                if (itemPuffer == null) {
                    itemPuffer = item;
                    if (validateItems()) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }
    //Konstruktor
    public Raucher(Table table, String myItem, int threadID){
        this.table=table;
        this.myItem=myItem;
        this.threadID=threadID;
        counter=0;
    }

    public void rauchen() {
        //Counter zurücksetzen wenn Raucher rauchen kann
        counter=0;
        //genommene Ressourcen verbrauchen
        item=null;
        itemPuffer=null;
        System.out.println("Raucher "+threadID+" faengt an zu rauchen");
        //Dealer kann wieder neue Items hinlegen
        StarvationSolvedMain.dealerSperre.release();
        try {
            //Zeit zum rauchen
            this.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Raucher "+threadID+" hoert auf zu rauchen");
    }
    @Override
    public void run() {
        while(true){
            try {
                this.sleep(100);
                System.out.println("Raucher"+threadID+" will Items");
                //Sperre die Items für andere Threads
                StarvationSolvedMain.itemsOnTable.acquire();
                if(validateItems()) {
                    rauchen();
                } else {
                    //Wenn Items nicht passen wird der Counter erhöht
                    counter++;
                    //Wenn der Counter 2 erreicht hat (Ein Raucher konnte zwei mal mit den Items nichts anfangen)
                    // werden die Items gelöscht und der Dealer darf neue Items austeielen
                    if (counter >= 2) {
                        item = null;
                        itemPuffer = null;
                        StarvationSolvedMain.dealerSperre.release();
                    }
                    else {
                        //Gib die Items wieder frei
                        StarvationSolvedMain.itemsOnTable.release();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
