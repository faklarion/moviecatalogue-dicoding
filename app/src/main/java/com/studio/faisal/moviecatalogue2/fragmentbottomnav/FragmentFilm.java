package com.studio.faisal.moviecatalogue2.fragmentbottomnav;


import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studio.faisal.moviecatalogue2.R;
import com.studio.faisal.moviecatalogue2.movie.Film;
import com.studio.faisal.moviecatalogue2.movie.FilmViewModel;
import com.studio.faisal.moviecatalogue2.movie.ListFilmAdapter;
import java.util.List;


public class FragmentFilm extends Fragment {
    private FilmViewModel filmViewModel;
    public FragmentFilm() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_film, container, false);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMax(200);
        progressDoalog.setMessage("Loading....");
        progressDoalog.setTitle(getResources().getString(R.string.title_film));
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();;
        filmViewModel = ViewModelProviders.of(getActivity()).get(FilmViewModel.class);
        filmViewModel.getMovieDetails().observe(getActivity(), new Observer<List<Film>>() {
            @Override
            public void onChanged(@Nullable List<Film> filmList) {
                ListFilmAdapter adapter = new ListFilmAdapter(getActivity(), filmList);
                recyclerView.setAdapter(adapter);
                progressDoalog.dismiss();
            }
        });
        return view;
    }
}





