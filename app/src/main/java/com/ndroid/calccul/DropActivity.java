package com.ndroid.calccul;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ndroid.calccul.databinding.ActivityDropBinding;

public class DropActivity extends AppCompatActivity {

    private ActivityDropBinding binding;
    DataBaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDropBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbHelper = new DataBaseHelper(this);
        binding.dropUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUser=binding.emailInput.getEditText().getText().toString();
                dropUserDb(emailUser);
            }
        });
    }

    public void dropUserDb(String email){
        long deletedRow=dbHelper.dropUser(email);
        if(deletedRow!= -1L){
            Toast.makeText(this,"User Deleted Successfully",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Deleting failed",Toast.LENGTH_SHORT).show();
        }
    }
}