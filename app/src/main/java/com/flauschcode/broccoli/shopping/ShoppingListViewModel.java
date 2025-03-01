package com.flauschcode.broccoli.shopping;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;


public class ShoppingListViewModel extends ViewModel {

    private final ShoppingListRepository shoppingListRepository;
    public LiveData<List<ShoppingListItem>> shoppingListItems;

    @Inject
    public ShoppingListViewModel(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
        shoppingListItems = shoppingListRepository.getShoppingListItems();
    }

    public void updateShoppingListItem(ShoppingListItem item, boolean isChecked) {
        item.setChecked(isChecked);
        shoppingListRepository.update(item);
    }

    public LiveData<List<ShoppingListItem>> getShoppingListItems() {
        return shoppingListRepository.getShoppingListItems();
    }

    public void add(ShoppingListItem shoppingListItem) {
        shoppingListRepository.add(shoppingListItem);
    }

    public void delete(ShoppingListItem shoppingListItem) {
        shoppingListRepository.delete(shoppingListItem);
    }
}