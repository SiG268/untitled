package ProdCons.StarvationSolved;

public class Factory {

    private String[]products = {"Holz", "Stein", "Gold", "Silber"};

    private String randomItem(){
        int i =(int)(Math.random()*products.length);
        return products[i];
    }

    public String produce(){
        String item = randomItem();
        //System.out.println("Factory(prod): "+item);
        return item;
    }

    public void consum(String item){
        //System.out.println("Factory(cons): "+item);
        item=null;
    }
}
