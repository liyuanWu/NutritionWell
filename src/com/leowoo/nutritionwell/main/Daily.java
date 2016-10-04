package com.leowoo.nutritionwell.main;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leo on 9/29/16.
 */
public class Daily {

    private NutritionStandard nutritionStandard = new NutritionStandard();

    private Map<String, Integer> map = new HashMap<>();

    public void add(String cat, int weight){
        map.put(cat, map.getOrDefault(cat, 0) + weight);
    }

    public String toString(){
        String allResults = "";
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            String cat = entry.getKey();
            int wei = entry.getValue();
            int standard = nutritionStandard.getStandardWeight(cat);
            int count = wei / 5 ;
            int standardCount = standard / 5;
            String result = "";
            for(int i=0;i<count;i++){
                if(i < standardCount){
                    result+="=";
                }else{
                    result+="+";
                }
            }
            if(count < standardCount){
                for(int i=count ; i < standardCount; ++i){
                    result += ".";
                }
            }
            allResults += String.format("%4s\t|%s\t%dg (%.2f%%)\n", cat, result, wei, 100.0 * wei / standard);
        }
        return allResults;
    }
}
