package com.studio.faisal.moviecatalogue2.favoritemovie;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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

public class FavMovieAdapter extends RecyclerView.Adapter<FavMovieAdapter.FavMovieViewHolder> {
    private Context mContext;
    private Cursor movieList;
    View mView;

    public FavMovieAdapter(Context mContext, Cursor movieList){
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @Override
    public FavMovieAdapter.FavMovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_row_film, viewGroup, false);

        return new FavMovieViewHolder(view);
    }
    private DataMovie getItem(int i){
        if (!movieList.moveToPosition(i)) {
            throw new IllegalStateException("Position invalid");
        }
        return new DataMovie(movieList);
    }
    public void setListMovie(Cursor movieList) {
        this.movieList = movieList;
    }


    @Override
    public void onBindViewHolder(final FavMovieAdapter.FavMovieViewHolder viewHolder, final int i){
        final DataMovie item = getItem(i);
        viewHolder.title.setText(item.getJudul());
        viewHolder.tanggal.setText(item.getTanggal());
        String poster = item.getPoster();
        Picasso.get().load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + poster).resize(200, 250).into(viewHolder.thumbnail);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Desc desc = new Desc();
                desc.setName(item.getJudul());
                desc.setYear(item.getTanggal());
                desc.setSino(item.getSinopsis());
                desc.setPhoto(item.getPoster());
                desc.setId(item.getId());
                Intent moveWithObjectIntent = new Intent(mContext, Deskripsi.class);
                moveWithObjectIntent.putExtra(Deskripsi.EXTRA_DESC, desc);
                mContext.startActivity(moveWithObjectIntent);
            }
        });
    }

    @Override
    public int getItemCount(){
        if (movieList == null) return 0;
        return movieList.getCount();
    }

    public class FavMovieViewHolder extends RecyclerView.ViewHolder{
        public TextView title, tanggal;
        public ImageView thumbnail;

        public FavMovieViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.tv_item_name);
            tanggal = (TextView) view.findViewById(R.id.tv_item_thn);
            thumbnail = (ImageView) view.findViewById(R.id.img_item_photo);
            mView = itemView;
        }
    }
}
