package com.leowoo.nutritionwell.main;

import javafx.util.Pair;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by leo on 9/29/16.
 */
public class Mapper {

    Map<String, String> map = new HashMap<>();
    Set<String> legalCats = new HashSet<>();
    public Mapper(){
        try {
            Files.lines(Paths.get(ClassLoader.getSystemResource("com/leowoo/nutritionwell/main/mapper.conf").toURI())).forEach(line -> {
                String[] words = line.split(";");
                String key = words[0];
                String cat = key.substring(0, key.indexOf("/"));
                legalCats.add(cat);
                map.put(cat, cat);
                if(words.length > 1){
                    for(int i=1;i<words.length;i++){
                        map.put(words[i], cat);
                    }
                }
            });
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public Set<String> getAllCategory(){
        return legalCats;
    }

    public Map<String, String> map(IngredientResult ingredientResult){
        Map<String, String> result = new HashMap<>();
        for(Pair<String, String> pair : ingredientResult.getIngredients()){
            String ing = pair.getKey();
            String key = getKey(ing);
            result.put(ing, key);
        }
        return result;
    }

    public boolean isLegalCategory(String cat){
        return legalCats.contains(cat);
    }

    private String getKey(String ing){
        return map.getOrDefault(ing, ing);
    }
}
