package com.mgl.test.hugetest.utils.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class GenericAdapter<T extends GenericItem> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<T> items;

    protected GenericAdapterFactory factory;

    public GenericAdapter(GenericAdapterFactory factory) {
        this.factory = factory;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = (View) this.factory.onCreateViewHolder(parent, viewType);

        return new RecyclerView.ViewHolder(view) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GenericItemView genericItemView = (GenericItemView) holder.itemView;
        genericItemView.bind(items.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return this.items.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void update(T item) {
        int index = items.indexOf(item);
        if (index != -1) {
            items.set(index, item);
            notifyItemChanged(index);
        }
    }

    public void remove(T item) {
        int index = items.indexOf(item);
        if (index != -1) {
            items.remove(index);
            notifyItemRemoved(index);
        }
    }

    public void addItem(T item) {
        items.add(item);
        notifyDataSetChanged();
    }
}
