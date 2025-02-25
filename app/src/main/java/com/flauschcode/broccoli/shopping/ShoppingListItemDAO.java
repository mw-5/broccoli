package com.flauschcode.broccoli.shopping;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ShoppingListItemDAO {

    @Insert
    void insert(ShoppingListItem shoppingListItem);

    @Update
    void update(ShoppingListItem shoppingListItem);

    @Delete
    void delete(ShoppingListItem shoppingListItem);

    @Query("SELECT * FROM shopping_list_items")
    LiveData<List<ShoppingListItem>> findAll();
}
