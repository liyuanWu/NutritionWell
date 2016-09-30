package com.leowoo.nutritionwell.main;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leo on 9/29/16.
 */
public class Daily {

    private Map<String, Integer> map = new HashMap<>();

    public void add(String cat, int weight){
        map.put(cat, map.getOrDefault(cat, 0) + weight);
    }

    public String toString(){
        String allResults = "";
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            String cat = entry.getKey();
            int wei = entry.getValue();
            int count = wei / 5 ;
            String result = "";
            for(int i=0;i<count;i++){
                result+="+";
            }
            allResults += cat + "\t|" + result + "\t" + wei +"g\n";
        };
        return allResults;
    }
}
