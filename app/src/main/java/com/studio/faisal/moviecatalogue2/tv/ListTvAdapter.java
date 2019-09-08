package com.studio.faisal.moviecatalogue2.tv;

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
import com.studio.faisal.moviecatalogue2.detail.DeskripsiTV;
import com.studio.faisal.moviecatalogue2.R;
import java.util.List;

public class ListTvAdapter extends RecyclerView.Adapter<ListTvAdapter.TvViewHolder> {
    Context mCtx;
    List<Tv> filmList;
    View mView;

    public ListTvAdapter(Context mCtx, List<Tv> filmList) {
        this.mCtx = mCtx;
        this.filmList = filmList;
    }

    @NonNull
    @Override
    public ListTvAdapter.TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_row_film, parent, false);
        return new ListTvAdapter.TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTvAdapter.TvViewHolder holder, final int position) {
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
                Intent moveWithObjectIntent = new Intent(mCtx, DeskripsiTV.class);
                moveWithObjectIntent.putExtra(DeskripsiTV.EXTRA_DESC, desc);
                moveWithObjectIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mCtx.startActivity(moveWithObjectIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    class TvViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView, tvThn;

        public TvViewHolder(View view) {
            super(view);
            tvThn = view.findViewById(R.id.tv_item_thn);
            imageView = view.findViewById(R.id.img_item_photo);
            textView = view.findViewById(R.id.tv_item_name);
            mView = itemView;
        }
    }
}
