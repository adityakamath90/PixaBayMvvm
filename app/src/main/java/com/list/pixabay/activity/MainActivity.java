package com.list.pixabay.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.list.pixabay.R;
import com.list.pixabay.databinding.ActivityMainBinding;
import com.list.pixabay.model.PixaData;
import com.list.pixabay.viewmodel.PixaListViewModel;

public class MainActivity extends AppCompatActivity {

    private boolean isListView = true;
    private PixaListViewModel viewModel;
    private ActivityMainBinding activityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBindings();
    }

    private void setupBindings() {
        activityBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(PixaListViewModel.class);
        viewModel.init();
        activityBinding.setModel(viewModel);
        setupListUpdate();
    }

    private void setupListUpdate() {
        viewModel.loading.set(View.VISIBLE);
        viewModel.fetchList();
        viewModel.getPixaList().observe(this, new Observer<PixaData>() {
            @Override
            public void onChanged(PixaData pixaData) {
                viewModel.loading.set(View.GONE);
                if (pixaData != null && pixaData.getTotalHits() != 0) {
                    viewModel.showEmpty.set(View.GONE);
                    viewModel.setPixaDataInAdapter(pixaData.getHits());
                } else {
                    viewModel.showEmpty.set(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_toggle:
                isListView = !isListView;
                activityBinding.pixaList.setLayoutManager(isListView ? new LinearLayoutManager(this)
                        : new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
