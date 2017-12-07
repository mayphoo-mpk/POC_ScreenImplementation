package mayphoo.mpk.poc_screenimplementation.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mayphoo.mpk.poc_screenimplementation.R;
import mayphoo.mpk.poc_screenimplementation.data.vo.MovieVO;
import mayphoo.mpk.poc_screenimplementation.delegates.MovieItemDelegate;
import mayphoo.mpk.poc_screenimplementation.utils.AppConstants;

/**
 * Created by User on 11/9/2017.
 */

public class MovieViewHolder extends BaseViewHolder<MovieVO> {

    @BindView(R.id.iv_poster)
    ImageView ivPoster;

    @BindView(R.id.tv_vote_average)
    TextView tvVoteAverage;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    private MovieItemDelegate mDelegate;
    private MovieVO mMovie;

    public MovieViewHolder(View itemView, MovieItemDelegate movieItemDelegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mDelegate = movieItemDelegate;
    }

    @Override
    public void setData(MovieVO movie) {
        mMovie = movie;

        Glide.with(ivPoster.getContext())
                .load(AppConstants.BASE_URL + movie.getPosterPath())
                .into(ivPoster);

        tvVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
        tvTitle.setText(movie.getTitle());
    }

    @OnClick(R.id.btn_movie_overview)
    public void onTapMovieOverview(View view){
        mDelegate.onTapMovieOverview(mMovie);
    }

    @Override
    public void onClick(View view) {

    }
}
