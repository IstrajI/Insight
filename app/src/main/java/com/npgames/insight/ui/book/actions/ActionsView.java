package com.npgames.insight.ui.book.actions;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.npgames.insight.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by i_str on 18.01.2018.
 */

public class ActionsView extends LinearLayout{

    @BindView(R.id.button_bottom_panel_ui_top_center)
    protected ImageView uiTopPanel;

    @BindView(R.id.button_bottom_panel_find)
    protected Button findButton;
    @BindView(R.id.button_bottom_panel_station)
    protected Button stationButton;
    @BindView(R.id.button_bottom_panel_med_bay)
    protected Button medBayButton;
    @BindView(R.id.button_bottom_panel_armory)
    protected Button armoryButton;


    public ActionsView(Context context) {
        super(context);
    }

    public ActionsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        final LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = layoutInflater.inflate(R.layout.layout_bottom_panel, this, true);

        ButterKnife.bind(this, view);

    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int uiTopPanelHeight = uiTopPanel.getHeight();
        final int viewHeight = getHeight();
        setTranslationY(viewHeight - (uiTopPanelHeight/2));
    }

    public void setOnCustomClickListener() {
        
    }
}
