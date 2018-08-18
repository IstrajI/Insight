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

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreatePlayerPrcView extends FrameLayout implements ICreatePlayerPrcView{
    @BindView(R.id.create_player_prc_minus_button)
    protected Button minusButton;
    @BindView(R.id.create_player_prc_plus_button)
    protected Button plusButton;
    @BindView(R.id.create_player_prc_points_amount_text_view)
    protected TextView amountTextView;

    private MvpDelegate<CreatePlayerPrcView> mvpDelegate;
    private MvpDelegate parentDelegate;


    public CreatePlayerPrcView(@NonNull Context context) {
        this(context, null);
    }

    public CreatePlayerPrcView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CreatePlayerPrcView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final View cretePlayerView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_game_page_create_player_prc_item, this, true);
        ButterKnife.bind(this, cretePlayerView);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //createPlayerPresenter.loadCurrentPoints();
    }

/*    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.create_player_prc_minus_button:
                createPlayerPresenter.prcMinus();
                break;

            case R.id.create_player_prc_plus_button:
                createPlayerPresenter.prcPlus();
                break;
        }
    }*/

    @Override
    public void showPrcPoints(final int prcPoints) {
        amountTextView.setText(String.valueOf(prcPoints));
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        getMvpDelegate().onSaveInstanceState();
        getMvpDelegate().onDetach();
    }

    public MvpDelegate<CreatePlayerPrcView> getMvpDelegate() {
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
}
