package com.leowoo.nutritionwell.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/10/4.
 */
public class Crawler {

    public IngredientResult collect(CookBook cookBook) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        StringBuffer stringBuffer = new StringBuffer();

        try {
            url = new URL(cookBook.getUrl());
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));


            String line;
            while (( line = br.readLine()) != null) {
                stringBuffer.append(line);
            }

            Pattern titleP = Pattern.compile("<div class=\"name\" id=\"name\">(?: |\\n?)*<h1 class=\"plain\">(.*?)<\\/h1>(?: |\\n?)*<\\/div>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher titleM = titleP.matcher(stringBuffer.toString());
            IngredientResult ingredientResult = new IngredientResult();
            if(titleM.find()){
                ingredientResult.setTitle(titleM.group(1));
            }
            Pattern p = Pattern.compile("<span class=\"ingredient\">((?:.|\\n)*?)<\\/span>(?: |\\n)*<span class=\"weight\">((?:.|\\n)*?)<\\/span>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher m = p.matcher(stringBuffer.toString());
            Pattern p2 = Pattern.compile("<a.*?>(.*?)<\\/a>");
            while (m.find()) {
                String ing = m.group(1);
                Matcher m2 = p2.matcher(ing);
                if(m2.find()){
                    ing = m2.group(1);
                }
                ingredientResult.addIngredient(ing.replace(" ", "").replaceAll("（.*）", "").replaceAll("\\(.*\\)", ""), m.group(2));
            }
            System.out.println(ingredientResult.toString());
            return ingredientResult;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (is != null) try {
                is.close();
            } catch (IOException ignored) {
                ignored.printStackTrace();
            }
        }
        return null;
    }
}
