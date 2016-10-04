package com.leowoo.nutritionwell.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2016/10/4.
 */
public class WeightParser {

    private static final Logger logger = LoggerFactory.getLogger(WeightParser.class);

    public int convertToStandardWeight(String weightStr){
        String unit = weightStr.replaceAll("\\d+","");
        String weight = weightStr.replaceAll("\\D+","");
        int weightInt = weight.isEmpty() ? 0 : Integer.parseInt(weight);
        if(!("克".equals(unit) || "g".equals(unit))){
            logger.warn("Legal Unit: " + weightStr);
            return 0;
        }
        return weightInt;
    }
}
