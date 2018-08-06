package com.npgames.insight.ui.book.page;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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
import com.npgames.insight.data.model.PageBlock;
import com.npgames.insight.ui.all.adapters.BaseRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Влад on 24.10.2017.
 */

public class GamePageAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder>{

    private List<BlockArea> blocks;
    private View.OnClickListener onPageClickListener;

    public GamePageAdapter(final Resources resources) {
        this.resources = resources;
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
                final ButtonViewHolder buttonViewHolder = (ButtonViewHolder) holder;
                buttonViewHolder.jumpButton.setText(blocks.get(position).content);

                buttonViewHolder.jumpButton2.setText(blocks.get(position).content);
                buttonViewHolder.jumpButton4.setText(blocks.get(position).content);
                if (((BlockButton)blocks.get(position)).isEnable()) {
                    buttonViewHolder.jumpButton.setEnabled(((BlockButton)blocks.get(position)).isEnable());
                    buttonViewHolder.jumpButton.setBackground(resources.getDrawable(R.drawable.action_button_new99_trans13));
                } else {
                    buttonViewHolder.jumpButton.setEnabled(((BlockButton)blocks.get(position)).isEnable());
                    buttonViewHolder.jumpButton.setBackground(resources.getDrawable(R.drawable.action_button_new99_trans13disabled));
                }


                break;

            case BlockArea.BlockType.ACTION:
                final ActionsViewHolder actionViewHolder = (ActionsViewHolder) holder;
                actionViewHolder.actionButton.setText(blocks.get(position).content);
                actionViewHolder.actionButton.setEnabled(((BlockAction)blocks.get(position)).isEnable());

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

    public void setOnPageClickListener(final View.OnClickListener onPageClickListener) {
        this.onPageClickListener = onPageClickListener;
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_game_page_text)
        protected TextView textTextView;

        public TextViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }

    class ButtonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnTouchListener{
        @BindView(R.id.adapter_game_page_button_jump_button)
        protected TextView jumpButton;

        @BindView(R.id.adapter_game_page_button_jump_button2)
        protected TextView jumpButton2;

        @BindView(R.id.adapter_game_page_button_jump_button4)
        protected TextView jumpButton4;

        public ButtonViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //jumpButton.setOnClickListener(this);
            jumpButton.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_CANCEL:
                    Log.d("TestPish", "ActionUp");
                    onItemClickListener.onItemRelease(view, getAdapterPosition(), GamePageAdapter.this);
                    break;

                case MotionEvent.ACTION_DOWN:
                    Log.d("TestPish", "ActionDown");
                    onItemClickListener.onItemPress(view, getAdapterPosition(), GamePageAdapter.this);
                    break;

                case MotionEvent.ACTION_UP:
                    onItemClickListener.onItemClick(view, getAdapterPosition(), GamePageAdapter.this);
                    break;

            }

            return true;
        }

        @Override
        public void onClick(final View v) {
           onItemClickListener.onItemClick(v, getAdapterPosition(), GamePageAdapter.this);
        }
    }

    class ActionsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.adapter_game_page_action_button)
        protected Button actionButton;

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
}
