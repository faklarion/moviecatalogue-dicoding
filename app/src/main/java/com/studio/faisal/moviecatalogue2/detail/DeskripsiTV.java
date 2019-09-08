package com.studio.faisal.moviecatalogue2.detail;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.squareup.picasso.Picasso;
import com.studio.faisal.moviecatalogue2.R;
import com.studio.faisal.moviecatalogue2.favoritetv.DataTV;
import com.studio.faisal.moviecatalogue2.favoritetv.DatabaseContract;
import com.studio.faisal.moviecatalogue2.favoritetv.DatabaseHelper;

public class DeskripsiTV extends AppCompatActivity {
    TextView tvObject, sino;
    ImageView img;
    String text, text2;
    String judul, tanggal, poster;
    Integer movie_id;
    private SQLiteDatabase mDb;
    DatabaseHelper databaseHelper;
    public static final String EXTRA_DESC = "extra_desc";
    private final AppCompatActivity activity = DeskripsiTV.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi);
        tvObject = findViewById(R.id.tv_object_received);
        sino = findViewById(R.id.textsino);
        img = findViewById(R.id.img_post);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        mDb = dbHelper.getWritableDatabase();
        Desc desc = getIntent().getParcelableExtra(EXTRA_DESC);
        judul = desc.getName();
        tanggal = desc.getYear();
        poster = desc.getPhoto();
        movie_id = desc.getId();
        text = String.format(getResources().getString(R.string.judulnya))+" "+ desc.getName() + "\n\n"+String.format(getResources().getString(R.string.taun))+" "+ desc.getYear();
        text2 = desc.getSino();
        tvObject.setText(text);
        sino.setText(text2);
        Picasso.get().load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + desc.getPhoto()).into(img);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(desc.getName());
        MaterialFavoriteButton materialFavoriteButton = (MaterialFavoriteButton) findViewById(R.id.favorite_button);
        if (Exists(judul)){
            materialFavoriteButton.setFavorite(true);
            materialFavoriteButton.setOnFavoriteChangeListener(
                    new MaterialFavoriteButton.OnFavoriteChangeListener() {
                        @Override
                        public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                            if (favorite == true) {
                                saveFavorite();
                                Snackbar.make(buttonView, getResources().getString(R.string.addfav),
                                        Snackbar.LENGTH_SHORT).show();
                            } else {
                                databaseHelper = new DatabaseHelper(DeskripsiTV.this);
                                databaseHelper.deleteFavorite(movie_id);
                                Snackbar.make(buttonView, getResources().getString(R.string.remfav),
                                        Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    });


        }else {
            materialFavoriteButton.setOnFavoriteChangeListener(
                    new MaterialFavoriteButton.OnFavoriteChangeListener() {
                        @Override
                        public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                            if (favorite == true) {
                                saveFavorite();
                                Snackbar.make(buttonView, getResources().getString(R.string.addfav),
                                        Snackbar.LENGTH_SHORT).show();
                            } else {
                                int movie_id = getIntent().getExtras().getInt("id");
                                databaseHelper = new DatabaseHelper(DeskripsiTV.this);
                                databaseHelper.deleteFavorite(movie_id);
                                Snackbar.make(buttonView, getResources().getString(R.string.remfav),
                                        Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    });


        }
    }
    public boolean Exists(String searchItem) {

        String[] projection = {
                DatabaseContract.FavoriteMovie._ID,
                DatabaseContract.FavoriteMovie.COLUMN_MOVIEID,
                DatabaseContract.FavoriteMovie.COLUMN_JUDUL,
                DatabaseContract.FavoriteMovie.COLUMN_TANGGAL,
                DatabaseContract.FavoriteMovie.COLUMN_POSTER,
                DatabaseContract.FavoriteMovie.COLUMN_SINOPSIS

        };
        String selection = DatabaseContract.FavoriteMovie.COLUMN_JUDUL+ " =?";
        String[] selectionArgs = { searchItem };
        String limit = "1";
        Cursor cursor = mDb.query(DatabaseContract.FavoriteMovie.TABLE_NAME, projection, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public void saveFavorite(){
        databaseHelper = new DatabaseHelper(activity);
        DataTV favorite = new DataTV();

        favorite.setId(movie_id);
        favorite.setJudul(judul);
        favorite.setPoster(poster);
        favorite.setTanggal(tanggal);
        favorite.setSinopsis(text2);

        databaseHelper.addFavorite(favorite);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
