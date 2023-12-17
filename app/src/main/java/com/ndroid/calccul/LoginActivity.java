package com.ndroid.calccul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ndroid.calccul.databinding.ActivityLoginBinding;
public class LoginActivity extends AppCompatActivity {

    FloatingActionButton option,add,remove;
    private ActivityLoginBinding binding;
    private DataBaseHelper dbHelper;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbHelper=new DataBaseHelper(this);
        binding.Connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginName=binding.emailInput.getEditText().getText().toString();
                String loginPassword=binding.passwordInput.getEditText().getText().toString();
                loginDataBase(loginName,loginPassword);
            }
        });
        //Ajouter une ligne dans la table Admin
        SQLiteOpenHelper dbOpenHelper=new DataBaseHelper(this);
        SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
        ContentValues valeur=new ContentValues();
        valeur.put("Email","mounanmer@gmail.com");
        valeur.put("Password","Mouna2003");
        long newRowId=db.insert("Admin",null,valeur);
        db.close();
        option=findViewById(R.id.options);
        add=findViewById(R.id.addUser);
        remove=findViewById(R.id.dropUser);
        option.setOnClickListener(view ->{
            add.show();
            remove.show();
        });
        add.setOnClickListener(view->{
            Intent intent = new Intent(this, AdminaddActivity.class);
            startActivity(intent);
        });
        remove.setOnClickListener(view->{
            Intent intent=new Intent(this, AdminremoveActivity.class);
            startActivity(intent);
        });


    }

    private void loginDataBase(String email,String password){
        boolean userExists=dbHelper.readUser(email,password);
        boolean adminExists=dbHelper.readAdmin(email,password);
        if(userExists || adminExists==true){
            Toast.makeText(this,"Login successful",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,"Login failed",Toast.LENGTH_SHORT).show();
        }
    }
}