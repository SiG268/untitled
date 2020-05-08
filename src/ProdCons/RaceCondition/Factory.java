package ProdCons.RaceCondition;

public class Factory {
    //Produktpalette
    private String[]products = {"Holz", "Stein", "Gold", "Silber"};

    //Wähle ein zufälliges Item aus der Produktpalette aus
    private String randomItem(){
        int i =(int)(Math.random()*products.length);
        return products[i];
    }

    //Erstrelle ein zufälliges Item und gebe dieses zurück
    public String produce(){
        String item = randomItem();
        //System.out.println("Factory(prod): "+item);
        return item;
    }

    //Setzte die Obejektreferenz von übergebenen Objekt auf null
    public void consum(String item){
        //System.out.println("Factory(cons): "+item);
        item=null;
    }
}
