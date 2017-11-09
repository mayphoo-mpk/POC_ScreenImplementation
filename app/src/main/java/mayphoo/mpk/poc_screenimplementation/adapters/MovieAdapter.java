package mayphoo.mpk.poc_screenimplementation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mayphoo.mpk.poc_screenimplementation.R;
import mayphoo.mpk.poc_screenimplementation.viewholders.MovieViewHolder;

/**
 * Created by User on 11/9/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private LayoutInflater mLayoutInflater;

    public MovieAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View movieItemView = mLayoutInflater.inflate(R.layout.view_item_movie, parent, false);
        return new MovieViewHolder(movieItemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 12;
    }
}
