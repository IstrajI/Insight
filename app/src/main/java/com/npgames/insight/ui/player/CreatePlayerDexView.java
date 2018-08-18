package com.npgames.insight.ui.player;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.npgames.insight.R;
import com.npgames.insight.ui.book.ICreatePlayer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreatePlayerDexView extends FrameLayout implements View.OnClickListener, ICreatePlayerView{
    @BindView(R.id.create_player_dex_minus_button)
    protected Button dexMinusButton;
    @BindView(R.id.create_player_dex_plus_button)
    protected Button dexPlusButton;
    @BindView(R.id.create_player_dex_points_amount_text_view)
    protected TextView dexAmountTextView;
    @BindView(R.id.create_player_prc_minus_button)
    protected Button prcMinusButton;
    @BindView(R.id.create_player_prc_plus_button)
    protected Button prcPlusButton;
    @BindView(R.id.create_player_prc_points_amount_text_view)
    protected TextView prcAmountTextView;
    @BindView(R.id.create_player_distribute_points_text_view)
    protected TextView pointsToDistributeTextView;
    @BindView(R.id.create_player_ok_button)
    protected Button okButton;
    @BindView(R.id.create_player_reset_button)
    protected Button resetButton;

    private MvpDelegate<CreatePlayerDexView> mvpDelegate;
    private MvpDelegate parentDelegate;

    @InjectPresenter
    CreatePlayerPresenter createPlayerPresenter;
    private View.OnClickListener clickListener;
    private ICreatePlayer createPlayerListener;

    @ProvidePresenter
    CreatePlayerPresenter provideGameBookPresenter() {
        return new CreatePlayerPresenter(getContext());
    }

    public CreatePlayerDexView(@NonNull Context context) {
        this(context, null);
    }

    public CreatePlayerDexView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CreatePlayerDexView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final View cretePlayerView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_game_page_create_player_dex_item, this, true);
        ButterKnife.bind(this, cretePlayerView);

        prcMinusButton.setOnClickListener(this);
        prcPlusButton.setOnClickListener(this);
        dexMinusButton.setOnClickListener(this);
        dexPlusButton.setOnClickListener(this);
        okButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        createPlayerPresenter.loadCreatePanelData();
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.create_player_dex_minus_button:
                createPlayerPresenter.dexMinus();
                clickListener.onClick(view);

                break;

            case R.id.create_player_dex_plus_button:
                createPlayerPresenter.dexPlus();
                clickListener.onClick(view);
                break;

            case R.id.create_player_prc_minus_button:
                createPlayerPresenter.prcMinus();
                clickListener.onClick(view);
                break;

            case R.id.create_player_prc_plus_button:
                createPlayerPresenter.prcPlus();
                clickListener.onClick(view);
                break;

            case R.id.create_player_ok_button:
                createPlayerPresenter.addPointsToPlayer();
                break;

            case R.id.button_create_character_reset:
                createPlayerPresenter.resetPoints();
        }
    }

    @Override
    public void showDexPoints(final int dexPoints) {
        dexAmountTextView.setText(String.valueOf(dexPoints));
    }

    @Override
    public void showPrcPoints(final int prcPoints) {
        prcAmountTextView.setText(String.valueOf(prcPoints));
    }

    @Override
    public void showPointsToDistribute(final int pointsToDistribute) {
        //pointsToDistributeTextView.setText(String.valueOf(pointsToDistribute));
    }

    @Override
    public void stateNoPointsToDistribute() {
        prcPlusButton.setEnabled(false);
        dexPlusButton.setEnabled(false);
        createPlayerListener.onFinish();
    }

    @Override
    public void stateMaxPointsToDistribute() {
        prcMinusButton.setEnabled(false);
        dexMinusButton.setEnabled(false);
    }

    @Override
    public void stateSomePointsToDistribute() {
        prcPlusButton.setEnabled(true);
        prcMinusButton.setEnabled(true);
        dexMinusButton.setEnabled(true);
        dexPlusButton.setEnabled(true);
    }

    @Override
    public void disableDexMinus() {
        dexMinusButton.setEnabled(false);
    }

    @Override
    public void disablePrcMinus() {
        prcMinusButton.setEnabled(false);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        getMvpDelegate().onSaveInstanceState();
        getMvpDelegate().onDetach();
    }

    public MvpDelegate<CreatePlayerDexView> getMvpDelegate() {
        if (this.mvpDelegate != null) {
            return mvpDelegate;
        }

        mvpDelegate = new MvpDelegate<>(this);
        mvpDelegate.setParentDelegate(parentDelegate, String.valueOf(getId()));
        return mvpDelegate;
    }

    public void addDelegate(final MvpDelegate parentDelegate) {
        this.parentDelegate = parentDelegate;

        getMvpDelegate().onCreate();
        getMvpDelegate().onAttach();
    }

    public void setClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setCreatePlayerListener(final ICreatePlayer createPlayerListener) {
        this.createPlayerListener = createPlayerListener;
    }
}
