package com.npgames.insight.ui.book;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.npgames.insight.R;
import com.npgames.insight.data.model.Jump;
import com.npgames.insight.ui.all.adapters.BaseRecyclerAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class JumpsAdapter extends BaseRecyclerAdapter<JumpsAdapter.ViewHolder> {
    private Context context;
    private List<Jump> items;

    public JumpsAdapter(final Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_jump_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.jumpTitleTextView.setText(items.get(position).getText());
        holder.jumpButton.setText(items.get(position).getId());
        holder.jumpButton.setEnabled(items.get(position).isStatus());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void update(final List<Jump> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public Jump getItemAt(final int position) {
        return items.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.text_view_adapter_jump_title)
        protected TextView jumpTitleTextView;
        @BindView(R.id.button_adapter_jump)
        protected Button jumpButton;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            jumpButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition(), JumpsAdapter.this);
        }
    }
}
