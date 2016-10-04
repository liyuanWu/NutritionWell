package com.leowoo.nutritionwell.main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/4.
 */
public class NutritionStandard {

    private WeightParser weightParser = new WeightParser();
    private Map<String, Integer> standardWeights = new HashMap<>();

    public NutritionStandard(){
        try {
            Files.lines(Paths.get(ClassLoader.getSystemResource("com/leowoo/nutritionwell/main/mapper.conf").toURI())).forEach(line -> {
                String[] words = line.split(";");
                String[] keys = words[0].split("/");
                String cat = keys[0];
                String standard = keys[1];
                int weight = weightParser.convertToStandardWeight(standard);
                standardWeights.put(cat, weight);
            });
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public int getStandardWeight(String cat){
        return standardWeights.get(cat);
    }

}
