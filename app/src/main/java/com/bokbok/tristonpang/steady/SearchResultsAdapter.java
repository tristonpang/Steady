package com.bokbok.tristonpang.steady;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>{
    private List<Vendor> mDataset;
    private String mQueryService;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mName;
        public TextView mService;
        public TextView mPrice;

        public ViewHolder(View v) {
            super(v);
            mName = v.findViewById(R.id.row_name);
            mService = v.findViewById(R.id.row_service);
            mPrice = v.findViewById(R.id.row_price);
        }
    }

    public SearchResultsAdapter(List<Vendor> dataset, String service) {
        mDataset = dataset;
        mQueryService = service;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SearchResultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result_row, parent, false);
        Log.d("Steady", "Adapter, onCreateViewHolder");

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Vendor vendor = mDataset.get(position);
        Log.d("Steady", "Adapter, Vendor name: " + vendor.getName());
        holder.mName.setText(vendor.getName());


        for (String key : vendor.getPrice().keySet()) {
            if (key.contains(mQueryService)) {
                Log.d("Steady", "Key: " + key);
                String pr = vendor.getPrice().get(key);
                holder.mPrice.setText(pr);
                holder.mService.setText(key.replace("_", " ").trim());
            }
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
