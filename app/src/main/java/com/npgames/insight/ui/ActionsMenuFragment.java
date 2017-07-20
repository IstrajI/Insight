package com.npgames.insight.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.npgames.insight.R;
import com.npgames.insight.ui.all.fragments.BaseMvpFragment;
import com.npgames.insight.ui.book.ActionsMenuAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ActionsMenuFragment extends BaseMvpFragment implements View.OnClickListener, ActionsMenuView{

    @BindView(R.id.recycler_view_actions_menu_action)
    protected RecyclerView actionsRecyclerView;
    @BindView(R.id.button_actions_menu_open)
    protected Button openActionsButton;

    @InjectPresenter
    ActionsMenuPresenter actionsMenuPresenter;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_actions_menu, container, false);
    }

    @Override
    protected void bindViews() {
        initActions();

        openActionsButton.setOnClickListener(this);
    }

    private void initActions() {
        final Context context = getActivity().getApplicationContext();
        final LinearLayoutManager actionsMenuLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        final ActionsMenuAdapter actionsMenuAdapter = new ActionsMenuAdapter();
        actionsRecyclerView.setAdapter(actionsMenuAdapter);
        actionsRecyclerView.setLayoutManager(actionsMenuLayoutManager);

        final List<ActionsMenuAdapter.ActionItem> actions = new ArrayList<>();
        actions.add(new ActionsMenuAdapter.ActionItem(ActionsMenuAdapter.ActionTypes.ARMORY));
        actions.add(new ActionsMenuAdapter.ActionItem(ActionsMenuAdapter.ActionTypes.INSPECT));
        actions.add(new ActionsMenuAdapter.ActionItem(ActionsMenuAdapter.ActionTypes.MEDBAY));
        actions.add(new ActionsMenuAdapter.ActionItem(ActionsMenuAdapter.ActionTypes.STATION));
        actionsMenuAdapter.update(actions);

        actionsRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(final View v) {
        switch(v.getId()) {
            case R.id.button_actions_menu_open:
                actionsMenuPresenter.changeActionsMenuStatus();
        }
    }

    @Override
    public void openOrCloseActionsMenu(final boolean isOpen) {
        if (isOpen) {
            actionsRecyclerView.setVisibility(View.VISIBLE);
            return;
        }
        actionsRecyclerView.setVisibility(View.INVISIBLE);
    }
}
