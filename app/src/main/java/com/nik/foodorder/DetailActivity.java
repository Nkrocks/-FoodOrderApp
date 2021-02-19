package com.nik.foodorder;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nik.foodorder.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        DBHelper helper = new DBHelper(this);


        if (getIntent().getIntExtra("type", 0) == 1) {

            final int image = getIntent().getIntExtra("image", 0);
            String name = getIntent().getStringExtra("name");
            String description = getIntent().getStringExtra("desc");
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));



            binding.detailImage.setImageResource(image);
            binding.priceLbl.setText(String.format("%d", price));
            binding.foodName.setText(name);
            binding.detailDescription.setText(description);



        binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binding.quantity.setText(""+(Integer.parseInt(""+binding.quantity.getText().toString())+1));
                    binding.priceLbl.setText(""+(price * (Integer.parseInt(binding.quantity.getText().toString()))));
                }
        });
        binding.subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((Integer.parseInt(""+binding.quantity.getText().toString())>1)) {
                    binding.quantity.setText("" + (Integer.parseInt("" + binding.quantity.getText().toString()) - 1));
                    binding.priceLbl.setText(""+(price * (Integer.parseInt(binding.quantity.getText().toString()))));
                }
            }
        });




            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = helper.insertOrder(
                            Integer.parseInt(binding.priceLbl.getText().toString()),
                            price,
                            image,
                            Integer.parseInt(binding.quantity.getText().toString()),
                            name,
                            description
                    );

                    if (isInserted) {
                        Toast.makeText(DetailActivity.this, "Order Created", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(DetailActivity.this,MainActivity.class);
                        startActivity(intent);
                    }else
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            int id= getIntent().getIntExtra("id",0);
            Cursor cursor=helper.getOrderById(id);
            int image = cursor.getInt(3);
            int price = cursor.getInt(2);

            binding.detailImage.setImageResource(image);
            binding.priceLbl.setText(String.format("%d", cursor.getInt(1)));
            binding.foodName.setText(cursor.getString(6));
            binding.detailDescription.setText(cursor.getString(5));
            binding.quantity.setText(cursor.getString(4));

            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    binding.quantity.setText(""+(Integer.parseInt(""+binding.quantity.getText().toString())+1));
                    binding.priceLbl.setText(""+(Integer.parseInt(""+price*(Integer.parseInt(binding.quantity.getText().toString())))));
                }
            });
            binding.subtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((Integer.parseInt(""+binding.quantity.getText().toString())>1)) {
                        binding.quantity.setText("" + (Integer.parseInt("" + binding.quantity.getText().toString()) - 1));
                        binding.priceLbl.setText(""+(Integer.parseInt(""+price * (Integer.parseInt(binding.quantity.getText().toString())))));
                    }
                }
            });
            binding.insertBtn.setText("Update Now");
            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isUpdated = helper.updateOrder(
                            Integer.parseInt(binding.priceLbl.getText().toString()),
                            price,
                            image,
                            binding.detailDescription.getText().toString(),
                            binding.foodName.getText().toString(), Integer.parseInt(binding.quantity.getText().toString()),
                            id);

                    if (isUpdated) {
                        Toast.makeText(DetailActivity.this, "Order Updated", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(DetailActivity.this,OrderActivity.class);
                        startActivity(intent);
                    }else
                        Toast.makeText(DetailActivity.this,"Update Failed",Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

}