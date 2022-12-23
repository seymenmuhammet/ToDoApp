package com.example.todoapp.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.data.entity.ToDo;
import com.example.todoapp.databinding.CardTasarimBinding;
import com.example.todoapp.ui.fragment.AnaSayfaFragment;
import com.example.todoapp.ui.fragment.AnaSayfaFragmentDirections;
import com.example.todoapp.ui.viewmodel.AnaSayfaViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.CardTasarimTutucu>{
    private Context mContext;
    private List<ToDo> toDoList;
    private AnaSayfaViewModel viewModel;

    public ToDoAdapter(Context mContext, List<ToDo> toDoList, AnaSayfaViewModel viewModel) {
        this.mContext = mContext;
        this.toDoList = toDoList;
        this.viewModel = viewModel;
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private CardTasarimBinding binding;

        public CardTasarimTutucu(CardTasarimBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardTasarimBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.card_tasarim,parent,false);

        return new CardTasarimTutucu(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        ToDo toDo = toDoList.get(position);
        CardTasarimBinding t = holder.binding;
        t.setTodoNesnesi(toDo);

        t.satirCard.setOnClickListener(view -> {
            AnaSayfaFragmentDirections.UpdateGecis gecis = AnaSayfaFragmentDirections.updateGecis(toDo);
            Navigation.findNavController(view).navigate(gecis);
        });

        t.imageViewSil.setOnClickListener(view -> {
            Snackbar.make(view,toDo.getTodo_name()+" silinsin mi ?",Snackbar.LENGTH_LONG)
                    .setAction("EVET",view1 -> {
                        viewModel.sil(toDo.getTodo_id());
                    }).show();
        });
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }


}
