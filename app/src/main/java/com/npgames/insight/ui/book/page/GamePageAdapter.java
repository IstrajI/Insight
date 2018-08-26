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

import com.arellomobile.mvp.MvpDelegate;
import com.npgames.insight.R;
import com.npgames.insight.data.model.BlockAction;
import com.npgames.insight.data.model.BlockArea;
import com.npgames.insight.data.model.BlockButton;
import com.npgames.insight.ui.all.adapters.BaseRecyclerAdapter;
import com.npgames.insight.ui.book.ICreatePlayer;
import com.npgames.insight.ui.player.CreatePlayerDexView;
import com.npgames.insight.ui.player.CreatePlayerPrcView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GamePageAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder>{
    private MvpDelegate mvpDelegate;

    private List<BlockArea> blocks;

    private final Drawable jumpPressedDrawable;
    private final Drawable jumpEnabledDrawable;
    private final Drawable jumpDisabledDrawable;
    private View.OnClickListener clickListener;
    private ICreatePlayer createPlayerListener;

    public GamePageAdapter(final Resources resources, final MvpDelegate mvpDelegate) {
        this.mvpDelegate = mvpDelegate;
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
                final CreatePlayerDexView createPlayerDexView = new CreatePlayerDexView(parent.getContext());
                createPlayerDexView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                return new CreatePlayerDexViewHolder(createPlayerDexView);
/*
            case BlockArea.BlockType.CREATE_PLAYER_PRC:
                final View createPlayerPrcViewHolderLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_game_page_create_player_prc_item, parent, false);
                return new CreatePlayerPrcViewHolder(createPlayerPrcViewHolderLayout);*/

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
                //createPlayerDexViewHolder.bind();
                break;

            case BlockArea.BlockType.CREATE_PLAYER_PRC:
                final CreatePlayerPrcViewHolder createPlayerPrcViewHolder = (CreatePlayerPrcViewHolder) holder;

                break;

            case BlockArea.BlockType.CREATE_PLAYER_BUTTONS:
                final CreatePlayerButtonsViewHolder createPlayerButtonsViewHolder = (CreatePlayerButtonsViewHolder) holder;
                break;
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

    public void setClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setCreatePlayerListener(final ICreatePlayer createPlayerListener) {
        this.createPlayerListener = createPlayerListener;
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
        public ImageViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    class CreatePlayerDexViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CreatePlayerDexViewHolder(final View itemView) {
            super(itemView);

            final CreatePlayerDexView createPlayerDexView = ((CreatePlayerDexView) itemView);
            createPlayerDexView.addDelegate(mvpDelegate);
            createPlayerDexView.setClickListener(clickListener);
            ((CreatePlayerDexView) itemView).addDelegate(mvpDelegate);

        }

        @Override
        public void onClick(final View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition(), GamePageAdapter.this);
        }
    }

    class CreatePlayerPrcViewHolder extends RecyclerView.ViewHolder {
        public CreatePlayerPrcViewHolder(final @NonNull View itemView) {
            super(itemView);

            ((CreatePlayerPrcView) itemView).addDelegate(mvpDelegate);
        }
    }

    class CreatePlayerButtonsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.create_player_buttons_reset_button)
        protected Button resetButton;
        @BindView(R.id.create_player_dex_ok_button)
        protected Button okButton;

        public CreatePlayerButtonsViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            resetButton.setOnClickListener(this);
            okButton.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition(), GamePageAdapter.this);
        }
    }
}
