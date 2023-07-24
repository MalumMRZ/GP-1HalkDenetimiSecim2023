package com.yusufmirza.halkdenetimisecim2023;

import java.io.Serializable;
import java.util.ArrayList;

public class Chest implements Serializable {

    static ArrayList<Chest> chestArrayList = new ArrayList<>();
    int kemal,tayyip;
    String chestNumber;

    public Chest(int tayyip,int kemal,String chestNumber) {
        this.kemal=kemal;
        this.tayyip= tayyip;
        this.chestNumber=chestNumber;

    }
    public static void addChestToList(Chest chest) {
        chestArrayList.add(chest);

    } public static ArrayList<Chest> getChestList(){
        return chestArrayList;
    }

      public static ArrayList<Chest> setChestList(ArrayList<Chest> chestArrayList1) {
        chestArrayList= chestArrayList1;
        return chestArrayList;
      }
      public static  ArrayList<Chest> deleteItem(ArrayList<Chest> chestArrayList,int position){
        chestArrayList.remove(position);
        return chestArrayList;
    }
}
