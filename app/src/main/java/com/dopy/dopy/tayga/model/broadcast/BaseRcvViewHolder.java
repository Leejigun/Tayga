package com.dopy.dopy.tayga.model.broadcast;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Dopy on 2017-08-08.
 */

public abstract class BaseRcvViewHolder extends RecyclerView.ViewHolder{
    public BaseRcvViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(Object data);

}
