package com.npgames.insight.ui.book.armory;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.npgames.insight.R;
import com.npgames.insight.data.model.Equipment;
import com.npgames.insight.ui.all.activities.BaseMvpActivity;
import com.npgames.insight.ui.all.listeners.RecyclerViewListeners;
import com.npgames.insight.ui.all.presentation.player.PlayerPresenter;
import com.npgames.insight.ui.all.presentation.player.PlayerView;

import java.util.List;

import butterknife.BindView;

public class ArmoryActivity extends BaseMvpActivity implements ArmoryView, RecyclerViewListeners.OnItemClickListener, PlayerView,
            View.OnClickListener{
    private final Equipment.Owner owner = Equipment.Owner.ARRMORY;
    @BindView(R.id.recycler_view_armory_equipment)
    protected RecyclerView armoryRecyclerView;
    @BindView(R.id.button_armory_continue)
    protected Button continueButton;

    private EquipmentDialogFragment equipmentMoreDialogFragment;

    @InjectPresenter
    ArmoryPresenter armoryPresenter;
    @InjectPresenter
    PlayerPresenter playerPresenter;

    private ArmoryEquipmentAdapter armoryEquipmentAdapter;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armory);
    }

    @Override
    protected void bindViews() {
        final int numberOfColumns = 3;
        armoryRecyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        armoryEquipmentAdapter = new ArmoryEquipmentAdapter();
        armoryRecyclerView.setAdapter(armoryEquipmentAdapter);
        armoryEquipmentAdapter.setOnItemClickListener(this);
        continueButton.setOnClickListener(this);
        playerPresenter.loadEquipmentsOwnedBy(owner);

        equipmentMoreDialogFragment = new EquipmentDialogFragment();
    }

    @Override
    public void onItemClick(final View view, final int position, final RecyclerView.Adapter adapter) {
        switch(view.getId()) {
            case R.id.text_view_equipment_item_name:
                final Bundle equipmentMoreBundle();
                equipmentMoreDialogFragment.setArguments();
                equipmentMoreDialogFragment.show(getFragmentManager(), "dlg1");
                break;
            case R.id.button_equipment_take_on:
                final Equipment equipmentOn = ((ArmoryEquipmentAdapter) adapter).getEquipmentByPosition(position);
                armoryPresenter.takeOnEquipment(equipmentOn);
                break;
            case R.id.button_equipment_take_out:
                final Equipment equipmentOut = ((ArmoryEquipmentAdapter) adapter).getEquipmentByPosition(position);
                armoryPresenter.putOutEquipment(equipmentOut);
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
        armoryEquipmentAdapter.update(playerPresenter.getEquipment());
    }
}
