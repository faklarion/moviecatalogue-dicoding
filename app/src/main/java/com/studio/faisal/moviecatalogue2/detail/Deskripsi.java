package com.studio.faisal.moviecatalogue2.detail;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.squareup.picasso.Picasso;
import com.studio.faisal.moviecatalogue2.R;
import com.studio.faisal.moviecatalogue2.favoritemovie.DataMovie;
import com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract;
import com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseHelper;
import com.studio.faisal.moviecatalogue2.favoritemovie.MovieHelper;

import static android.provider.BaseColumns._ID;
import static com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract.CONTENT_URI;
import static com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract.FavoriteMovie.COLUMN_JUDUL;
import static com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract.FavoriteMovie.COLUMN_MOVIEID;
import static com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract.FavoriteMovie.COLUMN_POSTER;
import static com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract.FavoriteMovie.COLUMN_SINOPSIS;
import static com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract.FavoriteMovie.COLUMN_TANGGAL;


public class Deskripsi extends AppCompatActivity {
    TextView tvObject, sino;
    ImageView img;
    String text, text2;
    String judul, tanggal, poster;
    Integer movie_id;
    public Context context;
    public MovieHelper helper;
    public DataMovie dataMovie;
    private SQLiteDatabase mDb;
    DatabaseHelper databaseHelper;
    public static final String EXTRA_DESC = "extra_desc";
    private final AppCompatActivity activity = Deskripsi.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi);
        helper = new MovieHelper(this);
        helper.open();
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
        text = String.format(getResources().getString(R.string.judulnya)) + " " + desc.getName() + "\n\n" + String.format(getResources().getString(R.string.taun)) + " " + desc.getYear();
        text2 = desc.getSino();
        tvObject.setText(text);
        sino.setText(text2);
        Picasso.get().load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + desc.getPhoto()).into(img);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(desc.getName());
        MaterialFavoriteButton materialFavoriteButton = (MaterialFavoriteButton) findViewById(R.id.favorite_button);
        if (helper.checkData(movie_id)) {
            materialFavoriteButton.setFavorite(true);
            materialFavoriteButton.setOnFavoriteChangeListener(
                    new MaterialFavoriteButton.OnFavoriteChangeListener() {
                        @Override
                        public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                            if (favorite == true) {
                                ContentValues values = new ContentValues();
                                values.put(_ID,movie_id);
                                values.put(COLUMN_JUDUL,judul);
                                values.put(COLUMN_TANGGAL,tanggal);
                                values.put(COLUMN_POSTER,poster);
                                values.put(COLUMN_SINOPSIS,text2);
                                getContentResolver().insert(CONTENT_URI,values);
                                Snackbar.make(buttonView, getResources().getString(R.string.addfav),
                                        Snackbar.LENGTH_SHORT).show();
                            } else {
                                getContentResolver().delete(Uri.parse(CONTENT_URI + "/" + movie_id),null,null);;
                                Snackbar.make(buttonView, getResources().getString(R.string.remfav),
                                        Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    });


        } else {
            materialFavoriteButton.setOnFavoriteChangeListener(
                    new MaterialFavoriteButton.OnFavoriteChangeListener() {
                        @Override
                        public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                            if (favorite == true) {
                                ContentValues values = new ContentValues();
                                values.put(_ID,movie_id);
                                values.put(COLUMN_JUDUL,judul);
                                values.put(COLUMN_TANGGAL,tanggal);
                                values.put(COLUMN_POSTER,poster);
                                values.put(COLUMN_SINOPSIS,text2);
                                getContentResolver().insert(CONTENT_URI,values);
                                Snackbar.make(buttonView, getResources().getString(R.string.addfav),
                                        Snackbar.LENGTH_SHORT).show();
                            } else {
                                int movie_id = getIntent().getExtras().getInt("id");
                                getContentResolver().delete(Uri.parse(CONTENT_URI + "/" + movie_id),null,null);;
                                Snackbar.make(buttonView, getResources().getString(R.string.remfav),
                                        Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    });


        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static class DataObserver extends ContentObserver {
        final Context context;

        public DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }


    }

}