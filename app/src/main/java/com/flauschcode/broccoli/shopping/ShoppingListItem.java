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

    private boolean checked;

    private String ingredientName;
    private String quantity;
    private String recipeTitle;
    private String mealId;

    public ShoppingListItem(String ingredientName, String quantity, String recipeTitle, String mealId, boolean checked) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.recipeTitle = recipeTitle;
        this.mealId = mealId;
        this.checked = checked;
    }

    @Ignore
    public ShoppingListItem(String ingredientName, String quantity, String recipeTitle, String mealId) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
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

    public String getText() {
        return quantity + ingredientName;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public boolean equals(Object o) {
        return o.getClass() == ShoppingListItem.class
                && ((ShoppingListItem) o).id == this.id
                && ((ShoppingListItem)o).isChecked() == this.isChecked();
    }

}
