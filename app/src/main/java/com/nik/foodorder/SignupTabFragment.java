package com.nik.foodorder;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupTabFragment extends Fragment {

    EditText email_edit,mobile_edit,name_edit,pass_edit;
    FloatingActionButton fb,google,twitter;
    ProgressBar progressBar;
    float v=0;
    Button signupBtn;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_frag,container,false);

        mAuth = FirebaseAuth.getInstance();

        email_edit =(EditText) root.findViewById(R.id.email);
        mobile_edit =(EditText) root.findViewById(R.id.mobile);
        name_edit =(EditText) root.findViewById(R.id.fullName);
        pass_edit =(EditText) root.findViewById(R.id.password);
        signupBtn =(Button) root.findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpUser();
            }
        });
        return root;
    }

    private void signUpUser() {
        String email = email_edit.getText().toString().trim();
        String mobile = mobile_edit.getText().toString().trim();
        String name = name_edit.getText().toString().trim();
        String password = pass_edit.getText().toString().trim();

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email_edit.setError("Valid email is required");
            email_edit.requestFocus();
            return;
        }

        if (mobile.isEmpty() || !isPhoneNumberValid(mobile)) {
            mobile_edit.setError("Valid mobile number is required");
            mobile_edit.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            name_edit.setError("Full name is required");
            name_edit.requestFocus();
            return;
        }

        if (password.isEmpty() || password.length() < 6 ) {
            pass_edit.setError("Atleast 6 character Password is required");
            pass_edit.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user=new User(email,mobile,name);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getContext(),"User registered successfully!",Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        Toast.makeText(getContext(),"User registration failed! Try Again!",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(getContext(),"User registration failed! Try Again!",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    public static boolean isPhoneNumberValid(String phoneNumber) {

        boolean valid = true;

        if (!(phoneNumber.length()==10)){
            valid = false;
        }
        return valid;
    }
}
