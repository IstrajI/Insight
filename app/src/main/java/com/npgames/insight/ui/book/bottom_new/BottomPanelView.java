package com.npgames.insight.ui.book.bottom_new;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.npgames.insight.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomPanelView extends RelativeLayout implements IBottomPanelView, View.OnClickListener{

    @BindView(R.id.button_bottom_panel_ui_top_center)
    protected View uiTopImage;
    @BindView(R.id.button_bottom_panel_find)
    protected ImageView findButton;
    @BindView(R.id.button_bottom_panel_station)
    protected ImageView stationButton;
    @BindView(R.id.button_bottom_panel_med_bay)
    protected ImageView medBayButton;
    @BindView(R.id.button_bottom_panel_armory)
    protected ImageView armoryButton;

    @InjectPresenter
    BottomPanelPresenter bottomPanelPresenter;

    private OnClickListener onClickListener;

    private MvpDelegate mParentDelegate;
    private MvpDelegate<BottomPanelView> mMvpDelegate;

    public BottomPanelView(Context context) {
        this(context, null);
    }

    public BottomPanelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomPanelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(final Context context, final AttributeSet attrs) {
        final View rootLayout = LayoutInflater.from(context).inflate(R.layout.view_bottom_panel, this, true);
        ButterKnife.bind(this, rootLayout);

        uiTopImage.setOnClickListener(this);
        findButton.setOnClickListener(this);
        stationButton.setOnClickListener(this);
        medBayButton.setOnClickListener(this);
        armoryButton.setOnClickListener(this);
    }

    public void init(final MvpDelegate parentDelegate, final OnClickListener onClickListener) {
        mParentDelegate = parentDelegate;
        this.onClickListener = onClickListener;

        getMvpDelegate().onCreate();
        getMvpDelegate().onAttach();

        bottomPanelPresenter.changeYPosition(getY(), getHeight(), uiTopImage.getHeight());
    }

    public MvpDelegate<BottomPanelView> getMvpDelegate() {
        if (mMvpDelegate != null) {
            return mMvpDelegate;
        }

        mMvpDelegate = new MvpDelegate<>(this);
        mMvpDelegate.setParentDelegate(mParentDelegate, String.valueOf(getId()));
        return mMvpDelegate;
    }

    @Override
    public void moveYTo(final float y) {
        setY(y);
        invalidate();
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.button_bottom_panel_ui_top_center:
                bottomPanelPresenter.changeYPosition(getY(), getHeight(), uiTopImage.getHeight());
                break;

            case R.id.button_bottom_panel_find:
                onClickListener.onFind();
                break;

            case R.id.button_bottom_panel_station:
                onClickListener.onStation();
                break;

            case R.id.button_bottom_panel_med_bay:
                onClickListener.onMedBay();
                break;

            case R.id.button_bottom_panel_armory:
                onClickListener.onArmory();
                break;
        }
    }

    public interface OnClickListener {
        void onFind();
        void onStation();
        void onMedBay();
        void onArmory();
    }
}
