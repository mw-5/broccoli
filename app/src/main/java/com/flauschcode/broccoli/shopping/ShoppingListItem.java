package com.flauschcode.broccoli.shopping;

import com.flauschcode.broccoli.recipe.Recipe;
import com.flauschcode.broccoli.recipe.ingredients.Ingredient;

public class ShoppingListItem {

    private Ingredient ingredient;
    private boolean checked;
    private Recipe recipe;
    private String recipeId;

    public ShoppingListItem(Ingredient ingredient, Recipe recipe, String recipeId, boolean checked) {
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.recipeId = recipeId;
        this.checked = checked;
    }

    public ShoppingListItem(Ingredient ingredient, Recipe recipe, String recipeId) {
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.recipeId = recipeId;
        this.checked = false;
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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }
}
