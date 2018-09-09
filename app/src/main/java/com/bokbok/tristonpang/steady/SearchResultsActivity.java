package com.bokbok.tristonpang.steady;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SearchResultsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Vendor> mVendorsDataset;
    private String mQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        mQuery = getIntent().getStringExtra("searchQuery").toLowerCase()
                .replace(" ", "_").trim();

        mRecyclerView = (RecyclerView) findViewById(R.id.searchListView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mVendorsDataset = new ArrayList<>();

        // specify an adapter (see also next example)
        mAdapter = new SearchResultsAdapter(mVendorsDataset, mQuery);
        mRecyclerView.setAdapter(mAdapter);

        //add on click listener
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Vendor vendor = mVendorsDataset.get(position);
                Log.d("Steady", "Vendor selected: " + vendor.getName());
                goToVendor(vendor.getId());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareVendorsData();
    }

    private void prepareVendorsData() {
        final String currentUser = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        Log.d("Steady", "prepareVendorsData(), currentUser = " + currentUser);
        FirebaseDatabase.getInstance().getReference().child("vendors").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mVendorsDataset.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //retrieve vendor name, service and price
                    String vendorName = (String) snapshot.child("name").getValue();
                    HashMap<String, String> priceList = (HashMap<String, String>) snapshot.child("prices").getValue();
                    for (String key : priceList.keySet()) {
                        if (key.contains(mQuery)) {
                            Vendor vendor = new Vendor((String) snapshot.child("id").getValue(),
                                    (double) snapshot.child("rating").getValue(), priceList, vendorName);
                            mVendorsDataset.add(vendor);
                            Log.d("InTheLoop", "Adding event: " + vendorName);
                        }
                    }



                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    private void goToVendor(String id) {
        Intent intent = new Intent(SearchResultsActivity.this, ServiceDetailActivity.class);
        intent.putExtra("vendorId", id);
        intent.putExtra("query", mQuery);
        startActivity(intent);
    }

}
