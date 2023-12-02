package com.gs.h2oapp.Utils;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by Warley Lima
 */
public interface ItemClickListener {
    public void onClick(View view, int position);
    public void onClick(LinearLayout view, int position);
    public void onClick(ImageButton view, int position);
}
