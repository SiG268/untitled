package ProdCons.Dead;

import java.util.ArrayList;

public class Puffer {
    //LIFO List
    private ArrayList<String>list = new ArrayList<>();

    public String getItem(){
        String item=null;
        if(list.size()!=0){
            item=list.get(list.size()-1);
            list.remove(list.size()-1);              //removes last item from list
        }
        return item;
    }

    public void putItem(String i){
        list.add(list.size(),i);            //set item on last possible index
    }

    public String toString(){
        String s ="Items: ";
        for(String i:list){
            s=s+i+" ";
        }
        return s;
    }
}
