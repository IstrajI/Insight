package com.npgames.insight.ui.directory;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.npgames.insight.R;
import com.npgames.insight.data.directory.DirectoryItem;

import java.util.ArrayList;
import java.util.List;

public class DirectoryAdapter extends RecyclerView.Adapter<DirectoryAdapter.DirectoryItemViewHolder> {
    private List<DirectoryItem> directoryItems = new ArrayList<>();

    @NonNull
    @Override
    public DirectoryItemViewHolder onCreateViewHolder(final @NonNull ViewGroup viewGroup, final int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_directory_item, viewGroup, false);
        return new DirectoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DirectoryItemViewHolder directoryItemViewHolder, final int position) {
        final DirectoryItem directoryItem = directoryItems.get(position);

        if (directoryItem.isOpen) {
            Log.d("TestPish", "visibile");
            directoryItemViewHolder.directoryTextView.setVisibility(View.VISIBLE);
            directoryItemViewHolder.directoryTextView.setText(directoryItem.content);
        } else {
            Log.d("TestPish", "invisibile");
            directoryItemViewHolder.directoryTextView.setVisibility(View.GONE);
        }

        directoryItemViewHolder.titleTextView.setText(directoryItem.title);
    }

    @Override
    public int getItemCount() {
        return directoryItems.size();
    }

    public void updateItems(final List<DirectoryItem> directoryItems) {
        this.directoryItems.clear();
        this.directoryItems.addAll(directoryItems);
        notifyDataSetChanged();
    }

    public class DirectoryItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titleTextView;
        public TextView directoryTextView;

        public DirectoryItemViewHolder(final @NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.directory_item_title_text_view);
            directoryTextView = itemView.findViewById(R.id.directory_item_content_text_view);
            titleTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final DirectoryItem directoryItem = directoryItems.get(getAdapterPosition());
            directoryItem.isOpen = !directoryItem.isOpen;
            notifyDataSetChanged();
        }
    }
}
