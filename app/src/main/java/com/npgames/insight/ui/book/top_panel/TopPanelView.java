package com.npgames.insight.ui.book.top_panel;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Stats;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopPanelView extends FrameLayout implements View.OnClickListener {
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

    private Animation inAnimation;
    private Animation outAnimation;

    private Stats stats;

    final String HP = "HP";
    final String AUR = "AUR";
    final String DEX = "DEX";
    final String PRC = "PRC";
    final String TIME = "TIME";
    final String AMN = "AMN";

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

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        bundle.putInt(HP, stats.getHp());
        bundle.putInt(AUR, stats.getAur());
        bundle.putInt(DEX, stats.getDex());
        bundle.putInt(PRC, stats.getPrc());
        bundle.putInt(TIME, stats.getTime());
        bundle.putInt(AMN, stats.getAmn());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            final Bundle bundle = (Bundle) state;
            this.stats = Stats.builder()
                    .setHp(bundle.getInt(HP))
                    .setPrc(bundle.getInt(PRC))
                    .setAur(bundle.getInt(AUR))
                    .setDex(bundle.getInt(DEX))
                    .setTime(bundle.getInt(TIME))
                    .setAmn(bundle.getInt(AMN))
                    .build();


            setStatsWithoutAnimation(this.stats);
            state = bundle.getParcelable("superState");
        }

        super.onRestoreInstanceState(state);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //initStates(getY(), getHeight());
    }

    private void init(final Context context, final AttributeSet attrs) {
        final ViewGroup layout = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.layout_stats_panel, this, true);
        ButterKnife.bind(layout, this);
        menuButtonImageView.setOnClickListener(this);
    }



    public void setStats(final Stats newStats) {
        Log.d("TestPish", "updatePlayerStatsWithoutAnimation");
        if (stats == null) {
            Log.d("TestPish", "stats == null");
            setStatsWithoutAnimation(newStats);
        } else {
            Log.d("TestPish", "else");
            updateStats(newStats);
        }
    }

    public void setStatsWithoutAnimation(final Stats newStats) {
        Log.d("TestGG", "dex" +newStats.getDex());
        Log.d("TestGG", "prc" +newStats.getPrc());

        amnTextView.setText(String.valueOf(newStats.getAmn()));
        aurTextView.setText(String.valueOf(newStats.getAur()));
        timeTextView.setText(String.valueOf(newStats.getTime()));
        dexTextView.setText(String.valueOf(newStats.getDex()));
        hpTextView.setText(String.valueOf(newStats.getHp()));
        prcTextView.setText(String.valueOf(newStats.getPrc()));

        this.stats = Stats.builder()
                .setTime(newStats.getTime())
                .setAmn(newStats.getAmn())
                .setAur(newStats.getAur())
                .setDex(newStats.getDex())
                .setHp(newStats.getHp())
                .setPrc(newStats.getPrc())
                .build();
    }

    public void updateStats(final Stats newStats) {
        updateStatView(amnTextView, newStats.getAmn(), stats.getAmn());
        updateStatView(timeTextView, newStats.getTime(), stats.getTime());
        updateStatView(dexTextView, newStats.getDex(), stats.getDex());
        updateStatView(hpTextView, newStats.getHp(), stats.getHp());
        updateStatView(prcTextView, newStats.getPrc(), stats.getPrc());
        updateStatView(aurTextView, newStats.getAur(), stats.getAur());

        this.stats = Stats.builder()
                .setTime(newStats.getTime())
                .setAmn(newStats.getAmn())
                .setAur(newStats.getAur())
                .setDex(newStats.getDex())
                .setHp(newStats.getHp())
                .setPrc(newStats.getPrc())
                .build();
    }

    private void updateStatView(final TextView statView, final int newValue, final int oldValue) {
        final int difference = newValue - oldValue;
        Log.d("TestPish", ""+difference);
        if (difference != 0) {

            if (difference > 0) {
                statView.setTextAppearance(getContext(), R.style.D_Positive);
                statView.setText("+" +String.valueOf(difference));
            } else {
                statView.setTextAppearance(getContext(), R.style.D_Negative);
                statView.setText(String.valueOf(difference));
            }

            Animation animation = new AlphaAnimation(getContext(), null);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    statView.setTextAppearance(getContext(), R.style.D);
                    statView.setText(String.valueOf(newValue));
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            animation.setDuration(700);
            statView.setAnimation(animation);
            animation.start();
        }
    }

    public void setClickListener(final TopPanelClickListener topPanelClickListener) {
        this.topPanelClickListener = topPanelClickListener;
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.top_panel_menu_button_image_view:
                topPanelClickListener.topPanelOnMenuClick();
                break;
        }
    }

    public interface TopPanelClickListener {
        void topPanelOnMenuClick();
    }
}
