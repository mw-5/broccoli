package com.flauschcode.broccoli.shopping;

import androidx.lifecycle.LiveData;


import com.flauschcode.broccoli.recipe.Recipe;
import com.flauschcode.broccoli.recipe.ingredients.Ingredient;
import com.flauschcode.broccoli.recipe.ingredients.IngredientBuilder;

import java.util.List;

public class ShoppingListRepository {
    private final ShoppingListItemDAO ShoppingListItemDAO;

    public ShoppingListRepository(ShoppingListItemDAO ShoppingListItemDAO) {
        this.ShoppingListItemDAO = ShoppingListItemDAO;
    }

    public LiveData<List<ShoppingListItem>> getShoppingListItems() {
        return ShoppingListItemDAO.findAll();
    }

    public void add(ShoppingListItem shoppingListItem) {
        ShoppingListItemDAO.insert(shoppingListItem);
    }

    public void update(ShoppingListItem shoppingListItem) {
        ShoppingListItemDAO.update(shoppingListItem);
    }

    public void delete(ShoppingListItem shoppingListItem) {
        ShoppingListItemDAO.delete(shoppingListItem);
    }

    public void add(Recipe recipe) {
        for (Ingredient ingredient : IngredientBuilder.from(recipe.getIngredients())) {
            add(new ShoppingListItem(ingredient, recipe.getTitle(), "TEST"));
        }
    }
}
