package mayphoo.mpk.poc_screenimplementation.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import mayphoo.mpk.poc_screenimplementation.R;
import mayphoo.mpk.poc_screenimplementation.data.vo.MovieVO;
import mayphoo.mpk.poc_screenimplementation.viewholders.TrailerViewHolder;

/**
 * Created by User on 12/15/2017.
 */

public class TrailerAdapter extends BaseRecyclerAdapter<TrailerViewHolder, MovieVO> {

    public TrailerAdapter(Context context) {
        super(context);
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View trailerItemView = mLayoutInflater.inflate(R.layout.view_item_trailer, parent, false);
        return new TrailerViewHolder(trailerItemView);
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
