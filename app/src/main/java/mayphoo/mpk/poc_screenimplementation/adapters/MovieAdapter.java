package mayphoo.mpk.poc_screenimplementation.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import mayphoo.mpk.poc_screenimplementation.R;
import mayphoo.mpk.poc_screenimplementation.data.vo.MovieVO;
import mayphoo.mpk.poc_screenimplementation.delegates.MovieItemDelegate;
import mayphoo.mpk.poc_screenimplementation.viewholders.MovieViewHolder;

/**
 * Created by User on 11/9/2017.
 */

public class MovieAdapter extends BaseRecyclerAdapter<MovieViewHolder, MovieVO> {

    private MovieItemDelegate mMovieItemDelegate;

    public MovieAdapter(Context context, MovieItemDelegate movieItemDelegate) {
        super(context);
        mMovieItemDelegate = movieItemDelegate;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View movieItemView = mLayoutInflater.inflate(R.layout.view_item_movie, parent, false);
        return new MovieViewHolder(movieItemView, mMovieItemDelegate);
    }

}
