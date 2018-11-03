package com.npgames.insight.ui.book.armory;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.npgames.insight.R;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.Equipment;
import com.npgames.insight.ui.all.activities.BaseMvpActivity;
import com.npgames.insight.ui.all.listeners.RecyclerViewListeners;
import com.npgames.insight.ui.book.top_panel.TopPanelView;

import java.util.List;

import butterknife.BindView;


public class ArmoryActivity extends BaseMvpActivity implements ArmoryView,
        View.OnClickListener, ArmoryEquipmentAdapter.Wearable {

    @BindView(R.id.recycler_view_armory_equipment)
    protected RecyclerView armoryRecyclerView;
    @BindView(R.id.button_armory_continue)
    protected Button continueButton;

    private EquipmentDialogFragment equipmentMoreDialogFragment;
    @BindView(R.id.gamebook_stats_top_panel_view)
    TopPanelView statsTopPanel;
    @InjectPresenter
    ArmoryPresenter armoryPresenter;

    @ProvidePresenter
    ArmoryPresenter provideGameBookPresenter() {
        return new ArmoryPresenter(getApplicationContext());
    }

    private ArmoryEquipmentAdapter armoryEquipmentAdapter;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armory);
    }

    @Override
    protected void bindViews() {
        final int numberOfColumns = 3;

        final GridLayoutManager equipmentLayoutManager = new GridLayoutManager(this, numberOfColumns);
        armoryRecyclerView.setLayoutManager(equipmentLayoutManager);
        armoryEquipmentAdapter = new ArmoryEquipmentAdapter(getApplicationContext());
        armoryRecyclerView.setAdapter(armoryEquipmentAdapter);

        statsTopPanel.post(() -> {
            final int recyclerViewPadding = statsTopPanel.getHeight() + (int) (getResources().getDimension(R.dimen.spacing_8) * Resources.getSystem().getDisplayMetrics().density);
            Log.d("TestPish", "topPadding = " + recyclerViewPadding);
            armoryRecyclerView.setPadding(0, recyclerViewPadding, 0, 0);
            equipmentLayoutManager.scrollToPositionWithOffset(0, 0);
            //armoryRecyclerView.smoothScrollToPosition(recyclerViewPadding);
        });

        armoryEquipmentAdapter.setWearable(this);
        continueButton.setOnClickListener(this);

        armoryPresenter.loadEquipment();
        armoryPresenter.loadStatsPlayerStats();

        equipmentMoreDialogFragment = new EquipmentDialogFragment();
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.button_armory_continue:
                final Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
        }
    }

    @Override
    public void showEquipment(final List<Equipment> equipments) {
        armoryEquipmentAdapter.update(equipments);
    }

    @Override
    public void showStats(final Stats stats) {
        statsTopPanel.setStats(stats);
    }

    @Override
    public void takeOnOffEquipment(final Equipment equipment) {
        armoryPresenter.takeOnOffEquipment(equipment);
        //showStatsDifference(Stats.builder().build());
    }

    @Override
    public void showDetailedInfo(final Equipment equipment) {
        final String equipmentName = equipment.getName();
        final String equipmentDescription = equipment.getDescription();
        final int equipmentDrawable = equipment.getDrawable();

        final EquipmentDialogFragment equipmentDialogFragment = EquipmentDialogFragment.createEquipmentDialogFragment(equipmentName, equipmentDescription, equipmentDrawable);
        equipmentDialogFragment.show(getFragmentManager(), EquipmentDialogFragment.TAG);
    }
}
