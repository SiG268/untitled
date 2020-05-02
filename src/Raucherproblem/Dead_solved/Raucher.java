package Raucherproblem.Dead_solved;

public class Raucher extends Thread {
    String item=null;
    String itemPuffer = null;
    Table table=null;
    String myItem = null;
    int threadID;

    public boolean validateItems() {
        //Solved (removed semaphore from items)
        item = table.getItem(); //Irgend ein Item was auf dem Tisch liegt
        System.out.println("Raucher"+threadID+" nimmt sich: "+ item);
        if (item != null) {
            if (item.equals(myItem)) {
                if(itemPuffer!=null){
                    table.putItem(itemPuffer);
                    System.out.println("Raucherproblem.Dead.Raucher"+threadID+" legt ein Item zurück");
                    //Solved (removed semaphore from items)
                    itemPuffer=null;

                }
                table.putItem(item); //Legt Item auf den Tisch
                System.out.println("Raucherproblem.Dead.Raucher"+threadID+" legt ein Item zurück");
                //Solved (removed semaphore from items)
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

    public Raucher(Table table, String myItem, int threadID){
        this.table=table;
        this.myItem=myItem;
        this.threadID=threadID;

    }

    public void rauchen() {
        item=null;
        itemPuffer=null;
        System.out.println("Raucherproblem.Dead.Raucher "+threadID+" faengt an zu rauchen");
        DeadSolvedMain.s.release();
        try {
            this.sleep(10000);  //Delay erhöht für erkentlichkeit
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Raucherproblem.Dead.Raucher "+threadID+" hoert auf zu rauchen");
    }

    @Override
    public void run() {
        while(true){
            System.out.println("Raucherproblem.Dead.Raucher"+threadID+" will Items"); //Sysout hierher bewegt
            try {
                //Solved
                DeadSolvedMain.itemsOnTable.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(validateItems()) {
                rauchen();
           }
            //Solved
            else {
                DeadSolvedMain.itemsOnTable.release();
            }
        }
    }
}
