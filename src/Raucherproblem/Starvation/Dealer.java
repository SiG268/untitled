package Raucherproblem.Starvation;

public class Dealer extends Thread{
    Table table=null;
    String item1=null;
    String item2=null;
    private String[] auswahl={"Streichholz","Papier","Tabak"};

    public void randomItem(){
        int i=(int)(Math.random()*3);
        switch(i){
            case 0:
                item1="Streichholz";
                item2="Papier";
                break;
            case 1:
                item1="Streichholz";
                item2="Tabak";
                break;
            case 2:
                item1="Papier";
                item2="Tabak";
                break;
        }
    }

    public void putItemsOnTable(){
        randomItem();
        System.out.println("Raucherproblem.Dead.Dealer dealt: "+item1+", "+item2);
        table.putItem(item1);
        table.putItem(item2);
        StarvationMain.itemsOnTable.release();


    }

    public Dealer(Table table){
        this.table=table;
    }
    @Override
    public void run() {
        while(true) {
            try {
                StarvationMain.dealerSperre.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            putItemsOnTable();
        }
    }
}
