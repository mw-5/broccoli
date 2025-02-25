package com.flauschcode.broccoli;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.flauschcode.broccoli.category.Category;
import com.flauschcode.broccoli.category.CategoryDAO;
import com.flauschcode.broccoli.recipe.CoreRecipe;
import com.flauschcode.broccoli.recipe.CoreRecipeFts;
import com.flauschcode.broccoli.recipe.RecipeCategoryAssociation;
import com.flauschcode.broccoli.recipe.RecipeDAO;
import com.flauschcode.broccoli.shopping.ShoppingListItem;
import com.flauschcode.broccoli.shopping.ShoppingListItemDAO;

@Database(
        version = 4,
        entities = {
                CoreRecipe.class,
                Category.class,
                RecipeCategoryAssociation.class,
                CoreRecipeFts.class,
                ShoppingListItem.class
        },
        autoMigrations = {
                @AutoMigration(from = 1, to = 2),
                @AutoMigration(from = 2, to = 3),
                @AutoMigration(from = 3, to = 4)
        }
)
public abstract class BroccoliDatabase extends RoomDatabase {

    public abstract RecipeDAO recipeDAO();
    public abstract CategoryDAO categoryDAO();
    public abstract ShoppingListItemDAO shoppingListItemDAO();

}
