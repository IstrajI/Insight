package com.npgames.insight.ui.book;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.npgames.insight.R;
import com.npgames.insight.ui.all.adapters.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActionsMenuAdapter extends BaseRecyclerAdapter<ActionsMenuAdapter.ViewHolder>{

    private List<ActionItem> items;
    public enum ActionTypes {STATION, MEDBAY, ARMORY, INSPECT}

    ActionsMenuAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_actions_menu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.actionButton.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void update(final List<ActionItem> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.button_actions_menu_action)
        Button actionButton;
        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class ActionItem {

        public ActionItem(final ActionTypes actionType) {
            this.text = text;
        }
        private boolean status;
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
