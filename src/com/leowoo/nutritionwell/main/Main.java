package com.leowoo.nutritionwell.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 9/28/16.
 */
public class Main {

    private Mapper mapper = new Mapper();
    private Daily daily = new Daily();
    private Crawler crawler = new Crawler();

    public void run(){
        CookBook cookBook = new CookBook("http://m.xiachufang.com/recipe/102117068/");
        IngredientResult ingredientResult = crawler.collect(cookBook);
        mapper.map(ingredientResult).forEach((ing, cat) -> ingredientResult.setCategory(ing, cat));
        System.out.println(ingredientResult.toString());
        List<String> unknownCat = new ArrayList<>();
        ingredientResult.getCategoryWeight().forEach((cat, weight) -> {
            if(mapper.isLegalCategory(cat)){
                daily.add(cat, weight);
            }else{
                unknownCat.add(cat);
            }
        });
        System.out.println(daily.toString());
        System.out.println("No category: " + String.join(", ", unknownCat));
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}
