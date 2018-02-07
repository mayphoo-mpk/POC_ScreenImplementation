package mayphoo.mpk.poc_screenimplementation.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mayphoo.mpk.poc_screenimplementation.R;
import mayphoo.mpk.poc_screenimplementation.activities.MovieDetailActivity;
import mayphoo.mpk.poc_screenimplementation.activities.MovieOverviewActivity;
import mayphoo.mpk.poc_screenimplementation.adapters.MovieAdapter;
import mayphoo.mpk.poc_screenimplementation.components.EmptyViewPod;
import mayphoo.mpk.poc_screenimplementation.components.SmartRecyclerView;
import mayphoo.mpk.poc_screenimplementation.components.SmartScrollListener;
import mayphoo.mpk.poc_screenimplementation.data.models.MovieModel;
import mayphoo.mpk.poc_screenimplementation.data.persistence.MovieContract;
import mayphoo.mpk.poc_screenimplementation.data.vo.MovieVO;
import mayphoo.mpk.poc_screenimplementation.delegates.MovieItemDelegate;
import mayphoo.mpk.poc_screenimplementation.events.RestApiEvents;

/**
 * Created by User on 11/10/2017.
 */

public class MovieListFragment extends Fragment implements MovieItemDelegate, LoaderManager.LoaderCallbacks<Cursor> {

    private static final int MOVIE_LIST_LOADER_ID = 1001;

    @BindView(R.id.rv_movies)
    SmartRecyclerView rvMovies;

    @BindView(R.id.vp_empty_movies)
    EmptyViewPod vpEmptyMovies;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private SmartScrollListener mSmartScrollListener;

    private MovieAdapter moviesAdapter;

    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies_list, container, false);
        ButterKnife.bind(this, view);

       /* MovieModel.getInstance().startLoadingPopularMovies(getContext());*/

        rvMovies.setEmptyView(vpEmptyMovies);
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        moviesAdapter = new MovieAdapter(getContext(), this);
        rvMovies.setAdapter(moviesAdapter);

        mSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReach() {
                //Snackbar.make(rvMovies, "This is all the data.", Snackbar.LENGTH_SHORT).show();
                MovieModel.getInstance().loadMorePopularMovies(getContext());
            }
        });

        rvMovies.addOnScrollListener(mSmartScrollListener);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MovieModel.getInstance().forceRefreshMovies(getContext());
            }
        });

        rvMovies.addOnScrollListener(mSmartScrollListener);

        getActivity().getSupportLoaderManager().initLoader(MOVIE_LIST_LOADER_ID, null, this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        List<MovieVO> movieList = MovieModel.getInstance().getMoives();
        if(!movieList.isEmpty()){
            moviesAdapter.setNewData(movieList);
        } else {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onTapMovieOverview(MovieVO movie) {
        Intent intent = MovieOverviewActivity.newIntent(getContext(), movie);
        startActivity(intent);
    }

    @Override
    public void onTapMovie(MovieVO movie) {
        Intent intent = MovieDetailActivity.newIntent(getContext(), movie);
        startActivity(intent);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                MovieContract.PopularMovieEntry.CONTENT_URI,
                null,
                null, null,
                null);
    }

    //convert cursor object to object format
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data != null && data.moveToFirst()) {
            List<MovieVO> popularMovieList = new ArrayList<>();
            int i = 0;
            do{
                MovieVO popularMovies = MovieVO.parseFromCursor(data);
                popularMovieList.add(popularMovies);
                i++;
            //} while (data.moveToNext() && i < 10);
            } while (data.moveToNext());

            moviesAdapter.setNewData(popularMovieList);
            swipeRefreshLayout.setRefreshing(false);
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPopularMoviesDataLoaded(RestApiEvents.PopularMoviesDataLoadedEvent event){
        //moviesAdapter.appendNewData(event.getLoadedMovies());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvents.ErrorInvokingAPIEvent event){
        Snackbar.make(rvMovies, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();
        swipeRefreshLayout.setRefreshing(false);
    }

}
