package com.ndroid.calccul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.ndroid.calccul.databinding.ActivityAdduserBinding;

public class AdduserActivity extends AppCompatActivity {


    private ActivityAdduserBinding binding;
    DataBaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAdduserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbHelper =new DataBaseHelper(this);
        dbHelper.insertUser("","","","");

        binding.addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUser=binding.emailInput.getEditText().getText().toString();
                String passwordUser=binding.passwordInput.getEditText().getText().toString();
                String nomUser=binding.nameInput.getEditText().getText().toString();
                String prenomUser=binding.prenomInput.getEditText().getText().toString();
                addUserDb(emailUser,passwordUser,nomUser,prenomUser);
            }
        });

    }

    public void addUserDb(String email,String password,String nom,String prenom){
        long insertedRow=dbHelper.insertUser(email,password,nom,prenom);
        if(insertedRow!= -1L){
            Toast.makeText(this,"User Added Successfully",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Adding failed",Toast.LENGTH_SHORT).show();
        }
    }
}