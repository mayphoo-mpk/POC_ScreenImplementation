package mayphoo.mpk.poc_screenimplementation.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import mayphoo.mpk.poc_screenimplementation.R;

/**
 * Created by User on 11/11/2017.
 */

public class MovieImagesPagerAdapter extends PagerAdapter {

    private LayoutInflater mLayoutInflater;
    private List<String> images;

    public MovieImagesPagerAdapter(Context context) {
        super();
        mLayoutInflater = LayoutInflater.from(context);
        images = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return images.size();
    }

    //check child view object('view' parameter) is view pager object ('object' parameter)
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.view_item_movie_overview_image, container, false);
        ImageView imageView = itemView.findViewById(R.id.iv_details_image);
        if(images != null && images.size() > 0){
            Glide.with(imageView.getContext())
                    .load(images.get(0))
                    .into(imageView);
        }
        container.addView(itemView);
        return itemView;
    }

    // 'position' is user scroll position, not child view position
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    public void setImages(List<String> newsImages){
        this.images = newsImages;
        notifyDataSetChanged();
    }
}
