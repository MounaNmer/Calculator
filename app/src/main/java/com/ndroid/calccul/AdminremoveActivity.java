package com.ndroid.calccul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.ndroid.calccul.databinding.ActivityAdminBinding;
import com.ndroid.calccul.databinding.ActivityAdminremoveBinding;

public class AdminremoveActivity extends AppCompatActivity {
    private ActivityAdminremoveBinding binding;
    DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminremoveBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbHelper=new DataBaseHelper(this);
        binding.Loginr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adminEmail=binding.emailInput.getEditText().getText().toString();
                String adminPassword=binding.passwordInput.getEditText().getText().toString();
                LoginDb(adminEmail,adminPassword);
            }
        });
    }
    public void LoginDb(String email,String password){
        boolean adminExist=dbHelper.readAdmin(email,password);
        if(adminExist){
            Toast.makeText(this,"Welcome Admin",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this, DropActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,"You're not the Admin",Toast.LENGTH_SHORT).show();
        }
    }
}