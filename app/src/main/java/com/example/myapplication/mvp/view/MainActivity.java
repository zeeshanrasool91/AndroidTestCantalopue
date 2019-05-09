package com.example.myapplication.mvp.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.RecyclerViewAdapter;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.model.MostPopularResponse;
import com.example.myapplication.mvp.MainContract;
import com.example.myapplication.mvp.MainPresenter;

import java.util.List;

public class MainActivity extends BaseActivity implements MainContract.View {


    MainPresenter mainPresenter;
    ActivityMainBinding activityMainBinding;
    public static String TAG = MainActivity.class.getSimpleName();
    private RecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        mainPresenter = new MainPresenter(this);
        mainPresenter.initScreen();
    }

    @Override
    public void setupViews() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_menu_white_24dp));
        getSupportActionBar().setTitle(getResources().getString(R.string.main_activity_title));
        mainPresenter.getAccessCodeFromServer("");
    }

    @Override
    public void showLoading() {
        activityMainBinding.progressCircular.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        activityMainBinding.progressCircular.setVisibility(View.GONE);
    }

    @Override
    public void setupRecyclerViewAdapter() {
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this);
        activityMainBinding.recyclerView.addItemDecoration(new DividerItemDecoration(activityMainBinding.recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        activityMainBinding.recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setMainPresenter(mainPresenter);
    }

    @Override
    public void showData(List<MostPopularResponse> dataResponse) {
        recyclerViewAdapter.setItems(dataResponse);
    }


    @Override
    public void showSuccess(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFailure(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showInternetError(String showInternetError) {
        Toast.makeText(MainActivity.this, showInternetError, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean getInternetConnectivityStatus() {
        return checkConnection();
    }

    @Override
    public void handleClickResponse(int position,MostPopularResponse mostPopularResponse) {
        Toast.makeText(MainActivity.this,"Position\t"+position+"\nvalue\t"+mostPopularResponse.getTitle(),Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_more:
                Toast.makeText(MainActivity.this, "More Action clicked", Toast.LENGTH_LONG).show();
                ;
                return true;
            case R.id.action_search:
                Toast.makeText(MainActivity.this, "Search Action clicked", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
