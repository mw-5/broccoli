package com.flauschcode.broccoli.shopping;

import androidx.room.TypeConverter;

import com.flauschcode.broccoli.recipe.ingredients.Ingredient;
import com.flauschcode.broccoli.recipe.ingredients.IngredientBuilder;

public class IngredientConverter {

    @TypeConverter
    public static Ingredient toIngredient(String ingredientString) {
        if (ingredientString == null) {
            return null;
        }
        return IngredientBuilder.from(ingredientString).get(0);
    }

    @TypeConverter
    public static String toIngredientString(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }
        return ingredient.toString();
    }
}