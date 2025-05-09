package com.flauschcode.broccoli.shopping;

import android.icu.text.SimpleDateFormat;

import androidx.lifecycle.LiveData;


import com.flauschcode.broccoli.recipe.Recipe;
import com.flauschcode.broccoli.recipe.ingredients.Ingredient;
import com.flauschcode.broccoli.recipe.ingredients.IngredientBuilder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class ShoppingListRepository {
    private final ShoppingListItemDAO ShoppingListItemDAO;
    private final Executor executor = Executors.newSingleThreadExecutor();

    @Inject
    public ShoppingListRepository(ShoppingListItemDAO ShoppingListItemDAO) {
        this.ShoppingListItemDAO = ShoppingListItemDAO;
    }

    public LiveData<List<ShoppingListItem>> getShoppingListItems() {
        return ShoppingListItemDAO.findAll();
    }

    public void add(ShoppingListItem shoppingListItem) {
        executor.execute(() -> ShoppingListItemDAO.insert(shoppingListItem));
    }

    public void update(ShoppingListItem shoppingListItem) {
        executor.execute(() -> ShoppingListItemDAO.update(shoppingListItem));
    }

    public void delete(ShoppingListItem shoppingListItem) {
        executor.execute(() -> ShoppingListItemDAO.delete(shoppingListItem));
    }

    public void clearShoppingList() {
        executor.execute(() -> ShoppingListItemDAO.deleteAll());
    }

    public CompletableFuture<Void> add(Recipe recipe) {
        return CompletableFuture.runAsync(() -> {
            String mealId = generateMealId(recipe);
            for (Ingredient ingredient : IngredientBuilder.from(recipe.getIngredients())) {
                add(new ShoppingListItem(ingredient.getText(), ingredient.getQuantity(), recipe.getTitle(), mealId));
            }
        });
    }

    public static String generateMealId(Recipe recipe) {
        String now = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
        return recipe.getRecipeId() + now;
    }
}
