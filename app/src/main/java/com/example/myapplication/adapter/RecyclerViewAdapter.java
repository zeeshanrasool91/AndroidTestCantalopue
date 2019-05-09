package com.example.myapplication.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.android.databinding.library.baseAdapters.BR;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemMostPopularBinding;
import com.example.myapplication.model.MostPopularResponse;
import com.example.myapplication.mvp.MainPresenter;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<MostPopularResponse> dataModelList;
    private Context context;
    private onItemClickListener onItemClickListener;

    MainPresenter mainPresenter;


    public RecyclerViewAdapter(Context ctx) {
        context = ctx;
        this.dataModelList = new ArrayList<>();
    }

    public RecyclerViewAdapter(List<MostPopularResponse> dataModelList, Context ctx) {
        this.dataModelList = dataModelList;
        context = ctx;
    }

    public void setOnItemClickListener(RecyclerViewAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }


    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        ItemMostPopularBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.item_most_popular, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MostPopularResponse dataModel = dataModelList.get(position);
        holder.bind(dataModel);
    }

    public void setItems(List<MostPopularResponse> dataModelList){
        this.dataModelList=dataModelList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return dataModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ItemMostPopularBinding itemRowBinding;

        public ViewHolder(ItemMostPopularBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
            itemRowBinding.getRoot().setOnClickListener(this);
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(BR.responseModel, obj);
            itemRowBinding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            if(mainPresenter != null) {
                mainPresenter.onItemClick(getAdapterPosition(),itemRowBinding.getResponseModel());
            }
        }
    }
    public interface onItemClickListener {
        void onItemClick(int position, MostPopularResponse mostPopularResponse);
    }
}