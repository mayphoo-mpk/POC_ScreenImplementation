package mayphoo.mpk.poc_screenimplementation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mayphoo.mpk.poc_screenimplementation.R;
import mayphoo.mpk.poc_screenimplementation.adapters.MovieAdapter;

/**
 * Created by User on 11/10/2017.
 */

public class MovieListFragment extends Fragment {

    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies_list, container, false);

        RecyclerView rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        MovieAdapter moviesAdapter = new MovieAdapter(getContext());
        rvMovies.setAdapter(moviesAdapter);
        return view;
    }
}
