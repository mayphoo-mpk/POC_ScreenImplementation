package mayphoo.mpk.poc_screenimplementation.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by User on 12/7/2017.
 */

public abstract class BaseViewHolder<W> extends RecyclerView.ViewHolder implements View.OnClickListener {

    public BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public abstract void setData(W data);

}
