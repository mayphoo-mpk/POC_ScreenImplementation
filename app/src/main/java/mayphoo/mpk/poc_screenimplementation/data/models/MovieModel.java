package mayphoo.mpk.poc_screenimplementation.data.models;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import mayphoo.mpk.poc_screenimplementation.POCMoviesApp;
import mayphoo.mpk.poc_screenimplementation.data.persistence.MovieContract;
import mayphoo.mpk.poc_screenimplementation.data.vo.MovieVO;
import mayphoo.mpk.poc_screenimplementation.events.RestApiEvents;
import mayphoo.mpk.poc_screenimplementation.network.MovieDataAgent;
import mayphoo.mpk.poc_screenimplementation.network.MovieDataAgentImpl;
import mayphoo.mpk.poc_screenimplementation.utils.AppConstants;

/**
 * Created by User on 12/7/2017.
 */

public class MovieModel {

    private static MovieModel objInstance;

    private List<MovieVO> mMovies;
    private int mMoviesPageIndex = 1;

    private MovieModel() {
        EventBus.getDefault().register(this);
        mMovies = new ArrayList<>();
    }

    public static MovieModel getInstance() {
        if(objInstance == null){
            objInstance = new MovieModel();
        }
        return objInstance;
    }

    public List<MovieVO> getMoives() {
        return mMovies;
    }

    public void startLoadingPopularMovies(Context context){
        MovieDataAgentImpl.getInstance().loadPopularMovies(AppConstants.ACCESS_TOKEN, mMoviesPageIndex, context);
    }

    public void loadMorePopularMovies(Context context) {
        MovieDataAgentImpl.getInstance().loadPopularMovies(AppConstants.ACCESS_TOKEN, mMoviesPageIndex, context);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPopularMoviesDataLoaded(RestApiEvents.PopularMoviesDataLoadedEvent event){
        mMovies.addAll(event.getLoadedMovies());
        mMoviesPageIndex = event.getLoadedPageIndex() + 1;

        //save the data in Persistence Layer
        ContentValues[] movieCVs = new ContentValues[event.getLoadedMovies().size()];
        for(int index = 0; index < movieCVs.length; index++){
            movieCVs[index] = event.getLoadedMovies().get(index).parseToContentValues();
        }

        //getContentResolver can access content provider
        int insertedRows = event.getContext().getContentResolver().bulkInsert(MovieContract.PopularMovieEntry.CONTENT_URI, movieCVs);
        Log.d(POCMoviesApp.LOG_TAG, "Inserted Row : " + insertedRows);
    }

    public MovieVO getMovieById(int id){
        for(MovieVO movies : mMovies){
            if(movies.getId()== id){
                return movies;
            }
        }
        return null;
    }

    public void forceRefreshMovies(Context context) {
        mMovies = new ArrayList<>();
        mMoviesPageIndex = 1;
        startLoadingPopularMovies(context);
    }
}
