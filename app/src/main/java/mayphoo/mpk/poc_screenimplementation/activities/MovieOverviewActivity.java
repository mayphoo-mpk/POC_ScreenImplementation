package mayphoo.mpk.poc_screenimplementation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mayphoo.mpk.poc_screenimplementation.R;
import mayphoo.mpk.poc_screenimplementation.adapters.MovieImagesPagerAdapter;
import mayphoo.mpk.poc_screenimplementation.data.models.MovieModel;
import mayphoo.mpk.poc_screenimplementation.data.vo.MovieVO;

/**
 * Created by User on 11/11/2017.
 */

public class MovieOverviewActivity extends BaseActivity {

    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.vp_movie_overview_images)
    ViewPager vpMovieOverviewImage;

    @BindView(R.id.tv_released_date)
    TextView tvReleasedDate;

    @BindView(R.id.tv_movie_overview)
    TextView tvMovieOverview;

    private static final String tap_movie_id = "tap_movie_id";
    private MovieVO mMovie;
    MovieImagesPagerAdapter movieImagesPagerAdapter;

    public static Intent newIntent(Context context, MovieVO movies){
        Intent intent = new Intent(context, MovieOverviewActivity.class);
        intent.putExtra(tap_movie_id, movies.getId());
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_overview);
        ButterKnife.bind(this, this);

        movieImagesPagerAdapter = new MovieImagesPagerAdapter(getApplicationContext());
        vpMovieOverviewImage.setAdapter(movieImagesPagerAdapter);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            int movieId = bundle.getInt(tap_movie_id);
            mMovie = MovieModel.getInstance().getMovieById(movieId);
            bindData(mMovie);
        }

    }

    private void bindData(MovieVO movie){
        if(movie.getBackdropPath() != null){
            List<String> images = new ArrayList<>() ;
            images.add(movie.getBackdropPath());
            movieImagesPagerAdapter.setImages(images);
        }

        collapsingToolbarLayout.setTitle(movie.getTitle());
        tvReleasedDate.setText(movie.getReleaseDate());
        tvMovieOverview.setText(movie.getOverview());

    }
}
