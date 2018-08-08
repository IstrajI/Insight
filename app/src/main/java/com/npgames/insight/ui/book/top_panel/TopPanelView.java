package com.npgames.insight.ui.book.top_panel;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpDelegate;
import com.npgames.insight.R;
import com.npgames.insight.data.model.Player;
import com.npgames.insight.data.model.Stats;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopPanelView extends FrameLayout implements View.OnClickListener{
    @BindView(R.id.text_view_stats_panel_mem_bar)
    protected TextView amnTextView;
    @BindView(R.id.image_view_stats_panel_time_bar)
    protected TextView timeTextView;
    @BindView(R.id.image_view_stats_panel_hp_bar)
    protected TextView hpTextView;
    @BindView(R.id.image_view_stats_panel_prc_bar)
    protected TextView prcTextView;
    @BindView(R.id.image_view_stats_panel_dex_bar)
    protected TextView dexTextView;
    @BindView(R.id.image_view_stats_panel_au_bar)
    protected TextView aurTextView;
    @BindView(R.id.top_panel_menu_button_image_view)
    protected ImageView menuButtonImageView;

    protected TopPanelClickListener topPanelClickListener;
    public TopPanelView(@NonNull Context context) {
        super(context);
        init(context, null);
        setWillNotDraw(false);
    }

    public TopPanelView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        setWillNotDraw(false);
    }

    public TopPanelView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
        setWillNotDraw(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TopPanelView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("testPish", "" +getY() +" , " +getBottom());
        initStates(getY(), getHeight());
    }

    private void init(final Context context, final AttributeSet attrs) {
        final ViewGroup layout = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.layout_stats_panel, this, true);
        ButterKnife.bind(layout, this);
        menuButtonImageView.setOnClickListener(this);
    }

    public void setStats(final Stats stats) {
        amnTextView.setText(String.valueOf(stats.getAmn()));
        timeTextView.setText(String.valueOf(stats.getTime()));
        hpTextView.setText(String.valueOf(stats.getHp()));
        prcTextView.setText(String.valueOf(stats.getPrc()));
        dexTextView.setText(String.valueOf(stats.getDex()));
        aurTextView.setText(String.valueOf(stats.getAur()));
    }

    public void onTopMenuButtonClick(final View view) {
        setY(isOpen ? closeYposition : openYposition);
        invalidate();
        isOpen = !isOpen;
    }

    private float openYposition;
    private float closeYposition;
    private boolean isOpen = true;
    private boolean isPositionsInited = false;

    void initStates(final float openYposition, final int viewHeight) {
        if (isPositionsInited) return;
        this.openYposition = openYposition;
        this.closeYposition = openYposition - viewHeight / 2;
        isPositionsInited = true;
    }

    public void setClickListener(final TopPanelClickListener topPanelClickListener) {
        this.topPanelClickListener = topPanelClickListener;
    }

    @Override
    public void onClick(final View view) {
        switch(view.getId()) {
            case R.id.top_panel_menu_button_image_view:
                topPanelClickListener.topPanelOnMenuClick();
                break;
        }
    }

    public interface TopPanelClickListener {
        void topPanelOnMenuClick();
    }
}
