package com.list.pixabay.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.list.pixabay.BR;
import com.list.pixabay.model.Hit;
import com.list.pixabay.viewmodel.PixaListViewModel;

import java.util.List;

public class PixaListAdapter extends RecyclerView.Adapter<PixaListAdapter.GenericViewHolder> {

    private int layoutId;
    private List<Hit> pixaDataList;
    private PixaListViewModel viewModel;

    public PixaListAdapter(int layoutId, PixaListViewModel viewModel) {
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }

    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @Override
    public int getItemCount() {
        return pixaDataList == null ? 0 : pixaDataList.size();
    }

    @NonNull
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return new GenericViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        holder.bind(viewModel, position);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    public void setPixaList(List<Hit> pixaListData) {
        this.pixaDataList = pixaListData;
    }

    class GenericViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;

        GenericViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(PixaListViewModel viewModel, Integer position) {
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
    }

    }
}