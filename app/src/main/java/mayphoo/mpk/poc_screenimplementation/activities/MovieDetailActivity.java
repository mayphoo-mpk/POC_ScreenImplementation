package mayphoo.mpk.poc_screenimplementation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import mayphoo.mpk.poc_screenimplementation.R;
import mayphoo.mpk.poc_screenimplementation.adapters.TrailerAdapter;
import mayphoo.mpk.poc_screenimplementation.components.EmptyViewPod;
import mayphoo.mpk.poc_screenimplementation.components.SmartRecyclerView;
import mayphoo.mpk.poc_screenimplementation.data.vo.MovieVO;

/**
 * Created by User on 12/14/2017.
 */

public class MovieDetailActivity extends BaseActivity {

    @BindView(R.id.rv_trailer_movies)
    SmartRecyclerView rvTrailerMovies;

    @BindView(R.id.vp_empty_trailer_movies)
    EmptyViewPod vpEmptyTrailerMovies;

    TrailerAdapter mTrailerAdapter;

    private static final String tap_movie_id = "tap_movie_id";

    public static Intent newIntent(Context context, MovieVO movie){
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(tap_movie_id, movie.getId());
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this, this);

        rvTrailerMovies.setEmptyView(vpEmptyTrailerMovies);
        rvTrailerMovies.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        mTrailerAdapter = new TrailerAdapter(getApplicationContext());
        rvTrailerMovies.setAdapter(mTrailerAdapter);

    }
}
