package com.flauschcode.broccoli.shopping;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.flauschcode.broccoli.recipe.ingredients.Ingredient;

import java.io.Serializable;

@Entity(tableName = "shopping_list_items")
public class ShoppingListItem implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @TypeConverters(IngredientConverter.class)
    private Ingredient ingredient;
    private boolean checked;

    private String recipeTitle;
    private String mealId;

    public ShoppingListItem(Ingredient ingredient, String recipeTitle, String mealId, boolean checked) {
        this.ingredient = ingredient;
        this.recipeTitle = recipeTitle;
        this.mealId = mealId;
        this.checked = checked;
    }

    @Ignore
    public ShoppingListItem(Ingredient ingredient, String recipeTitle, String mealId) {
        this.ingredient = ingredient;
        this.recipeTitle = recipeTitle;
        this.mealId = mealId;
        this.checked = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public boolean equals(Object o) {
        return o.getClass() == ShoppingListItem.class
                && ((ShoppingListItem) o).id == this.id
                && ((ShoppingListItem)o).isChecked() == this.isChecked();
    }

}
