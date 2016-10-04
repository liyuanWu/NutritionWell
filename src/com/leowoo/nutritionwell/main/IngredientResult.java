package com.leowoo.nutritionwell.main;

import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leo on 9/28/16.
 */
public class IngredientResult {

    private WeightParser weightParser = new WeightParser();

    String title;
    List<Pair<String, String>> ingredients = new ArrayList<>();
    Map<String, String> category = new HashMap<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public void addIngredient(String ingredient, String weight){
        ingredients.add(new Pair<>(ingredient, weight));
    }

    public void setCategory(String ing, String cat){
        category.put(ing, cat);
    }

    public List<Pair<String, String>> getIngredients() {
        return ingredients;
    }

    public Map<String, Integer> getCategoryWeight(){

        Map<String, Integer> result = new HashMap<>();
        for(Pair<String, String> pair : ingredients){
            String ing = pair.getKey();
            String cat = category.getOrDefault(ing, ing);
            result.put(cat, result.getOrDefault(cat, 0) + weightParser.convertToStandardWeight(pair.getValue()));
        }
        return result;
    }

    public String toString(){
        String result = "Title: " + title + "\n";
        result += "Ingredients:\n";
        for(Pair pair : ingredients){
            result += "\t" + pair.getKey() + "\t" + pair.getValue() + (category.containsKey(pair.getKey()) ? "\t" + category.get(pair.getKey()):"") + "\n";
        }
        return result;
    }

}
