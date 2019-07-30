package com.list.pixabay.viewmodel;

import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.list.pixabay.R;
import com.list.pixabay.adapters.PixaListAdapter;
import com.list.pixabay.model.Hit;
import com.list.pixabay.model.PixaData;
import com.list.pixabay.model.PixaList;

import java.util.List;

public class PixaListViewModel extends ViewModel {

    private PixaListAdapter adapter;
    private PixaList pixaList;
    public ObservableInt loading;
    public ObservableInt showEmpty;

    public void init() {
        pixaList = new PixaList();
        adapter = new PixaListAdapter(R.layout.pixa_list_item, this);
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);
    }

    public PixaListAdapter getAdapter() {
        return adapter;
    }

    public void fetchList() {
        pixaList.fetchList();
    }

    public MutableLiveData<PixaData> getPixaList() {
        return pixaList.getPixaDataList();
    }

    public void setPixaDataInAdapter(List<Hit> pixaDataList) {
        this.adapter.setPixaList(pixaDataList);
        this.adapter.notifyDataSetChanged();
    }

    public Hit getDataAt(Integer index) {

        if (pixaList!=null && pixaList.getPixaDataList()!=null && pixaList.getPixaDataList().getValue()!=null
                && pixaList.getPixaDataList().getValue().getHits().size() > index) {
            return pixaList.getPixaDataList().getValue().getHits().get(index);
        }

        return null;
    }

}