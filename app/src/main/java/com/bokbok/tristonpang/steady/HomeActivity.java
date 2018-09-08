package com.bokbok.tristonpang.steady;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private String mName;
    private DatabaseReference mGeneralRef;
    private TextView mGreetView;
    private EditText mSearchView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    mTextMessage.setText(R.string.title_search);
                    return true;
                case R.id.navigation_browse:
                    mTextMessage.setText(R.string.title_browse);
                    return true;
                case R.id.navigation_schedule:
                    mTextMessage.setText(R.string.title_schedule);
                    return true;
                case R.id.navigation_more:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mSearchView = findViewById(R.id.searchEditText);
        mSearchView.setBackgroundColor(R.color.colorPrimaryDark);
        mGreetView = findViewById(R.id.searchGreetingView);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        mGeneralRef = FirebaseDatabase.getInstance().getReference();
        //retrieve user's full name
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("Steady", "UserID: " + id);
        mGeneralRef.child("users").child(id).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mName = dataSnapshot.getValue(String.class);
                mGreetView.setText(getString(R.string.search_greeting, mName));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}
