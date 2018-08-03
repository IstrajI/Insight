package com.npgames.insight.ui.book.armory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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


public class ArmoryActivity extends BaseMvpActivity implements ArmoryView, RecyclerViewListeners.OnItemClickListener,
            View.OnClickListener{

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

        armoryEquipmentAdapter.setOnItemClickListener(this);
        continueButton.setOnClickListener(this);

        armoryPresenter.loadEquipment();
        armoryPresenter.loadStatsPlayerStats();

        equipmentMoreDialogFragment = new EquipmentDialogFragment();
    }

    @Override
    public void onItemClick(final View view, final int position, final RecyclerView.Adapter adapter) {
        switch(view.getId()) {
            case R.id.text_view_equipment_item_name:
            case R.id.image_view_equipment_item_picture:
                final Equipment equipment = ((ArmoryEquipmentAdapter) adapter).getEquipmentByPosition(position);

                final Bundle equipmentMoreBundle = new Bundle();
                final String equipmentName = equipment.getName();
                final String equipmentDescription = equipment.getDescription();

                equipmentMoreBundle.putString(EquipmentDialogFragment.NAME, equipmentName);
                equipmentMoreBundle.putString(EquipmentDialogFragment.DESCRIPTION, equipmentDescription);

                equipmentMoreDialogFragment.setArguments(equipmentMoreBundle);
                equipmentMoreDialogFragment.show(getFragmentManager(), EquipmentDialogFragment.TAG);
                break;

            case R.id.button_equipment_take_on:
                final Equipment equipmentOn = ((ArmoryEquipmentAdapter) adapter).getEquipmentByPosition(position);
                armoryPresenter.takeOnEquipment(equipmentOn);
                break;

            case R.id.button_equipment_take_out:
                final Equipment equipmentOff = ((ArmoryEquipmentAdapter) adapter).getEquipmentByPosition(position);
                armoryPresenter.takeOffEquipment(equipmentOff);
                break;
        }
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()){
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
}
