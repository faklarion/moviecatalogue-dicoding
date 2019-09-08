package com.studio.faisal.moviecatalogue2.fragmentbottomnav;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studio.faisal.moviecatalogue2.R;
import com.studio.faisal.moviecatalogue2.tv.ListTvAdapter;
import com.studio.faisal.moviecatalogue2.tv.Tv;
import com.studio.faisal.moviecatalogue2.tv.TvViewModel;
import java.util.List;


public class FragmentTv extends Fragment {
    private TvViewModel tvViewModel;
    public FragmentTv() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv, container, false);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_category1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMax(200);
        progressDoalog.setMessage("Loading....");
        progressDoalog.setTitle(getResources().getString(R.string.title_tv));
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();;
        tvViewModel = ViewModelProviders.of(getActivity()).get(TvViewModel.class);
        tvViewModel.getMovieDetails().observe(getActivity(), new Observer<List<Tv>>() {
            @Override
            public void onChanged(@Nullable List<Tv> filmList) {
                ListTvAdapter adapter = new ListTvAdapter(getActivity(), filmList);
                recyclerView.setAdapter(adapter);
                progressDoalog.dismiss();
            }
        });
        return view;
    }

}


