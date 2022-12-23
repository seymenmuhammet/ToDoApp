package com.example.todoapp.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.todoapp.R;
import com.example.todoapp.data.entity.ToDo;
import com.example.todoapp.databinding.FragmentAnaSayfaBinding;
import com.example.todoapp.ui.adapter.ToDoAdapter;
import com.example.todoapp.ui.viewmodel.AnaSayfaViewModel;
import com.example.todoapp.ui.viewmodel.ToDoSaveViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AnaSayfaFragment extends Fragment implements SearchView.OnQueryTextListener {
    private FragmentAnaSayfaBinding binding;
    private AnaSayfaViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_ana_sayfa, container, false);

        binding.setAnaSayfaToolbarBaslik("Yapılacaklar");

        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbarAnaSayfa);

        viewModel.todoList.observe(getViewLifecycleOwner(),toDoArrayList -> {
            ToDoAdapter adapter = new ToDoAdapter(requireContext(),toDoArrayList,viewModel);
            binding.setTodoAdapter(adapter);
        });

        binding.setAnaSayfaFragment(this);


        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu,menu);

                MenuItem item = menu.findItem(R.id.action_ara);
                SearchView searchView = (SearchView) item.getActionView();
                searchView.setOnQueryTextListener(AnaSayfaFragment.this);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        },getViewLifecycleOwner(), Lifecycle.State.RESUMED);

        return binding.getRoot();
    }

    public void fabTikla(View view){
        Navigation.findNavController(view).navigate(R.id.saveGecis);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AnaSayfaViewModel.class);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        viewModel.ara(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        viewModel.ara(newText);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.todoYukle();
    }
}