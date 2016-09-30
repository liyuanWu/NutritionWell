package com.leowoo.nutritionwell.main;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leo on 9/28/16.
 */
public class IngredientResult {

    String title;
    List<Pair<String, String>> ingredinents = new ArrayList<>();
    Map<String, String> catagory = new HashMap<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public void addIngredient(String ingredient, String weight){
        ingredinents.add(new Pair<>(ingredient, weight));
    }

    public void setCatagory(String ing, String cat){
        catagory.put(ing, cat);
    }

    public List<Pair<String, String>> getIngredinents() {
        return ingredinents;
    }

    public Map<String, Integer> getCatagoryWeight(){

        Map<String, Integer> result = new HashMap<>();
        for(Pair<String, String> pair : ingredinents){
            String ing = pair.getKey();
            String weight = pair.getValue().replaceAll("\\D+","");
            int weightInt = weight.isEmpty() ? 0 : Integer.parseInt(weight);
            String cat = catagory.getOrDefault(ing, ing);
            result.put(cat, result.getOrDefault(cat, 0) + weightInt);
        }
        return result;
    }

    public String toString(){
        String result = "Title: " + title + "\n";
        result += "Ingredients:\n";
        for(Pair pair : ingredinents){
            result += "\t" + pair.getKey() + "\t" + pair.getValue() + (catagory.containsKey(pair.getKey()) ? "\t" + catagory.get(pair.getKey()):"") + "\n";
        }
        return result;
    }

}
