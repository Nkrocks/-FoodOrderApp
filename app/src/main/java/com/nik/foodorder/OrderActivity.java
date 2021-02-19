package com.nik.foodorder;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nik.foodorder.Adapters.OrdersAdapter;
import com.nik.foodorder.Models.OrdersModel;
import com.nik.foodorder.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper helper = new DBHelper(this);
        ArrayList<OrdersModel> list = helper.getOrders();

//        list.add(new OrdersModel(R.drawable.burger,"Cheese Burger","150","1234577"));
//        list.add(new OrdersModel(R.drawable.burger,"Cheese Burger","150","1234577"));
//        list.add(new OrdersModel(R.drawable.burger,"Cheese Burger","150","1234577"));
//        list.add(new OrdersModel(R.drawable.burger,"Cheese Burger","150","1234577"));
//        list.add(new OrdersModel(R.drawable.burger,"Cheese Burger","150","1234577"));
//        list.add(new OrdersModel(R.drawable.burger,"Cheese Burger","150","1234577"));

        OrdersAdapter adapter = new OrdersAdapter(list,this);

        binding.ordersRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        binding.ordersRecyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}