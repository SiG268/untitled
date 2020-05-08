package Raucherproblem.RaceCondition;

public class Dealer extends Thread{
    Table table;
    String item1;
    String item2;

    //Auswählen von zwei random Items
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

    //2 Neue Items auf den Tisch legen
    public void putItemsOnTable(){
        randomItem();
        System.out.println("Dealer dealt: "+item1+", "+item2);
        table.putItem(item1);
        table.putItem(item2);
        System.out.println("Items on Table: "+table.items.size());
    }

    //Konstruktor
    public Dealer(Table table){
        this.table=table;
    }

    @Override
    public void run() {
        while(true) {
            try {
                //Versuche Items auf den Tisch zu legen
                RaceMain.dealerSperre.acquire();
                putItemsOnTable();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
