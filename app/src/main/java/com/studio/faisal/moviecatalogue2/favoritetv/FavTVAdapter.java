package com.studio.faisal.moviecatalogue2.favoritetv;

import android.content.Context;
import android.content.Intent;
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

public class FavTVAdapter extends RecyclerView.Adapter<FavTVAdapter.FavTVViewHolder> {
    private Context mContext;
    private List<DataTV> tvList;
    View mView;

    public FavTVAdapter(Context mContext, List<DataTV> tvList){
        this.mContext = mContext;
        this.tvList = tvList;
    }

    @Override
    public FavTVAdapter.FavTVViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_row_film, viewGroup, false);

        return new FavTVAdapter.FavTVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FavTVAdapter.FavTVViewHolder viewHolder, final int i){
        viewHolder.title.setText(tvList.get(i).getJudul());
        viewHolder.tanggal.setText(tvList.get(i).getTanggal());
        String poster = tvList.get(i).getPoster();
        Picasso.get().load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + poster).resize(200, 250).into(viewHolder.thumbnail);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Desc desc = new Desc();
                desc.setName(tvList.get(i).getJudul());
                desc.setYear(tvList.get(i).getTanggal());
                desc.setSino(tvList.get(i).getSinopsis());
                desc.setPhoto(tvList.get(i).getPoster());
                desc.setId(tvList.get(i).getId());
                Intent moveWithObjectIntent = new Intent(mContext, DeskripsiTV.class);
                moveWithObjectIntent.putExtra(DeskripsiTV.EXTRA_DESC, desc);
                mContext.startActivity(moveWithObjectIntent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return tvList.size();
    }

    public class FavTVViewHolder extends RecyclerView.ViewHolder{
        public TextView title, tanggal;
        public ImageView thumbnail;

        public FavTVViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.tv_item_name);
            tanggal = (TextView) view.findViewById(R.id.tv_item_thn);
            thumbnail = (ImageView) view.findViewById(R.id.img_item_photo);
            mView = itemView;
        }
    }
}
