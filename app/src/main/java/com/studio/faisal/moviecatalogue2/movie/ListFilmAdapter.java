package com.studio.faisal.moviecatalogue2.movie;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.studio.faisal.moviecatalogue2.detail.Desc;
import com.studio.faisal.moviecatalogue2.detail.Deskripsi;
import com.studio.faisal.moviecatalogue2.R;

import java.util.List;

public class ListFilmAdapter extends RecyclerView.Adapter<ListFilmAdapter.MovieViewHolder> {
    Context mCtx;
    List<Film> filmList;
    View mView;

    public ListFilmAdapter(Context mCtx, List<Film> filmList) {
        this.mCtx = mCtx;
        this.filmList = filmList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_row_film, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        holder.textView.setText(filmList.get(position).getTitle());
        holder.tvThn.setText(filmList.get(position).getReleaseDate());
        Picasso.get().load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + filmList.get(position).getPosterPath()).resize(200, 250).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Desc desc = new Desc();
                desc.setName(filmList.get(position).getTitle());
                desc.setYear(filmList.get(position).getReleaseDate());
                desc.setSino(filmList.get(position).getOverview());
                desc.setPhoto(filmList.get(position).getPosterPath());
                desc.setId(filmList.get(position).getId());
                Intent moveWithObjectIntent = new Intent(mCtx, Deskripsi.class);
                moveWithObjectIntent.putExtra(Deskripsi.EXTRA_DESC, desc);
                moveWithObjectIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mCtx.startActivity(moveWithObjectIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView, tvThn;

        public MovieViewHolder(View view) {
            super(view);
            tvThn = view.findViewById(R.id.tv_item_thn);
            imageView = view.findViewById(R.id.img_item_photo);
            textView = view.findViewById(R.id.tv_item_name);
            mView = itemView;
        }
    }
 }
