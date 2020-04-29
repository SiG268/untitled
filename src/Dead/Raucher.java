package Dead;

public class Raucher extends Thread {
    String item=null;
    String itemPuffer = null;
    Table table=null;
    String myItem = null;
    int threadID;

    public boolean validateItems() {
        System.out.println("Dead.Raucher"+threadID+" will ein Item");
        try {
            DeadMain.itemsOnTable.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Dead.Raucher"+threadID+" darf ein Item nehmen");
        item = table.getItem(); //Irgend ein Item was auf dem Tisch liegt
        if (item != null) {
            if (item.equals(myItem)) {
                if(itemPuffer!=null){
                    table.putItem(itemPuffer);
                    System.out.println("Dead.Raucher"+threadID+" legt ein Item zurück");
                    DeadMain.itemsOnTable.release();
                    itemPuffer=null;

                }
                table.putItem(item); //Legt Item auf den Tisch
                System.out.println("Dead.Raucher"+threadID+" legt ein Item zurück");
                DeadMain.itemsOnTable.release();
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
        System.out.println("Dead.Raucher "+threadID+" faengt an zu rauchen");
        DeadMain.s.release();
        try {
            this.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Dead.Raucher "+threadID+" hoert auf zu rauchen");
    }

    @Override
    public void run() {
        while(true){
           if(validateItems()) {
               rauchen();
           }
        }

    }
}
