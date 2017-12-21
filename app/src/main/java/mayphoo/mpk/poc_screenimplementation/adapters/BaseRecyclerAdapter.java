package mayphoo.mpk.poc_screenimplementation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mayphoo.mpk.poc_screenimplementation.POCMoviesApp;
import mayphoo.mpk.poc_screenimplementation.viewholders.BaseViewHolder;

/**
 * Created by User on 12/7/2017.
 */

public abstract class BaseRecyclerAdapter<T extends BaseViewHolder, W> extends RecyclerView.Adapter<T> {

    protected List<W> mData;
    protected LayoutInflater mLayoutInflater;

    public BaseRecyclerAdapter(Context context) {
        mData = new ArrayList<>();
        mLayoutInflater = mLayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d(POCMoviesApp.LOG_TAG, "movie count: " + mData.size());
        return mData.size();
    }

    public void setNewData(List<W> newData){
        mData = newData;
        notifyDataSetChanged();
    }

    public void appendNewData(List<W> movieData) {
        mData.addAll(movieData);
        notifyDataSetChanged();
    }

}
