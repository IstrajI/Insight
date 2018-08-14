package com.npgames.insight.ui.book.page;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.npgames.insight.R;
import com.npgames.insight.data.model.BlockAction;
import com.npgames.insight.data.model.BlockArea;
import com.npgames.insight.data.model.BlockButton;
import com.npgames.insight.data.model.create_player.BlockCreatePlayerDex;
import com.npgames.insight.ui.all.adapters.BaseRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GamePageAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder>{

    private List<BlockArea> blocks;
    private final Drawable jumpPressedDrawable;
    private final Drawable jumpEnabledDrawable;
    private final Drawable jumpDisabledDrawable;

    public GamePageAdapter(final Resources resources) {
        jumpEnabledDrawable = resources.getDrawable(R.drawable.action_button_new99_trans13);
        jumpDisabledDrawable = resources.getDrawable(R.drawable.action_button_new99_trans13disabled);
        jumpPressedDrawable = resources.getDrawable(R.drawable.action_button_new99_trans13pressed);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        switch(viewType) {
            case 0:
                final View textViewHolderLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_game_page_text_item, parent, false);
                return new TextViewHolder(textViewHolderLayout);
            case 1:
                final View buttonViewHolderLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_game_page_button_item, parent, false);
                return new ButtonViewHolder(buttonViewHolderLayout);
            case 3:
                final View actionViewHolderLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_game_page_action_item, parent, false);
                return new ActionsViewHolder(actionViewHolderLayout);

            case BlockArea.BlockType.CREATE_PLAYER_DEX:
                final View createPlayerDexViewHolderLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_game_page_create_player_dex_item, parent, false);
                return new CreatePlayerDexViewHolder(createPlayerDexViewHolderLayout);
        }
        return null;
    }

    @Override
    public int getItemViewType(final int position) {
        final int type = blocks.get(position).type;
        return type;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final String content = blocks.get(position).content;
        switch(holder.getItemViewType()) {

            case BlockArea.BlockType.TEXT:
                ((TextViewHolder)holder).textTextView.setText(content);
                break;

            case BlockArea.BlockType.BUTTON:
                final BlockButton blockButton = (BlockButton) blocks.get(position);
                final ButtonViewHolder buttonViewHolder = (ButtonViewHolder) holder;
                buttonViewHolder.jumpButton.setText(blockButton.content);
                buttonViewHolder.jumpButton.setEnabled(blockButton.isEnable());
                buttonViewHolder.jumpButton.setBackground(blockButton.isEnable() ? jumpEnabledDrawable : jumpDisabledDrawable);
                break;

            case BlockArea.BlockType.ACTION:
                final ActionsViewHolder actionViewHolder = (ActionsViewHolder) holder;
                actionViewHolder.actionButton.setText(blocks.get(position).content);
                actionViewHolder.actionButton.setEnabled(((BlockAction)blocks.get(position)).isEnable());

                break;

            case BlockArea.BlockType.CREATE_PLAYER_DEX:
                final CreatePlayerDexViewHolder createPlayerDexViewHolder = (CreatePlayerDexViewHolder) holder;
                createPlayerDexViewHolder.dexPointsAmountTextView.setText(String.valueOf(((BlockCreatePlayerDex) blocks.get(position)).getDexPoints()));

                break;

            case BlockArea.BlockType.CREATE_PLAYER_PRC:
                final CreatePlayerPrcViewHolder createPlayerPrcViewHolder = (CreatePlayerPrcViewHolder) holder;
                createPlayerPrcViewHolder.
        }
    }

    @Override
    public int getItemCount() {
        return blocks.size();
    }

    public void update(final List<BlockArea> blocks) {
        this.blocks = blocks;
        notifyDataSetChanged();
    }

    public BlockArea getItemAt(final int position) {
        return blocks.get(position);
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_game_page_text)
        protected TextView textTextView;

        TextViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }

    class ButtonViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener{
        @BindView(R.id.adapter_game_page_button_jump_button)
        protected TextView jumpButton;

        ButtonViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            jumpButton.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_CANCEL:
                    view.setBackground(jumpEnabledDrawable);
                    break;

                case MotionEvent.ACTION_DOWN:
                    view.setBackground(jumpPressedDrawable);
                    break;

                case MotionEvent.ACTION_UP:
                    onItemClickListener.onItemClick(view, getAdapterPosition(), GamePageAdapter.this);
                    break;
            }
            return true;
        }
    }

    class ActionsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.adapter_game_page_action_button)
        protected TextView actionButton;

        public ActionsViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            actionButton.setOnClickListener(this);
        }

        @Override
        public void onClick(final View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition(), GamePageAdapter.this);
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    class CreatePlayerDexViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.create_player_dex_points_amount_text_view)
        protected TextView dexPointsAmountTextView;
        @BindView(R.id.create_player_dex_minus_button)
        protected Button dexMinusButton;
        @BindView(R.id.create_player_dex_plus_button)
        protected Button dexPlusButton;

        CreatePlayerDexViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            dexMinusButton.setOnClickListener(this);
            dexPlusButton.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition(), GamePageAdapter.this);
        }
    }

    class CreatePlayerPrcViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.create_player_dex_points_amount_text_view)
        protected TextView prcPointsAmountTextView;
        @BindView(R.id.create_player_dex_minus_button)
        protected Button prcMinusButton;
        @BindView(R.id.create_player_dex_plus_button)
        protected Button prcPlusButton;

        public CreatePlayerPrcViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
