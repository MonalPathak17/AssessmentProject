package com.example.assessmentproject.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.assessmentproject.R;
import com.example.assessmentproject.adapter.FactsAdapter;
import com.example.assessmentproject.databinding.ActivityMainBinding;
import com.example.assessmentproject.fragment.FragmentA;
import com.example.assessmentproject.model.FactsModel;
import com.example.assessmentproject.model.Row;
import com.example.assessmentproject.viewModel.MainViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FactsAdapter.ItemClickListener {

    private MainViewModel mainViewModel;
    private FactsAdapter employeeDataAdapter;
    ProgressDialog mProgressDialog;
    public MaterialToolbar toolbar;
    public ActionBar actionBar;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
         activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
         mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
         setSupportActionBar(activityMainBinding.toolbarHome);
        actionBar=getSupportActionBar();
        // bind RecyclerView
        RecyclerView recyclerView = activityMainBinding.viewEmployees;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        employeeDataAdapter = new FactsAdapter();
        recyclerView.setAdapter(employeeDataAdapter);

        employeeDataAdapter.setClickListener(this); // Bind the listener

         SwipeRefreshLayout swipeRefreshLayout=activityMainBinding.swipeRefreshLayout;

        // SetOnRefreshListener on SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(false);
            getAllEmployee();
        });
             if (isNetworkStatusAvialable(this)) {

                 getAllEmployee();
             }
             else
             {
                 loadData();
             }
    }

    private void loadData()
    {
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setMessage("Loding....");
        mProgressDialog.show();
        // method to load arraylist from shared prefs
        // initializing our shared prefs with name as
        // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for gson.
        Gson gson = new Gson();

        // below line is to get to string present from our
        // shared prefs if not present setting it as null.
        String json = sharedPreferences.getString("courses", null);

        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<Row>>() {}.getType();

        // in below line we are getting data from gson
        // and saving it to our array list
        ArrayList<Row> employees = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (employees == null)
        {
            // if the array list is empty
            // creating a new array list.
           // employees = new ArrayList<>();
            getAllEmployee();
        }
        else {
            employeeDataAdapter.setEmployeeList(employees, MainActivity.this);

            mProgressDialog.dismiss();
        }

    }

    private void getAllEmployee()
    {
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setMessage("Loding....");
        mProgressDialog.show();
        mainViewModel.getAllEmployee().observe(this, employees -> {
            employeeDataAdapter.setEmployeeList((ArrayList<Row>) employees,MainActivity.this);
            mProgressDialog.dismiss();


            SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

            // creating a variable for editor to
            // store data in shared preferences.
            SharedPreferences.Editor editor = sharedPreferences.edit();

            // creating a new variable for gson.
            Gson gson = new Gson();

            // getting data from gson and storing it in a string.
            String json = gson.toJson(employees);

            // below line is to save data in shared
            // prefs in the form of string.
            editor.putString("courses", json);

            // below line is to apply changes
            // and save data in shared prefs.
            editor.apply();

            // after saving data we are displaying a toast message.
            //Toast.makeText(MainActivity.this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();
        });
    }
    public  boolean isNetworkStatusAvialable(Context context)
    {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
                if(netInfos.isConnected())
                    return netInfos.isAvailable();
        }
        return false;

    }


    @Override
    public void onClick(View view, Row position)
    {
        //before calling fragment remove all views from recyclerview
        activityMainBinding.rec.removeAllViews();
        activityMainBinding.rec.clearDisappearingChildren();
        Log.e("click","Click on position");
        FragmentA fragmentA=new FragmentA();
        Bundle bundle=new Bundle();
        bundle.putString("title",position.getTitle());
        bundle.putString("des",position.getDescription());
        bundle.putString("image",position.getImageHref());
        fragmentA.setArguments(bundle);


        //   if (fragmentA != null) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.rec, fragmentA).addToBackStack(null).commit();
        //  }

    }
    }
