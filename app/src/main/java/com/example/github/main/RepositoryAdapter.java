package com.example.github.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.github.R;
import com.example.github.data.network.response.Repo;

public class RepositoryAdapter extends ArrayAdapter<Repo> {

    public RepositoryAdapter(@NonNull Context context) {
        super(context, 0);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_repository, parent, false);
        }

        TextView repoName = view.findViewById(R.id.tv_repoName);
        TextView repoLang = view.findViewById(R.id.tv_repoLang);
        TextView repoOwner = view.findViewById(R.id.tv_repoOwner);


        Repo item = getItem(position);

        repoName.setText(item.getName());
        if (item.getLanguage() != null){
            repoLang.setText(item.getLanguage().toString());
        }

        repoOwner.setText(item.getOwner().getLogin());
        return view;
    }
}