package Raucherproblem.Dead_solved;

import java.util.ArrayList;

public class Table {
    public ArrayList<String> items = new ArrayList<String>();

    //Item vom Tisch nehmen
    public String getItem() {
        String item=null;
        if(items.size()!=0) {
            item = items.get(0);
            items.remove(item);
        }
        return item;
    }

    //Item auf den Tisch legen
    public void putItem(String item) {
        items.add(item);
    }


}
