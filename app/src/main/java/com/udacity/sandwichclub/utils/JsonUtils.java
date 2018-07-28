package com.udacity.sandwichclub.utils;

import android.support.annotation.Nullable;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    @Nullable
    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            String mainName = ((JSONObject)jsonObject.get("name")).getString("mainName");

            JSONArray alsoKnownAsJsonArray =  ((JSONObject)jsonObject.get("name")).getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = stringJsonArrayToList(alsoKnownAsJsonArray);

            String placeOfOrigin = jsonObject.getString("placeOfOrigin");
            String description = jsonObject.getString("description");
            String image = jsonObject.getString("image");

            JSONArray ingredientsJsonArray =  jsonObject.getJSONArray("ingredients");
            List<String> ingredients = stringJsonArrayToList(ingredientsJsonArray);


           return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<String> stringJsonArrayToList(JSONArray jsonArray) throws JSONException {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            stringList.add(jsonArray.getString(i));
        }
        return stringList;
    }

}
