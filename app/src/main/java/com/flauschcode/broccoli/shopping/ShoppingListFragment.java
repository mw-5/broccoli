package com.flauschcode.broccoli.shopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.flauschcode.broccoli.R;
import com.flauschcode.broccoli.databinding.FragmentShoppingListBinding;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class ShoppingListFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ShoppingListViewModel viewModel;

    private FragmentShoppingListBinding binding;

    public static ShoppingListFragment newInstance() {
        return new ShoppingListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentShoppingListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Toolbar toolbar = binding.toolbarShoppingList;
        //((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        ShoppingListAdapter adapter = setUpAdapter();
        binding.recyclerViewShoppingList.setAdapter(adapter);
        binding.recyclerViewShoppingList.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new ViewModelProvider(this, viewModelFactory).get(ShoppingListViewModel.class);
        viewModel.getShoppingListItems().observe(getViewLifecycleOwner(), adapter::submitList);

        return view;
    }

    private ShoppingListAdapter setUpAdapter() {
        return new ShoppingListAdapter((item, isChecked) -> viewModel.updateShoppingListItem(item, isChecked));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @Nullable@NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_shopping_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_shopping_list_clear) {
            viewModel.clearShoppingList();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}