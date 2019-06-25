package com.npgames.insight.ui.directory;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.npgames.insight.R;
import com.npgames.insight.data.directory.DirectoryItem;
import com.npgames.insight.ui.all.activities.BaseMvpActivity;
import com.npgames.insight.ui.book.bottom_new.BottomPanelPresenter;

import java.util.List;

import butterknife.BindView;

public class DirectoryActivity extends BaseMvpActivity implements DirectoryView {
    @BindView(R.id.directory_list_recycler_view)
    protected RecyclerView listRecyclerView;

    private DirectoryAdapter directoryAdapter;

    public static String DIRECTORY_ITEM_NUMBER_EXTRA = "DIRECTORY_ITEM_NUMBER_EXTRA";

    @InjectPresenter
    DirectoryPresenter directoryPresenter;
    @ProvidePresenter
    DirectoryPresenter provideDirectoryPresenter() {
        return new DirectoryPresenter(getApplicationContext());
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
    }

    @Override
    protected void bindViews() {
        directoryAdapter = new DirectoryAdapter();
        listRecyclerView.setAdapter(directoryAdapter);

        listRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        directoryPresenter.loadDirectory();
    }

    @Override
    public void showDirectory(final List<DirectoryItem> directoryItem) {
        directoryAdapter.updateItems(directoryItem);

        final int directoryItemToOpen = getIntent().getIntExtra(DIRECTORY_ITEM_NUMBER_EXTRA, 0);

        if (directoryItemToOpen != 0) {
            directoryAdapter.openItem(directoryItemToOpen);
            listRecyclerView.scrollToPosition(directoryItemToOpen);
        }
    }
}
