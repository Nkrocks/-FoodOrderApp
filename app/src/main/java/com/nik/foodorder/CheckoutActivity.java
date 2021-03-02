package com.nik.foodorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nik.foodorder.databinding.ActivityCheckoutBinding;

public class CheckoutActivity extends AppCompatActivity {

    ActivityCheckoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int tPrice = getIntent().getIntExtra("tPrice",0);
        binding.totalPrice.setText((String.format("%d", tPrice)));


        binding.checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();

            }
        });
    }

    private boolean validate() {
        boolean valid = true;

        String name = binding.edName.getText().toString();
        String phone = binding.edPhone.getText().toString();
        int radioId = binding.radioGrp.getCheckedRadioButtonId();
        RadioButton radioBtn = findViewById(radioId);

        if (name.isEmpty() || name.length() < 3) {
            binding.edName.setError("at least 3 characters");
            valid = false;
        } else {
            binding.edName.setError(null);
        }

        if (phone.isEmpty() || !isPhoneNumberValid(phone)) {
            binding.edPhone.setError("enter a valid phone number");
            valid = false;
        } else {
            binding.edPhone.setError(null);
        }
        if(!(radioBtn == binding.cashOnDel)){
            Toast.makeText(this,"Only Cash On Delivery Available",Toast.LENGTH_SHORT).show();
            valid = false;
        }


        return valid;
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {

        boolean valid = true;

        if (!(phoneNumber.length()==10)){
            valid = false;
        }
        return valid;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,OrderActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}