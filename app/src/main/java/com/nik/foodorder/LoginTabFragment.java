package com.nik.foodorder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginTabFragment extends Fragment {
    EditText email_edit,pass_edit;
    TextView forgetPass;
    Button loginBtn;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_frag,container,false);

        mAuth = FirebaseAuth.getInstance();

        email_edit =(EditText) root.findViewById(R.id.emailLog);
        pass_edit =(EditText) root.findViewById(R.id.passwordLog);
        loginBtn =(Button) root.findViewById(R.id.logBtn);
        forgetPass=(TextView) root.findViewById(R.id.forgetPass);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),ForgotPassword.class));
            }
        });



        return root;
    }

    private void loginUser() {
        String email = email_edit.getText().toString().trim();
        String password = pass_edit.getText().toString().trim();

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email_edit.setError("Valid email is required");
            email_edit.requestFocus();
            return;
        }

        if (password.isEmpty() || password.length() < 6 ) {
            pass_edit.setError("Atleast 6 character Password is required");
            pass_edit.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                    if(firebaseUser.isEmailVerified()) {
                        Toast.makeText(getContext(), "Login successful!", Toast.LENGTH_LONG).show();
                        // Redirect to user profile
                        startActivity(new Intent(getContext(), MainActivity.class));
                    }
                    else {
                        firebaseUser.sendEmailVerification();
                        Toast.makeText(getContext(), "Check your email to verify your account!", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getContext(),"Invalid email_id or password!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
