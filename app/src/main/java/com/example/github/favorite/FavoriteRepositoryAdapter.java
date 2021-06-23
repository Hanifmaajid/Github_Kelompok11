package com.example.github.favorite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.github.R;
import com.example.github.data.local.entity.Favorite;

import java.util.ArrayList;
import java.util.List;

public class FavoriteRepositoryAdapter extends RecyclerView.Adapter<FavoriteRepositoryAdapter.ViewHolder> {

    private ArrayList<Favorite> favoriteArrayList = new ArrayList<>();
    private OnFavoriteRepoItemClickListener onFavoriteRepoItemClickListener;

    public FavoriteRepositoryAdapter(OnFavoriteRepoItemClickListener onFavoriteRepoItemClickListener) {
        this.onFavoriteRepoItemClickListener = onFavoriteRepoItemClickListener;
    }


    public void setData(List<Favorite> favoriteList){
        favoriteArrayList.clear();
        favoriteArrayList.addAll(favoriteList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView username, language, repoName;
        private final ImageButton deleteFavorite;

        public ViewHolder(View view) {
            super(view);
            username = (TextView) view.findViewById(R.id.tv_nameDetail);
            language = (TextView) view.findViewById(R.id.tv_langDetail);
            repoName = (TextView) view.findViewById(R.id.tv_name);
            deleteFavorite = (ImageButton) view.findViewById(R.id.btn_delete);
        }

        public TextView getLanguage() {
            return language;
        }

        public TextView getRepoName() {
            return repoName;
        }

        public TextView getUsername() {
            return username;
        }

        public ImageButton getDeleteFavorite() {
            return deleteFavorite;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_favorite, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getUsername().setText(favoriteArrayList.get(position).getUsernameFav());
        viewHolder.getLanguage().setText(favoriteArrayList.get(position).getLanguageFav());
        viewHolder.getRepoName().setText(favoriteArrayList.get(position).getRepositoryFav());
        viewHolder.getDeleteFavorite().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFavoriteRepoItemClickListener.onItemClick(favoriteArrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteArrayList.size();
    }
}
