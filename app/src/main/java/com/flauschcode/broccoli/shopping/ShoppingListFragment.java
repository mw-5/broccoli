package com.flauschcode.broccoli.shopping;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flauschcode.broccoli.databinding.FragmentShoppingListBinding;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class ShoppingListFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ShoppingListViewModel viewModel;

    private FragmentShoppingListBinding binding;
    private ShoppingListAdapter adapter;

    public static ShoppingListFragment newInstance() {
        return new ShoppingListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentShoppingListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        ShoppingListAdapter adapter = setUpAdapter();
        binding.recyclerViewShoppingList.setAdapter(adapter);
        binding.recyclerViewShoppingList.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new ViewModelProvider(this, viewModelFactory).get(ShoppingListViewModel.class);
        viewModel.getShoppingListItems().observe(getViewLifecycleOwner(), adapter::submitList);

        return view;
    }

    private ShoppingListAdapter setUpAdapter() {
        adapter = new ShoppingListAdapter((item, isChecked) -> {
            item.setChecked(isChecked);
            viewModel.updateShoppingListItem(item);
            adapter.submitList(viewModel.getShoppingListItems().getValue());
        });
        return adapter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}