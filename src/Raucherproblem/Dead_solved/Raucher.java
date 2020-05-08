package Raucherproblem.Dead_solved;

public class Raucher extends Thread {
    String item;
    String itemPuffer;
    Table table;
    String myItem;
    int threadID;

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
    }

    public void rauchen() {
        //genommene Ressourcen verbrauchen
        item=null;
        itemPuffer=null;
        System.out.println("Raucher "+threadID+" faengt an zu rauchen");
        //Dealer kann wieder neue Items hinlegen
        DeadSolvedMain.dealerSperre.release();
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
                System.out.println("Raucherproblem.Dead.Raucher"+threadID+" will Items");
                //Sperre die Items für andere Threads
                DeadSolvedMain.itemsOnTable.acquire();
                if(validateItems()) {
                    rauchen();
                }
                else {
                    //Gib die Items wieder frei
                    DeadSolvedMain.itemsOnTable.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
