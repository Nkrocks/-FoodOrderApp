package com.nik.foodorder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
        int Tprice = helper.totalPrice();
        boolean valid=false;

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


        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.size() == 0) {
                    validate();
                }
                else {
                    Intent intent1 = new Intent(OrderActivity.this, CheckoutActivity.class);
                    intent1.putExtra("tPrice", Tprice);
                    startActivity(intent1);
                }
            }
        });

    }

    private void validate() {
        new AlertDialog.Builder(this)
                .setMessage("Please select atleast 1 order!")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}