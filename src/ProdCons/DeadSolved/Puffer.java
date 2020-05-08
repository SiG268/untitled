package ProdCons.DeadSolved;

import java.util.ArrayList;

public class Puffer {
    //LIFO List
    private ArrayList<String>list = new ArrayList<>();

    //Letztes Item wird aus der Liste entfernt
    public String getItem(){
        String item=null;
        if(list.size()!=0){
            item=list.get(list.size()-1);
            list.remove(list.size()-1);
        }
        return item;
    }

    //Item wird an letzter Stelle in der Liste eingefügt
    public void putItem(String i){
        list.add(list.size(),i);
    }

    //Lagerausgabe als String
    public String toString(){
        String s ="Items: ";
        for(String i:list){
            s=s+i+" ";
        }
        return s;
    }
}
