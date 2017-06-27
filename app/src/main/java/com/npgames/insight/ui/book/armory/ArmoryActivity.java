package com.npgames.insight.ui.book.armory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.npgames.insight.R;
import com.npgames.insight.data.model.equipment.Equipment;
import com.npgames.insight.ui.all.activities.BaseMvpActivity;
import com.npgames.insight.ui.all.listeners.RecyclerViewListeners;
import com.npgames.insight.ui.all.presentation.player.PlayerPresenter;
import com.npgames.insight.ui.all.presentation.player.PlayerView;

import java.util.List;

import butterknife.BindView;

public class ArmoryActivity extends BaseMvpActivity implements ArmoryView, RecyclerViewListeners.OnItemClickListener, PlayerView,
            View.OnClickListener{

    @BindView(R.id.recycler_view_armory_equipment)
    protected RecyclerView armoryRecyclerView;
    @BindView(R.id.button_armory_continue)
    protected Button continueButton;

    @BindView(R.id.text_view_stats_panel_time)
    protected TextView timeTextView;
    @BindView(R.id.text_view_stats_panel_amn)
    protected TextView amnTextView;
    @BindView(R.id.text_view_stats_panel_hp)
    protected TextView hpTextView;
    @BindView(R.id.text_view_stats_panel_prc)
    protected TextView prcTextView;
    @BindView(R.id.text_view_stats_panel_dex)
    protected TextView dexTextView;
    @BindView(R.id.text_view_stats_panel_aur)
    protected TextView aurTextView;

    private EquipmentDialogFragment equipmentMoreDialogFragment;
    private GridLayoutManager equipmentLayoutManager;

    @InjectPresenter
    PlayerPresenter playerPresenter;

    private final Equipment.Owner owner = Equipment.Owner.ARRMORY;
    private ArmoryEquipmentAdapter armoryEquipmentAdapter;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armory);
    }

    @Override
    protected void bindViews() {
        playerPresenter.printStats();
        final int numberOfColumns = 3;
        equipmentLayoutManager = new GridLayoutManager(this, numberOfColumns);
        armoryRecyclerView.setLayoutManager(equipmentLayoutManager);
        armoryEquipmentAdapter = new ArmoryEquipmentAdapter(getApplicationContext());
        armoryRecyclerView.setAdapter(armoryEquipmentAdapter);
        armoryEquipmentAdapter.setOnItemClickListener(this);
        continueButton.setOnClickListener(this);

        playerPresenter.loadEquipmentsOwnedBy(owner);
        playerPresenter.loadStats();

        equipmentMoreDialogFragment = new EquipmentDialogFragment();

        playerPresenter.printStats();
    }


    @Override
    public void onItemClick(final View view, final int position, final RecyclerView.Adapter adapter) {
        switch(view.getId()) {
            case R.id.text_view_equipment_item_name:
            case R.id.image_view_equipment_item_picture:
                final Equipment equipment = ((ArmoryEquipmentAdapter) adapter).getEquipmentByPosition(position);
                Log.d("shared name", ""+equipment.getSharedPropertyName());
                final String equipmentName = getString(equipment.getNameResource());
                final String equipmentDescription = getString(equipment.getDescriptionResource());
                final Bundle equipmentMoreBundle = new Bundle();
                equipmentMoreBundle.putString(Equipment.NAME, equipmentName);
                equipmentMoreBundle.putString(Equipment.DESCRIPTION, equipmentDescription);
                equipmentMoreDialogFragment.setArguments(equipmentMoreBundle);
                equipmentMoreDialogFragment.show(getFragmentManager(), "dlg1");
                break;
            case R.id.button_equipment_take_on:
                playerPresenter.printStats();
                final Equipment equipmentOn = ((ArmoryEquipmentAdapter) adapter).getEquipmentByPosition(position);
                playerPresenter.wearEquipment(equipmentOn);
                playerPresenter.printStats();
                break;
            case R.id.button_equipment_take_out:
                playerPresenter.printStats();
                final Equipment equipmentOut = ((ArmoryEquipmentAdapter) adapter).getEquipmentByPosition(position);
                playerPresenter.unwearEquipment(equipmentOut);
                playerPresenter.printStats();
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
    public void showEquipmentsOwnedBy(final List<Equipment> equipments) {
        armoryEquipmentAdapter.update(equipments);
    }

    @Override
    public void showStats(final int hp, final int aur, final int prc, final int dex,final int time,
                          final int amn) {
        hpTextView.setText(String.valueOf(hp));
        aurTextView.setText(String.valueOf(aur));
        prcTextView.setText(String.valueOf(prc));
        dexTextView.setText(String.valueOf(dex));
        timeTextView.setText(String.valueOf(time));
        amnTextView.setText(String.valueOf(amn));
    }

    public void checkEquipmentWearStatus() {

    }

    @Override
    public void showPlayerOwnEquipment() {

    }

    @Override
    public void showCantWearEquipment(final int equipmentNumber) {
        equipmentLayoutManager.findViewByPosition(equipmentNumber).findViewById(R.id.button_equipment_take_on).setEnabled(false);
    }

    @Override
    public void showCanWearEquipment(int equipmentNumber) {
        equipmentLayoutManager.findViewByPosition(equipmentNumber).findViewById(R.id.button_equipment_take_on).setEnabled(true);
    }

    @Override
    public void showWearedEquipment() {
    }
}
