package com.nik.foodorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nik.foodorder.Adapters.MainAdapter;
import com.nik.foodorder.Models.MainModel;
import com.nik.foodorder.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.burger,"Burger","150","This is a big patty big Burger ..."));
        list.add(new MainModel(R.drawable.fries,"French Fries","80","Very Very Tasty yummy Fries ..."));
        list.add(new MainModel(R.drawable.pizza,"Pizza","250","An Awesome Pizza on the House full pArtyyy pizzaa party ..."));
        list.add(new MainModel(R.drawable.burgertwo,"Big Burger","200","This is a big patty big Burger ..."));
        list.add(new MainModel(R.drawable.burger,"Burger","140","This is a big patty big Burger ..."));
        list.add(new MainModel(R.drawable.burger,"Burger","40","This is a big patty big Burger ..."));

        MainAdapter adapter = new MainAdapter(list,this);
        binding.recyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders: startActivity(new Intent(MainActivity.this,OrderActivity.class));
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

}